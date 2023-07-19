package com.iiikn.processor;

import com.iiikn.factory.DefaultElementFactory;

public class AnnotationLatePostProcessor implements ElementPostProcessor {

	@Override
	public void registerElement(String primaryPath, DefaultElementFactory elementFactory) {
		elementFactory.getElementMark().forEach(lateElement -> {
			Object element = elementFactory.getElement(lateElement.getLateElementName());
			lateElement.getLateElementField().setAccessible(true);
			try {
				lateElement.getLateElementField().set(lateElement.getSeekElement(), element);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			lateElement.getLateElementField().setAccessible(false);
		});

	}
}
