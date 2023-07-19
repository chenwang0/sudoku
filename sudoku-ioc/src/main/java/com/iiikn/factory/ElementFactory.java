package com.iiikn.factory;

import java.util.Collection;

public interface ElementFactory {

	Object getElement(String elementName);

	Collection<Object> getAllElement();

	<T> T getElement(Class<T> clazz);
}
