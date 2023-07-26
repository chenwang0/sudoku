package com.iiikn.core.resovler;

import com.iiikn.annotaion.Autowired;
import com.iiikn.execption.FoundElementException;
import com.iiikn.factory.DefaultElementFactory;

import java.lang.reflect.Field;


/**
 * &#064;Autowired  注解解析器。
 * 此类实现了依赖注入接口： {@link AnnotationInjectResolver}
 * 提供 @Autowired 注解的解析以及完成根据类型注入实现
 * 这个类会在在容器扫描时被初始化，然后存放到 {@link AnnotationInjectFactory} 被托管
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class AutowiredAnnotationInjectResolver implements AutowireCandidateResolver {

    @Override
    public boolean isResolver(Field declaredField) {
        return declaredField.isAnnotationPresent(Autowired.class);
    }

    @Override
    public void inject(Field declaredField, Object generatorElement, DefaultElementFactory defaultElementFactory) throws IllegalAccessException {

        declaredField.setAccessible(true);

        /*
            1.实现优先根据 变量名字获取实例（依赖注入）对象
            2.如果变量名字查无实例对象则会再通过 依赖对象类型
            3.经过两类查找依旧查无结果则抛出异常
         */


        // 根据属性变量名获取
        Object targetElement = defaultElementFactory.getElement(declaredField.getName());

        if(targetElement == null) {

            try {
                // 根据类型获取
                targetElement = defaultElementFactory.getElement(declaredField.getType());
            } catch (FoundElementException e) {
                e.printStackTrace();
                throw new FoundElementException(String.format( "%s : Cannot find a dependency for %s ",
                        generatorElement.getClass(), declaredField.getType()), e
                );
            }

        }
        declaredField.set(generatorElement, targetElement);
    }
}
