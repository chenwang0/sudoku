package com.iiikn.factory;

import com.iiikn.Constant;
import com.iiikn.ElementDefinition;
import com.iiikn.annotaion.Autowired;
import com.iiikn.processor.ElementPostProcessor;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SudokuElementFactory implements DefaultElementFactory {

	// 元素描述信息
	private final ConcurrentHashMap<String, ElementDefinition> elementDefinitionMap = new ConcurrentHashMap<>();
	// 单例对象
	private final ConcurrentHashMap<String, Object> singletonObjectsMap = new ConcurrentHashMap<>();
	// 后置处理器
	List<ElementPostProcessor> elementPostProcessorList = new ArrayList<>(0);

	@Override
	public Object getElement(String elementName) {
		ElementDefinition elementDefinition = elementDefinitionMap.get(elementName);
		if (elementDefinition == null) {
			return null;
		}

		// 获取单例
		if (Constant.SCOPE_SINGLETON.equals(elementDefinition.getScope())){
			Object element = singletonObjectsMap.get(elementName);
			if (element == null) {
				element = this.createElement(elementName, elementDefinition);
				singletonObjectsMap.put(elementName, element);
			}
			return element;
		}

		// 获取多例
		if (Constant.SCOPE_PROTOTYPE.equals(elementDefinition.getScope())) {
			return this.createElement(elementName, elementDefinition);
		}

		return singletonObjectsMap.get(elementName);
	}

	@Override
	public <T> T getElement(Class<T> clazz) {

		for (String elementName : elementDefinitionMap.keySet()) {
			ElementDefinition elementDefinition = elementDefinitionMap.get(elementName);
			if (elementDefinition.getType().equals(clazz)) {
				return (T) getElement(Introspector.decapitalize(clazz.getSimpleName()));
			}
		}
		throw new IllegalArgumentException("not find " + clazz.toString());
	}

	@Override
	public Object createElement(String elementName, ElementDefinition elementDefinition) {
		Class<?> clazz = elementDefinition.getType();
		try {

			Object newInstance = clazz.getConstructor().newInstance();

			// simple impl
			for (Field declaredField : clazz.getDeclaredFields()) {
				if (declaredField.isAnnotationPresent(Autowired.class)) {
					declaredField.setAccessible(true);
					declaredField.set(newInstance, getElement(declaredField.getType()));
				}
			}


			// Aware 回调
			if (newInstance instanceof ElementFactoryAware) {
				((ElementFactoryAware) newInstance).setBeanFactory(this);
			}


			// TODO 初始化前

			// TODO 初始化

			// TODO 初始化后

			return newInstance;

		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public ConcurrentHashMap<String, ElementDefinition> getElementDefinitionMap() {
		return elementDefinitionMap;
	}

	public ConcurrentHashMap<String, Object> getSingletonObjectsMap() {
		return singletonObjectsMap;
	}

	public List<ElementPostProcessor> getElementPostProcessorList() {
		return elementPostProcessorList;
	}
}
