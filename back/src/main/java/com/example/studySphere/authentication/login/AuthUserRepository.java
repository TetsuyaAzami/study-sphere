package com.example.studySphere.authentication.login;

import org.apache.ibatis.annotations.Mapper;
import com.example.studySphere.authentication.AuthUser;

@Mapper
public interface AuthUserRepository {

	AuthUser findByCondition(AuthUser person);
}
