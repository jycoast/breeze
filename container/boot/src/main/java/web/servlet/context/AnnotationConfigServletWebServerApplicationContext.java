package web.servlet.context;

import com.breeze.beans.factory.config.ConfigurableListableBeanFactory;
import com.breeze.context.annotation.AnnotatedBeanDefinitionReader;
import com.breeze.context.annotation.AnnotationConfigRegistry;
import com.breeze.context.annotation.ClassPathBeanDefinitionScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationConfigServletWebServerApplicationContext extends ServletWebServerApplicationContext implements AnnotationConfigRegistry {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationConfigServletWebServerApplicationContext.class);

    private AnnotatedBeanDefinitionReader reader;

    private ClassPathBeanDefinitionScanner scanner;

    private String[] basePackages;

    public AnnotationConfigServletWebServerApplicationContext() {
        this("com.breeze");
        this.reader = new AnnotatedBeanDefinitionReader(this);
        this.scanner = new ClassPathBeanDefinitionScanner(this);
    }

    public AnnotationConfigServletWebServerApplicationContext(String... basePackages) {
        super();
        scan(basePackages);
    }

    private void scan(String... basePackages) {
        this.basePackages = basePackages;
    }

    @Override
    protected void postBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        scanner.scan(this.basePackages);
    }
}
