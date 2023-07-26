package com.iiikn.core.analysis;

import com.iiikn.Constant;
import com.iiikn.ElementDefinition;
import com.iiikn.annotaion.Configuration;
import com.iiikn.factory.DefaultElementFactory;

import java.beans.Introspector;

/**
 * Configuration注解解析器
 *
 * <p>
 * @author: cw
 * @since: 2023/7/25 21:25
 * @version: v0.1
 * <p>
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public class ConfigurationAnnotationClassAnalysis implements ClassAnalysis {
    @Override
    public boolean isAnalysis(Class<?> scanClass) {
        return scanClass.isAnnotationPresent(Configuration.class);
    }

    @Override
    public void analysis(Class<?> scanClass, DefaultElementFactory defaultElementFactory) {

        Configuration configuration = scanClass.getAnnotation(Configuration.class);
        String elementName = configuration.name();
        if ("".equals(elementName)) {
            // 获取注册 元素名
            elementName = Introspector.decapitalize(scanClass.getSimpleName());
        }
        // 设置类型
        ElementDefinition elementDefinition = new ElementDefinition();
        elementDefinition.setType(scanClass);
        elementDefinition.setScope(Constant.SCOPE_SINGLETON);
        defaultElementFactory.registerElementDefinition(elementName, elementDefinition);
    }
}
