package br.edu.ifpb.dac.myfinances.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.AuthenticationService;
import br.edu.ifpb.dac.myfinances.business.service.EntryService;
import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.model.enums.EntryStatus;
import br.edu.ifpb.dac.myfinances.model.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService{

	@Autowired
	private EntryRepository repository;
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	public Entry save(Entry entry) {		
		if(entry.getId() != null) {
			throw new IllegalStateException("Entry is already in the database. Maybe you can try update it.");
		}
		
		entry.setUser(authenticationService.getLoggedUser());
		entry.setStatus(EntryStatus.PENDING);
		
		return repository.save(entry);
	}

	@Override
	public Entry update(Entry entry) {
		if(entry.getId() == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		return repository.save(entry);
	}

	@Override
	public void delete(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		Entry entry = repository.findById(id).get();
		
		if(entry == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%l", id));
		}
		
		repository.delete(entry);
	}

	@Override
	public Entry findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		return findById(id);
	}

	@Override
	public List<Entry> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Entry> find(Entry filter) {
		Example example = Example.of(filter,
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example);
	}

}
