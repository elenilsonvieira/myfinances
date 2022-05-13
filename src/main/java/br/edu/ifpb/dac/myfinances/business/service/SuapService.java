package br.edu.ifpb.dac.myfinances.business.service;

import java.util.HashMap;
import java.util.Map;

public interface SuapService {

	public static final String OBTAIN_TOKEN_URL = "https://suap.ifpb.edu.br/api/jwt/obtain_token/";
	public static final String EMPLOYEES_URL = "https://suap.ifpb.edu.br/api/recursos-humanos/servidores/v1/";
	public static final String STUDENTS_URL = "https://suap.ifpb.edu.br/api/ensino/alunos/v1/";
	
	public static final Map<String, String> DEFAULT_HEADERS = new HashMap<>() {{
		put("Content-Type", "application/json");
	}};
	
	public String login(String username, String password);
	
	public String findEmployee(String username);
	
	public String findEmployee();
	
	public String findStudent(String username);
	
	public String findStudent();
	
}
