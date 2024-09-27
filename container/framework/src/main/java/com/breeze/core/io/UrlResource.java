package com.breeze.core.io;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * {@link URL} 的 resource 实现
 */
public class UrlResource extends AbstractResource {

    private URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public URL getURL() throws FileNotFoundException {
        return this.url;
    }

    @Override
    public File getFile() throws IOException {
        return ResourceUtils.getFile(url, "description");
    }
}
