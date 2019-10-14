package gov.michigan.mdot.lamda.mock.data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
			} else if (field.getType().equals(Map.class)) {
				boolean accessible = field.isAccessible();
				if (!accessible)
					field.setAccessible(true);
				if (userValues != null && userValues.containsKey(field.getName()))
					field.set(obj, userValues.get(field.getName()));
				else {
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					Object key = null;
					Object value = null;
					if (listType.getActualTypeArguments()[0] instanceof ParameterizedType) {
						key = setMapData(field, obj, className, customFields, accessible, listType);
					} else {
						Class<?> classTypeKey = (Class<?>) listType.getActualTypeArguments()[0];
						key = getMapData(classTypeKey, key);
					}
					if (listType.getActualTypeArguments()[1] instanceof ParameterizedType) {
						value = setMapData(field, obj, className, customFields, accessible, listType);
					} else {
						Class<?> classTypeValue = (Class<?>) listType.getActualTypeArguments()[1];
						value = getMapData(classTypeValue, key);
					}
					if (!accessible)
						field.setAccessible(true);
					Map<Object, Object> objMap = new HashMap<Object, Object>();
					objMap.put(key, value);
					field.set(obj, objMap);
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

	/**
	 * @param listType
	 * @param key
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Object getMapData(Class<?> classType, Object key) throws ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		if (classType.isInstance(new Integer(0))) {
			Class<?> classInteger = Class.forName("java.lang.Integer");
			Constructor<?> constructor = classInteger.getConstructor(new Class[] { int.class });
			key = constructor.newInstance(1);
		} else if (classType.isInstance(new BigDecimal(0)) || classType.isInstance(new Double(0))) {
			Class<?> classDouble = Class.forName("java.lang.BigDecimal");
			Constructor<?> constructor = classDouble.getConstructor(new Class[] { double.class });
			key = constructor.newInstance(1);
		} else if (classType.isInstance(new String(""))) {
			Class<?> classString = Class.forName("java.lang.String");
			Constructor<?> constructor = classString.getConstructor(new Class[] { String.class });
			key = constructor.newInstance("Test");
		}
		return key;
	}

	/**
	 * @param field
	 * @param obj
	 * @param className
	 * @param customFields
	 * @param accessible
	 * @param listType
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public Object setMapData(Field field, Object obj, String className, Map<String, Object> customFields,
			boolean accessible, ParameterizedType listType)
			throws InstantiationException, IllegalAccessException, Exception {
		Class<?> classRawType;
		ParameterizedType paramTypeImpl = (ParameterizedType) listType.getActualTypeArguments()[1];
		if (paramTypeImpl.getActualTypeArguments() != null && paramTypeImpl.getActualTypeArguments().length > 0) {
			classRawType = (Class<?>) paramTypeImpl.getActualTypeArguments()[0];
			Field[] childFields = classRawType.getDeclaredFields();
			Object obj2 = classRawType.newInstance();
			for (Field childField : childFields) {
				if (!"serialVersionUID".equalsIgnoreCase(childField.getName())) {
					createFieldValues(childField, obj2, className, customFields);
				}
			}

			return obj2;
		} else
			return obj;
	}
}
