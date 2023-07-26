package com.iiikn.factory.processor;

import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.factory.ExtenderFactory;
import com.iiikn.util.ForeachFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


/**
 * 实例工厂的后处理器工厂。
 * 提供了一系列维护扩展器的模板操作接口。
 * 提供静态方法embedElementFactory() 将工厂添加到元素实例工厂。
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class ElementFactoryPostProcessorFactory implements ExtenderFactory<ElementFactoryPostProcessor> {

    /**
     * Factory 后处理器
     */
    final List<ElementFactoryPostProcessor> elementFactoryPostProcessorList;

    private ElementFactoryPostProcessorFactory() {
        this.elementFactoryPostProcessorList = new ArrayList<>(0);
    }

    public ElementFactoryPostProcessorFactory(List<ElementFactoryPostProcessor> elementFactoryPostProcessorList) {
        this.elementFactoryPostProcessorList = elementFactoryPostProcessorList;
    }


    @Override
    public boolean isExtender(Object obj) {
        if (obj instanceof ElementFactoryPostProcessor) return true;
        if (obj instanceof Class<?>) {
            return ElementFactoryPostProcessor.class.isAssignableFrom((Class<?>) obj);
        }
        return false;
    }

    @Override
    public void foreach(ForeachFunction<ElementFactoryPostProcessor> function) {
        for (ElementFactoryPostProcessor factoryPostProcessor : elementFactoryPostProcessorList) {
            function.foreach(factoryPostProcessor);
        }
    }

    @Override
    public Collection<ElementFactoryPostProcessor> getAllExtenderFactory() {
        return elementFactoryPostProcessorList;
    }

    @Override
    public void registerExtender(ElementFactoryPostProcessor processor) {

        Objects.requireNonNull(processor, "注册元素不可为空");
        this.removeExtender(processor.getClass());
        this.elementFactoryPostProcessorList.add(processor);
    }

    @Override
    public void registerExtender(Class<? extends ElementFactoryPostProcessor> processorClass) {

        Objects.requireNonNull(processorClass, "注册元素不可为空");
        this.removeExtender(processorClass);
        try {
            this.elementFactoryPostProcessorList.add(processorClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeExtender(Class<? extends ElementFactoryPostProcessor> clazz) {

        Objects.requireNonNull(clazz, "删除元素不可为空");
        this.elementFactoryPostProcessorList.removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
    }

    public static void embedElementFactory(DefaultElementFactory defaultElementFactory) {
        ElementFactoryPostProcessorFactory processorFactory = new ElementFactoryPostProcessorFactory();
        defaultElementFactory.addExtenderFactory(processorFactory);
    }
}
