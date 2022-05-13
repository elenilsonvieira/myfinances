package br.edu.ifpb.dac.myfinances.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
	
	Optional<SystemUser> findByEmail(String email);

}
