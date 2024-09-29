package com.breeze.context.annotation;

import com.breeze.beans.factory.support.GenericBeanDefinition;
import com.breeze.core.type.AnnotationMetadata;
import com.breeze.core.type.MetadataReader;

/**
 * 从classpath 扫描到的BeanDefinition
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(MetadataReader metadataReader) {
        super(null);
        this.metadata = metadataReader.getAnnotationMetadata();
    }

    public AnnotationMetadata getMetadata() {
        return metadata;
    }
}
