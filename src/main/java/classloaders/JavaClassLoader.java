package classloaders;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaClassLoader extends ClassLoader {

    public JavaClassLoader(ClassLoader parent, String[] CustomLoadSpace) {
        super(parent);
        CustomLoadingList = Arrays.asList(CustomLoadSpace);
    }

    List<String> CustomLoadingList;

    private Class<?> getClass(String name) {
        String file = "/"+name.replace('.', '/') + ".class";
        try {
            byte[] bytes = loadClassFileData(file);
            Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
            resolveClass(clazz);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        for (String str: CustomLoadingList) {
            if (!name.startsWith(str)) {
                System.out.println("Loading Class '" + name + "' with 'Custom Loader :)'");
                return getClass(name);
            }
        }
        return super.loadClass(name);
    }

    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = this.getClass().getResourceAsStream(name);
        assert stream != null;
        int size = stream.available();
        byte[] buff = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }

}