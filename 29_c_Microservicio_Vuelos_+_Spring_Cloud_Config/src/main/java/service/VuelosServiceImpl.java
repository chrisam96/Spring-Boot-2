package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VuelosDAO;
import model.Vuelo;

@Service
public class VuelosServiceImpl implements VuelosService {

	@Autowired
	VuelosDAO dao;
	
	@Override
	public List<Vuelo> recuperarVuelosDisponibles(int plazas) {		
		return dao.devolverVuelos()
				.stream()
				.filter(vuelo -> vuelo.getPlazas() >= plazas)
				.collect(Collectors.toList());
	}

	@Override
	public void actualizarPlazas(int idVuelo, int plazas) { 
		Vuelo vuelo = dao.devolverVuelo(idVuelo);
		if (vuelo != null) {
			vuelo.setPlazas( vuelo.getPlazas() - plazas );
			dao.actualizarVuelo(vuelo);
		}
	}

}
