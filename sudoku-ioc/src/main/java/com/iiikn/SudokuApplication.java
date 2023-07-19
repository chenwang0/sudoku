package com.iiikn;

import com.iiikn.core.ApplicationContext;
import com.iiikn.core.SudokuApplicationContext;
import com.iiikn.factory.SudokuElementFactory;
import com.iiikn.processor.AnnotationElementPostProcessor;
import com.iiikn.processor.AnnotationLatePostProcessor;
import com.iiikn.processor.AnnotationPilingPostProcessor;

public class SudokuApplication {
	private SudokuApplication() { }

	public static ApplicationContext run(Class<?> clazz) {
		SudokuElementFactory sudokuElementFactory = new SudokuElementFactory();
		ApplicationContext sudokuApplicationContext = new SudokuApplicationContext(clazz, sudokuElementFactory);
		sudokuApplicationContext.registerProcessor(AnnotationPilingPostProcessor.class);
		sudokuApplicationContext.registerProcessor(AnnotationElementPostProcessor.class);
		sudokuApplicationContext.registerProcessor(AnnotationLatePostProcessor.class);
		sudokuApplicationContext.refresh();
		return sudokuApplicationContext;
	}
}
