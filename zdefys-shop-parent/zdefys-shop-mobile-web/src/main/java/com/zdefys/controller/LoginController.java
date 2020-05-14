package com.zdefys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zdefys.base.controller.BaseController;
import com.zdefys.common.web.CookieUtil;
import com.zdefys.constants.BaseApiConstants;
import com.zdefys.constants.Constants;
import com.zdefys.entity.UserEntity;
import com.zdefys.feign.UserFeign;

@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private UserFeign userFeign;
	
	private static final String LOGIN = "login";
	private static final String INDEX = "index";

	@GetMapping("/")
	public String locaRegist() {
		return LOGIN;
	}

	@PostMapping("/login")
	public String login(UserEntity userEntity,HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> login = userFeign.login(userEntity);
			Integer code = (Integer) login.get(BaseApiConstants.HTTP_RES_CODE_NAME);
			if (!code.equals(BaseApiConstants.HTTP_RES_CODE_200)) {
				String msg = (String) login.get(login.get("msg"));
				return setError(request, msg, LOGIN);
			}
			// 登录成功之后，获取token，将token存放在cookie当中
			String token = (String)login.get("data");
			CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
			return INDEX;
		} catch (Exception e) {
			return setError(request, "注册失败", LOGIN);

		}
	}
}
