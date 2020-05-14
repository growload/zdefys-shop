package com.zdefys.manage.impl;

import java.util.Map;
import java.util.UUID;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.zdefys.common.api.BaseApiService;
import com.zdefys.common.redis.BaseRedisService;
import com.zdefys.common.token.TokenUtils;
import com.zdefys.common.utils.DateUtils;
import com.zdefys.common.utils.MD5Util;
import com.zdefys.constants.Constants;
import com.zdefys.constants.DBTableName;
import com.zdefys.constants.MQInterfaceType;
import com.zdefys.dao.UserDao;
import com.zdefys.entity.UserEntity;
import com.zdefys.manage.UserServiceManage;
import com.zdefys.mq.producer.RegisterMailboxProducer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceManageImpl extends BaseApiService implements UserServiceManage {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private BaseRedisService baseRedisService;

	@Value("${messages.queue}")
	private String MESSAGE_QUEUE;

	/**
	 * 会员注册服务
	 * 
	 * @param userEntity
	 */
	@Override
	public void regist(UserEntity userEntity) {
		userEntity.setCreated(DateUtils.getTimestamp());
		userEntity.setUpdated(DateUtils.getTimestamp());
		String username = userEntity.getUsername();
		String email = userEntity.getEmail();
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		userEntity.setPassword(md5PassSalt(phone, password));
		userDao.save(userEntity, DBTableName.TABLE_MB_NAME);
		// 队列
		// json组装
		ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGE_QUEUE);
		// 组装报文格式
		String mailMessage = mailMessage(email, username);
		log.info("###regist()  注册发送邮件报文mailMessage:{}", mailMessage);
		// mq
		registerMailboxProducer.send(activeMQQueue, mailMessage);
	}

	/**
	 * 密码加盐
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@Override
	public String md5PassSalt(String phone, String password) {
		String newPass = MD5Util.MD5(phone + password);
		return newPass;
	}

	/**
	 * 组装报文消息
	 * 
	 * @param email
	 * @param username
	 * @return
	 */
	private String mailMessage(String email, String username) {
		// 组装报文格式
		JSONObject root = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", MQInterfaceType.SMS_MAIL);
		JSONObject content = new JSONObject();
		content.put("mail", email);
		content.put("username", username);
		root.put("header", header);
		root.put("content", content);
		return root.toJSONString();
	}

	/**
	 * 会员登录服务
	 * 
	 * @param userEntity
	 */
	@Override
	public Map<String, Object> login(UserEntity userEntity) {
		// 往数据库进行查找数据
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		String newPassword = md5PassSalt(phone, password);
		UserEntity userPhoneAndPwd = userDao.getUserPhoneAndPwd(phone, newPassword);
		if (userPhoneAndPwd == null) {
			return setResultError("账号或密码错误");
		}
		// 生成对应的token
		String token = tokenUtils.getToken();
		Long userId = userPhoneAndPwd.getId();
		// 存放在redis中，key为自定义令牌，将用户的userId作为value
		baseRedisService.set(token, userId+"", Constants.USER_TOKEN_TERMVALIDITY);
		// 返回token
		return setResultSuccessData(token);
	}

	/**
	 * 通过token获取会员信息
	 * 
	 * @param token
	 */
	@Override
	public Map<String, Object> getUser(String token) {
		String userId = baseRedisService.get(token);
		if (StringUtils.isEmpty(userId)) {
			return setResultError("用户已经过期！");
		}
		UserEntity userInfo = userDao.getUserInfo(Long.parseLong(userId));
		if (userInfo == null) {
			return setResultError("用户不存在");
		}
		userInfo.setPassword(null);
		return setResultSuccessData(userInfo);
	}
}
