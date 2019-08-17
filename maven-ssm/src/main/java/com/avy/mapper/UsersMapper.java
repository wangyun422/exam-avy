package com.avy.mapper;

import com.avy.bean.Users;

public interface UsersMapper {
	/**
	 * 根据用户Id查询  获取角色信息
	 */
	public Users selectRoleByPrimaryKey(String id);
}
