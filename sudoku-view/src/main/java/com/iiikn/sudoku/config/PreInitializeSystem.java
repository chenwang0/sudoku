package com.iiikn.sudoku.config;

import com.iiikn.annotaion.Configuration;
import com.iiikn.annotaion.Element;
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
@Configuration
public class PreInitializeSystem {


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
