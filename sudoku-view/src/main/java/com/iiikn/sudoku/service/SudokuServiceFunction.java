package com.iiikn.sudoku.service;

/**
 * 对象操作回调实现拉姆达
 * @param <T>
 */
@FunctionalInterface
public interface SudokuServiceFunction<T> {

	void execute(T param);
}
