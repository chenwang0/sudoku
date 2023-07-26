package com.iiikn.type.classreading;

import com.iiikn.type.io.Resource;

import java.io.IOException;

public interface MetadataReaderFactory {
    MetadataReader getMetadataReader(String className) throws IOException;

    MetadataReader getMetadataReader(Resource resource) throws IOException;
}
