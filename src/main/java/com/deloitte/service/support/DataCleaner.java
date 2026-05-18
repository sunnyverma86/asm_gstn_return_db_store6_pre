package com.deloitte.service.support;

import java.lang.reflect.Field;

public class DataCleaner {

	public static void cleanObject(Object obj) {

		if (obj == null) {
			return;
		}

		Field[] fields = obj.getClass().getDeclaredFields();

		for (Field field : fields) {

			field.setAccessible(true);

			try {

				Object value = field.get(obj);

				if (value instanceof String) {

					String cleaned = ((String) value).trim();

					if (cleaned.isEmpty()) {
						field.set(obj, null);
					} else {
						field.set(obj, cleaned);
					}
				}

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}