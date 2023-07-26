package com.iiikn.core.resovler;

import com.iiikn.Constant;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.factory.ExtenderFactory;
import com.iiikn.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


/**
 * 实现解析注解实现依赖查找完成注入的工厂
 * - 不对外提供无参构造
 * - 提供静态方法embedElementFactory() 将工厂添加到元素实例工厂
 * 应用之初设计了 @Autowired根据注入类查找依赖实现注入: {@link AutowiredAnnotationInjectResolver}
 * 还有根据设置元素名查找依赖完成注入：{@link SeekAnnotationInjectResolver}
 *
 * 该工厂实现了扩展器模板工厂。扩展器模板工厂提供了一系列维护模板属性的接口 {@link ExtenderFactory}
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class AnnotationInjectFactory implements ExtenderFactory<AnnotationInjectResolver> {

    /**
     * 注解解析器集合。存储了应用程序扫描上下文时找到的解析器。
     */
    final List<AnnotationInjectResolver> annotationInjectResolverList;

    private AnnotationInjectFactory() {
        annotationInjectResolverList = new ArrayList<>(0);
    }

    public AnnotationInjectFactory(List<AnnotationInjectResolver> annotationInjectResolverList) {
        this.annotationInjectResolverList = annotationInjectResolverList;
    }

    @Override
    public boolean isExtender(Object obj) {
        if (obj instanceof AnnotationInjectResolver) return true;
        if (obj instanceof Class<?>) {
            return AnnotationInjectResolver.class.isAssignableFrom((Class<?>) obj);
        }
        return false;
    }

    @Override
    public Collection<AnnotationInjectResolver> getAllExtenderFactory() {
        return this.annotationInjectResolverList;
    }

    @Override
    public void registerExtender(AnnotationInjectResolver extender) {

        Assert.isNull(extender, "注册元素不可为空");
        this.removeExtender(extender.getClass());
        this.annotationInjectResolverList.add(extender);
    }

    @Override
    public void registerExtender(Class<? extends AnnotationInjectResolver> extenderClass) {
        Assert.isNull(extenderClass, "注册元素不可为空");
        this.removeExtender(extenderClass);
        try {
            this.annotationInjectResolverList.add(extenderClass.getConstructor().newInstance());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeExtender(Class<? extends AnnotationInjectResolver> clazz) {

        Assert.isNull(clazz, "删除元素不可为空");
        this.annotationInjectResolverList.removeIf( extender -> extender.getClass().isAssignableFrom(clazz));
    }


    /**
     * 将此此扩展工厂添加到实例工厂中
     * @param defaultElementFactory 默认实例工厂对象
     */
    public static void embedElementFactory(DefaultElementFactory defaultElementFactory) {
        AnnotationInjectFactory injectFactory = new AnnotationInjectFactory(Constant.annotationInjectResolverList);
        defaultElementFactory.addExtenderFactory(injectFactory);
    }
}
