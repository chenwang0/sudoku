package com.iiikn.factory.supplier;

import com.iiikn.ElementDefinition;

import java.util.Map;

/**
 * 声明对外暴露的接口，提供获取单例或存入单例元素的接口。
 * <p>
 *
 * @author: cw
 * @since: 2023/7/25 23:24
 * @version: v0.1
 * <p>
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface SingletonElementMapSupplier {

    /**
     * 声明获取所有单例信息
     * @return 返回储存单例元素的Map对象
     */
    Map<String, Object> getSingletonObjectsMap();

    /**
     * 向容器中注册一个单例 element 实例。
     * @param elementName		实例名称
     * @param singletonObject	单例对象
     */
    default void registerSingleton(String elementName, Object singletonObject) {
        getSingletonObjectsMap().put(elementName, singletonObject);
    }

    /**
     * 销毁所有的单例 element 实例。
     */
    default void destroySingletons() {
        getSingletonObjectsMap().clear();
    }
}
