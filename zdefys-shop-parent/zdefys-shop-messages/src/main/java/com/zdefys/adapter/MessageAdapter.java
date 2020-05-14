package com.zdefys.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 所有消息都会交给他进行转发
 * 
 * @author xinyin
 *
 */
public interface MessageAdapter {
	/**
	 * 接收消息
	 */
	void distribute(JSONObject jsonObject);

}
