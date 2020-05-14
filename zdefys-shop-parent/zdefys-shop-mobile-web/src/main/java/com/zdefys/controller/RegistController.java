package com.zdefys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zdefys.base.controller.BaseController;
import com.zdefys.common.api.BaseApiService;
import com.zdefys.constants.BaseApiConstants;
import com.zdefys.entity.UserEntity;
import com.zdefys.feign.UserFeign;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegistController extends BaseController {

	private static final String LOCAREGIST = "locaRegist";
	private static final String LOGIN = "login";

	@Autowired
	private UserFeign userFeign;

	@GetMapping("/locaRegist")
	public String locaRegist() {
		return LOCAREGIST;
	}

	@PostMapping("/regist")
	public String regist(HttpServletRequest request,UserEntity userEntity) {
		try {
			Map<String, Object> registMap = userFeign.regist(userEntity);
			Integer code = (Integer) registMap.get(BaseApiConstants.HTTP_RES_CODE_NAME);
			if (!code.equals(BaseApiConstants.HTTP_RES_CODE_200)) {
				String msg = (String) registMap.get(registMap.get("msg"));
				return setError(request, msg, LOCAREGIST);
			}
			// 注册成功
			return LOGIN;

		} catch (Exception e) {
			return setError(request, "注册失败", LOCAREGIST);

		}
	}

}
