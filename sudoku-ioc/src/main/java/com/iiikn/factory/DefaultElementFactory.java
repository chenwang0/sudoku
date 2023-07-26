package com.iiikn.factory;

import com.iiikn.ElementDefinition;
import com.iiikn.factory.supplier.DefaultElementFactorySupplier;

import java.util.Collection;


/**
 * 继承自{@link ElementFactory}，的核心扩展接口。 用于管理和创建 bean 实例。
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface DefaultElementFactory extends ElementFactory, ExtenderFactory<Object>, DefaultElementFactorySupplier {

	Object createElement(String elementName, ElementDefinition elementDefinition);

	<T> Collection<T> getExtenderFactory(Class<T> clazz);

	void addExtenderFactory(ExtenderFactory<?> extenderFactory);

	void destroyExtenderFactory();
}
