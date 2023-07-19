package com.iiikn.sudoku.controller.factory;

/**
 * 数独扩展工厂接口
 * @param <T> 声明扩展泛型
 */
public interface SudokuFactory<T> {

	/**
	 * 通过完整实例注册接口
	 * @param processor 一个已经被实例化的对象
	 */
	void registerProcessor(T processor);

	/**
	 * 通过实例对象的class注册接口 (由实现完成实例化)
	 * @param processorClass 即将注入实例对象的clazz
	 */
	void registerProcessor(Class<? extends T> processorClass);

	/**
	 * 移除一个扩展实例
	 * @param clazz 移除扩展实例的clazz对象
	 */
	void removeProcessor(Class<? extends T> clazz);

}
