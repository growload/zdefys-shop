package com.zdefys.common.utils;

import java.lang.reflect.Field;

import org.hibernate.validator.internal.util.privilegedactions.GetDeclaredFields;

import com.zdefys.common.entity.TestEntity;

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
	public static String fatherAndSon(Object obj) {
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

	public static void main(String[] args) {
		TestEntity testEntity = new TestEntity();
		String inString = fatherAndSon(testEntity);
		System.out.println(inString);
	}

}
