package com.iiikn;

import com.iiikn.core.resovler.AnnotationInjectResolver;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    private Constant() {
    }

    public static final String FILE_CLASS = ".class";

    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_SINGLETON = "singleton";

    public static final List<AnnotationInjectResolver> annotationInjectResolverList = new ArrayList<>();
}
