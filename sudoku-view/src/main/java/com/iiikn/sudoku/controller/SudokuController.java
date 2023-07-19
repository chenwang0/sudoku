package com.iiikn.sudoku.controller;

import com.iiikn.annotaion.Seek;
import com.iiikn.annotaion.Piling;
import com.iiikn.sudoku.controller.factory.SudokuShapeFactory;
import com.iiikn.sudoku.controller.factory.SudokuViewFactory;

/**
 * 数独游戏后端控制层
 * 提供初始化模型和开局时刷新游戏视图的能力
 */
@Piling("sudokuController")
public class SudokuController {

	@Seek("sudokuShapeFactory")
	SudokuShapeFactory shapeFactory;
	@Seek("sudokuViewFactory")
	SudokuViewFactory viewFactory;

	public void run() {

		// 步骤1. 初始化模型
		shapeFactory.prepareShape();

		// 步骤2. 刷新窗体视图
		viewFactory.refreshView();
	}
}
