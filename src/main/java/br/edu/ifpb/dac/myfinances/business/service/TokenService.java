package br.edu.ifpb.dac.myfinances.business.service;

import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {

	String generate(SystemUser user);
	
	Claims getClaims(String token) throws ExpiredJwtException;
	
	boolean isValid(String token);
	
	String getUsername(String token);
	
	Long getUserId(String token);
	
	String get(HttpServletRequest request);
	
}
