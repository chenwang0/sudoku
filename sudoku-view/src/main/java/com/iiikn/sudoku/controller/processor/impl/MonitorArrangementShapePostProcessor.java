package com.iiikn.sudoku.controller.processor.impl;

import com.iiikn.annotaion.Autowired;
import com.iiikn.annotaion.Component;
import com.iiikn.sudoku.controller.dispose.FillSudokuButtonDispose;
import com.iiikn.sudoku.controller.dispose.FixedSudokuButtonDispose;
import com.iiikn.sudoku.controller.dispose.OptionSudokuButtonDispose;
import com.iiikn.sudoku.controller.dispose.SudokuButtonDispose;
import com.iiikn.sudoku.controller.processor.ShapePostProcessor;
import com.iiikn.sudoku.entity.SudokuButton;
import com.iiikn.sudoku.service.SudokuService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 模型增强处理器之 监听
 * ShapePostProcessor：提供处理sudoku系统后台模型能力，在此类中主要工作是给按钮添加监听器
 * ActionListener: 提供处理按钮监听能力，在此类中只做转发任务。
 */
@Component
public class MonitorArrangementShapePostProcessor implements ShapePostProcessor, ActionListener {

	@Autowired
	FillSudokuButtonDispose fillSudokuButtonDispose;
	@Autowired
	FixedSudokuButtonDispose fixedSudokuButtonDispose;
	@Autowired
	OptionSudokuButtonDispose optionSudokuButtonDispose;

	@Override
	public void prepareShape() {
		// 声明监听器
		MonitorArrangementShapePostProcessor postProcessor = this;
		// 给填充按钮添加监听器
		SudokuService.foreachFillButton(btn -> btn.addActionListener(postProcessor));
		// 添加操作填入按钮添加监听器
		SudokuService.foreachOptionButton(btn -> btn.addActionListener(postProcessor));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Button actBtn =  e.getSource() instanceof SudokuButton ? (Button) e.getSource() : null;
		if (actBtn == null) {
			return;
		}

		SudokuButton sudokuButton = (SudokuButton) actBtn;
		switch (sudokuButton.getOneselfProperty()){
			// 调用 空填按钮点击监听处理实现
			case FILL: fillSudokuButtonDispose.buttonActHandle(sudokuButton);
				break;
			// 调用 系统提示按钮点击监听处理实现
			case FIXED: fixedSudokuButtonDispose.buttonActHandle(sudokuButton);
				break;
			// 调用 输入操作按钮点击监听处理实现
			case OPTION: optionSudokuButtonDispose.buttonActHandle(sudokuButton);
				break;
			default: throw new IllegalArgumentException("system not find Dispose.");
		}

		// 点击刷新高亮高亮
		SudokuButtonDispose.replace(sudokuButton);
	}
}
