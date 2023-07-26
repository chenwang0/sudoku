package com.iiikn.core;

import com.iiikn.core.analysis.ClassAnalysis;
import com.iiikn.factory.ElementFactory;
import com.iiikn.factory.ExtenderFactory;


/**
 * 应用程序的核心容器，负责管理应用程序中的所有 element 对象以及它们之间的依赖关系。
 * ApplicationContext 也是 ElementFactory 的子接口，
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ApplicationContext extends ElementFactory, ExtenderFactory<ClassAnalysis> {

	/**
	 * 声明容器初始化方法
	 */
	void initialize();

	/**
	 * 声明容器刷新方法
	 */
	void refresh();

	/**
	 * 声明容器销毁方法
	 */
	void close();
}
