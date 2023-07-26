package com.iiikn.core.resovler;

import com.iiikn.execption.FoundElementException;
import com.iiikn.factory.DefaultElementFactory;

import java.lang.reflect.Field;


/**
 * 实现依赖注入接口
 * TODO: 依赖注入的方式不能限于 Field，在真正spring中，可以通过多种方式注入，例如构造器或初始化方法。
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface AnnotationInjectResolver {

    /**
     * 声明解析器验证接口。接口实现作为条件校验，用于筛选解析器。
     * @param declaredField 要处理的依赖 Field 信息
     * @return 返回 ture 表示有子类符合处理条件。
     */
    boolean isResolver(Field declaredField);

    /**
     * 声明注入接口
     * @param declaredField 实例中依赖属性的Field对象，即为依赖对象。
     * @param generatorElement  实例对象。
     * @param defaultElementFactory 实例工厂对象，实现依赖注入时不免要进行查找依赖对象。
     * @throws IllegalAccessException 声明注入执行异常
     * @throws FoundElementException 声明查无元素对象异常
     */
    void inject(Field declaredField, Object generatorElement, DefaultElementFactory defaultElementFactory) throws IllegalAccessException, FoundElementException;
}
