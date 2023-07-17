package com.iiikn;

/**
 * 元素描述信息
 */
public class ElementDefinition {

    // 元素 的类型
    private Class<?> type;

    // 元素 作用域
    private String scope;


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
