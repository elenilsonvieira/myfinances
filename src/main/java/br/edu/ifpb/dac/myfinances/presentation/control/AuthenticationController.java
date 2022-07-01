package br.edu.ifpb.dac.myfinances.presentation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.business.service.AuthenticationService;
import br.edu.ifpb.dac.myfinances.business.service.SystemUserService;
import br.edu.ifpb.dac.myfinances.business.service.TokenService;
import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;
import br.edu.ifpb.dac.myfinances.presentation.dto.LoginDTO;
import br.edu.ifpb.dac.myfinances.presentation.dto.SystemUserDTO;
import br.edu.ifpb.dac.myfinances.presentation.dto.TokenDTO;

@RestController
@RequestMapping("/api")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;
	@Autowired
	private ConverterService converterService;
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO dto) {
		try {
			String token = service.login(dto.getUsername(), dto.getPassword());
			SystemUser entity = systemUserService.findByUsername(dto.getUsername());
			SystemUserDTO systemUserDTO = converterService.systemUserToDTO(entity);
			
			TokenDTO tokenDTO = new TokenDTO(token, systemUserDTO);
			
			return new ResponseEntity(tokenDTO, HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/isTokenValid")
	public ResponseEntity isTokenValid(@RequestBody TokenDTO dto) {
		try {
			boolean isTokenValid = tokenService.isValid(dto.getToken());
			
			return new ResponseEntity(isTokenValid, HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
