package com.breeze.core.type;

import com.breeze.core.io.Resource;

/**
 * 元数据读取的简单实现
 */
public class SimpleMetadataReader implements MetadataReader {

    private final Resource resource;

    private final ClassLoader classLoader;

    public SimpleMetadataReader(Resource resource, ClassLoader classLoader) {
        this.resource = resource;
        this.classLoader = classLoader;
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return null;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return null;
    }
}
