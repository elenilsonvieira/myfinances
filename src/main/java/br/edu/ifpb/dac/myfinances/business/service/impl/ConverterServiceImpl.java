package br.edu.ifpb.dac.myfinances.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;
import br.edu.ifpb.dac.myfinances.model.enums.EntryStatus;
import br.edu.ifpb.dac.myfinances.model.enums.EntryType;
import br.edu.ifpb.dac.myfinances.presentation.dto.EntryDTO;
import br.edu.ifpb.dac.myfinances.presentation.dto.SystemUserDTO;

@Service
public class ConverterServiceImpl implements ConverterService{

	@Override
	public Entry dtoToEntry(EntryDTO dto) {
		Entry entity = new Entry();
		
		entity.setId(dto.getId());
		entity.setDescription(dto.getDescription());
		entity.setMonth(dto.getMonth());
		entity.setYear(dto.getYear());
		entity.setValue(dto.getValue());
		
		if(dto.getType() != null) {
			EntryType type = EntryType.valueOf(dto.getType());
			entity.setType(type);
		}
		
		if(dto.getStatus() != null) {
			EntryStatus status = EntryStatus.valueOf(dto.getStatus());
			entity.setStatus(status);
		}
		
		return entity;
	}

	@Override
	public EntryDTO entryToDTO(Entry entity) {
		EntryDTO dto = new EntryDTO();
		
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setMonth(entity.getMonth());
		dto.setYear(entity.getYear());
		dto.setValue(entity.getValue());
		dto.setType(entity.getType().toString());
		dto.setUserId(entity.getUser().getId().toString());
		
		return dto;
	}

	@Override
	public List<EntryDTO> entryToDTO(List<Entry> entities) {
		List<EntryDTO> dtos = new ArrayList<>();
		
		for (Entry entry : entities) {
			EntryDTO dto = entryToDTO(entry);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<Entry> dtoToEntry(List<EntryDTO> dtos) {
		List<Entry> entities = new ArrayList<>();
		
		for (EntryDTO dto : dtos) {
			Entry entity = dtoToEntry(dto);
			entities.add(entity);
		}
		
		return entities;
	}

	@Override
	public List<SystemUserDTO> systemUserToDTO(List<SystemUser> entities) {
		List<SystemUserDTO> dtos = new ArrayList<>();
		
		for (SystemUser dto : entities) {
			SystemUserDTO entity = systemUserToDTO(dto);
			dtos.add(entity);
		}
		
		return dtos;
	}

	@Override
	public SystemUser dtoToSystemUser(SystemUserDTO dto) {
		SystemUser entity = new SystemUser();
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		
		return entity;
	}

	@Override
	public SystemUserDTO systemUserToDTO(SystemUser entity) {
		SystemUserDTO dto = new SystemUserDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		
		return dto;
	}

}
