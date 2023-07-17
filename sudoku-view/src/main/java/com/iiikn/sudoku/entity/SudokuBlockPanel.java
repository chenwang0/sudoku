package com.iiikn.sudoku.entity;

import com.iiikn.sudoku.enums.BlockEnum;
import com.iiikn.sudoku.enums.SudokuEnum;
import com.iiikn.sudoku.service.SudokuService;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * 数独宫面板对象
 */
public class SudokuBlockPanel extends JPanel {

	/**
	 * 宫名
	 */
	private String blockName;
	/**
	 * 当前宫的枚举
	 */
	private BlockEnum blockEnum;
	/**
	 * 定义该宫内的9个按钮
	 */
	private SudokuButton[] containSudokuButton;

	public SudokuBlockPanel() {}

	public SudokuBlockPanel(BlockEnum blockEnum) {
		this.blockEnum = blockEnum;
		this.blockName = blockEnum.toString();
		this.createBlock(blockEnum);
	}

	public void createBlock(BlockEnum blockEnum) {
		containSudokuButton = new SudokuButton[9];

		super.setLayout(null);
		super.setBounds(blockEnum.getX(), blockEnum.getY(),
				 SudokuEnum.DEF_FILL_PANEL_SIZE +2, SudokuEnum.DEF_FILL_PANEL_SIZE+2);
		super.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		int row = 0;
		int col = 0;
		for (int i = 0,j = 1; i < blockEnum.getIdx().length; i++, j++) {
			BlockEnum.Index idx = blockEnum.getIdx()[i];
			containSudokuButton[i] = SudokuService.getFillBtn()[idx.r][idx.c];
			containSudokuButton[i].setBounds(
					row++ * SudokuEnum.DEF_FILL_BTN_SIZE +1,
					col * SudokuEnum.DEF_FILL_BTN_SIZE+1,
					SudokuEnum.DEF_FILL_BTN_SIZE,
					SudokuEnum.DEF_FILL_BTN_SIZE
			);

			super.add(containSudokuButton[i]);

			if (j % 3 == 0) {
				row = 0;
				col++;
			}
		}
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public BlockEnum getBlockEnum() {
		return blockEnum;
	}

	public void setBlockEnum(BlockEnum blockEnum) {
		this.blockEnum = blockEnum;
	}

	public SudokuButton[] getContainSudokuButton() {
		return containSudokuButton;
	}

	public void setContainSudokuButton(SudokuButton[] containSudokuButton) {
		this.containSudokuButton = containSudokuButton;
	}

	@Override
	public String toString() {
		return "SudokuBlockPanel{" +
				"blockName='" + blockName + '\'' +
				", blockEnum=" + blockEnum +
				", containSudokuButton=" + Arrays.toString(containSudokuButton) +
				'}';
	}
}
