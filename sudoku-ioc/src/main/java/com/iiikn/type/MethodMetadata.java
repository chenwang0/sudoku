package com.iiikn.type;


/**
 * 作用于描述方法的元数据信息。
 * MethodMetadata 接口定义了获取和设置方法元数据信息的方法，包括方法名称、方法参数、返回类型、方法注解等。
 * 在原型中MethodMetadata继承了AnnotatedTypeMetadata接口，所以与3原型相比它没有包含注解信息的能力。
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface MethodMetadata {

    /**
     * 声明获取方法名。
     * @return 方法名
     */
    String getMethodName();

    /**
     * 声明获取声明该方法的类名。
     * @return 类名
     */
    String getDeclaringClassName();

    /**
     * 声明获取方法返回值类型的名称。
     * @return 返回值类型
     */
    String getReturnTypeName();

    /**
     * 声明判断该方法是否为抽象方法。
     * @return 返回ture表示该方法为抽象方法
     */
    boolean isAbstract();

    /**
     * 声明判断该方法是否为静态方法。
     * @return 返回true表示该方法为静态方法，false则不是
     */
    boolean isStatic();

    /**
     * 声明判断该方法是否为final方法。
     * @return 返回ture表示该方法被final修饰
     */
    boolean isFinal();

    /**
     * 声明判断该方法是否可以被重写。
     * @return 返回true表示可以被重写
     */
    boolean isOverridable();
}
