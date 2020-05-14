package com.zdefys.manage;

import java.util.Map;

import com.zdefys.entity.UserEntity;

public interface UserServiceManage {
	
	/**
	 * 会员注册服务
	 * 
	 * @param userEntity
	 */
	public void regist(UserEntity userEntity);
	
	/**
	 * 密码加盐
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public String  md5PassSalt(String phone,String password);
	
	/**
	 * 会员登录服务
	 * 
	 * @param userEntity
	 * @return 
	 */
	public Map<String, Object> login(UserEntity userEntity);
	
	/**
	 * 通过token 查询会员
	 * 
	 * @param token
	 * @return
	 */
	public Map<String,Object> getUser(String token);

}
