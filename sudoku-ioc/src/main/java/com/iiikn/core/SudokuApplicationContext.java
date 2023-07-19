package com.iiikn.core;

import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.processor.ElementPostProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SudokuApplicationContext implements ApplicationContext {

	List<ElementPostProcessor> elementPostProcessorList = new ArrayList<>(0);
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
		this.elementPostProcessorList.add(processor);
	}

	@Override
	public void registerProcessor(Class<? extends ElementPostProcessor> processorClass) {
		Objects.requireNonNull(processorClass, "注册元素不可为空");
		this.removeProcessor(processorClass);
		try {
			this.elementPostProcessorList.add(processorClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeProcessor(Class<? extends ElementPostProcessor> clazz) {
		Objects.requireNonNull(clazz, "删除元素不可为空");
		this.elementPostProcessorList.removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
	}

	@Override
	public void refresh() {
		Package sourcePackage = primarySource.getPackage();
		for (ElementPostProcessor elementPostProcessor : elementPostProcessorList) {
			elementPostProcessor.registerElement(sourcePackage.toString(), defaultElementFactory);
		}
	}

	@Override
	public void close() {
		elementPostProcessorList.clear();
		defaultElementFactory.getElementMark().clear();
		defaultElementFactory.getAllElement().clear();
	}

	@Override
	public Object getElement(String elementName) {
		return defaultElementFactory.getElement(elementName);
	}

	@Override
	public Collection<Object> getAllElement() {
		return defaultElementFactory.getAllElement();
	}

	@Override
	public <T> T getElement(Class<T> clazz) {
		return defaultElementFactory.getElement(clazz);
	}
}
