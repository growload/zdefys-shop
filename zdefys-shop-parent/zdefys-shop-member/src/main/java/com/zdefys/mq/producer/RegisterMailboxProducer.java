package com.zdefys.mq.producer;

import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

/**
 * 往消息服务推送邮件信息
 * 
 * @author xinyin
 *
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	public void send(ActiveMQQueue destination,String mailMessage) {
		
		jmsMessagingTemplate.convertAndSend(destination,mailMessage);
	}

}
