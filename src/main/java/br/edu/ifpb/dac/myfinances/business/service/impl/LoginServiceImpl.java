package br.edu.ifpb.dac.myfinances.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.business.service.LoginService;
import br.edu.ifpb.dac.myfinances.business.service.SuapService;
import br.edu.ifpb.dac.myfinances.business.service.SystemUserService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SuapService suapService;
	@Autowired
	private ConverterService converterService;
	
	@Value("${app.logintype}")
	private String logintype;
	
	private String suapToken;
	
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
		SystemUser user = systemUserService.findByEmail(email);
		
		if(user == null || password == null || 
				!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Incorrect Email or Password");
		}
		
		return user;
	}
	
	private SystemUser suapLogin(String username, String password) {
		String jsonToken = suapService.login(username, password);
		this.suapToken = converterService.jsonToToken(jsonToken);
		
		if(this.suapToken == null) {
			throw new IllegalArgumentException("Incorrect Email or Password");
		}
		
		SystemUser user = systemUserService.findByUsername(username);
		
		if(user == null) {
			String json = suapService.findUser(suapToken, username);
			user = converterService.jsonToUser(json);
			
			user = systemUserService.save(user);
		}
		
		return user;
	}
	
}
