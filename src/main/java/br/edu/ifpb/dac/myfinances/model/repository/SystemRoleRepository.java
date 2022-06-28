package br.edu.ifpb.dac.myfinances.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface SystemRoleRepository extends JpaRepository<SystemUser, Long> {
	
}
