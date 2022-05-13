package br.edu.ifpb.dac.myfinances.business.service;

import java.util.List;
import java.util.Map;

import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;
import br.edu.ifpb.dac.myfinances.presentation.dto.EntryDTO;
import br.edu.ifpb.dac.myfinances.presentation.dto.SystemUserDTO;

public interface ConverterService {

	Entry dtoToEntry(EntryDTO dto);
	
	EntryDTO entryToDTO(Entry entity);

	List<EntryDTO> entryToDTO(List<Entry> entities);
	
	List<Entry> dtoToEntry(List<EntryDTO> dtos);

	List<SystemUserDTO> systemUserToDTO(List<SystemUser> entities);
	
	SystemUser dtoToSystemUser(SystemUserDTO dto);
	
	SystemUserDTO systemUserToDTO(SystemUser entity);
	
	String mapToJson(Map<String, String> map);

	SystemUser jsonToUser(String json);

	String jsonToToken(String json);
	
}
