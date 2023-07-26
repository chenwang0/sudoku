package com.iiikn.core.resovler;

import com.iiikn.annotaion.Seek;
import com.iiikn.execption.FoundElementException;
import com.iiikn.factory.DefaultElementFactory;

import java.lang.reflect.Field;


/**
 * &#064;Seek  注解解析器。
 * 此类实现了依赖注入提供的接口： {@link AnnotationInjectResolver}
 * 提供解析@Seek 注解，以及完成根据设置的依赖元素名或属性名完成注入
 * 需要注意的是没有实现根据依赖对象的类型进行查找注入
 * 这个类也是在容器扫描时被初始化，然后存放到 {@link AnnotationInjectFactory} 托管
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class SeekAnnotationInjectResolver implements AnnotationInjectResolver {
    @Override
    public boolean isResolver(Field declaredField) {
        return declaredField.isAnnotationPresent(Seek.class);
    }

    @Override
    public void inject(Field declaredField, Object generatorElement, DefaultElementFactory defaultElementFactory) throws IllegalAccessException {

        declaredField.setAccessible(true);

        // 注解信息获取
        Object targetElement;
        try {

            Seek annotation = declaredField.getAnnotation(Seek.class);
            if ("".equals(annotation.value())) {
                targetElement = defaultElementFactory.getElement(declaredField.getName());
            } else {
                targetElement = defaultElementFactory.getElement(annotation.value());
            }
        } catch (FoundElementException e) {
            e.printStackTrace();
            throw new FoundElementException(String.format("%s ：Cannot find a dependency for %s", getClass(), declaredField.getType()), e);
        }

        declaredField.set(generatorElement, targetElement);

    }
}
