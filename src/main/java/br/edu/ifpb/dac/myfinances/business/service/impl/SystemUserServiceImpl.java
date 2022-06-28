package br.edu.ifpb.dac.myfinances.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.SystemUserService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;
import br.edu.ifpb.dac.myfinances.model.repository.SystemUserRepository;

@Service
public class SystemUserServiceImpl implements SystemUserService{

	@Autowired
	private SystemUserRepository repository;
	
	@Override
	public SystemUser save(SystemUser systemUser) {
		if(systemUser.getId() != null) {
			throw new IllegalStateException("User is already in the database. Maybe you can try update it.");
		}
		
		return repository.save(systemUser);
	}

	@Override
	public SystemUser update(SystemUser systemUser) {
		if(systemUser.getId() == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		return repository.save(systemUser);
	}

	@Override
	public void delete(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		SystemUser systemUser = findById(id);
		
		if(systemUser == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%l", id));
		}
		
		repository.delete(systemUser);
	}

	@Override
	public SystemUser findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		Optional<SystemUser> optional = repository.findById(id);
		
		return optional.isPresent() ? optional.get() : null;
	}
	

	@Override
	public SystemUser findByEmail(String email) {
		if(email == null) {
			throw new IllegalStateException("Email cannot be null");
		}
		
		Optional<SystemUser> optional = repository.findByEmail(email); 
		
		return optional.isPresent() ? optional.get() : null;
	}
	
	@Override
	public SystemUser findByUsername(String username) {
		if(username == null) {
			throw new IllegalStateException("Username cannot be null");
		}
		
		Optional<SystemUser> optional = repository.findByUsername(username); 
		
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Iterable<SystemUser> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<SystemUser> find(SystemUser filter) {
		Example example = Example.of(filter,
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUser user = findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Could not find any user with username %s", username));
		}
		
		return user;
	}

}
