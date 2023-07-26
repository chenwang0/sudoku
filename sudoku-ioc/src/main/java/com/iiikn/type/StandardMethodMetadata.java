package com.iiikn.type;

import com.iiikn.util.Assert;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * MethodMetadata接口的一个默认实现类，
 * 它实现了MethodMetadata接口中的所有方法。它包含了方法名、参数列表、返回值类型等信息。
 *
 * <p>
 * @author: cw
 * @since: 2023/7/26 11:35
 * @version: v0.1
 * <p>
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class StandardMethodMetadata implements MethodMetadata {

    /**
     * 表示被描述的方法。
     */
    private final Method introspectedMethod;
    /**
     * 嵌套注解作为映射
     */
    private final boolean nestedAnnotationsAsMap;

    /**
     * MergedAnnotations是一个用于合并注解的工具类。
     * 它可以将多个注解合并成一个注解，并提供了一些便捷的方法来获取合并后的注解信息。
     * private final MergedAnnotations mergedAnnotations;
     */

    @Deprecated
    public StandardMethodMetadata(Method introspectedMethod) {
        this(introspectedMethod, false);
    }

    /** @deprecated */
    @Deprecated
    public StandardMethodMetadata(Method introspectedMethod, boolean nestedAnnotationsAsMap) {
        Assert.isNull(introspectedMethod, "Method must not be null");
        this.introspectedMethod = introspectedMethod;
        this.nestedAnnotationsAsMap = nestedAnnotationsAsMap;
        // this.mergedAnnotations = MergedAnnotations.from(introspectedMethod, SearchStrategy.DIRECT, RepeatableContainers.none());
    }


    public final Method getIntrospectedMethod() {
        return this.introspectedMethod;
    }

    public String getMethodName() {
        return this.introspectedMethod.getName();
    }

    public String getDeclaringClassName() {
        return this.introspectedMethod.getDeclaringClass().getName();
    }

    public String getReturnTypeName() {
        return this.introspectedMethod.getReturnType().getName();
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this.introspectedMethod.getModifiers());
    }

    public boolean isStatic() {
        return Modifier.isStatic(this.introspectedMethod.getModifiers());
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.introspectedMethod.getModifiers());
    }

    public boolean isOverridable() {
        return !this.isStatic() && !this.isFinal() && !this.isPrivate();
    }

    private boolean isPrivate() {
        return Modifier.isPrivate(this.introspectedMethod.getModifiers());
    }


    public int hashCode() {
        return this.introspectedMethod.hashCode();
    }

    public String toString() {
        return this.introspectedMethod.toString();
    }
}
