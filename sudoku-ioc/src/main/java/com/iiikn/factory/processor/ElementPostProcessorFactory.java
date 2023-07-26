package com.iiikn.factory.processor;

import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.factory.ExtenderFactory;
import com.iiikn.util.ForeachFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * element后处理器的工厂。
 * 提供了一系列维护后处理器的模板操作接口。
 * 提供静态方法embedElementFactory() 将此工厂添加到元素实例工厂。
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class ElementPostProcessorFactory implements ExtenderFactory<ElementPostProcessor> {

    /**
     * element 后置处理器
     */
    final List<ElementPostProcessor> elementPostProcessorList;

    private ElementPostProcessorFactory() {
        this.elementPostProcessorList = new ArrayList<>(0);
    }

    public ElementPostProcessorFactory(List<ElementPostProcessor> elementPostProcessorList) {
        this.elementPostProcessorList = elementPostProcessorList;
    }

    public void foreach(ForeachFunction<ElementPostProcessor> function) {
        for (ElementPostProcessor elementPostProcessor : elementPostProcessorList) {
            function.foreach(elementPostProcessor);
        }
    }

    @Override
    public Collection<ElementPostProcessor> getAllExtenderFactory() {
        return elementPostProcessorList;
    }

    @Override
    public boolean isExtender(Object obj) {
        if (obj instanceof ElementPostProcessor) return true;
        if (obj instanceof Class<?>) {
            return ElementPostProcessor.class.isAssignableFrom((Class<?>) obj);
        }
        return false;
    }

    @Override
    public void registerExtender(ElementPostProcessor processor) {
        Objects.requireNonNull(processor, "注册元素不可为空");
        this.removeExtender(processor.getClass());
        this.elementPostProcessorList.add(processor);
    }

    @Override
    public void registerExtender(Class<? extends ElementPostProcessor> processorClass) {

        Objects.requireNonNull(processorClass, "注册元素不可为空");
        this.removeExtender(processorClass);
        try {
            this.elementPostProcessorList.add(processorClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeExtender(Class<? extends ElementPostProcessor> clazz) {
        Objects.requireNonNull(clazz, "删除元素不可为空");
        this.elementPostProcessorList.removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
    }


    public static void embedElementFactory(DefaultElementFactory defaultElementFactory) {
        ElementPostProcessorFactory processorFactory = new ElementPostProcessorFactory();
        defaultElementFactory.addExtenderFactory(processorFactory);
    }
}
