package test.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.api.User;

public class ExternalUserResourceService {
	
	private static URI FAST_ENDPOINT;
	private static URI SLOW_ENDPOINT;

	private HttpClient httpClient;
	private ObjectMapper mapper;
	
	public ExternalUserResourceService(HttpClient httpClient, ObjectMapper mapper){
		this.httpClient = httpClient;
		this.mapper = mapper;
		try {
			FAST_ENDPOINT =  new URI("http://178.62.44.175:9999/fast-user");
			SLOW_ENDPOINT =  new URI("http://178.62.44.175:9999/slow-user");
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User loadFastUser(){
		return load(FAST_ENDPOINT);
	}
	
	public User loadSlowUser(){
		return load(SLOW_ENDPOINT);
	}
	
	
	private User load(URI uri){
		HttpUriRequest reques = new HttpGet(uri);
		try {
			HttpEntity entry = httpClient.execute(reques).getEntity();
			return mapper.readValue(entry.getContent(), User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
