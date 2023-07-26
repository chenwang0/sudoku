package com.iiikn.factory.processor;

import com.iiikn.execption.ElementException;
import com.iiikn.factory.DefaultElementFactory;


/**
 * 实例工厂后处理器。
 * 允许开发人员在 {@link DefaultElementFactory} 加载 element 定义文件之后、
 * 实例化 element 之前修改 DefaultElementFactory 中的 element 定义。
 * <p>
 *     在容器加载 element 定义文件时，
 *     如果发现某个 element 实现了 ElementFactoryPostProcessor 接口，
 *     容器会在实例化任何其他 element 之前调用它的回调方法，以允许开发人员修改 element 定义。
 * </p>
 * <p>作用1：修改 element 定义中的属性值，例如将敏感信息从明文转换为密文。</p>
 * <p>作用2：添加自定义的 element 定义，例如从外部配置文件中加载配置信息。</p>
 * <p>作用3：移除不需要的 element 定义，例如测试环境中的 element。</p>
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
@FunctionalInterface
public interface ElementFactoryPostProcessor {

    /**
     * 声明回调函数。
     * 在 DefaultElementFactory 加载 element 定义文件之后、实例化 element 之前调用，允许开发人员修改 element 定义。
     * @param defaultElementFactory 实例工厂
     * @throws ElementException 声明执行后处理回调函数时可能会发生的异常
     */
    void postProcessElementFactory(DefaultElementFactory defaultElementFactory) throws ElementException;
}