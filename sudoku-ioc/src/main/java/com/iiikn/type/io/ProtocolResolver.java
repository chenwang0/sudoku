package com.iiikn.type.io;

import com.iiikn.lang.Nullable;

@FunctionalInterface
public interface ProtocolResolver {
    @Nullable
    Resource resolve(String location, ResourceLoader resourceLoader);
}
