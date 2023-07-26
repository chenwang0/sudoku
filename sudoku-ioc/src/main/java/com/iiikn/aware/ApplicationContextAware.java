package com.iiikn.aware;

import com.iiikn.core.ApplicationContext;
import com.iiikn.execption.ElementException;

/**
 * 容器回调接口
 * 在执行容器扫描类文件之际，若发现有实现了此接口子类，
 * 会将 {@link com.iiikn.core.ApplicationContext} 对象作为参数传入实现类中
 * @author: cw
 * @since: 2023
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ApplicationContextAware {

    /**
     * 声明容器回调接口。在执行容器扫描类文件之际，会对实现方法进行调用
     * @param var1 应用容器对象。 {@link com.iiikn.core.ApplicationContext}
     * @throws ElementException 声明系统异常
     */
    void setApplicationContext(ApplicationContext var1) throws ElementException;
}
