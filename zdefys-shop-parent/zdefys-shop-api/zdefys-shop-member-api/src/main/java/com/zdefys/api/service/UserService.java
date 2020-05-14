package com.zdefys.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * 会员登录接口:登录成功后，生成对应的token，作为key，将用户userID作为value存放在redis中
	 * 返回token 给客户端
	 * 
	 * @return
	 */
	@PostMapping("/login")
	public Map<String,Object> login(@RequestBody UserEntity userEntity);
	
	/**
	 * 使用token查找用户信息
	 * 
	 * @return
	 */
	@PostMapping("/{token}")
	public Map<String,Object> getUserInfo(@PathVariable(value = "token") String token);
}
