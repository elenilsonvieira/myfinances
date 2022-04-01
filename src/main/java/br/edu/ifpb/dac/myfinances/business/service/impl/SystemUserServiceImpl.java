package br.edu.ifpb.dac.myfinances.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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
		
		return repository.findById(id).get();
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

}
