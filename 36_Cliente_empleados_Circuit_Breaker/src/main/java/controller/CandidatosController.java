package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Candidato;
import service.CandidatosService;

@RestController
public class CandidatosController {

	@Autowired
	CandidatosService service;
	
	@GetMapping(value="candidatos/{puesto}")
	public List<Candidato> candidatosPuesto(@PathVariable("puesto") String puesto){
		return service.candidatos(puesto);
	}
}
