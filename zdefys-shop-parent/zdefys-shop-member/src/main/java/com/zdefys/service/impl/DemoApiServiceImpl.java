package com.zdefys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.zdefys.api.service.DemoApiService;

@RestController
public class DemoApiServiceImpl implements DemoApiService {

	@Override
	public Map<String, Object> demo() {
		Map<String, Object> result = new HashMap<>(1);
		result.put("code", 200);
		result.put("msg", "success");
		return result;
	}

}
