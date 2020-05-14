package com.zdefys.entity;

import com.zdefys.common.entity.BaseEntity;

import lombok.Data;

@Data
public class UserEntity extends BaseEntity{
		
		private String username;
		
		private String email;
		
		private String phone;
		
		private String password;

}
