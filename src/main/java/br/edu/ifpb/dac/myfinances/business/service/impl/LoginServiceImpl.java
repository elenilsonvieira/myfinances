package br.edu.ifpb.dac.myfinances.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.LoginService;
import br.edu.ifpb.dac.myfinances.business.service.SystemUserService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SystemUserService service;
	
	public SystemUser login(String email, String password) {
		SystemUser user = service.findByEmail(email);
		
		if(user == null || password == null || 
				!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Incorrect Email or Password");
		}
		
		return user;
	}
	
}
