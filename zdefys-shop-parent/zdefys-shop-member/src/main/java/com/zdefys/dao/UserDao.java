package com.zdefys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zdefys.common.mybatis.BaseDao;
import com.zdefys.entity.UserEntity;

@Mapper
public interface UserDao extends BaseDao {

	@Select("SELECT id,username,password,phone,email,created,updated from mb_user where phone = #{phone} and password = #{password}")
	public UserEntity getUserPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);

	@Select("SELECT id,username,password,phone,email,created,updated from mb_user where id=#{userId}")
	public UserEntity getUserInfo(@Param("userId") Long userId);

}
