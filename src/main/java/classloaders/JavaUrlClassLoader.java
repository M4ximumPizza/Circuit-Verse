package classloaders;

import java.net.URL;
import java.net.URLClassLoader;

public class JavaUrlClassLoader extends URLClassLoader {
    public JavaUrlClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addURL(URL url) {
        super.addURL(url);
    }
}
