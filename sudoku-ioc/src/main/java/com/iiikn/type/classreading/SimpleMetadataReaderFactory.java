package com.iiikn.type.classreading;

import com.iiikn.lang.Nullable;
import com.iiikn.type.io.DefaultResourceLoader;
import com.iiikn.type.io.Resource;
import com.iiikn.type.io.ResourceLoader;
import com.iiikn.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SimpleMetadataReaderFactory implements MetadataReaderFactory {
    private final ResourceLoader resourceLoader;

    public SimpleMetadataReaderFactory(@Nullable ResourceLoader resourceLoader) {
        this.resourceLoader = (ResourceLoader)(resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }



    public final ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    public MetadataReader getMetadataReader(String className) throws IOException {
        try {
            String resourcePath = "classpath:" + ClassUtils.convertClassNameToResourcePath(className) + ".class";
            Resource resource = this.resourceLoader.getResource(resourcePath);
            return this.getMetadataReader(resource);
        } catch (FileNotFoundException var7) {
            int lastDotIndex = className.lastIndexOf(46);
            if (lastDotIndex != -1) {
                String innerClassName = className.substring(0, lastDotIndex) + '$' + className.substring(lastDotIndex + 1);
                String innerClassResourcePath = "classpath:" + ClassUtils.convertClassNameToResourcePath(innerClassName) + ".class";
                Resource innerClassResource = this.resourceLoader.getResource(innerClassResourcePath);
                if (innerClassResource.exists()) {
                    return this.getMetadataReader(innerClassResource);
                }
            }

            throw var7;
        }
    }

    public MetadataReader getMetadataReader(Resource resource) throws IOException {
        return new SimpleMetadataReader(resource, this.resourceLoader.getClassLoader());
    }
}