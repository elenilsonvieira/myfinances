package br.edu.ifpb.dac.myfinances.business.service;

import java.util.List;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface SystemUserService {

	public SystemUser save(SystemUser systemUser);
	
	public SystemUser update(SystemUser systemUser);
	
	public void delete(Long id);
	
	public SystemUser findById(Long id);
	
	public SystemUser findByEmail(String email);
	
	public Iterable<SystemUser> findAll();
	
	public List<SystemUser> find(SystemUser filter);
	
}
