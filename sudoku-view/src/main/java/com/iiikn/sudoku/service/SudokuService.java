package com.iiikn.sudoku.service;

import com.iiikn.sudoku.entity.SudokuBlockPanel;
import com.iiikn.sudoku.entity.SudokuButton;
import com.iiikn.sudoku.enums.SudokuEnum;
import com.iiikn.sudoku.enums.SudokuOneselfPropertyEnum;

import javax.swing.*;

/**
 * 数独服务类
 * 提供全局实例：全局窗体对象、表示数独格子按钮、填数操作按钮
 * 提供外部接口：获取实例、处理实例接口。
 * @author: cw
 * @since: 2023-04-26
 */
public class SudokuService {
	private SudokuService() {}

	/**
	 * 声明定义全局 窗体 对象
	 */
	public static final JFrame SUDOKU_FRAME = new JFrame(SudokuEnum.JFrameTitle.getVal());

	/**
	 * 声明定义游戏内宫面板数组对象（一个元素表示一宫）
	 */
	private static final SudokuBlockPanel[] SUDOKU_BLOCK_PANELS = new SudokuBlockPanel[9];

	/**
	 * 声明定义填充按钮（数组内没一个按钮表示数独中每一格）
	 */
	private static final SudokuButton[][] FILL_BTN = new SudokuButton[9][9];

	/**
	 * 声明定义操作按钮
	 */
	private static final SudokuButton[] OPTION_BTN = new SudokuButton[9];

	/**
	 * mark 如果设计多线程记得加 volatile
	 * 当前选择的填充按钮（即将填数的格）
	 */
	private static SudokuButton currentFillBtn = new SudokuButton();

	// 初始化按钮块
	static {
		// 初始填充块按钮
		for (int i = 0; i < SudokuService.FILL_BTN.length; i++){
			for (int j = 0; j < SudokuService.FILL_BTN[i].length; j++){
				SudokuService.FILL_BTN[i][j] = new SudokuButton();
				SudokuService.FILL_BTN[i][j].setR(i);
				SudokuService.FILL_BTN[i][j].setC(j);
			}
		}
		// 初始操作按钮块
		for (int i = 0; i < SudokuService.OPTION_BTN.length; i++) {
			SudokuService.OPTION_BTN[i] = new SudokuButton(String.valueOf(i+1));
			SudokuService.OPTION_BTN[i].setOneselfProperty(SudokuOneselfPropertyEnum.OPTION);
			SudokuService.OPTION_BTN[i].setC(i);
		}
	}


	/**
	 * 提供对外修改 填充数按钮接口
	 * @param ssFunction 回调方法
	 */
	public static synchronized void foreachFillButton(SudokuServiceFunction<SudokuButton> ssFunction) {
		for (SudokuButton[] sudokuButtons : SudokuService.FILL_BTN) {
			for (SudokuButton sudokuButton : sudokuButtons) {
				ssFunction.execute(sudokuButton);
			}
		}
	}

	/**
	 * 提供对外 操作按钮按钮接口
	 * @param ssFunction 回调方法
	 */
	public static synchronized void foreachOptionButton(SudokuServiceFunction<SudokuButton> ssFunction) {
		for (SudokuButton sudokuButton : SudokuService.OPTION_BTN) {
			ssFunction.execute(sudokuButton);
		}
	}


	/**
	 * 提供对外操作 宫数组对象接口
	 * @param ssFunction 回调
	 */
	public static synchronized void foreachBlockPanel(SudokuServiceFunction<SudokuBlockPanel> ssFunction) {
		for (SudokuBlockPanel sudokuBlockPanel : SudokuService.SUDOKU_BLOCK_PANELS) {
			ssFunction.execute(sudokuBlockPanel);
		}
	}



	public static synchronized JFrame getSudokuFrame() {
		return SUDOKU_FRAME;
	}

	public static SudokuButton[][] getFillBtn() {
		return FILL_BTN;
	}

	public static SudokuButton[] getOptionBtn() {
		return OPTION_BTN;
	}

	public static SudokuButton getCurrentFillBtn() {
		return currentFillBtn;
	}

	public static void setCurrentFillBtn(SudokuButton currentFillBtn) {
		SudokuService.currentFillBtn = currentFillBtn;
	}

	public static SudokuBlockPanel[] getSudokuBlockPanels() {
		return SUDOKU_BLOCK_PANELS;
	}
}
