package com.zdefys.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @classDesc: 功能描述：（Spring Cloud 服务注册）
 * @author: zhangdeen
 * @version: v1.0
 *
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer{
	
	public static void main(String[] args){
		
		SpringApplication.run(EurekaServer.class, args);
		
	}

}
