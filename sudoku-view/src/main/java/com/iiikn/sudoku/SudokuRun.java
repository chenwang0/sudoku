package com.iiikn.sudoku;

import com.iiikn.SudokuApplication;
import com.iiikn.core.ApplicationContext;
import com.iiikn.sudoku.controller.SudokuController;
import com.iiikn.sudoku.view.SudokuView;

public class SudokuRun {
	public static void main(String[] args) {
		ApplicationContext context = SudokuApplication.run(SudokuRun.class);
		SudokuController sudokuController = context.getElement(SudokuController.class);
		SudokuView sudokuView = context.getElement(SudokuView.class);
		sudokuController.run();
		sudokuView.show();
	}
}
