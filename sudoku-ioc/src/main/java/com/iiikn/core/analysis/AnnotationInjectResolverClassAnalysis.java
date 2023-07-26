package com.iiikn.core.analysis;

import com.iiikn.core.resovler.AnnotationInjectResolver;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.util.ClassUtil;

/**
 * {@link AnnotationInjectResolver} 解析器
 * 该类实现{@link ClassAnalysis}，提供解析处理扫描出的class文件
 * 主要处理该类是否满足为 {@link AnnotationInjectResolver} 的特殊类
 * <p>
 *
 * @author: cw
 * @since: 2023/7/25 20:19
 * @version: v0.1
 * <p>
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class AnnotationInjectResolverClassAnalysis implements ClassAnalysis {

    @Override
    public boolean isAnalysis(Class<?> scanClass) {
        return ClassUtil.isExtendsInterface(AnnotationInjectResolver.class, scanClass);
    }

    @Override
    public void analysis(Class<?> scanClass, DefaultElementFactory defaultElementFactory) {

        // isAnalysis() 返回true 则表示 scanClass 是一个 AnnotationInjectResolver.class 的实现类
        // 那么将这个实现类添入到 实例工厂中
        defaultElementFactory.registerExtender(scanClass);
    }
}
