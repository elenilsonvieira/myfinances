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

@RestController
@RequestMapping("/api/entry")
public class EntryController {

	@Autowired
	private ConverterService converterService;
	@Autowired
	private EntryService entryService;
	@Autowired
	private SystemUserService systemUserService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody EntryDTO dto) {
		try {
			Entry entry = converterService.dtoToEntity(dto);
			entry = entryService.save(entry);
			dto = converterService.entityToDTO(entry);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EntryDTO dto) {
		try {
			dto.setId(id);
			Entry entry = converterService.dtoToEntity(dto);
			entry = entryService.update(entry);
			dto = converterService.entityToDTO(entry);
			
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			entryService.delete(id);
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find(
				@RequestParam(value = "description", required = false) String description,
				@RequestParam(value = "month", required = false) Integer month,
				@RequestParam(value = "year", required = false) Integer year,
				@RequestParam(value = "userId") Long userId
			) {
		try {
			Entry filter = new Entry();
			filter.setDescription(description);
			filter.setMonth(month);
			filter.setYear(year);
			
			SystemUser user = systemUserService.findById(userId);
			
			if(user == null) {
				throw new IllegalStateException(String.format("Cound not find any user with id=%l", userId));
			}
			
			filter.setUser(user);
			
			List<Entry> entities = entryService.find(filter);
			List<EntryDTO> dtos = converterService.entityToDTO(entities);
			
			return ResponseEntity.ok(dtos);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
