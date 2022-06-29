package br.edu.ifpb.dac.myfinances.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.business.service.AuthenticationService;
import br.edu.ifpb.dac.myfinances.business.service.SuapService;
import br.edu.ifpb.dac.myfinances.business.service.SystemUserService;
import br.edu.ifpb.dac.myfinances.business.service.TokenService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SuapService suapService;
	@Autowired
	private ConverterService converterService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${app.logintype}")
	private String logintype;
	
	private String suapToken;
	
	public String login(String username, String password) {
		switch (logintype) {
		case "suap":
			return suapLogin(username, password);
		case "local":
			return localLogin(username, password);
		default:
			return localLogin(username, password);
		}
	}
	
	private String localLogin(String username, String password) {	
		//Excecao sera lancada em caso de falha
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		SystemUser user = systemUserService.findByUsername(username);
		
		return tokenService.generate(user);
	}
	
	private String suapLogin(String username, String password) {
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
		
		return tokenService.generate(user);
	}

	@Override
	public SystemUser getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (SystemUser) authentication.getPrincipal();
	}
	
}
