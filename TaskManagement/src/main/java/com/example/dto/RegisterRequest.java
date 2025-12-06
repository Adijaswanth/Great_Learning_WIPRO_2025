package com.example.dto;

import lombok.Data;
import com.example.model.Role;

@Data
public class RegisterRequest
{
	private String username;
	private String password;
	private Role role;
}