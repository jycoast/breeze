package com.breeze.core.io;

import java.io.FileNotFoundException;
import java.net.URL;

public abstract class AbstractResource implements Resource {

    @Override
    public URL getURL() throws FileNotFoundException {
        throw new FileNotFoundException("cannot be resolved to URL");
    }
}
