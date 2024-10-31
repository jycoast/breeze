package com.breeze.beans.factory.config;

/**
 * Bean后置处理器
 * <p>
 * Spring Bean 生命周期处理过程：
 * <p>
 * 1. InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()
 * - 在 Bean 实例化之前进行处理，可以返回一个替代的 Bean 实例。
 * <p>
 * 2. 实例化
 * - 创建 Bean 实例的过程，通常是通过反射来完成的。
 * <p>
 * 3. MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition()
 * - 对合并后的 Bean 定义进行处理，可以在此阶段修改 Bean 的定义。
 * <p>
 * 4. InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()
 * - 在 Bean 实例化之后，但在属性设置之前进行处理，可以执行自定义逻辑。
 * <p>
 * 5. 自动注入
 * - 根据配置的依赖关系，自动将需要的依赖注入到 Bean 中。
 * <p>
 * 6. InstantiationAwareBeanPostProcessor.postProcessProperties()
 * - 在设置属性之前执行的处理，可以在这里修改或增强属性的设置过程。
 * <p>
 * 7. Aware对象
 * - 实现 Aware 接口的 Bean，可以在容器中获得特定的功能或服务，如 ApplicationContextAware。
 * <p>
 * 8. BeanPostProcessor.postProcessBeforeInitialization()
 * - 在 Bean 初始化之前进行的处理，可以用于准备工作。
 * <p>
 * 9. 初始化
 * - 执行 Bean 的初始化方法，如 @PostConstruct 注解的方法或自定义的初始化方法。
 * <p>
 * 10. BeanPostProcessor.postProcessAfterInitialization()
 * - 在 Bean 初始化之后进行处理，用于对 Bean 进行进一步的增强。
 */
public interface BeanPostProcessor {

    /**
     * 初始化前回调
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    /**
     * 初始化后
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
