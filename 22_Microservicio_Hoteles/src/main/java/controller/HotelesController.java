package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Hotel;
import service.HotelesService;

/*Notacion @CrossOrigin
 * 
 * Indica que orígenes (de URL) se quieren admitir en el controller. 
 * Al usar * se permiten peticiones desde cualquier URL
 */

/* Eliminación de @CrossOrigin sustituido 
 * por el Filtro CORS de Gateway
 * @CrossOrigin(origins = "*")*/
@RestController
public class HotelesController {

	@Autowired
	HotelesService hotelesService;
	
	@GetMapping(value = "hoteles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> devolverHoteles(){
		return hotelesService.devolverHotelesDisponibles();
	}
}
