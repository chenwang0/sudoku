package com.iiikn.sudoku.config;

import com.iiikn.annotaion.Element;
import com.iiikn.annotaion.Piling;
import com.iiikn.sudoku.controller.factory.SudokuShapeFactory;
import com.iiikn.sudoku.controller.factory.SudokuViewFactory;
import com.iiikn.sudoku.controller.processor.impl.MonitorArrangementShapePostProcessor;
import com.iiikn.sudoku.controller.processor.impl.OverallArrangementViewPostProcessor;
import com.iiikn.sudoku.service.SudokuService;
import com.iiikn.sudoku.view.SudokuView;
import com.iiikn.sudoku.view.component.SudokuBlockPanelComponent;
import com.iiikn.sudoku.view.component.SudokuOptionButtonComponent;
import com.iiikn.sudoku.view.component.SudokuSysExitButtonComponent;
import com.sudoku.SudokuSolverEnhance;

import javax.swing.*;

/**
 * 预初始化系统环境类（与spring的 @Configuration&@Bean 理念一致）
 * 完成配置注入
 * IOC容器由 sudoku-ioc 外部依赖提供支持
 */
@Piling("preInitializeSystem")
public class PreInitializeSystem {

	@Element("sudokuShapeFactory")
	public SudokuShapeFactory sudokuShapeFactory(
			SudokuShapeFactory sudokuShapeFactory,
			MonitorArrangementShapePostProcessor shapePostProcessor
	) {
		sudokuShapeFactory.registerProcessor(shapePostProcessor);
		return sudokuShapeFactory;
	}


	@Element("sudokuViewFactory")
	public SudokuViewFactory sudokuViewFactory(SudokuViewFactory sudokuViewFactory,
											   OverallArrangementViewPostProcessor postProcessor
	) {
		sudokuViewFactory.registerProcessor(postProcessor);
		return sudokuViewFactory;
	}

	@Element("sudokuSolverEnhance")
	public SudokuSolverEnhance sudokuSolverEnhance() {
		return SudokuSolverEnhance.init();
	}


	@Element("sudokuFrame")
	public JFrame sudokuFrame(){
		return SudokuService.getSudokuFrame();
	}


	@Element("sudokuSwing")
	public SudokuView sudokuSwing(SudokuView sudokuSwing) {
		SudokuBlockPanelComponent sudokuBlockPanelComponent = new SudokuBlockPanelComponent(SudokuService.getSudokuFrame(), SudokuService.getSudokuBlockPanels());
		SudokuOptionButtonComponent sudokuOptionButtonComponent = new SudokuOptionButtonComponent(SudokuService.getSudokuFrame(), SudokuService.getOptionBtn());
		SudokuSysExitButtonComponent sudokuSysExitButtonComponent = new SudokuSysExitButtonComponent(SudokuService.getSudokuFrame());
		sudokuSwing.registerProcessor(sudokuBlockPanelComponent);
		sudokuSwing.registerProcessor(sudokuOptionButtonComponent);
		sudokuSwing.registerProcessor(sudokuSysExitButtonComponent);
		return sudokuSwing;
	}

	/*


	@Late
	@Seek("sudokuBlockPanelComponent")
	SudokuBlockPanelComponent sudokuBlockPanelComponent;

	@Late
	@Seek("sudokuOptionButtonComponent")
	SudokuOptionButtonComponent sudokuOptionButtonComponent;

	@Late
	@Seek("sudokuSysExitButtonComponent")
	SudokuSysExitButtonComponent sudokuSysExitButtonComponent;


	*/
}
