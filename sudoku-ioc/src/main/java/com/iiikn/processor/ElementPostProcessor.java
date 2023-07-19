package com.iiikn.processor;

import com.iiikn.factory.DefaultElementFactory;

public interface ElementPostProcessor {

	/**
	 * 注册 Element
	 */
	void registerElement(String primaryPath, DefaultElementFactory elementFactory);
}
