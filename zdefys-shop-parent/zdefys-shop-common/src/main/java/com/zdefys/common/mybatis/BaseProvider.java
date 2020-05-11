package com.zdefys.common.mybatis;

import java.util.Map;

public class BaseProvider {
	
	/**
	 * 自定义封装sql语句
	 * 
	 * @param map
	 * @return
	 */
	public String save(Map<String,Object> map) {
		// 实体类
		Object object = map.get("obj");
		// 表名称
		String table = (String)map.get("table");
		// 生成添加的sql语句，使用反射机制
		// 步骤，使用反射机制加载类的所有属性
		
		
		return null;

	}

}
