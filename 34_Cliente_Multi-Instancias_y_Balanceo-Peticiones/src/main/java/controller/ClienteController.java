package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Ficha;

@RestController
public class ClienteController {
	
	private String url = "http://servicio-productos/ficha/";
	
	@Autowired
	private RestTemplate template;
	
	@GetMapping(value="fichas", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Ficha> fichas(){
		List<Ficha> fichas = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			fichas.add(template.getForObject(url+"producto"+i, Ficha.class));
		}
		return fichas;
	}
}
