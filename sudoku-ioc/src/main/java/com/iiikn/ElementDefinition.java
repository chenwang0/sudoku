package com.iiikn;

import com.iiikn.core.supplier.ConstructorInstanceSupplier;
import com.iiikn.core.supplier.InstanceSupplier;

import java.beans.Introspector;
import java.util.Optional;

/**
 * 元素描述信息
 */
public class ElementDefinition {

    // 元素 的类型
    private Class<?> type;

    // 元素 作用域
    private String scope;

    // 注入名字
    private String elementName;

    // 创建方式
    private InstanceSupplier instanceSupplier;


    public ElementDefinition() {  }

    public ElementDefinition(Class<?> type, String scope) {
        this.type = type;
        this.scope = scope;
    }

    public String getElementName() {

        return Optional.ofNullable(elementName).orElse(Introspector.decapitalize(getType().getSimpleName()));
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public InstanceSupplier getInstanceSupplier() {
        return instanceSupplier == null ? new ConstructorInstanceSupplier(type) : instanceSupplier;
    }

    public void setInstanceSupplier(InstanceSupplier instanceSupplier) {
        this.instanceSupplier = instanceSupplier;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
