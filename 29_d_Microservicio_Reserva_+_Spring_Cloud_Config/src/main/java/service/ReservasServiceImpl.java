package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ReservasDAO;
import model.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService {

	@Autowired
	ReservasDAO dao;
	
	@Autowired
	RestTemplate template;
	
	String url = "http://servicio-vuelos";
	
	@Override
	public void realizarReserva(Reserva reserva, int totalPersonas) {
		dao.generarReserva(reserva);
		/*Como no hay nada que pasar en el cuerpo, 
		 * se pone null*/
		template.put(url + "/vuelos/{p1}/{p2}", null, reserva.getVuelo(), totalPersonas);
	}

	@Override
	public List<Reserva> getReservas() {
		return this.dao.getReservas();
	}

}
