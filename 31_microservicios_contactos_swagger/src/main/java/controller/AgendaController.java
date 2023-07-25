package controller;

import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.Contacto;
import service.AgendaService;

/*Notacion @CrossOrigin
 * 
 * Indica que orígenes (de URL) se quieren admitir en el controller. 
 * Al usar * se permiten peticiones desde cualquier URL
 */
@CrossOrigin(origins = "*")
@Api(value = "CRUD de contactos")
@RestController
public class AgendaController {
	
	@Autowired
	AgendaService service;
	
	@ApiOperation(value = "Devuelve una lista de contactos existentes", response = List.class, code = 0, hidden = false )
	@GetMapping(value="contactos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contacto> recuperarContactos(){
		return service.recuperarContactos();
	}

	@ApiOperation(value = "Devuelve un contacto por su identificador", response = Contacto.class, code = 1, hidden = false, httpMethod = "GET" )
	@GetMapping(value="contactos/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Contacto recuperarContactos(
		@ApiParam(value = "Identificador del contacto", required = true, allowEmptyValue = false, type = "Integer.class" )
		@PathVariable("id") int id
	){
		return service.buscarContacto(id);
	}

	@ApiOperation(value = "Devuelve una lista de contactos existentes", response = String.class, code = 2, hidden = false )
	@PostMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	public String guardarContacto(
		@ApiParam(value = "Objeto JSON (Contacto.class) a agregar", required = true, allowEmptyValue = true, type="Contacto.class" )
		@RequestBody Contacto contacto
	){
		return String.valueOf(service.agregarContacto(contacto));
	}

	@ApiOperation(value = "Devuelve una lista de contactos existentes", response = Void.class, code = 3, hidden = false)
	@PutMapping(value="contactos", consumes =MediaType.APPLICATION_JSON_VALUE)
	public void actualizarContacto(
		@ApiParam(value = "Objeto JSON (Contacto.class) a actualizar", required = true, allowEmptyValue = true, type="Contacto.class" )
		@RequestBody Contacto contacto
	){
		service.actualizarContactos(contacto);
	}

	@ApiOperation(value = "Devuelve una lista de contactos existentes", response = Void.class, code = 4, hidden = false )
	@DeleteMapping(value="contactos/{id}")
	public void eliminarPorId(
		@ApiParam(value = "Identificador del contacto a eliminar", required = true, allowEmptyValue = false, type="Integer.class" )
		@PathVariable("id") int idContacto
	){
		service.eliminarContacto(idContacto);
	}

	
}
