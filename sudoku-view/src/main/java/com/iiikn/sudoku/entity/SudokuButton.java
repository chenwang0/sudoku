package com.iiikn.sudoku.entity;

import com.iiikn.sudoku.enums.SudokuOneselfPropertyEnum;

import java.awt.*;

/**
 * 数独按钮
 * 继承拥有 awt.Button 所有实现
 * 附加程序设计属性：描述按钮属性、按钮坐标。
 */
public class SudokuButton extends Button {

	/**
	 * 描述自身属性
	 */
	SudokuOneselfPropertyEnum oneselfProperty;

	int r;
	int c;

	public SudokuButton() throws HeadlessException {
	}

	public SudokuButton(String label) throws HeadlessException {
		super(label);
	}

	public SudokuOneselfPropertyEnum getOneselfProperty() {
		return oneselfProperty;
	}

	public void setOneselfProperty(SudokuOneselfPropertyEnum oneselfProperty) {
		this.oneselfProperty = oneselfProperty;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "SudokuButton{" +
				"oneselfProperty=" + oneselfProperty +
				", r=" + r +
				", c=" + c +
				'}';
	}
}
