package com.iiikn.sudoku.controller.dispose;

import com.iiikn.sudoku.entity.SudokuButton;
import com.iiikn.sudoku.enums.SudokuEnum;
import com.iiikn.sudoku.enums.SudokuOneselfPropertyEnum;
import com.iiikn.sudoku.service.SudokuService;

public interface SudokuButtonDispose {

	void buttonActHandle(SudokuButton button);

	static void replace(SudokuButton sudokuButtonAct){

		String label = sudokuButtonAct.getLabel();
		// 处理 点击系统按钮高亮显示
		SudokuService.foreachFillButton(btn -> {
			if (label.equals(btn.getLabel())) {
				// 将这个按钮颜色为高亮显示
				btn.setBackground(SudokuEnum.highLightColor);
			} else if (SudokuOneselfPropertyEnum.FIXED.equals(btn.getOneselfProperty())) {
				if (SudokuEnum.highLightColor.equals(btn.getBackground())) {
					// 消除上一次标记高亮的按钮 系统按钮则回复默认颜色
					btn.setBackground(SudokuEnum.sysDefaultColor);
				}
			} else {
				// 默认按钮恢复白色
				btn.setBackground(SudokuEnum.fillDefaultColor);
			}
		});
	}
}
