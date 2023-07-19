package com.iiikn.processor;

import com.iiikn.annotaion.Late;
import com.iiikn.annotaion.Seek;
import com.iiikn.annotaion.Piling;
import com.iiikn.factory.DefaultElementFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class AnnotationPilingPostProcessor implements ElementPostProcessor {

	@Override
	public void registerElement(String primaryPath, DefaultElementFactory elementFactory) {

		try {

			Class<?>[] resourceCla = findClass(primaryPath);

			Set<Object> pilingSet = findPiling(resourceCla, elementFactory);

			// 解决依赖
			solveCollect(pilingSet, elementFactory);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private Class<?>[] findClass(String primaryPath) {

		// TODO 测试时用一个绝得路径扫描
		primaryPath = "D:\\workSpace\\生产介质备份\\chenwang\\sudoku-view\\target\\classes\\";

		Set<String> classPathSet = new HashSet<>();
		Set<Class<?>> classSet = new HashSet<>();
		try {

			findFullClassName(primaryPath, classPathSet);

			for (String resourcePath : classPathSet) {
				String replace = resourcePath.replace(primaryPath, "").replace("\\", ".");
				Class<?> clazz = Class.forName(replace);
				classSet.add(clazz);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Class<?>[] findClazzArray = new Class<?>[classSet.size()];
		return classSet.toArray(findClazzArray);
	}

	public File[] findFiles(String filePath, boolean recursive) {
		return new File(filePath).listFiles(file ->
				file.isFile() && file.getName().endsWith(".class")
						|| file.isDirectory() && recursive
		);
	}

	public void findFullClassName(String path, Set<String> classSet) {

		for (File file : findFiles(path, true)) {
			if (file.isDirectory()) {
				findFullClassName(file.getPath(), classSet);
			} else {
				classSet.add(file.getPath().replace(".class", ""));
			}
		}
	}

	public Set<Object> solveCollect(Set<Object> pilingSet, DefaultElementFactory elementFactory) throws IllegalAccessException {

		Set<Object> collectSet = new HashSet<>();

		for (Object pilingObject : pilingSet) {
			Class<?> pilingObjectClass = pilingObject.getClass();
			Field[] declaredFields = pilingObjectClass.getDeclaredFields();
			for (Field declaredField : declaredFields) {
				declaredField.setAccessible(true);

				Seek seekAnnotation = declaredField.getAnnotation(Seek.class);
				if (seekAnnotation == null) {
					continue;
				}

				Late lateAnnotation = declaredField.getAnnotation(Late.class);
				if (lateAnnotation != null) {
					DefaultElementFactory.LateElement lateElement = new DefaultElementFactory.LateElement()
							.setLateElementName(seekAnnotation.value())
							.setMarkElementName(pilingObjectClass.getAnnotation(Piling.class).value())
							.setLateElementField(declaredField)
							.setSeekElement(pilingObject);
					elementFactory.registerMark(lateElement);
					continue;
				}
				collectSet.add(pilingObject);
				Object element = elementFactory.getElement(seekAnnotation.value());
				declaredField.set(pilingObject, element);
				String pilingString = pilingObjectClass.getAnnotation(Piling.class).value();
				elementFactory.registerAlias(pilingString, pilingObject);
			}
		}
		return collectSet;
	}

	public Set<Object> findPiling(Class<?>[] resourceClazz, DefaultElementFactory elementFactory) throws IllegalAccessException, InstantiationException {
		Set<Object> pilingElementSet = new HashSet<>();
		for (Class<?> clazz : resourceClazz) {
			Piling pilingAnnotation = clazz.getAnnotation(Piling.class);
			if (pilingAnnotation != null) {
				Object newInstance = clazz.newInstance();
				pilingElementSet.add(newInstance);
				elementFactory.registerAlias(pilingAnnotation.value(), newInstance);
			}
		}
		return pilingElementSet;
	}
}
