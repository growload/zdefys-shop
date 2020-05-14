package com.zdefys.manage;

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

}
