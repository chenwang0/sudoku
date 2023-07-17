package com.iiikn.sudoku.view;

import com.iiikn.annotaion.*;
import com.iiikn.annotaion.Component;
import com.iiikn.sudoku.controller.factory.SudokuFactory;
import com.iiikn.sudoku.enums.SudokuEnum;
import com.iiikn.sudoku.service.SudokuService;
import com.iiikn.sudoku.view.component.SudokuBlockPanelComponent;
import com.iiikn.sudoku.view.component.SudokuOptionButtonComponent;
import com.iiikn.sudoku.view.component.SudokuSysExitButtonComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SudokuView implements SudokuFactory<SudokuViewComponent> {

	List<SudokuViewComponent> sudokuViewComponentList = new ArrayList<>(0);

	public SudokuView() {
		SudokuBlockPanelComponent sudokuBlockPanelComponent = new SudokuBlockPanelComponent(SudokuService.getSudokuFrame(), SudokuService.getSudokuBlockPanels());
		SudokuOptionButtonComponent sudokuOptionButtonComponent = new SudokuOptionButtonComponent(SudokuService.getSudokuFrame(), SudokuService.getOptionBtn());
		SudokuSysExitButtonComponent sudokuSysExitButtonComponent = new SudokuSysExitButtonComponent(SudokuService.getSudokuFrame());
		this.registerProcessor(sudokuBlockPanelComponent);
		this.registerProcessor(sudokuOptionButtonComponent);
		this.registerProcessor(sudokuSysExitButtonComponent);
	}

//	@Autowired
	JFrame sudokuFrame = SudokuService.getSudokuFrame();

	public void initialize() {
		// 消除边框
		sudokuFrame.setUndecorated(true);
		sudokuFrame.setAlwaysOnTop(true);

		// 创建及设置窗口
		sudokuFrame.pack();
		sudokuFrame.setBackground(Color.white);
		sudokuFrame.setLayout(null);
		sudokuFrame.setBounds(600, 100, SudokuEnum.DEF_FILL_BTN_SIZE * 9 + 50, 550);
		sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		for (SudokuViewComponent sudokuViewComponent : sudokuViewComponentList) {
			sudokuViewComponent.initialize();
			sudokuViewComponent.addToFrame();
		}
	}

	public void show() {
		this.initialize();
		this.sudokuFrame.setVisible(true);
	}

	@Override
	public void registerProcessor(SudokuViewComponent processor) {
		Objects.requireNonNull(processor, "注册元素不可为空");
		this.removeProcessor(processor.getClass());
		this.sudokuViewComponentList.add(processor);
	}

	@Override
	public void registerProcessor(Class<? extends SudokuViewComponent> processorClass) {
		Objects.requireNonNull(processorClass, "注册元素不可为空");
		this.removeProcessor(processorClass);
		try {
			this.sudokuViewComponentList.add(processorClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeProcessor(Class<? extends SudokuViewComponent> clazz) {

		Objects.requireNonNull(clazz, "删除元素不可为空");
		this.sudokuViewComponentList.removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
	}

}
