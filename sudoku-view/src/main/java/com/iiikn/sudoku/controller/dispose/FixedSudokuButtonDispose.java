package com.iiikn.sudoku.controller.dispose;

import com.iiikn.annotaion.Component;
import com.iiikn.sudoku.entity.SudokuButton;

@Component
public class FixedSudokuButtonDispose implements SudokuButtonDispose {

	@Override
	public void buttonActHandle(SudokuButton button) {

		System.out.printf("%s  - %s \n",button.getR(), button.getC());
	}
}