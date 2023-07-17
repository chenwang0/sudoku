package com.iiikn.sudoku.enums;

/**
 * 宫枚举
 * 用于记录每宫内按钮下标
 * 和每宫在面板上的位置（坐标：x,y）
 */
public enum BlockEnum {

	B1(
			SudokuEnum.PANEL_MARGIN_LEFT ,
			SudokuEnum.PANEL_MARGIN_TOP,
			new Index[]{
			new Index(0,0), new Index(0,1), new Index(0,2),
			new Index(1,0), new Index(1,1), new Index(1,2),
			new Index(2,0), new Index(2,1), new Index(2,2),
	}),

	B2(
			SudokuEnum.PANEL_MARGIN_LEFT + SudokuEnum.DEF_FILL_PANEL_SIZE ,
			SudokuEnum.PANEL_MARGIN_TOP,
			new Index[]{
			new Index(0,3), new Index(0,4), new Index(0,5),
			new Index(1,3), new Index(1,4), new Index(1,5),
			new Index(2,3), new Index(2,4), new Index(2,5),
	}),
	B3(
			SudokuEnum.PANEL_MARGIN_LEFT + SudokuEnum.DEF_FILL_PANEL_SIZE * 2,
			SudokuEnum.PANEL_MARGIN_TOP,
			new Index[]{
			new Index(0,6), new Index(0,7), new Index(0,8),
			new Index(1,6), new Index(1,7), new Index(1,8),
			new Index(2,6), new Index(2,7), new Index(2,8),
	}),

	// ============================ 第二行
	B4(
			SudokuEnum.PANEL_MARGIN_LEFT ,
			SudokuEnum.PANEL_MARGIN_TOP + SudokuEnum.DEF_FILL_PANEL_SIZE,
			new Index[]{
			new Index(3,0), new Index(3,1), new Index(3,2),
			new Index(4,0), new Index(4,1), new Index(4,2),
			new Index(5,0), new Index(5,1), new Index(5,2),
	}),
	B5(
			SudokuEnum.PANEL_MARGIN_LEFT + SudokuEnum.DEF_FILL_PANEL_SIZE,
			SudokuEnum.PANEL_MARGIN_TOP + SudokuEnum.DEF_FILL_PANEL_SIZE,
			new Index[]{
			new Index(3,3), new Index(3,4), new Index(3,5),
			new Index(4,3), new Index(4,4), new Index(4,5),
			new Index(5,3), new Index(5,4), new Index(5,5),
	}),
	B6(
			SudokuEnum.PANEL_MARGIN_LEFT + SudokuEnum.DEF_FILL_PANEL_SIZE * 2,
			SudokuEnum.PANEL_MARGIN_TOP + SudokuEnum.DEF_FILL_PANEL_SIZE,
			new Index[]{
			new Index(3,6), new Index(3,7), new Index(3,8),
			new Index(4,6), new Index(4,7), new Index(4,8),
			new Index(5,6), new Index(5,7), new Index(5,8),
	}),

	// ============================ 第三行
	B7(
			SudokuEnum.PANEL_MARGIN_LEFT,
			SudokuEnum.PANEL_MARGIN_TOP + SudokuEnum.DEF_FILL_PANEL_SIZE * 2,
			new Index[]{
			new Index(6,0), new Index(6,1), new Index(6,2),
			new Index(7,0), new Index(7,1), new Index(7,2),
			new Index(8,0), new Index(8,1), new Index(8,2),
	}),
	B8(
			SudokuEnum.PANEL_MARGIN_LEFT + SudokuEnum.DEF_FILL_PANEL_SIZE,
			SudokuEnum.PANEL_MARGIN_TOP + SudokuEnum.DEF_FILL_PANEL_SIZE * 2,
			new Index[]{
			new Index(6,3), new Index(6,4), new Index(6,5),
			new Index(7,3), new Index(7,4), new Index(7,5),
			new Index(8,3), new Index(8,4), new Index(8,5),
	}),
	B9(
			SudokuEnum.PANEL_MARGIN_LEFT + SudokuEnum.DEF_FILL_PANEL_SIZE * 2,
			SudokuEnum.PANEL_MARGIN_TOP + SudokuEnum.DEF_FILL_PANEL_SIZE * 2,
			new Index[]{
			new Index(6,6), new Index(6,7), new Index(6,8),
			new Index(7,6), new Index(7,7), new Index(7,8),
			new Index(8,6), new Index(8,7), new Index(8,8),
	})
	;


	private final Index[] idx;
	private final int x;
	private final int y;

	BlockEnum(int x, int y, Index[] idx) {
		this.x=x;
		this.y=y;
		this.idx = idx;
	}

	public Index[] getIdx() {
		return idx;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * 用于标记按钮下标
	 */
	public static class Index {
		public int r;
		public int c;

		public Index(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
