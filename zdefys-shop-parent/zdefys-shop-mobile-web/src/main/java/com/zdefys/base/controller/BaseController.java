package com.zdefys.base.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.alibaba.fastjson.JSONObject;
import com.zdefys.constants.BaseApiConstants;
import com.zdefys.entity.UserEntity;
import com.zdefys.feign.UserFeign;

/**
 * 
 * 
 * @author zdefys
 *
 */
@Controller
public class BaseController {

	@Autowired
	private UserFeign userFeign;

	public UserEntity getUserEntity(String token) {
		Map<String, Object> userMap = userFeign.getUserInfo(token);
		Integer code = (Integer) userMap.get(BaseApiConstants.HTTP_RES_CODE_NAME);
		if (!code.equals(BaseApiConstants.HTTP_RES_CODE_200)) {
			return null;
		}
		// 获取data参数
		LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) userMap.get(BaseApiConstants.HTTP_RES_CODE_DATA);
		String json = new JSONObject().toJSONString(data);
		UserEntity userEntity = new JSONObject().parseObject(json, UserEntity.class);
		return userEntity;
	}
	
	public String setError(HttpServletRequest request, String msg, String address) {
		request.setAttribute("error", msg);
		return address;
	}

}
