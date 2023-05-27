package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Vuelo;
import service.VuelosService;

/*Notacion @CrossOrigin
 * 
 * Indica que orígenes (de URL) se quieren admitir en el controller. 
 * Al usar * se permiten peticiones desde cualquier URL
 */

/* Eliminación de @CrossOrigin sustituido 
 * por el Filtro CORS de Gateway
 * @CrossOrigin(origins = "*")*/
@RestController
public class VuelosController {

	@Autowired
	VuelosService service;
	
	@GetMapping(value="vuelos/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> devolerVuelos (@PathVariable("plazas") int plazas) {
		return service.recuperarVuelosDisponibles(plazas);
	}
	
	@PutMapping(value="vuelos/{idVuelo}/{plazas}")
	public void modificarVuelo(@PathVariable("idVuelo") int id,
			@PathVariable("plazas") int plazas) {
		service.actualizarPlazas(id, plazas);
	}
	
}
