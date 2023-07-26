package com.iiikn.aware;

/**
 * 元素注入时name回调接口
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ElementNameAware {

    /**
     * 回传 元素注入时的name。
     * @param elementName 实例在工厂中的 name
     */
    void setElementName(String elementName);
}
