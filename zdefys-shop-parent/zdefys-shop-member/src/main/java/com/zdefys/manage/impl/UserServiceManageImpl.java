package com.zdefys.manage.impl;

import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zdefys.common.utils.DateUtils;
import com.zdefys.common.utils.MD5Util;
import com.zdefys.constants.DBTableName;
import com.zdefys.constants.MQInterfaceType;
import com.zdefys.dao.UserDao;
import com.zdefys.entity.UserEntity;
import com.zdefys.manage.UserServiceManage;
import com.zdefys.mq.producer.RegisterMailboxProducer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceManageImpl implements UserServiceManage {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;

	@Value("${messages.queue}")
	private String MESSAGE_QUEUE;

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
		log.info("###regist()  注册发送邮件报文mailMessage:{}",mailMessage);
		// mq
		registerMailboxProducer.send(activeMQQueue, mailMessage);
	}

	@Override
	public String md5PassSalt(String phone, String password) {
		String newPass = MD5Util.MD5(phone + password);
		return newPass;
	}

	private String mailMessage(String email,String username) {
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

}
