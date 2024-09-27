package com.breeze.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * 资源管理
 */
public interface Resource {

    URL getURL() throws FileNotFoundException;

    File getFile() throws IOException;
}
