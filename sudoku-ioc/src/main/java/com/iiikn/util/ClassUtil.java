package com.iiikn.util;

import com.iiikn.factory.processor.ElementPostProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class ClassUtil {

    public static List<Class<?>> scan(String packageName) throws ClassNotFoundException, IOException {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File file = new File(resource.getFile());
            for (File classFile : Objects.requireNonNull(file.listFiles())) {
                if (classFile.isDirectory()) {
                    classes.addAll(scan(packageName + "." + classFile.getName()));
                } else if (classFile.getName().endsWith(".class")) {
                    String className = packageName + "." + classFile.getName().replace(".class", "");
                    Class<?> clazz = Class.forName(className);

                    // 排除接口和注解、枚举
                    if (!clazz.isInterface() && !clazz.isAnnotation() && !clazz.isEnum()){
                        classes.add(clazz);
                    }
                }
            }
        }
        return classes;
    }


    public static boolean isExtendsInterface(Class<?> supplierClazz, Class<?> targetClazz) {

        return supplierClazz.isAssignableFrom(targetClazz) && !targetClazz.isInterface();
    }
}