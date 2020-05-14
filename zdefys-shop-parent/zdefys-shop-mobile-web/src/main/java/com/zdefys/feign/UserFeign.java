package com.zdefys.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zdefys.api.service.UserService;

@FeignClient("member")
@Service
public interface UserFeign extends UserService{
	
}
