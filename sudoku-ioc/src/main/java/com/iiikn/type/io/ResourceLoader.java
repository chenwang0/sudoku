package com.iiikn.type.io;

import com.iiikn.lang.Nullable;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

    @Nullable
    ClassLoader getClassLoader();
}
