package controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.Contacto;
import service.AgendaService;

/*Notacion @CrossOrigin
 * 
 * Indica que orígenes (de URL) se quieren admitir en el controller. 
 * Al usar * se permiten peticiones desde cualquier URL
 */

/*
[[[ANOTACIONES DEL CONTROLADOR]]]

Spring doc soporta las siguientes anotaciones para la definición
de la clase controladora:

> @Tag: Se coloca delante de la clase controlador para agrupar las operaciones bajo 
una o varias etiquetas (tags). Dichas etiquetas se indican en el atributo "tags".

> @Operation: Utilizada en cada operación expuesta por el controller. El texto 
inicial de ayuda se indica a través de "summary", mientras que en "description" se 
ofrece información más detallada.

> @Parameter: Se utiliza delante de cada parámetro del método controlador para 
informar sobre el uso del mismo. Mediante el atributo description se especifica 
una descripción del parámetro
 */
@CrossOrigin(origins = "*")
@Tag(name = "REST Controller", description = "Metodos del controller de Contactos")
@RestController
public class AgendaController {
	
	@Autowired
	AgendaService service;
	
	@Operation(summary = "contactos", description = "Listado de todos los contactos", 
			hidden = false, operationId = "0", method = "recuperarContactos()")
	@GetMapping(value="contactos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos(){
		return service.recuperarContactos();
	}
	
	@Operation(summary = "contactos/{id}", description = "Obtiene un contacto mediante su ID",
			hidden = false, operationId = "1", method = "recuperarContactos()")
	@GetMapping(value="contactos/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactos(
		@Parameter(name = "id", description = "Identificador del contacto para recuperar", allowEmptyValue = true, required = true)
		@PathVariable("id") int id
	){
		return service.buscarContacto(id);
	}
	
	@Operation(summary = "adicion", description = "Agrega un contacto al enviar un objeto Contactos",
			hidden = false, operationId = "2", method = "guardarContactos()")
	@PostMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	public String guardarContacto(
		//@Parameter(name = "contacto", description = "Objeto Contacto a agregar a la BD", allowEmptyValue = false, required = true)
		@ParameterObject
		@RequestBody Contacto contacto
	){
		return String.valueOf(service.agregarContacto(contacto));
	}
	
	@Operation(summary = "actualizacion", description = "Modifica un objeto Contacto al localizarlo mediante su ID",
			hidden = false, operationId = "3", method = "PUT")
	@PutMapping(value="contactos", consumes =MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(
		@Parameter(name = "contacto", description = "Objeto Contacto para modificar", allowEmptyValue = true, required = true)
		@RequestBody Contacto contacto
	){
		service.actualizarContactos(contacto);
	}
	
	@Operation(summary = "eliminacion", description = "Elimina un contacto por ID",
			hidden = false, operationId = "4", method = "eliminarPorId()")
	@DeleteMapping(value="contactos/{id}")
	public void eliminarPorId(
		@Parameter(name = "id", description = "Identificador del contacto a eliminar", allowEmptyValue = false, required = true)
		@PathVariable("id") int idContacto
	){
		service.eliminarContacto(idContacto);
	}

	
}
