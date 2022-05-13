package br.edu.ifpb.dac.myfinances.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		dto.setStatus(entity.getStatus().toString());
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
		entity.setUsername(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		
		return entity;
	}

	@Override
	public SystemUserDTO systemUserToDTO(SystemUser entity) {
		SystemUserDTO dto = new SystemUserDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		
		return dto;
	}

	@Override
	public String mapToJson(Map<String, String> map) {
		Gson gson = new Gson();
		String json = gson.toJson(map);
		return json;
	}

	@Override
	public SystemUser jsonToUser(String json) {
		JsonElement jsonElement = JsonParser.parseString(json);
		JsonObject results = jsonElement.getAsJsonObject()
										.get("results")
										.getAsJsonArray()
										.get(0)
										.getAsJsonObject();
		
		String name = results.get("nome").getAsString();
		String username = results.get("matricula").getAsString();
		
		SystemUser user = new SystemUser();
		user.setName(name);
		user.setUsername(username);
		
		return user;
	}

	@Override
	public String jsonToToken(String json) {
		JsonElement jsonElement = JsonParser.parseString(json);
		String token = jsonElement.getAsJsonObject().get("token").getAsString();
		return token;
	}

}
