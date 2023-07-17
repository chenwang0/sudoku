package com.iiikn.factory;


public interface ElementFactory {

	Object getElement(String elementName);

	<T> T getElement(Class<T> clazz);
}
