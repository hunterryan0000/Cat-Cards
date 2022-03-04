package com.techelevator.services;

import org.jboss.logging.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	public static final String API_BASE_URL = "https://cat-data.netlify.app/api/pictures/random/";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {
		return restTemplate.getForObject(API_BASE_URL, CatPic.class);
	}

}	
