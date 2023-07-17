package com.iiikn.factory;

import com.iiikn.ElementDefinition;
import com.iiikn.processor.ElementPostProcessor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface DefaultElementFactory extends ElementFactory {


	Object createElement(String elementName, ElementDefinition elementDefinition);


	ConcurrentHashMap<String, ElementDefinition> getElementDefinitionMap();

	ConcurrentHashMap<String, Object> getSingletonObjectsMap();

	List<ElementPostProcessor> getElementPostProcessorList();
}
