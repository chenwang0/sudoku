package com.iiikn.core;

import com.iiikn.Constant;
import com.iiikn.ElementDefinition;
import com.iiikn.annotaion.*;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.processor.ElementPostProcessor;
import com.iiikn.util.ClassScanner;
import com.iiikn.util.PackageUtil;

import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SudokuApplicationContext implements ApplicationContext {

	final ConcurrentHashMap<String, ElementDefinition> stairCacheMap = new ConcurrentHashMap<>();
	final DefaultElementFactory defaultElementFactory;
	final Class<?> primarySource;

	public SudokuApplicationContext(Class<?> primarySource, DefaultElementFactory defaultElementFactory) {
		this.primarySource = primarySource;
		this.defaultElementFactory = defaultElementFactory;
	}

	@Override
	public void registerProcessor(ElementPostProcessor processor) {
		Objects.requireNonNull(processor, "注册元素不可为空");
		this.removeProcessor(processor.getClass());
		defaultElementFactory.getElementPostProcessorList().add(processor);
	}

	@Override
	public void registerProcessor(Class<? extends ElementPostProcessor> processorClass) {
		Objects.requireNonNull(processorClass, "注册元素不可为空");
		this.removeProcessor(processorClass);
		try {
			defaultElementFactory.getElementPostProcessorList().add(processorClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeProcessor(Class<? extends ElementPostProcessor> clazz) {
		Objects.requireNonNull(clazz, "删除元素不可为空");
		defaultElementFactory.getElementPostProcessorList().removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
	}

	@Override
	public void init() {
		// 扫描所有 element definition
		ConcurrentHashMap<String, ElementDefinition> elementDefinitionMap = this.defaultElementFactory.getElementDefinitionMap();
		if (this.primarySource.isAnnotationPresent(ComponentScan.class)) {
			ComponentScan annotation = this.primarySource.getAnnotation(ComponentScan.class);
			List<Class<?>> scanClassList;
			try {
				scanClassList = ClassScanner.scan(annotation.value());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException("扫描class文件发生异常。");
			}

			for (Class<?> scanClass : scanClassList) {

				// 判断这个类是否为特殊类
				if (ElementPostProcessor.class.isAssignableFrom(scanClass) && !scanClass.isInterface() ) {
					this.registerProcessor((Class<? extends ElementPostProcessor>) scanClass);
				}

				if (scanClass.isAnnotationPresent(Component.class)) {

					ElementDefinition elementDefinition = new ElementDefinition();
					Component component = scanClass.getAnnotation(Component.class);
					String elementName = component.value();
					if ("".equals(elementName)) {
						// 获取注册 元素名
						elementName = Introspector.decapitalize(scanClass.getSimpleName());
					}

					// 设置作用域
					Scope scopeAnnotation = scanClass.getAnnotation(Scope.class);
					if (scopeAnnotation == null || "".equals(scopeAnnotation.value())) {
						elementDefinition.setScope(Constant.SCOPE_SINGLETON);
					} else {
						elementDefinition.setScope(scopeAnnotation.value());
					}
					// 设置类型
					elementDefinition.setType(scanClass);
					elementDefinitionMap.put(elementName, elementDefinition);
				}

				if (scanClass.isAnnotationPresent(Configuration.class)) {
					Configuration component = scanClass.getAnnotation(Configuration.class);
					String elementName = component.value();
					if ("".equals(elementName)) {
						// 获取注册 元素名
						elementName = Introspector.decapitalize(scanClass.getSimpleName());
					}
					ElementDefinition elementDefinition = new ElementDefinition();
					// 设置类型
					elementDefinition.setType(scanClass);
					elementDefinition.setScope(Constant.SCOPE_SINGLETON);
					elementDefinitionMap.put(elementName, elementDefinition);
				}
			}
		}

	}

	@Override
	public void refresh() {
		ConcurrentHashMap<String, ElementDefinition> elementDefinitionMap = this.defaultElementFactory.getElementDefinitionMap();
		ConcurrentHashMap<String, Object> singletonObjectsMap = this.defaultElementFactory.getSingletonObjectsMap();

		// 初始化单例
		for (String elementName : elementDefinitionMap.keySet()) {
			ElementDefinition elementDefinition = elementDefinitionMap.get(elementName);
			if (Constant.SCOPE_SINGLETON.equals(elementDefinition.getScope())) {
				Object element = this.defaultElementFactory.createElement(elementName, elementDefinition);
				singletonObjectsMap.put(elementName, element);
			}
		}

	}

	@Override
	public void close() {

	}

	@Override
	public Object getElement(String elementName) {
		return defaultElementFactory.getElement(elementName);
	}

	@Override
	public <T> T getElement(Class<T> clazz) {
		return defaultElementFactory.getElement(clazz);
	}
}
