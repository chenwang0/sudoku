package com.iiikn.aware;

import com.iiikn.factory.ElementFactory;

/**
 * 元素实例工厂回调接口
 * 在执行容器扫描类文件之际，若发现有实现了此接口子类，
 * 会将 {@link com.iiikn.factory.ElementFactory} 对象作为参数传入实现类中
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ElementFactoryAware {

    /**
     * 实例工厂回调接口.在执行容器扫描类文件之际，会对实现方法进行调用
     * @param elementFactory 应用容器对象。
     */
    void setBeanFactory(ElementFactory elementFactory);
}
