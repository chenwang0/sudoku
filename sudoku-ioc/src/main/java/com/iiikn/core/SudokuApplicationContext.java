package com.iiikn.core;

import com.iiikn.Constant;
import com.iiikn.ElementDefinition;
import com.iiikn.annotaion.*;
import com.iiikn.core.analysis.ClassAnalysis;
import com.iiikn.execption.IOCException;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.factory.processor.ElementFactoryPostProcessor;
import com.iiikn.util.Assert;
import com.iiikn.util.ClassUtil;

import java.io.IOException;
import java.util.*;


/**
 * {@link ApplicationContext} 实现类。
 * 提供维护应用容器相关实现接口
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class SudokuApplicationContext implements ApplicationContext {

	/**
	 * 声明元素工厂对象
	 * 负责管理应用程序中的所有 element 对象。
	 */
	final DefaultElementFactory defaultElementFactory;

	/**
	 * 用于启动应用程序的主要元素。
	 * 它通常是指包含 main() 方法的主应用程序类，
	 * 它的作用是告诉 应用程序从哪里开始扫描和加载 bean 定义。
	 */
	final Class<?> primarySource;

	/**
	 * 存储解析器
	 */
	final List<ClassAnalysis> classAnalyseList;

	public SudokuApplicationContext(Class<?> primarySource, DefaultElementFactory defaultElementFactory) {
		this.primarySource = primarySource;
		this.defaultElementFactory = defaultElementFactory;
		this.classAnalyseList = new Vector<>(0);
	}


	@Override
	public void initialize() {

		// 扫描所有类路径
		if (this.primarySource.isAnnotationPresent(ComponentScan.class)) {
			ComponentScan annotation = this.primarySource.getAnnotation(ComponentScan.class);
			List<Class<?>> scanClassList;
			try {
				scanClassList = ClassUtil.scan(annotation.value());
			} catch (ReflectiveOperationException | IOException e) {
				e.printStackTrace();
				throw new IOCException("扫描class文件发生异常。");
			}

			// 遍历路径下的所有 .class 文件
			for (Class<?> scanClass : scanClassList) {

				// 遍历调用解析器进行处理
				for (ClassAnalysis classAnalysis : classAnalyseList) {
					// 检查是否符合
					if (classAnalysis.isAnalysis(scanClass)) {
						// 符合则进行处理
						classAnalysis.analysis(scanClass, this.defaultElementFactory);
					}
				}
			}
		}

		// 调用 Factory 后处理器
		for (ElementFactoryPostProcessor factoryPostProcessor : defaultElementFactory.getExtenderFactory(ElementFactoryPostProcessor.class)) {
			factoryPostProcessor.postProcessElementFactory(defaultElementFactory);
		}
	}

	@Override
	public void refresh() {

		Map<String, ElementDefinition> elementDefinitionMap = this.defaultElementFactory.getElementDefinitionMap();

		// 初始化单例
		for (String elementName : elementDefinitionMap.keySet()) {
			ElementDefinition elementDefinition = elementDefinitionMap.get(elementName);
			if (Constant.SCOPE_SINGLETON.equals(elementDefinition.getScope())) {
				defaultElementFactory.registerElementDefinition(elementName, elementDefinition);
			}
		}
	}

	@Override
	public void close() {

		defaultElementFactory.destroySingletons();
		defaultElementFactory.destroyElementDefinition();
		defaultElementFactory.destroyExtenderFactory();
	}

	@Override
	public Object getElement(String elementName) {
		return defaultElementFactory.getElement(elementName);
	}

	@Override
	public <T> T getElement(Class<T> clazz) {
		return defaultElementFactory.getElement(clazz);
	}

	@Override
	public boolean isExtender(Object obj) {
		return false;
	}

	@Override
	public Collection<ClassAnalysis> getAllExtenderFactory() {
		return null;
	}

	@Override
	public void registerExtender(ClassAnalysis processor) {
		this.classAnalyseList.add(processor);
	}

	@Override
	public void registerExtender(Class<? extends ClassAnalysis> processorClass) {
		Assert.isNull(processorClass, "删除元素不可为空");
		try {
			ClassAnalysis classAnalysis = processorClass.getConstructor().newInstance();
			this.registerExtender(classAnalysis);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			throw new IOCException(String.format(
					"%s registerProcessor() execution exception: Unable to create an instance for %s reflection", getClass() , processorClass), e);
		}

	}

	@Override
	public void removeExtender(Class<? extends ClassAnalysis> clazz) {

		Assert.isNull(clazz, "删除元素不可为空");
		this.classAnalyseList.removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
	}
}
