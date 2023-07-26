package com.iiikn.type;

import com.iiikn.lang.Nullable;


/**
 * 用于表示类的元数据，它是一个用于描述类信息的抽象接口。
 * ClassMetadata接口中定义了一些方法，用于获取类的相关信息。
 * 需要注意的是，ClassMetadata接口中的方法都是只读的，不能修改类的元数据信息。
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ClassMetadata {

    /**
     * 获取类的全限定名。
     */
    String getClassName();

    /**
     * 判断该类是否为接口。
     */
    boolean isInterface();

    /**
     * 判断该类是否为注解。
     */
    boolean isAnnotation();

    /**
     * 判断该类是否为抽象类。
     */
    boolean isAbstract();

    /**
     * 判断该类是否为具体类。
     */
    default boolean isConcrete() {
        return !this.isInterface() && !this.isAbstract();
    }

    /**
     * 判断该类是否为final类。
     */
    boolean isFinal();

    /**
     * 判断该类是否为独立的，即不依赖于其他类。
     */
    boolean isIndependent();

    /**
     * 获取该类的父类的全限定名。
     */
    default boolean hasEnclosingClass() {
        return this.getEnclosingClassName() != null;
    }

    @Nullable
    String getEnclosingClassName();


    default boolean hasSuperClass() {
        return this.getSuperClassName() != null;
    }

    @Nullable
    String getSuperClassName();

    /**
     * 获取该类实现的接口的全限定名。
     */
    String[] getInterfaceNames();

    /**
     * 获取该类的成员类的全限定名。
     */
    String[] getMemberClassNames();
}
