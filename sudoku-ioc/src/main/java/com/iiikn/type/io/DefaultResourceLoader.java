package com.iiikn.type.io;

import com.iiikn.lang.Nullable;
import com.iiikn.util.Assert;
import com.iiikn.util.ClassUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultResourceLoader implements ResourceLoader {
    @Nullable
    private ClassLoader classLoader;
    private final Set<ProtocolResolver> protocolResolvers = new LinkedHashSet<>(4);
    private final Map<Class<?>, Map<Resource, ?>> resourceCaches = new ConcurrentHashMap<>(4);

    public DefaultResourceLoader() {
    }

    public DefaultResourceLoader(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setClassLoader(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Nullable
    public ClassLoader getClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader();
    }

    public void addProtocolResolver(ProtocolResolver resolver) {
        Assert.notNull(resolver, "ProtocolResolver must not be null");
        this.protocolResolvers.add(resolver);
    }

    public Collection<ProtocolResolver> getProtocolResolvers() {
        return this.protocolResolvers;
    }

    public <T> Map<Resource, T> getResourceCache(Class<T> valueType) {
        return (Map)this.resourceCaches.computeIfAbsent(valueType, (key) -> {
            return new ConcurrentHashMap();
        });
    }

    public void clearResourceCaches() {
        this.resourceCaches.clear();
    }

    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        Iterator<ProtocolResolver> var2 = this.getProtocolResolvers().iterator();

        Resource resource;
        do {
            if (!var2.hasNext()) {
                if (location.startsWith("/")) {
                    return null;
                }

                if (location.startsWith("classpath:")) {
                    return null;
                }

                try {
                    URL url = new URL(location);
                    return (Resource) null;
                } catch (MalformedURLException var5) {
                    return null;
                }
            }

            ProtocolResolver protocolResolver = (ProtocolResolver)var2.next();
            resource = protocolResolver.resolve(location, this);
        } while(resource == null);

        return resource;
    }

}
