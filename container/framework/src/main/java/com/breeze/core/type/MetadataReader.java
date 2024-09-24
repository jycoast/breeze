package com.breeze.core.type;

import com.breeze.core.io.Resource;

/**
 * 获取元数据
 */
public interface MetadataReader {

    Resource getResource();

    AnnotationMetadata getAnnotationMetadata();

    ClassMetadata getClassMetadata();
}
