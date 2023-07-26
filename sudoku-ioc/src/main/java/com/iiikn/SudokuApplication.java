package com.iiikn;

import com.iiikn.core.ApplicationContext;
import com.iiikn.core.SudokuApplicationContext;
import com.iiikn.core.analysis.AnnotationInjectResolverClassAnalysis;
import com.iiikn.core.analysis.ComponentAnnotationClassAnalysis;
import com.iiikn.core.analysis.ConfigurationAnnotationClassAnalysis;
import com.iiikn.core.analysis.ElementFactoryAwareClassAnalysis;
import com.iiikn.core.resovler.AnnotationInjectFactory;
import com.iiikn.factory.DefaultElementFactory;
import com.iiikn.factory.DefaultSudokuElementFactory;
import com.iiikn.factory.SudokuElementFactory;
import com.iiikn.factory.processor.*;

public class SudokuApplication {

	private SudokuApplication() {}

	public static ApplicationContext run(Class<?> primarySource) {
		SudokuElementFactory elementFactory = buildElementFactory();
		ApplicationContext context = buildApplicationContext(primarySource, elementFactory);
		context.initialize();
		context.refresh();
		return context;
	}

	private static ApplicationContext buildApplicationContext(Class<?> primarySource, DefaultElementFactory elementFactory) {

		ApplicationContext sudokuApplicationContext = new SudokuApplicationContext(primarySource, elementFactory);

		sudokuApplicationContext.registerExtender(AnnotationInjectResolverClassAnalysis.class);
		sudokuApplicationContext.registerExtender(ElementFactoryAwareClassAnalysis.class);
		sudokuApplicationContext.registerExtender(ComponentAnnotationClassAnalysis.class);
		sudokuApplicationContext.registerExtender(ConfigurationAnnotationClassAnalysis.class);

		return sudokuApplicationContext;
	}

	private static SudokuElementFactory buildElementFactory() {

		SudokuElementFactory sudokuElementFactory = new DefaultSudokuElementFactory();
		// 添加依赖注入工厂
		AnnotationInjectFactory.embedElementFactory(sudokuElementFactory);
		// 添加工厂后处理器工厂
		ElementFactoryPostProcessorFactory.embedElementFactory(sudokuElementFactory);
		// 添加后置处理器
		ElementPostProcessorFactory.embedElementFactory(sudokuElementFactory);

		// 添加 工厂 后处理器
		sudokuElementFactory.registerExtender(ConfigurationAnnotationPostProcessor.class);
		sudokuElementFactory.registerExtender(ElementAnnotationPostProcessor.class);
		return sudokuElementFactory;
	}
}
