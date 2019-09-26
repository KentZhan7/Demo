package com.demo.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.demo.orm.model.UserInfo;

public interface UserInfoDao {
	@Select({
	 	"<script>",
		     "select",
		     "USERID, NAME",
		     "from PTRMS.USERINFO",
		     "where 1=1",
	     "</script>"
	 })
	 @Results({
	     @Result(column="USERID", property="userId", jdbcType=JdbcType.NVARCHAR),
	     @Result(column="NAME", property="name", jdbcType=JdbcType.NVARCHAR)
	 })
	 List<UserInfo> getUserInfoByUserInfoModel(UserInfo userInfo);
}
