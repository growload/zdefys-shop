package com.zdefys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.zdefys.api.service.UserService;
import com.zdefys.common.api.BaseApiService;
import com.zdefys.entity.UserEntity;
import com.zdefys.manage.UserServiceManage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService{
	
	@Autowired
	private UserServiceManage userServiceManage;
	
	/**
	 * 会员注册服务接口
	 * 
	 * @param userEntity
	 * @return
	 */
	@Override
	public Map<String, Object> regist(@RequestBody UserEntity userEntity) {
		
		if(StringUtils.isEmpty(userEntity.getUsername())) {
			return setResultParameterError("用户名称不能为空!");
		}
		
		
		try {
			userServiceManage.regist(userEntity);
			return setResultSuccess();
		} catch (Exception e) {
			log.error("###regist() ERROR:",e);
			return setResultError("注册失败");
		}
		
	}

	/**
	 * 会员登录接口:登录成功后，生成对应的token，作为key，将用户userID作为value存放在redis中
	 * 返回token 给客户端
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> login(@RequestBody UserEntity userEntity) {
		try {
			return userServiceManage.login(userEntity);
		}catch (Exception e) {
			log.error("###login() ERROR:",e);
			return setResultError("登录失败");
		}
	}

	/**
	 * 使用token查找用户信息
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> getUserInfo(@PathVariable(value = "token") String token) {
		if (StringUtils.isEmpty(token)) {
			return setResultError("token 不能为空！");
		}
		return userServiceManage.getUser(token);
	}
}
