package com.iiikn.factory.processor;


/**
 * element后处理器接口
 * 扩展点接口，允许开发人员在 element 实例化、依赖注入和初始化的过程中插入自己的逻辑。
 * 在容器创建 元素 实例时，如果发现某个 元素 实现了 ElementPostProcessor 接口，
 * 容器会在实例化、依赖注入和初始化的过程中调用它的回调方法，以允许开发人员对 元素实例 进行自定义处理。
 * <p> 作用点1：添加自定义的依赖注入逻辑，例如从外部配置文件中加载配置信息。</p>
 * <p> 作用点2：在 元素 初始化之前或之后执行某些操作，例如记录日志或验证 元素。</p>
 * <p> 作用点3：修改 元素 实例的属性值，例如对敏感信息进行加密。</p>
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface ElementPostProcessor {

	/**
	 * 声明初始化 前 回调方法。在元素初始化之前调用，允许开发人员修改 元素 实例。
	 * @param element		即将初始化的元素实例对象
	 * @param elementName	元素实例在实例工厂中的名字
	 * @return 初始化前加工过后的实例对象
	 */
	default Object postProcessBeforeInitialization(Object element, String elementName) {
		return element;
	}

	/**
	 * 声明初始化 后 回调方法。在元素初始化之后调用，允许开发人员修改 元素 实例。
	 * @param element		即将初始化的元素实例对象
	 * @param elementName	元素实例在实例工厂中的名字
	 * @return 经过初始化后在加工后的实例对象
	 */
	default Object postProcessAfterInitialization(Object element, String elementName) {
		return element;
	}
}
