package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Reserva;
import service.ReservasService;


/*Notacion @CrossOrigin
 * 
 * Indica que orígenes (de URL) se quieren admitir en el controller. 
 * Al usar * se permiten peticiones desde cualquier URL
 */

/* Eliminación de @CrossOrigin sustituido 
 * por el Filtro CORS de Gateway
 * @CrossOrigin(origins = "*")*/
@RestController
public class ReservasController {

	@Autowired
	ReservasService service;
	
	@PostMapping(value="reserva/{personas}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void generarReserva(@RequestBody Reserva reserva,
			@PathVariable("personas") int personas) {
		service.realizarReserva(reserva, personas);
	}
	
	@GetMapping(value="reservas", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Reserva> getReservas(){
		return service.getReservas();
	}
	
}
