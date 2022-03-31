package br.edu.ifpb.dac.myfinances.business.service;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface SystemUserService {

	public SystemUser save(SystemUser systemUser);
	
	public SystemUser update(SystemUser systemUser);
	
	public void delete(Long id);
	
	public SystemUser findById(Long id);
	
	public Iterable<SystemUser> findAll();
	
}
