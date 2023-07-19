package com.iiikn.sudoku.view.component;

import com.iiikn.sudoku.entity.SudokuButton;
import com.iiikn.sudoku.enums.SudokuEnum;
import com.iiikn.sudoku.service.SudokuService;
import com.iiikn.sudoku.view.SudokuViewComponent;

import javax.swing.*;
import java.awt.*;

/**
 * 数独游戏中填数操作区域组件
 */
public class SudokuOptionButtonComponent extends SudokuViewComponent {

	private final SudokuButton[] sudokuButtons;
	public SudokuOptionButtonComponent(JFrame sudokuFrame, SudokuButton[] sudokuButtons) {
		super(sudokuFrame);
		this.sudokuButtons = sudokuButtons;
	}

	@Override
	public void initialize() {
		int y = SudokuEnum.DEF_FILL_PANEL_SIZE * 3 + 90;
		for (SudokuButton sudokuButton : sudokuButtons) {
			sudokuButton.setFont(new Font("Calibri", Font.BOLD, 25));
			sudokuButton.setBounds(sudokuButton.getC() * SudokuEnum.DEF_FILL_BTN_SIZE + SudokuEnum.PANEL_MARGIN_LEFT, y,
					SudokuEnum.DEF_FILL_BTN_SIZE - 3,
					SudokuEnum.DEF_FILL_BTN_SIZE -3);
			sudokuFrame.add(sudokuButton);
		}
	}

	@Override
	public void addToFrame() {
		SudokuService.foreachOptionButton(btn -> {});
	}
}
