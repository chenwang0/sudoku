package com.iiikn.util;


/**
 * 遍历函数
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
@FunctionalInterface
public interface ForeachFunction<T> {

    /**
     * 声明遍历扩展使用接口
     * @param each 传入某个集合遍历时的当前元素
     */
    void foreach(T each);
}
