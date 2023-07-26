package com.iiikn.aware;


/**
 * 类初始化回调接口
 * 在元素生命周期阶段，执行了实例化创建后，将会对实现了此接口的实例对象，调用对象中重写的的 afterPropertiesSet()方法
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface InitializingElement {

    /**
     * 实例化方法
     */
    void afterPropertiesSet();
}
