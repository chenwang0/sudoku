package com.iiikn.factory;

import com.iiikn.execption.FoundElementException;

/**
 * 提供获取元素接口。
 * 原型为 spring bean 工厂。
 * 在此应用中，用于管理和创建 element 实例。在容器启动时，会自动创建一个 ElementFactory 对象，用于管理容器中的所有 element。
 * 它提供了基本的 element 管理功能，但是不支持 AOP。
 *
 * @author
 * @since
 * @version
 *
 * 修改记录：
 * 时间      修改人员    	修改内容
 * ------------------------------
 */
public interface ElementFactory {

	/**
	 * 根据 element 的名称从容器中获取 element 实例。
	 * @param elementName 元素名
	 * @return 在工厂中找到的元素对象，查无结果返回 null。
	 */
	Object getElement(String elementName);


	/**
	 * 根据类型获取，获取工厂中相同的元素的实例对象
	 * @param requiredType 获取的元素类型的 class
	 * @return 在工厂中相匹配的实例对象，查无结果抛出异常。
	 * @param <T> 声明获取对象泛型
	 * @throws FoundElementException 声明查无元素异常，未在工厂中找到符合元素将抛出异常。
	 */
	<T> T getElement(Class<T> requiredType) throws FoundElementException;


	/**
	 * 声明判断容器中是否包含指定的 element接口。
	 * @param name 查找的element实例名称
	 * @return 查找结果返回true表示容器中存在指定的实例
	 */
	//boolean containsBean(String name);

	/**
	 * 声明判断指定的 element 是否为单例接口。
	 * @param name 需要验证element实例名称
	 * @return 返回验证结果true则表示指定对象在容器中以单例方式管理
	 */
	//boolean isSingleton(String name)：

	/**
	 * 声明获取指定 element 的类型方法。
	 * @param name 需要获取类型的实例名称
	 * @return 返回element实例类型
	 */
	//Class<?> getType(String name);
}
