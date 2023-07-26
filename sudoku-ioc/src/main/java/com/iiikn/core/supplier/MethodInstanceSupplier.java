package com.iiikn.core.supplier;

import com.iiikn.annotaion.Element;
import com.iiikn.execption.ElementException;
import com.iiikn.factory.DefaultElementFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 以反射执行创建实例方法，拿到返回值的方式完成创建一个元素实例。
 * 类实现了 {@link InstanceSupplier} 接口
 * 提供通过解析执行方法完成实例化创建元素的能力。
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class MethodInstanceSupplier implements InstanceSupplier {

    Object element;
    Method method;
    Parameter[] parameters;
    Object[] paramElements;
    Element elementAnnotation;

    public MethodInstanceSupplier(Object element, Method method, Object[] paramElements, Element elementAnnotation) {
        this.element = element;
        this.method = method;
        this.parameters = method.getParameters();
        this.paramElements = paramElements;
        this.elementAnnotation = elementAnnotation;
    }

    @Override
    public Object generator(DefaultElementFactory defaultElementFactory) throws InvocationTargetException, IllegalAccessException {

        /*
            1.检查参数是否需要获取依赖对象
            2.若需要一些依赖对象，获取相关依赖对象；若无依赖则直接反射执行函数拿到方法返回结果。
            3.在获取依赖对象中，优先通过参数名查找 > 参数类型获取
            4.若经过两轮查无结果则会抛出异常
            5.查找顺利，则进行注入创建
            6.创建成功则会返回最终完成创建的实例对象

            todo 1 需要考虑参数加 @param 注解和解析的功能
            todo 2 还需要考虑一些异常情况例如 函数没有定义返回。
            todo 3 死循环依赖问题
         */


        // 执行函数 获取 element 结果
        if (parameters.length > 0) {
            for (int i = 0; i < paramElements.length; i++) {

                // 通过参数名获取依赖
                Object element = defaultElementFactory.getElement(parameters[i].getName());

                if (element == null) {
                    // 通过参数类型获取依赖
                    element = defaultElementFactory.getElement(parameters[i].getType());
                }

                if (element == null) {
                    throw new ElementException(String.format("No such %s element was found ", parameters[i].getType()));
                }

                paramElements[i] = element;
            }
        }

        return this.method.invoke(element, paramElements);
    }
}
