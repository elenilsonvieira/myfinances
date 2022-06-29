package br.edu.ifpb.dac.myfinances.business.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.PasswordEnconderService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

@Service
public class PasswordEnconderServiceImpl extends BCryptPasswordEncoder implements PasswordEnconderService {

	@Override
	public void encryptPassword(SystemUser systemUser) {
		if(systemUser.getPassword() != null) {
			String encryptedPassword = encode(systemUser.getPassword());
			systemUser.setPassword(encryptedPassword);
		}
	}
}
