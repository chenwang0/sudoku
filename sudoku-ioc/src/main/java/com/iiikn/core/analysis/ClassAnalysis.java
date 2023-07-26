package com.iiikn.core.analysis;

import com.iiikn.core.ApplicationContext;
import com.iiikn.factory.DefaultElementFactory;

/**
 * class 文件解析器。处理扫描路径下的所有 class 文件
 * <p>
 * @author: cw
 * @since: 2023/7/25 19:55
 * @version: v0.1
 * <p>
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ClassAnalysis {

    boolean isAnalysis(Class<?> scanClass);

    /**
     * 声明解析处理class函数
     * @param scanClass 需要处理的class对象
     * @param defaultElementFactory 元素工厂
     */
    void analysis(Class<?> scanClass, DefaultElementFactory defaultElementFactory);
}
