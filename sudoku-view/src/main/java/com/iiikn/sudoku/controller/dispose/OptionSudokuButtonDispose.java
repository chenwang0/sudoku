package com.iiikn.sudoku.controller.dispose;

import com.iiikn.annotaion.Late;
import com.iiikn.annotaion.Piling;
import com.iiikn.annotaion.Seek;
import com.iiikn.sudoku.entity.SudokuButton;
import com.iiikn.sudoku.service.SudokuService;
import com.sudoku.SudokuSolverEnhance;

/**
 * 操作按钮点击事件处理实现类
 */
@Piling("optionSudokuButtonDispose")
public class OptionSudokuButtonDispose implements SudokuButtonDispose {

	@Late
	@Seek("sudokuSolverEnhance")
	SudokuSolverEnhance sudokuSolverEnhance;

	@Override
	public void buttonActHandle(SudokuButton button) {
		if (SudokuService.getCurrentFillBtn() == null){
			return;
		}

		boolean refresh = sudokuSolverEnhance.isValid(button.getR(), button.getC(), Integer.parseInt(button.getLabel()));

		System.out.println(refresh);
		SudokuService.getCurrentFillBtn().setLabel(button.getLabel());
		System.out.println(button.getOneselfProperty());
	}
}