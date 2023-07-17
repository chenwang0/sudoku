package com.iiikn;

import com.iiikn.core.ApplicationContext;
import com.iiikn.core.SudokuApplicationContext;
import com.iiikn.factory.SudokuElementFactory;

public class SudokuApplication {
	private SudokuApplication() { }

	public static ApplicationContext run(Class<?> clazz) {
		SudokuElementFactory sudokuElementFactory = new SudokuElementFactory();
		ApplicationContext sudokuApplicationContext = new SudokuApplicationContext(clazz, sudokuElementFactory);
		sudokuApplicationContext.init();
		sudokuApplicationContext.refresh();
		return sudokuApplicationContext;
	}
}
