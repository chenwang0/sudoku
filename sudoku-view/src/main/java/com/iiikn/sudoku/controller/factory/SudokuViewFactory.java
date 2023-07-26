package com.iiikn.sudoku.controller.factory;

import com.iiikn.annotaion.Component;
import com.iiikn.sudoku.controller.processor.ViewPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 数独视图处理工厂类
 * 目前只有一个扩展类：{@link com.iiikn.sudoku.controller.processor.impl.OverallArrangementViewPostProcessor}
 * 此类使用 模版设计模式之模版模式提高程序扩展
 */
@Component
public class SudokuViewFactory implements SudokuFactory<ViewPostProcessor>, ViewPostProcessor {

	private final List<ViewPostProcessor> viewPostProcessorList = new ArrayList<>(0);

	@Override
	public void refreshView() {
		for (ViewPostProcessor viewPostProcessor : viewPostProcessorList) {
			viewPostProcessor.refreshView();
		}
	}

	@Override
	public void registerProcessor(ViewPostProcessor processor) {

		Objects.requireNonNull(processor, "注册元素不可为空");
		this.removeProcessor(processor.getClass());
		this.viewPostProcessorList.add(processor);
	}

	@Override
	public void registerProcessor(Class<? extends ViewPostProcessor> processorClass) {

		Objects.requireNonNull(processorClass, "注册元素不可为空");
		this.removeProcessor(processorClass);
		try {
			this.viewPostProcessorList.add(processorClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeProcessor(Class<? extends ViewPostProcessor> clazz) {
		Objects.requireNonNull(clazz, "删除元素不可为空");
		this.viewPostProcessorList.removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
	}

}
