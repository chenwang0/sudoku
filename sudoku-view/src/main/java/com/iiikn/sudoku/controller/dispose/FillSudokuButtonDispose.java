package com.iiikn.sudoku.controller.dispose;

import com.iiikn.annotaion.Component;
import com.iiikn.sudoku.entity.SudokuButton;
import com.iiikn.sudoku.service.SudokuService;

@Component
public class FillSudokuButtonDispose implements SudokuButtonDispose {

	@Override
	public void buttonActHandle(SudokuButton button) {
		SudokuService.setCurrentFillBtn(button);
		System.setProperty("currentKey",button.getLabel());
		System.out.printf("%s  - %s \n",button.getR(), button.getC());
	}
}
