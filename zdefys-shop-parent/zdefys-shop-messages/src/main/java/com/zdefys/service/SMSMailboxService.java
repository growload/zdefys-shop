package com.zdefys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zdefys.adapter.MessageAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 发送邮件服务类
 * 
 * @author zdefys
 *
 */
@Service
@Slf4j
public class SMSMailboxService implements MessageAdapter{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	/**
	 * 接收消息
	 */
	@Override
	public void distribute(JSONObject jsonObject) {
		String mail = jsonObject.getString("mail");
		String username = jsonObject.getString("username");
		// 开始发送邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(mail);
		message.setSubject("恭喜您成为zdefys微信商城的新用户.....");
		message.setText("恭喜您成为微信商城的用户，谢谢您的关注！");
		log.info("###发送短信邮箱 mail：{}",mail);
		mailSender.send(message);
	}

}
