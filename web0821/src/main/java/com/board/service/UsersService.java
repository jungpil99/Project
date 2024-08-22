package com.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.board.db.UsersDao;
import com.board.db.UsersDto;

public class UsersService {
	
	
	public void joinUser(String id, String pwd, String name, String email, String tel) 
			throws Exception {
		if(id == null || id.length() == 0 ||
		   pwd == null || pwd.length() == 0 ||
		   name == null || name.length() == 0 ||
		   email == null || email.length() == 0 ||
		   tel == null || tel.length() == 0 ) {
			
			throw new Exception("모든 항목이 빈칸 없이 입력되어야 합니다.");
		}
		
	}
	
}
	