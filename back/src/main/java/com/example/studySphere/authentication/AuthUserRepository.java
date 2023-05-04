package com.example.studySphere.authentication;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthUserRepository {

	AuthUser findByCondition(AuthUser person);
}
