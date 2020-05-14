package com.zdefys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

}
