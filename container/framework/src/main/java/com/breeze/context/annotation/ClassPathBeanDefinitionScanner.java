package com.breeze.context.annotation;

import com.breeze.beans.factory.config.BeanDefinition;
import com.breeze.beans.factory.config.BeanDefinitionHolder;
import com.breeze.beans.factory.support.BeanDefinitionRegistry;
import com.breeze.beans.factory.support.BeanNameGenerator;
import com.breeze.beans.factory.support.PathMatchingResourcePatternResolver;
import com.breeze.beans.factory.support.ResourcePatternResolver;
import com.breeze.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private static final Logger logger = LoggerFactory.getLogger(ClassPathBeanDefinitionScanner.class);

    private final BeanDefinitionRegistry registry;

    private final BeanNameGenerator beanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;

    private ResourcePatternResolver resourcePatternResolver;

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void scan(String... basePackages) {
        doScan(basePackages);
    }

    private void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> beanDefinitions = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : beanDefinitions) {
                String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
                BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition, beanName);
                registerBeanDefinition(beanDefinitionHolder, this.registry);
            }
        }
    }

    private Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> beanDefinitions = new HashSet<>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + basePackage + "/" + this.DEFAULT_RESOURCE_PATTERN;
        logger.info("scan package path:{}", packageSearchPath);
        try {
            Resource[] resources = getResourcePatternResolver().getResources(packageSearchPath);
            for (Resource resource : resources) {
                BeanDefinition beanDefinition = null;
                beanDefinitions.add(beanDefinition);
            }
        } catch (Throwable err) {
            logger.error("scan package error:{}", err.getMessage(), err);
        }
        return beanDefinitions;
    }

    private ResourcePatternResolver getResourcePatternResolver() {
        if (this.resourcePatternResolver == null) {
            this.resourcePatternResolver = new PathMatchingResourcePatternResolver();
        }
        return this.resourcePatternResolver;
    }

    private void registerBeanDefinition(BeanDefinitionHolder beanDefinitionHolder, BeanDefinitionRegistry registry) {
        registry.registerBeanDefinition(beanDefinitionHolder.getBeanName(), beanDefinitionHolder.getBeanDefinition());
    }
}
