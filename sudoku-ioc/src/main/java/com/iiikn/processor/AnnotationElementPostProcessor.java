package com.iiikn.processor;

import com.iiikn.annotaion.Element;
import com.iiikn.factory.DefaultElementFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;

public class AnnotationElementPostProcessor implements ElementPostProcessor {

	@Override
	public void registerElement(String primaryPath, DefaultElementFactory elementFactory) {
		Collection<Object> allElement = elementFactory.getAllElement();
		for (Object element : allElement) {
			Method[] methods = element.getClass().getMethods();
			for (Method method : methods) {
				Element elementAnnotation = method.getAnnotation(Element.class);
				if (elementAnnotation != null) {
					Parameter[] parameters = method.getParameters();
					Object[] paramElements = new Object[parameters.length];
					for (int i = 0; i < parameters.length; i++) {
						// 获取方法需要的参数
						paramElements[i] = elementFactory.getElement(parameters[i].getType());
					}

					try {
						// 执行函数 获取 element 结果
						Object result = method.invoke(element, paramElements);
						// 完成注入
						elementFactory.registerAlias(elementAnnotation.value(), result);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
