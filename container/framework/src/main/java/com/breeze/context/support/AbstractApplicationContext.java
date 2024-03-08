package com.breeze.context.support;

import com.breeze.context.ConfigurableApplicationContext;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws Exception {

        try {
            onRefresh();
        } catch (Exception ignored) {

        }
    }


    protected void onRefresh() throws Exception {

    }
}
