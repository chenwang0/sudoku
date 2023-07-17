package com.iiikn.sudoku.controller;

import com.iiikn.annotaion.Autowired;
import com.iiikn.annotaion.Component;
import com.iiikn.annotaion.Seek;
import com.iiikn.annotaion.Piling;
import com.iiikn.sudoku.controller.factory.SudokuShapeFactory;
import com.iiikn.sudoku.controller.factory.SudokuViewFactory;

/**
 * 数独游戏后端控制层
 * 提供初始化模型和开局时刷新游戏视图的能力
 */
@Component
public class SudokuController {

	@Autowired
	SudokuShapeFactory shapeFactory;
	@Autowired
	SudokuViewFactory viewFactory;

	public void run() {

		// 步骤1. 初始化模型
		shapeFactory.prepareShape();

		// 步骤2. 刷新窗体视图
		viewFactory.refreshView();
	}
}
