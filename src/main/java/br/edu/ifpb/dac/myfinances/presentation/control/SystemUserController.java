package br.edu.ifpb.dac.myfinances.presentation.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.business.service.EntryService;
import br.edu.ifpb.dac.myfinances.business.service.SystemUserService;
import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;
import br.edu.ifpb.dac.myfinances.presentation.dto.EntryDTO;
import br.edu.ifpb.dac.myfinances.presentation.dto.SystemUserDTO;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {

	@Autowired
	private ConverterService converterService;
	@Autowired
	private SystemUserService service;
	
	@PostMapping
	public ResponseEntity save(@RequestBody SystemUserDTO dto) {
		try {
			SystemUser entity = converterService.dtoToSystemUser(dto);
			entity = service.save(entity);
			dto = converterService.systemUserToDTO(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody SystemUserDTO dto) {
		try {
			dto.setId(id);
			SystemUser entity = converterService.dtoToSystemUser(dto);
			entity = service.update(entity);
			dto = converterService.systemUserToDTO(entity);
			
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find(
				@RequestParam(value = "name", required = false) String name,
				@RequestParam(value = "email", required = false) String email,
				@RequestParam(value = "id", required = false) Long id
			) {
		try {
			SystemUser filter = new SystemUser();
			filter.setId(id);
			filter.setName(name);
			filter.setEmail(email);
			
			List<SystemUser> entities = service.find(filter);
			List<SystemUserDTO> dtos = converterService.systemUserToDTO(entities);
			
			return ResponseEntity.ok(dtos);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
