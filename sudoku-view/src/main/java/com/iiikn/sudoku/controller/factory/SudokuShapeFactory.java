package com.iiikn.sudoku.controller.factory;

import com.iiikn.annotaion.Component;
import com.iiikn.sudoku.controller.processor.ShapePostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * 与 {@link SudokuViewFactory} 同理
 */
@Component
public class SudokuShapeFactory implements SudokuFactory<ShapePostProcessor>, ShapePostProcessor {

	private final List<ShapePostProcessor> shapePostProcessorList = new ArrayList<>(0);

	@Override
	public void prepareShape() {
		for (ShapePostProcessor shapePostProcessor : shapePostProcessorList) {
			shapePostProcessor.prepareShape();
		}
	}

	@Override
	public void registerProcessor(ShapePostProcessor processor) {
		Objects.requireNonNull(processor, "注册元素不可为空");
		this.removeProcessor(processor.getClass());
		this.shapePostProcessorList.add(processor);
	}

	@Override
	public void registerProcessor(Class<? extends ShapePostProcessor> processorClass) {
		Objects.requireNonNull(processorClass, "注册元素不可为空");
		this.removeProcessor(processorClass);
		try {
			this.shapePostProcessorList.add(processorClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeProcessor(Class<? extends ShapePostProcessor> clazz) {
		Objects.requireNonNull(clazz, "删除元素不可为空");
		this.shapePostProcessorList.removeIf( processor -> processor.getClass().isAssignableFrom(clazz));
	}

}
