package com.iiikn.processor;

import com.iiikn.Constant;
import com.iiikn.ElementDefinition;
import com.iiikn.annotaion.Configuration;
import com.iiikn.annotaion.Element;
import com.iiikn.annotaion.Scope;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.factory.ElementFactory;
import com.iiikn.factory.ElementFactoryAware;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ConfigurationClassPostProcessor implements ElementPostProcessor, ElementFactoryAware {
    DefaultElementFactory defaultElementFactory;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String elementName) {
        return ElementPostProcessor.super.postProcessBeforeInitialization(bean, elementName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String elementName) {
        return ElementPostProcessor.super.postProcessAfterInitialization(bean, elementName);
    }


    @Override
    public void setBeanFactory(ElementFactory elementFactory) {

        this.defaultElementFactory = (DefaultElementFactory) elementFactory;

        for (String elementName : defaultElementFactory.getSingletonObjectsMap().keySet()) {

            Object element = defaultElementFactory.getSingletonObjectsMap().get(elementName);
            Class<?> elementClass = element.getClass();

            if (elementClass.isAnnotationPresent(Configuration.class)) {

                Method[] methods = elementClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Element.class)) {

                        Parameter[] parameters = method.getParameters();
                        Object[] paramElements = new Object[parameters.length];
                        for (int i = 0; i < parameters.length; i++) {
                            // 获取方法需要的参数
                            Object paramElement = elementFactory.getElement(parameters[i].getType());
                            paramElements[i] = paramElement;
                        }

                        try {
                            // 执行函数 获取 element 结果
                            Object result = method.invoke(element, paramElements);
                            // 完成注入

                            Element elementAnnotation = elementClass.getAnnotation(Element.class);

                            Class<?> resultClass = result.getClass();
                            ElementDefinition elementDefinition = new ElementDefinition();
                            elementDefinition.setType(resultClass);
                            if (resultClass.isAnnotationPresent(Scope.class)) {
                                String value = resultClass.getAnnotation(Scope.class).value();
                                elementDefinition.setScope(value);
                            } else {
                                elementDefinition.setScope(Constant.SCOPE_SINGLETON);
                                defaultElementFactory.createElement(elementAnnotation.value(), elementDefinition);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
