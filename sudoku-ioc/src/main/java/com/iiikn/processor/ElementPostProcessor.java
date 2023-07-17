package com.iiikn.processor;

import com.iiikn.factory.DefaultElementFactory;

public interface ElementPostProcessor {


	default Object postProcessBeforeInitialization(Object bean, String elementName) {
		return bean;
	}

	default Object postProcessAfterInitialization(Object bean, String elementName) {
		return bean;
	}
}
