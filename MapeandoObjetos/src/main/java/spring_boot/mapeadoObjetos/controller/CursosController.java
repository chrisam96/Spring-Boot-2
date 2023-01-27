package spring_boot.mapeadoObjetos.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.mapeadoObjetos.model.Cursos;

@RestController
public class CursosController {

	@GetMapping(value="curso", produces=MediaType.APPLICATION_JSON_VALUE)
	public Cursos getCurso() {
		return new Cursos("Java", "Mañana", 100);
	}
}
