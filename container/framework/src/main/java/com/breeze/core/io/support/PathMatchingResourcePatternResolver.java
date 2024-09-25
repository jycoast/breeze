package com.breeze.core.io.support;

import com.breeze.beans.factory.support.ResourcePatternResolver;
import com.breeze.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PathMatchingResourcePatternResolver implements ResourcePatternResolver {

    private static final Logger logger = LoggerFactory.getLogger(PathMatchingResourcePatternResolver.class);

    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        return findPathMathResources(locationPattern);
    }

    private Resource[] findPathMathResources(String locationPattern) throws IOException {
        Set<Resource> result = new LinkedHashSet<>();
        String rootDirPath = "classpath*:com/breeze/";
        String subPattern = locationPattern.substring(rootDirPath.length()); //  **/*.class
        Resource[] rootDirResources = getResources(rootDirPath);
        for (Resource rootDirResource : rootDirResources) {
            URL rootDirUrl = rootDirResource.getURL();
            if (ResourceUtils.isJarURL(rootDirUrl)) {
                result.addAll(doFindPathMatchingJarResource(rootDirResource, rootDirUrl, subPattern));
            }
        }
        return result.toArray(new Resource[0]);
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
