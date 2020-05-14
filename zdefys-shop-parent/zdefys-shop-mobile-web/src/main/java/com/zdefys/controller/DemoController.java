package com.zdefys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdefys.base.controller.BaseController;
import com.zdefys.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController extends BaseController{
	// index 页面
	public static final String INDEX = "index";
	
	@RequestMapping("/index/{token}")
	public String index(HttpServletRequest request,@PathVariable(value = "token") String token) {
		UserEntity userEntity = getUserEntity(token);
		log.info("我的web工程搭建成功了!,username:{}"+userEntity.getUsername());
		return INDEX;
	}

}
