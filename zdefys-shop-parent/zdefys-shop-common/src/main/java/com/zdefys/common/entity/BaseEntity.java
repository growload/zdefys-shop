package com.zdefys.common.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassDesc: 封装一些相同字段和属性
 * @author xinyin
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Timestamp created;
	/**
	 * 修改时间
	 */
	private Timestamp updated;

}
