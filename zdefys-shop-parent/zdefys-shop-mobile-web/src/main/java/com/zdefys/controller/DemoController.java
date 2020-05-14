package com.zdefys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController {
	// index 页面
	public static final String INDEX = "index";
	
	@RequestMapping("/index")
	public String index() {
		log.info("我的web工程搭建成功了");
		return INDEX;
	}

}
