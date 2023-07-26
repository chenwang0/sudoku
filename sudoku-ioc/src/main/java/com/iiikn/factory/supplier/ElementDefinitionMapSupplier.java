package com.iiikn.factory.supplier;

import com.iiikn.ElementDefinition;

import java.util.Map;

/**
 * 声明对外暴露的接口，
 * 提供获取元素描述对象{@link ElementDefinition}的接口。
 * <p>
 * @author: cw
 * @since: 2023/7/25 23:24
 * @version: v0.1
 * <p>
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ElementDefinitionMapSupplier {

    /**
     * 声明获取储存element定义的map集合
     * @return 返回储存element定义的集合
     */
    Map<String, ElementDefinition> getElementDefinitionMap();


    /**
     * 向容器中注册一个 Element 定义。
     * @param elementName   储存element定义名称
     * @param elementDefinition 储存element定义对象
     */
    default void registerElementDefinition(String elementName, ElementDefinition elementDefinition) {
        getElementDefinitionMap().put(elementName, elementDefinition);
    }

    /**
     * 从容器中移除一个 element 定义。
     * @param elementName 移除的element定义名称
     */
    default void removeElementDefinition(String elementName) {
        getElementDefinitionMap().remove(elementName);
    }


    /**
     * 销毁所有 element 定义。
     */
    default void destroyElementDefinition() {
        getElementDefinitionMap().clear();
    }
}
