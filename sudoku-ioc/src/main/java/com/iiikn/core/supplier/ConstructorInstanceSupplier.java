package com.iiikn.core.supplier;

import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
  * 以构造器的方式实现创建元素实例。
  * 类实现了 {@link InstanceSupplier} 接口
  * 提供通过构造器完成实例化创建元素的能力。
  *
  * @author: cw
  * @since:
  * @version: v0.1
  *
  * 修改记录：
  * 时间      修改人员    修改内容
  * ------------------------------
  */
public class ConstructorInstanceSupplier implements InstanceSupplier {

    /**
     * 将进行实例化创建对象的class
     */
    private Class<?> originClass;

    /**
     * 实例化对象的构造器信息
     */
    private Constructor<?>[] constructors;

    public ConstructorInstanceSupplier(Object originElement) {
        Assert.isNull(originElement, "originElement not empty !");
        this.originClass = originElement.getClass();
        this.constructors = this.originClass.getConstructors();
    }

    public ConstructorInstanceSupplier(Class<?> originClass) {
        Assert.isNull(originClass, "originClass not empty !");
        this.originClass = originClass;
        this.constructors = originClass.getConstructors();
    }

    @Override
    public Object generator(DefaultElementFactory defaultElementFactory) throws InstantiationException, IllegalAccessException, InvocationTargetException {

        /*

            todo 1 目前只支持无参构造的创建。在 spring 中是可以支持有参构造创建
            todo 2 实现有参构造需要考虑循环依赖问题，目前不是很清除的了解spring采用了三级缓存解决，可以参考一下

         */

        Object result = null;
        Constructor<?> parameterless = null;
        for (Constructor<?> constructor : this.constructors) {
            if (constructor.getParameterCount() == 0) {
                parameterless = constructor;
                break;
            }
        }

        if (parameterless != null) {
            result = parameterless.newInstance();
        } else {
            // 处理有参
        }

        return result;
    }
}
