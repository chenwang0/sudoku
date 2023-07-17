package com.iiikn.core;

import com.iiikn.factory.ElementFactory;
import com.iiikn.processor.ElementPostProcessor;

public interface ApplicationContext extends ElementFactory {

	void registerProcessor(ElementPostProcessor processor);

	void registerProcessor(Class<? extends ElementPostProcessor> processorClass);

	void removeProcessor(Class<? extends ElementPostProcessor> clazz);

	void init();

	void refresh();

	void close();

}
