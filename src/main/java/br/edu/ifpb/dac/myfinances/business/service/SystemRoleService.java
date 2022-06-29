package br.edu.ifpb.dac.myfinances.business.service;

import br.edu.ifpb.dac.myfinances.model.entity.SystemRole;

public interface SystemRoleService {
	
	public enum AVAILABLE_ROLES { ADMIN, USER }

	public void createDefaultValues();
	
	public SystemRole findByName(String name);
	
}
