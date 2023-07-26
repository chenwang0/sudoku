package com.iiikn.factory.processor;

import com.iiikn.ElementDefinition;
import com.iiikn.MethodMetadata;
import com.iiikn.annotaion.Configuration;
import com.iiikn.annotaion.Element;
import com.iiikn.execption.ElementException;
import com.iiikn.type.classreading.CachingMetadataReaderFactory;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.factory.SudokuElementFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;


/**
 * 解析 @Configuration 注解的后后置处理器，
 * 用于处理基于@Configuration注解的配置类。
 * 在容器启动时，ConfigurationAnnotationPostProcessor 会扫描所有的配置类，
 * 查找带有注解的方法，并将其注册到容器中。
 * <p>
 *     需要注意的是，ConfigurationAnnotationPostProcessor 类是一个后置处理器，
 *     它在容器启动时自动注册到容器中，并且在容器初始化过程中自动调用。
 *     如果需要自定义基于注解的配置类处理逻辑，可以通过继承 ConfigurationAnnotationPostProcessor 类，并重写其中的方法来实现。
 * </p>
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class ConfigurationAnnotationPostProcessor implements ElementFactoryPostProcessor {

    @Override
    public void postProcessElementFactory(DefaultElementFactory defaultElementFactory) throws ElementException {
        SudokuElementFactory elementFactory = (SudokuElementFactory) defaultElementFactory;
        Collection<ElementDefinition> values = elementFactory.getElementDefinitionMap().values();
        CachingMetadataReaderFactory readerFactory = elementFactory.getCachingMetadataReaderFactory();

        for (ElementDefinition value : values) {
            Class<?> elementClass = value.getType();
            if (elementClass.isAnnotationPresent(Configuration.class)) {
                Method[] methods = elementClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Element.class)) {

                        // 添加 MethodMetadata 后处理
                        Parameter[] parameters = method.getParameters();
                        MethodMetadata metadata = new MethodMetadata();
                        metadata.setMethod(method);
                        metadata.setDeclaringClass(elementClass);
                        metadata.setParameters(parameters);
                        readerFactory.put(method.getName(), metadata);
                    }
                }
            }
        }
    }

}
