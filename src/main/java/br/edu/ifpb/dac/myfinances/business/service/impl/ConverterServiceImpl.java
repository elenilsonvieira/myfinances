package br.edu.ifpb.dac.myfinances.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.model.enums.EntryStatus;
import br.edu.ifpb.dac.myfinances.model.enums.EntryType;
import br.edu.ifpb.dac.myfinances.presentation.dto.EntryDTO;

@Service
public class ConverterServiceImpl implements ConverterService{

	@Override
	public Entry dtoToEntity(EntryDTO dto) {
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
	public EntryDTO entityToDTO(Entry entity) {
		EntryDTO dto = new EntryDTO();
		
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setMonth(entity.getMonth());
		dto.setYear(entity.getYear());
		dto.setValue(entity.getValue());
		dto.setType(entity.getType().toString());
		
		return dto;
	}

	@Override
	public List<EntryDTO> entityToDTO(List<Entry> entities) {
		List<EntryDTO> dtos = new ArrayList<>();
		
		for (Entry entry : entities) {
			EntryDTO dto = entityToDTO(entry);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<Entry> dtoToEntity(List<EntryDTO> dtos) {
		List<Entry> entities = new ArrayList<>();
		
		for (EntryDTO dto : dtos) {
			Entry entry = dtoToEntity(dto);
			entities.add(entry);
		}
		
		return entities;
	}

}
