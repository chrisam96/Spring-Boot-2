package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Contacto;
import service.AgendaService;

@CrossOrigin(origins = "*")
@RestController
public class AgendaController {
	
	@Autowired
	AgendaService service;
	
	/*
	 * HttpHeaders
	 * 
	 * Se pueden agregar encabezados de las dos formas 
	 * pero varían en su forma de agregar datos a los 
	 * encabezados.
	 * 
	 * put():  Se usa cuando el encabezado tiene un 
	 * conjunto o lista de valores.
	 * 
	 * add(): Se usa para agregar un encabezado de la 
	 * forma Clave-Valor.
	 * */
	@GetMapping(value="contactos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contacto>> recuperarContactos(){
		List<Contacto> lista = service.recuperarContactos();
		HttpHeaders headers =  new HttpHeaders();
		headers.add("total", String.valueOf( lista.size() ) );
		//return service.recuperarContactos();
		return new ResponseEntity<List<Contacto>>(lista, headers, HttpStatus.OK);
	}

	@GetMapping(value="contactos/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contacto> recuperarContactos(@PathVariable("id") int id){
		//return service.buscarContacto(id);
		Contacto contacto = service.buscarContacto(id);
		return new ResponseEntity<Contacto>(contacto, HttpStatus.OK);
	}

	//@PostMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	//public String guardarContacto(@RequestBody Contacto contacto){
	@PostMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Void> guardarContacto(@RequestBody Contacto contacto){
		//return String.valueOf(service.agregarContacto(contacto));
		
		//Void Class:: Wrapper para un método "void"
		if(service.agregarContacto(contacto)) {
			return new ResponseEntity<Void>( HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>( HttpStatus.CONFLICT);
		} 
	}

	@PutMapping(value="contactos", consumes =MediaType.APPLICATION_JSON_VALUE)
	//public void actualizarContacto(@RequestBody Contacto contacto){
	public ResponseEntity<Void> actualizarContacto(@RequestBody Contacto contacto){
		service.actualizarContactos(contacto);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@DeleteMapping(value="contactos/{id}")
	//public void eliminarPorId(@PathVariable("id") int idContacto){
	public ResponseEntity<Void> eliminarPorId(@PathVariable("id") int idContacto){
		service.eliminarContacto(idContacto);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	
}
