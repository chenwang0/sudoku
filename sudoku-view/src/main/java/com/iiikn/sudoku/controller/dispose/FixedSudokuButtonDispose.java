package com.iiikn.sudoku.controller.dispose;

import com.iiikn.annotaion.Piling;
import com.iiikn.sudoku.entity.SudokuButton;

@Piling("fixedSudokuButtonDispose")
public class FixedSudokuButtonDispose implements SudokuButtonDispose {

	@Override
	public void buttonActHandle(SudokuButton button) {

		System.out.printf("%s  - %s \n",button.getR(), button.getC());
	}
}