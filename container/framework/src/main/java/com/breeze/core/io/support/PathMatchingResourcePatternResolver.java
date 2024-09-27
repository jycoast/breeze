package com.breeze.core.io.support;

import com.breeze.beans.factory.support.ResourcePatternResolver;
import com.breeze.core.io.FileSystemResource;
import com.breeze.core.io.Resource;
import com.breeze.core.io.UrlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.*;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 路径匹配资源的解析器
 */
public class PathMatchingResourcePatternResolver implements ResourcePatternResolver {

    private static final Logger logger = LoggerFactory.getLogger(PathMatchingResourcePatternResolver.class);

    private PathMatcher pathMatcher = new AntPathMatcher();

    public ClassLoader getClassLoader() {
        return classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }

    private ClassLoader classLoader;

    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        if (pathMatcher.isPattern(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()))) {
            return findPathMatchingResources(locationPattern);
        } else {
            return findAllClassPathResources(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()));
        }
    }

    private Resource[] findPathMatchingResources(String locationPattern) throws IOException {
        Set<Resource> result = new LinkedHashSet<>();
        String rootDirPath = "classpath*:com/breeze/";
        String subPattern = locationPattern.substring(rootDirPath.length()); //  **/*.class
        Resource[] rootDirResources = getResources(rootDirPath);
        for (Resource rootDirResource : rootDirResources) {
            URL rootDirUrl = rootDirResource.getURL();
            if (ResourceUtils.isJarURL(rootDirUrl)) {
                result.addAll(doFindPathMatchingJarResource(rootDirResource, rootDirUrl, subPattern));
            } else {
                result.addAll(doFindPathMatchingFileResources(rootDirResource, subPattern));
            }
        }
        return result.toArray(new Resource[0]);
    }

    protected Resource[] findAllClassPathResources(String location) throws IOException {
        String path = location;
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        Set<Resource> result = doFindAllClassPathResources(path);
        return result.toArray(new Resource[0]);
    }

    private Set<Resource> doFindAllClassPathResources(String path) throws IOException {
        Set<Resource> result = new HashSet<>();
        ClassLoader cl = getClassLoader();
        Enumeration<URL> resourceUrls = (cl != null ? cl.getResources(path) : ClassLoader.getSystemResources(path));
        while (resourceUrls.hasMoreElements()) {
            URL url = resourceUrls.nextElement();
            result.add(convertClassLoaderURL(url));
        }
        if (!StringUtils.hasLength(path)) {
            addAllClassLoaderJarRoots(cl, result);
        }
        return result;
    }

    private Set<Resource> doFindPathMatchingFileResources(Resource rootDirResource, String subPattern) {
        File rootDir = null;
        try {
            rootDir = rootDirResource.getFile().getAbsoluteFile();
        } catch (Exception ex) {
            logger.error("Failed to resolve classpath resource [" + rootDirResource + "]", ex);
            return Collections.emptySet();
        }
        return doFindMatchingFileSystemResources(rootDir, subPattern);
    }

    private Set<Resource> doFindMatchingFileSystemResources(File rootDir, String subPattern) {
        Set<File> matchingFiles = retrieveMatchingFiles(rootDir, subPattern);
        Set<Resource> result = new LinkedHashSet<>();
        for (File matchingFile : matchingFiles) {
            result.add(new FileSystemResource(matchingFile));
        }
        return result;
    }

    private Set<File> retrieveMatchingFiles(File rootDir, String pattern) {
        String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
        if (!pattern.startsWith("/")) {
            fullPattern += "/";
        }
        fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
        Set<File> result = new LinkedHashSet<>(8);
        doRetrieveMatchingFiles(fullPattern, rootDir, result);
        return result;
    }

    private void doRetrieveMatchingFiles(String fullPattern, File rootDir, Set<File> result) {
        for (File content : listDirectory(rootDir)) {
            String currPath = StringUtils.replace(content.getAbsolutePath(), File.separator, "/");
            if (content.isDirectory() && pathMatcher.matchStart(fullPattern, currPath + "/")) {
                doRetrieveMatchingFiles(fullPattern, content, result);
            }
            if (pathMatcher.match(fullPattern, currPath)) {
                result.add(content);
            }
        }
    }

    private void addAllClassLoaderJarRoots(ClassLoader cl, Set<Resource> result) {

    }

    private Resource convertClassLoaderURL(URL url) {
        return new UrlResource(url);
    }

    private File[] listDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            return new File[0];
        }
        Arrays.sort(files, Comparator.comparing(File::getName));
        return files;
    }

    private Set<Resource> doFindPathMatchingJarResource(Resource rootDirResource, URL rootDirUrl, String subPattern) throws IOException {
        Set<Resource> result = new HashSet<>();
        URLConnection con = rootDirUrl.openConnection();
        JarFile jarFile = null;
        String jarFileUrl = null;
        String rootEntryPath = null;
        boolean closeJarFile = false;

        if (con instanceof JarURLConnection) {
            JarURLConnection jarCon = (JarURLConnection) con;
            ResourceUtils.useCachesIfNecessary(jarCon);
            jarFile = jarCon.getJarFile();
            jarFileUrl = jarCon.getJarFileURL().toExternalForm();
            JarEntry jarEntry = jarCon.getJarEntry();
            rootEntryPath = (jarEntry != null ? jarEntry.getName() : "");
            closeJarFile = !jarCon.getUseCaches();
        }

        try {
            logger.trace("Looking for matching resources in jar file [" + jarFileUrl + "]");
            if (StringUtils.hasLength(rootEntryPath) && !rootEntryPath.endsWith("/")) {
                rootEntryPath = rootEntryPath + "/";
            }
            for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements(); ) {
                JarEntry entry = entries.nextElement();
                String entryPath = entry.getName();
                if (entryPath.startsWith(rootEntryPath)) {
                    String relativePath = entryPath.substring(rootEntryPath.length());
                    result.add(rootDirResource);
                }
            }
            return result;
        } finally {
            if (closeJarFile) {
                jarFile.close();
            }
        }
    }
}
