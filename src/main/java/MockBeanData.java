import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JagarlamudiS2
 *
 */
@SuppressWarnings("rawtypes")
public class MockBeanData {

	public Object createMockData(Class classType) throws Exception {
		Object obj = null;
		try {
			obj = classType.newInstance();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!"serialVersionUID".equalsIgnoreCase(field.getName()))
					createFieldValues(field, obj, obj.getClass().getName(), null);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return obj;

	}

	private void createFieldValues(Field field, Object obj, String className, Map<String, Object> customFields)
			throws Exception {
		Map<String, Object> userValues = new HashMap<String, Object>();
		if (customFields != null)
			userValues = customFields;
		else
			userValues = null;
		try {
			if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, Integer.valueOf((String) userValues.get(field.getName())));
				else
					field.set(obj, 1);
			} else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, Long.valueOf((String) userValues.get(field.getName())));
				else
					field.set(obj, 1L);
			} else if (field.getType().equals(String.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, String.valueOf(userValues.get(field.getName())));
				else
					field.set(obj, "Test");
			} else if (field.getType().equals(BigDecimal.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, new BigDecimal((String) userValues.get(field.getName())));
				else
					field.set(obj, new BigDecimal(2.5));
			} else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, Double.valueOf((String) userValues.get(field.getName())));
				else
					field.set(obj, 1.5);

			} else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, Boolean.valueOf((String) userValues.get(field.getName())));
				else
					field.set(obj, true);

			} else if (field.getType().equals(List.class)) {
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else {
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					Class<?> classType = (Class<?>) listType.getActualTypeArguments()[0];
					Field[] childFields = classType.getDeclaredFields();
					Object obj2 = classType.newInstance();
					for (Field childField : childFields) {
						if (!"serialVersionUID".equalsIgnoreCase(childField.getName())) {
							createFieldValues(childField, obj2, className, customFields);
						}
					}
					boolean accessible = field.isAccessible();
					if (!accessible)
						field.setAccessible(true);
					List<Object> objList = new ArrayList<Object>();
					objList.add(obj2);
					field.set(obj, objList);
				}
			} else if (field.getType().equals(Set.class)) {
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else {
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					Class<?> classType = (Class<?>) listType.getActualTypeArguments()[0];
					Field[] childFields = classType.getDeclaredFields();
					Object obj2 = classType.newInstance();
					for (Field childField : childFields) {
						if (!"serialVersionUID".equalsIgnoreCase(childField.getName())) {
							createFieldValues(childField, obj2, className, customFields);
						}
					}
					boolean accessible = field.isAccessible();
					if (!accessible)
						field.setAccessible(true);
					Set<Object> objList = new HashSet<Object>();
					objList.add(obj2);
					field.set(obj, objList);
				}
			} else if (field.getType().equals(Queue.class)) {
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else {
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					Class<?> classType = (Class<?>) listType.getActualTypeArguments()[0];
					Field[] childFields = classType.getDeclaredFields();
					Object obj2 = classType.newInstance();
					for (Field childField : childFields) {
						if (!"serialVersionUID".equalsIgnoreCase(childField.getName())) {
							createFieldValues(childField, obj2, className, customFields);
						}
					}
					boolean accessible = field.isAccessible();
					if (!accessible)
						field.setAccessible(true);
					Queue<Object> objList = new PriorityQueue<Object>();
					objList.add(obj2);
					field.set(obj, objList);
				}
			}else if (field.getType().equals(Map.class)) {
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else {
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					Class<?> classType = (Class<?>) listType.getActualTypeArguments()[0];
					Field[] childFields = classType.getDeclaredFields();
					Object obj2 = classType.newInstance();
					for (Field childField : childFields) {
						if (!"serialVersionUID".equalsIgnoreCase(childField.getName())) {
							createFieldValues(childField, obj2, className, customFields);
						}
					}
					boolean accessible = field.isAccessible();
					if (!accessible)
						field.setAccessible(true);
					Map<Object, Object> objList = new HashMap<Object, Object>();
					objList.put(field.getName(), obj2);
					field.set(obj, objList);
				}
			} else if (field.getType().equals(MultipartFile.class)) {
				MockMultipartFile file = new MockMultipartFile("data", "test.txt", "text/plain",
						"some test data".getBytes());
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else
					field.set(obj, file);
			} else if (field.getType().equals(Date.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else
					field.set(obj, new Date());
			} else {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else {
					Object childObj;
					Class<?> cl = field.getType();
					try {
						childObj = cl.newInstance();
						Field[] childFields = childObj.getClass().getDeclaredFields();
						if (!childObj.getClass().getName().equalsIgnoreCase(className)) {
							for (Field childField : childFields) {
								if (!"serialVersionUID".equalsIgnoreCase(childField.getName()))
									createFieldValues(childField, childObj, className, customFields);
							}
						}
						field.set(obj, childObj);
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Object createMockData(Class classType, Map<String, Object> customFields) throws Exception {
		Object obj = null;
		try {
			obj = classType.newInstance();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!"serialVersionUID".equalsIgnoreCase(field.getName()))
					createFieldValues(field, obj, obj.getClass().getName(), customFields);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return obj;

	}

}
