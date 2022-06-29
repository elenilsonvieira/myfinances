package br.edu.ifpb.dac.myfinances.business.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface PasswordEnconderService extends PasswordEncoder{

	void encryptPassword(SystemUser systemUser);
	
}
