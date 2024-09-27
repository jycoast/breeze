package com.breeze.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public abstract class AbstractResource implements Resource {

    @Override
    public URL getURL() throws FileNotFoundException {
        throw new FileNotFoundException("cannot be resolved to URL");
    }

    @Override
    public File getFile() throws IOException {
        return null;
    }
}
