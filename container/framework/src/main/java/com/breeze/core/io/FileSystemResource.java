package com.breeze.core.io;

import java.io.File;

public class FileSystemResource extends AbstractResource {

    private File file;

    public FileSystemResource(File file) {
        this.file = file;
    }

    @Override
    public File getFile() {
        return this.file;
    }
}
