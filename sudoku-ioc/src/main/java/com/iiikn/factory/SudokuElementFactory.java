package com.iiikn.factory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SudokuElementFactory implements DefaultElementFactory {

	Map<String, Object> elementMap = new ConcurrentHashMap<>(0);

	@Override
	public Object getElement(String elementName) {
		return Objects.requireNonNull(elementMap.get(elementName), "cannot find "+ elementName);
	}

	@Override
	public Collection<Object> getAllElement() {
		return elementMap.values();
	}

	@Override
	public <T> T getElement(Class<T> clazz) {
		Optional<Object> any = elementMap.values().stream().filter(element -> element.getClass().equals(clazz)).findAny();
		if (any.isPresent()) {
			return (T) any.get();
		}
		throw new IllegalArgumentException( clazz.toString() +" cannot find ");
	}

	@Override
	public void registerAlias(String elementName, Object element) {
		elementMap.put(elementName, element);
	}

	@Override
	public void registerMark(LateElement lateElement) {
		elementMarkList.add(lateElement);
	}

	@Override
	public List<LateElement> getElementMark() {
		return elementMarkList;
	}
}
