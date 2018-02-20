package xyz.timessuntech.cloud.common.lang.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

public class ClassUtils {

	/**
	 * 工具方法: 沿类的继承树向上查找其超类(参数化类型)的类型参数<br>
	 * 如果直到Object都未找到参数化类型，则返回null
	 */
	@SuppressWarnings("rawtypes")
	public static Class getParameterType(Class clazz, int index) {
		if (clazz == null)
			return null;

		Type parent = clazz.getGenericSuperclass();
		// 如果其直接超类不是参数化类型，则继续向上查找
		// 当使用CGLIB生成DAO的代理类时，就需要继续向上查找
		if (!(parent instanceof ParameterizedType))
			return getParameterType((Class<?>) parent, index);

		ParameterizedType genType = (ParameterizedType) parent;
		// 获得类型参数数组
		Type[] params = genType.getActualTypeArguments();
		if (params.length >= index) {
			return params[index] instanceof Class ? (Class) params[index] : null;
		} else {
			return null;
		}
	}

	/**
	 * 获取对象的属性值
	 * 
	 * @param object
	 *            对象实例
	 * @param property
	 *            属性名
	 * @return 属性的值
	 */
	public static Object getObjectProperty(Object object, String property) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchFieldException {
		if (object == null)
			return null;
		try {
			return getClassGetter(object.getClass(), property).invoke(object, new Object[0]);
		} catch (NoSuchMethodException e) {
			return object.getClass().getField(property).get(object);
		}
	}

	/**
	 * 查找getXXX与isXXX的属性Getter方法
	 * 
	 * @param clazz
	 *            类元
	 * @param property
	 *            属性名
	 * @return 属性Getter方法
	 */
	@SuppressWarnings("unchecked")
	public static Method getClassGetter(Class clazz, String property) throws NoSuchMethodException, SecurityException {

		Assert.notNull(clazz, "class不能为空！");
		Assert.notNull(property, "property不能为空！");
		property = property.trim();
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		try {
			Method getter = clazz.getMethod("get" + upper);
			Assert.isTrue(getter.getReturnType() != Void.class, "getter返回值类型不能为void！");
			return getter;
		} catch (NoSuchMethodException e1) {
			try {
				Method getter = clazz.getMethod("is" + upper);
				Assert.isTrue(getter.getReturnType() != Void.class, "getter返回值类型不能为void！");
				return getter;
			} catch (NoSuchMethodException e2) {
				Method getter = clazz.getMethod(property);
				Assert.isTrue(getter.getReturnType() != Void.class, "getter返回值类型不能为void！");
				return getter;
			}
		}
	}

	/**
	 * 转换一个object对象成为Map
	 * 
	 * @param object
	 *            对象
	 * @return Map对象
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 */
	public static Map<String, Object> getMapValue(Object object) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, NoSuchFieldException {
		Map<String, Object> map = new HashMap<String, Object>(0);
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object value = getObjectProperty(object, field.getName());
			if (value != null)
				map.put(field.getName(), value);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static void setFormBean(Map<String, String> map, Object bean) {
		Class c = bean.getClass();
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method ms = methods[i];
			String name = ms.getName();
			if (name.startsWith("set")) {
				Class[] cc = ms.getParameterTypes();
				if (cc.length == 1) {
					String type = cc[0].getName(); // parameter type
					try {
						// get property name:
						String prop = Character.toLowerCase(name.charAt(3)) + name.substring(4);
						// get parameter value:
						if (map.containsKey(prop) == false)
							continue;
						String param = map.get(prop);
						if (param != null && !param.equals("")) {
							// ms.setAccessible(true);
							if (type.equals("java.lang.String")) {
								ms.invoke(bean, new Object[] { param });
							} else if (type.equals("int") || type.equals("java.lang.Integer")) {
								ms.invoke(bean, new Object[] { Double.valueOf(param).intValue() });
							} else if (type.equals("long") || type.equals("java.lang.Long")) {
								ms.invoke(bean, new Object[] { Long.valueOf(param) });
							} else if (type.equals("double") || type.equals("java.lang.Double")) {
								ms.invoke(bean, new Object[] { Double.valueOf(param) });
							} else if (type.equals("boolean") || type.equals("java.lang.Boolean")) {
								ms.invoke(bean, new Object[] { Boolean.valueOf(param) });
							} else if (type.equals("java.util.Date")) {
								ms.invoke(bean, new Object[] { param });
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
