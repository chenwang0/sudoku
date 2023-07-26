package com.iiikn.core.supplier;

import com.iiikn.ElementDefinition;
import com.iiikn.factory.DefaultElementFactory;


/**
 * 实例化元素对象接口。
 * 处理收集到的 {@link ElementDefinition},将它们完成实例化创建对象
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface InstanceSupplier {

    /**
     * 声明实现生成对象接口
     * 实现将收集到的 ElementDefinition 实例化成可被使用或依赖的对象
     * @param defaultElementFactory 元素工厂
     * @return  返回实例化成型的元素
     * @throws ReflectiveOperationException 声明执行生成过程中的反射创建失败异常
     */
    Object generator(DefaultElementFactory defaultElementFactory) throws ReflectiveOperationException;
}
