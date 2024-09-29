package com.breeze.context.annotation;

import com.breeze.beans.factory.support.GenericBeanDefinition;
import com.breeze.core.io.Resource;
import com.breeze.core.type.AnnotationMetadata;
import com.breeze.core.type.MetadataReader;
import org.springframework.util.ClassUtils;

/**
 * 从classpath 扫描到的BeanDefinition
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(MetadataReader metadataReader) {
        super(null);
        try {
            Resource resource = metadataReader.getResource();
            String className = convertResourcePathToClassName(resource.getFile().getPath());
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            Class<?> clazz = ClassUtils.forName(className, classLoader);
            this.setBeanClass(clazz);
        } catch (Exception e) {
            System.err.println(e);
        }
        this.metadata = metadataReader.getAnnotationMetadata();
    }

    public AnnotationMetadata getMetadata() {
        return metadata;
    }

    public static String convertResourcePathToClassName(String path) {
        String classRoot = "target/classes/";
        int index = path.indexOf(classRoot.replace("/", "\\"));
        path = path.substring(index + classRoot.length()).replace("/", "\\");
        String relativePath = path.replace(classRoot, "");
        relativePath = relativePath.replace(".class", "");
        return relativePath.replace("\\", ".");
    }
}
