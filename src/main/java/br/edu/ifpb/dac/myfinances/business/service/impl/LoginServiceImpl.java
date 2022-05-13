package br.edu.ifpb.dac.myfinances.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.LoginService;
import br.edu.ifpb.dac.myfinances.business.service.SystemUserService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SystemUserService service;
	
	@Value("app.logintype")
	private String logintype;
	
	public SystemUser login(String username, String password) {
		switch (logintype) {
		case "suap":
			return suapLogin(username, password);
		case "local":
			return localLogin(username, password);
		default:
			return localLogin(username, password);
		}
	}
	
	private SystemUser localLogin(String email, String password) {
		SystemUser user = service.findByEmail(email);
		
		if(user == null || password == null || 
				!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Incorrect Email or Password");
		}
		
		return user;
	}
	
	private SystemUser suapLogin(String username, String password) {
		SystemUser user = service.findByEmail(username);
		
		if(user == null || password == null || 
				!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Incorrect Email or Password");
		}
		
		return user;
	}
	
}
