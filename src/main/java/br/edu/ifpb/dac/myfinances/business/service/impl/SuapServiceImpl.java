package br.edu.ifpb.dac.myfinances.business.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.edu.ifpb.dac.myfinances.business.service.SuapService;

@Service
public class SuapServiceImpl implements SuapService{

	@Override
	public String login(String username, String password) {
		Map body = new HashMap<String, String>();
		Gson gson = new Gson();
		String json = gson.toJson(body);
		
		try {
			HttpRequest url = generatePostUrl(OBTAIN_TOKEN_URL, null, json);
			return sendRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();			
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String findEmployee(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findStudent(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findStudent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private HttpRequest generateGetUrl(String url, Map<String, String> headers) throws URISyntaxException {
		Builder builder = HttpRequest.newBuilder().uri(new URI(url));
		
		for (Map.Entry<String, String> header : DEFAULT_HEADERS.entrySet()) {
			builder.setHeader(header.getKey(), header.getValue());
		}
		
		for (Map.Entry<String, String> header : headers.entrySet()) {
			builder.setHeader(header.getKey(), header.getValue());
		}
		
		HttpRequest request = builder.GET().build();
		
		return request;
	}
	
	private HttpRequest generatePostUrl(String url, Map<String, String> headers, String body) throws URISyntaxException {
		Builder builder = HttpRequest.newBuilder().uri(new URI(url));
		
		for (Map.Entry<String, String> header : DEFAULT_HEADERS.entrySet()) {
			builder.setHeader(header.getKey(), header.getValue());
		}
		
		for (Map.Entry<String, String> header : headers.entrySet()) {
			builder.setHeader(header.getKey(), header.getValue());
		}
		
		HttpRequest request = builder.POST(BodyPublishers.ofString(body)).build();
		
		return request;
	}
	
	private String sendRequest(HttpRequest httpRequest) throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
		String response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
		return response;
	}

}
