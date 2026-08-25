package com.ecomm.user.request;

import com.ecomm.user.enums.RoleType;

import lombok.Data;

@Data
public class AddRoleRequest {

	private RoleType roleName;
}
