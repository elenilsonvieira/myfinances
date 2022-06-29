package br.edu.ifpb.dac.myfinances.business.service;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface AuthenticationService {

	public String login(String email, String password);
	
	public SystemUser getLoggedUser();
	
}
