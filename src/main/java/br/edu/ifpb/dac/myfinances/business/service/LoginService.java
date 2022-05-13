package br.edu.ifpb.dac.myfinances.business.service;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface LoginService {

	public SystemUser login(String email, String password);
	
}
