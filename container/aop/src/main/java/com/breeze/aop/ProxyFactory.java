package com.breeze.aop;

import com.breeze.aop.framework.CglibAopProxy;
import com.breeze.aop.framework.JdkDynamicAopProxy;

public class ProxyFactory {

    private AdvisedSupport advise;


    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advise = advisedSupport;
    }


    public Object getProxy() {
        return this.createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        Class<?> targetClass = advise.getTargetSource().getTarget().getClass();
        if (targetClass.isInterface()) {
            return new JdkDynamicAopProxy(advise);
        } else {
            return new CglibAopProxy(advise);
        }
    }
}
