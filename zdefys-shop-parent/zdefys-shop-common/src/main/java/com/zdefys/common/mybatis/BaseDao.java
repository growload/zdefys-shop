package com.zdefys.common.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

import feign.Param;

public interface BaseDao {
	
	/**
	 * @InsertProvider 注解作用  自定义sql语句
	 * @param t
	 */
	@InsertProvider(type =BaseProvider.class,method = "save")
	public void save(@Param("obj") Object obj,@Param("table") String table);

}
