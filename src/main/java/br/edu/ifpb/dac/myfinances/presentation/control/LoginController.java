package br.edu.ifpb.dac.myfinances.presentation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.business.service.LoginService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;
import br.edu.ifpb.dac.myfinances.presentation.dto.LoginDTO;
import br.edu.ifpb.dac.myfinances.presentation.dto.SystemUserDTO;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginService service;
	@Autowired
	private ConverterService converterService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO dto) {
		try {
			SystemUser entity = service.login(dto.getUsername(), dto.getPassword());
			SystemUserDTO systemUserDTO = converterService.systemUserToDTO(entity);
			
			return new ResponseEntity(systemUserDTO, HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
