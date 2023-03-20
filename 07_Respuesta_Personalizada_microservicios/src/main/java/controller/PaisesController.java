package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Paises;
import service.PaisesService;

@RestController
public class PaisesController {

	@Autowired
	PaisesService service;
	
	@GetMapping(value="/paises", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Paises> paises(){
		return service.obtenerPaises();
	}
	
	@GetMapping(value="/paises/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Paises> paises(@PathVariable("name") String name){
		return service.buscarPaises(name);
	}
}
