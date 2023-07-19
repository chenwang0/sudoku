package com.iiikn.sudoku.controller.processor.impl;

import com.iiikn.annotaion.Late;
import com.iiikn.annotaion.Piling;
import com.iiikn.annotaion.Seek;
import com.iiikn.sudoku.controller.processor.ViewPostProcessor;
import com.iiikn.sudoku.enums.SudokuEnum;
import com.iiikn.sudoku.enums.SudokuOneselfPropertyEnum;
import com.iiikn.sudoku.service.SudokuService;
import com.sudoku.SudokuSolverEnhance;

/**
 * 模型增强处理器之 布局
 */
@Piling("overallArrangementViewPostProcessor")
public class OverallArrangementViewPostProcessor implements ViewPostProcessor {

	@Late
	@Seek("sudokuSolverEnhance")
	SudokuSolverEnhance sudokuSolverBuild;

	@Override
	public void refreshView() {

		this.sudokuSolverBuild.generateShape().upset(30);
		this.sudokuSolverBuild.printSolution();

		for (int i = 0; i < this.sudokuSolverBuild.sudoku.length; i++) {
			for (int j = 0; j < this.sudokuSolverBuild.sudoku[i].length; j++) {

				if (this.sudokuSolverBuild.sudoku[i][j] == 0) {
					SudokuService.getFillBtn()[i][j].setLabel(SudokuEnum.BtnDefaultLabel.getVal());
					SudokuService.getFillBtn()[i][j].setFont(SudokuEnum.notFillFont);
					SudokuService.getFillBtn()[i][j].setOneselfProperty(SudokuOneselfPropertyEnum.FILL);
				} else {
					SudokuService.getFillBtn()[i][j].setLabel(String.valueOf(sudokuSolverBuild.sudoku[i][j]));
					SudokuService.getFillBtn()[i][j].setBackground(SudokuEnum.sysDefaultColor);
					SudokuService.getFillBtn()[i][j].setFont(SudokuEnum.ownFillFont);
					SudokuService.getFillBtn()[i][j].setOneselfProperty(SudokuOneselfPropertyEnum.FIXED);
				}
			}
		}
	}
}
