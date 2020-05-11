package com.zdefys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.zdefys.api.service.DemoApiService;
import com.zdefys.common.api.BaseApiService;
import com.zdefys.common.redis.BaseRedisService;

import lombok.extern.slf4j.Slf4j;

@RestController
public class DemoApiServiceImpl extends BaseApiService implements DemoApiService{
	
	@Autowired
	private BaseRedisService baseRedisService;

	@Override
	public Map<String, Object> demo() {
		return setResultSuccess();
	}

	@Override
	public Map<String, Object> setKey(String key, String value) {
		
		baseRedisService.setString(key, value);
		return setResultSuccess();
	}

	@Override
	public Map<String,Object> getKey(String key) {
		String value = baseRedisService.get(key);
		return setResultSuccessData(value);
	}

}
