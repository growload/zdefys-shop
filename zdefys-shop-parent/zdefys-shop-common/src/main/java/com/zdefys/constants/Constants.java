package com.zdefys.constants;

public interface Constants {
	
	/**
	 * 用户会话保存90天
	 */
	Long USER_TOKEN_TERMVALIDITY= 60*60*24*90L;
	
	Integer WEBUSER_COOKIE_TOKEN_TERMVALIDITY = 30*30*30*1000;
	
	/**
	 *  用户token
	 */
	String USER_TOKEN = "token";

}
