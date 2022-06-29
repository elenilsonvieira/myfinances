package br.edu.ifpb.dac.myfinances.presentation.dto;

public class TokenDTO {

	private String token;
	private SystemUserDTO user;
	
	public TokenDTO() {
	}
	public TokenDTO(String token, SystemUserDTO user) {
		super();
		this.token = token;
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public SystemUserDTO getUser() {
		return user;
	}
	public void setUser(SystemUserDTO user) {
		this.user = user;
	}
	
}
