package br.edu.ifpb.dac.myfinances.business.service;

import java.util.List;

import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.presentation.dto.EntryDTO;

public interface ConverterService {

	Entry dtoToEntity(EntryDTO dto);
	
	EntryDTO entityToDTO(Entry entity);

	List<EntryDTO> entityToDTO(List<Entry> entities);
	
	List<Entry> dtoToEntity(List<EntryDTO> dtos);
	
}
