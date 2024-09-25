package com.breeze.core.io;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * 资源管理
 */
public interface Resource {

    URL getURL() throws FileNotFoundException;
}
