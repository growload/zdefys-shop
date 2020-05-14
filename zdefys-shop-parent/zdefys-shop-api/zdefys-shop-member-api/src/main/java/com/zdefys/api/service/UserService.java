package com.zdefys.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdefys.entity.UserEntity;

@RequestMapping("/member")
public interface UserService {
	
	/**
	 * 会员注册服务接口
	 * 
	 * @param userEntity
	 * @return
	 */
	@PostMapping("/regist")
	public Map<String,Object> regist(@RequestBody UserEntity userEntity);
}
