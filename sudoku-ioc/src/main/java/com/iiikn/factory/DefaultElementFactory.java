package com.iiikn.factory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface DefaultElementFactory extends ElementFactory {

	List<LateElement> elementMarkList = new ArrayList<>(0);

	void registerAlias(String elementName, Object element);

	void registerMark(LateElement lateElement);

	List<LateElement> getElementMark();

	class LateElement {
		String lateElementName;
		String markElementName;
		Object seekElement;
		Field lateElementField;

		public String getLateElementName() {
			return lateElementName;
		}

		public LateElement setLateElementName(String lateElementName) {
			this.lateElementName = lateElementName;
			return this;
		}

		public String getMarkElementName() {
			return markElementName;
		}

		public LateElement setMarkElementName(String markElementName) {
			this.markElementName = markElementName;
			return this;
		}

		public Object getSeekElement() {
			return seekElement;
		}

		public LateElement setSeekElement(Object seekElement) {
			this.seekElement = seekElement;
			return this;
		}

		public Field getLateElementField() {
			return lateElementField;
		}

		public LateElement setLateElementField(Field lateElementField) {
			this.lateElementField = lateElementField;
			return this;
		}

	}
}
