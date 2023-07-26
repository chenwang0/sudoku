package com.iiikn.core.analysis;

import com.iiikn.aware.ElementFactoryAware;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.util.ClassUtil;

/**
 *
 * 该类实现{@link ClassAnalysis}，提供解析处理扫描出的class文件
 * 检查 scanClass 是否满足为{@link com.iiikn.aware.ElementFactoryAware} 元素实例工厂回调工厂的子类
 * 符合条件则进行回调，将实例工厂以以参数形式传给class类中的函数
 * <p>
 *
 * @author: cw
 * @since: 2023/7/25 20:19
 * @version: v0.1
 * <p>
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class ElementFactoryAwareClassAnalysis implements ClassAnalysis {

    @Override
    public boolean isAnalysis(Class<?> scanClass) {
        return ClassUtil.isExtendsInterface(ElementFactoryAware.class, scanClass);
    }

    @Override
    public void analysis(Class<?> scanClass, DefaultElementFactory defaultElementFactory) {
        try {
            ((ElementFactoryAware) scanClass.getConstructor().newInstance()).setBeanFactory(defaultElementFactory);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
