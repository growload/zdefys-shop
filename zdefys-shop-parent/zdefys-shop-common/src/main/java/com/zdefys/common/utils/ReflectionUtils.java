package com.zdefys.common.utils;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import org.apache.ibatis.jdbc.SQL;
import org.hibernate.validator.internal.util.privilegedactions.GetDeclaredFields;

import lombok.extern.slf4j.Slf4j;

/**
 * 反射工具类
 * 
 * @author xinyin
 *
 *
 */
public class ReflectionUtils {

	/**
	 * 封装当前类和父类的所有属性 拼接属性sql
	 * 
	 * @return
	 */
	public static String fatherAndSonFidld(Object obj) {
		try {
			if (obj == null) {
				return null;
			}
			// 获取class文件
			Class clazz = obj.getClass();
			// 获取当前类的所有属性
			Field[] sonFields = clazz.getDeclaredFields();
			String sonField = getField(sonFields);
			Field[] parentFields = clazz.getSuperclass().getDeclaredFields();
			String parentField = getField(parentFields);
			return sonField + "," + parentField;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String fatherAndSonFidldValue(Object obj) {
		try {
			if (obj == null) {
				return null;
			}
			// 获取class文件
			Class clazz = obj.getClass();
			// 获取当前类的所有属性
			Field[] sonFields = clazz.getDeclaredFields();
			String sonField = getFidldValue(obj, sonFields);
			Field[] parentFields = clazz.getSuperclass().getDeclaredFields();
			String parentField = getFidldValue(obj, parentFields);
			return sonField + "," + parentField;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getField(Field[] declaredFields) {
		StringBuilder sf = new StringBuilder();

		for (int i = 0; i < declaredFields.length; i++) {
			sf.append(declaredFields[i].getName());
			if (i < declaredFields.length - 1) {
				sf.append(",");
			}
		}
		return sf.toString();
	}

	public static String getFidldValue(Object obj, Field[] declaredFields) {
		StringBuilder sf = new StringBuilder();
		try {
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				field.setAccessible(true);
				Object value = field.get(obj);
				// 表示是否为String类型
				boolean flag = false;
				if (value != null && (value instanceof String) || value instanceof Timestamp) {
					flag = true;
				}
				if (flag) {
					sf.append("'");
					sf.append(value);
					sf.append("'");
				} else {
					sf.append(value);
				}
				if (i < declaredFields.length - 1) {
					sf.append(",");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sf.toString();
	}
}
