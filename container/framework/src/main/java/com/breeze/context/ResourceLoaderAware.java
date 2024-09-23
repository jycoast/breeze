package com.breeze.context;

import com.breeze.beans.factory.Aware;
import com.breeze.core.io.ResourceLoader;

public interface ResourceLoaderAware extends Aware {

    void setResourceLoader(ResourceLoader resourceLoader);
}
