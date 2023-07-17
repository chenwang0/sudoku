package com.iiikn.sudoku.enums;

import java.awt.*;

/**
 * 常量或枚举类
 */
public enum SudokuEnum {

	JFrameTitle("sudoku"),
	BtnDefaultLabel(" ");


	private String val;

	SudokuEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

	public static final Font notFillFont = new Font("微软雅黑",Font.PLAIN, 10);
	public static final Font ownFillFont = new Font("微软雅黑", Font.BOLD, 10);
	public static final Color sysDefaultColor = new Color(220, 220, 220);
	public static final Color highLightColor = new Color(190, 190, 190);
	public static final Color fillDefaultColor = new Button().getBackground();

	/**
	 * 一格的默认size
	 */
	public static final int DEF_FILL_BTN_SIZE = 35;

	/**
	 * 一宫默认的size
	 */
	public static final int DEF_FILL_PANEL_SIZE = DEF_FILL_BTN_SIZE * 3;


	/**
	 * 面板（第一列的宫）较窗体左边距
	 */
	public static final int PANEL_MARGIN_LEFT = 25;
	/**
	 * 面板（第一行的宫）与窗体顶部的距离
	 */
	public static final int PANEL_MARGIN_TOP = 50;



}
