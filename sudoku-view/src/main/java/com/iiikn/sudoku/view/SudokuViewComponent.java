package com.iiikn.sudoku.view;

import javax.swing.*;

/**
 * 数独游戏窗体组件类
 */
public abstract class SudokuViewComponent {

	protected final JFrame sudokuFrame;
	protected SudokuViewComponent(JFrame sudokuFrame) {
		this.sudokuFrame = sudokuFrame;
	}

	protected abstract void initialize();
	protected abstract void addToFrame();
}
