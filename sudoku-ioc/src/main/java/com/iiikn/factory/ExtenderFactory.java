package com.iiikn.factory;

import com.iiikn.util.ForeachFunction;

import java.util.Collection;


/**
 * 程序扩展的模板工厂。提供了一系列维护扩展器的模板操作接口。
 *
 * @param <T> 声明扩展器泛型
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ExtenderFactory<T> {


    /**
     * 检验方法
     * @param obj 检查对象
     * @return 返回true表示有符合处理的扩展器
     */
    boolean isExtender(Object obj);

    /**
     * 遍历扩展器，通过拉姆达写法是外部操作。
     * @param function lambda对象。外部实现对扩展器的使用。
     */
    default void foreach(ForeachFunction<T> function) {
        for (T t : getAllExtenderFactory()) {
            function.foreach(t);
        }
    }

    /**
     * 声明获取所有扩展器接口
     * @return 返回实现工厂中维护的所有扩展器。
     */
    Collection<T> getAllExtenderFactory();

    /**
     * 通过完整实例注册接口
     * @param processor 一个已经被实例化的对象
     */
    void registerExtender(T processor);

    /**
     * 通过实例对象的class注册接口 (由实现完成实例化)
     * @param extenderClass 即将注入实例对象的clazz
     */
    void registerExtender(Class<? extends T> extenderClass);

    /**
     * 移除一个扩展实例
     * @param clazz 移除扩展实例的clazz对象
     */
    void removeExtender(Class<? extends T> clazz);

    /**
     * 销毁所有扩展器
     */
    default void destroy() {
        getAllExtenderFactory().clear();
    }
}