package com.breeze.beans.factory.support;

import com.breeze.core.io.Resource;

import java.io.IOException;

public class PathMatchingResourcePatternResolver implements ResourcePatternResolver {

    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        return new Resource[0];
    }
}
