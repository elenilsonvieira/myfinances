package br.edu.ifpb.dac.myfinances.business.service;

import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.presentation.dto.EntryDTO;

public interface ConverterService {

	Entry dtoToDomain(EntryDTO dto);
	
}
