package com.iiikn.sudoku.view.component;

import com.iiikn.sudoku.entity.SudokuBlockPanel;
import com.iiikn.sudoku.enums.BlockEnum;
import com.iiikn.sudoku.enums.SudokuEnum;
import com.iiikn.sudoku.service.SudokuService;
import com.iiikn.sudoku.view.SudokuViewComponent;

import javax.swing.*;
import java.awt.*;

public class SudokuBlockPanelComponent extends SudokuViewComponent {

	private final SudokuBlockPanel[] sudokuBlockPanels;
	public SudokuBlockPanelComponent(JFrame sudokuFrame, SudokuBlockPanel[] sudokuBlockPanels) {
		super(sudokuFrame);
		this.sudokuBlockPanels = sudokuBlockPanels;
	}

	@Override
	public void initialize() {
		int idx = 0;
		for (BlockEnum blockEnum : BlockEnum.values()) {
			this.sudokuBlockPanels[idx++] = new SudokuBlockPanel(blockEnum);
		}
	}

	@Override
	public void addToFrame() {
		for (SudokuBlockPanel sudokuBlockPanel : this.sudokuBlockPanels) {
			super.sudokuFrame.add(sudokuBlockPanel);
		}
	}

	private void placeComponents(Panel panel) {
		panel.setLayout(null);

		panel.setBounds(25 , 50, sudokuFrame.getWidth() - 50,500);

		SudokuService.foreachFillButton( btn-> {

			int row = btn.getR() * SudokuEnum.DEF_FILL_BTN_SIZE;
			int col = btn.getC() * SudokuEnum.DEF_FILL_BTN_SIZE;

			btn.setBounds(row, col, SudokuEnum.DEF_FILL_BTN_SIZE, SudokuEnum.DEF_FILL_BTN_SIZE);

			panel.add(btn);
		});

		int y = SudokuEnum.DEF_FILL_BTN_SIZE * 9 + 50;
		SudokuService.foreachOptionButton(btn -> {

			btn.setFont(new Font("Calibri", Font.BOLD, 25));
			btn.setBounds(btn.getC() * SudokuEnum.DEF_FILL_BTN_SIZE, y, SudokuEnum.DEF_FILL_BTN_SIZE -3, SudokuEnum.DEF_FILL_BTN_SIZE-3);
			panel.add(btn);
		});
	}


}
