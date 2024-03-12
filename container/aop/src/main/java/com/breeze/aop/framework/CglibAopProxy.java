package com.breeze.aop.framework;

import com.breeze.aop.AdvisedSupport;
import com.breeze.aop.AopProxy;
import com.breeze.aop.MethodInterceptor;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class CglibAopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor());
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private AdvisedSupport advised;

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(this.advised.getTargetSource().getTarget()
                    , method, null, null, null, null);
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
//                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            return methodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        /**
         * Construct a new ReflectiveMethodInvocation with the given arguments.
         *
         * @param proxy                                the proxy object that the invocation was made on
         * @param target                               the target object to invoke
         * @param method                               the method to invoke
         * @param arguments                            the arguments to invoke the method with
         * @param targetClass                          the target class, for MethodMatcher invocations
         * @param interceptorsAndDynamicMethodMatchers interceptors that should be applied,
         *                                             along with any InterceptorAndDynamicMethodMatchers that need evaluation at runtime.
         *                                             MethodMatchers included in this struct must already have been found to have matched
         *                                             as far as was possibly statically. Passing an array might be about 10% faster,
         *                                             but would complicate the code. And it would work only for static pointcuts.
         */
        protected CglibMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {
            super(proxy, target, method, arguments, targetClass, interceptorsAndDynamicMethodMatchers);
        }

        @Override
        public Object proceed() throws Throwable {
            return null;
        }
    }
}
