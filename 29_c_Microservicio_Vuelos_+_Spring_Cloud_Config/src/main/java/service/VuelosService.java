package service;

import java.util.List;

import model.Vuelo;

public interface VuelosService {
	
	List<Vuelo> recuperarVuelosDisponibles(int plazas);
	
	void actualizarPlazas (int vuelo, int plazas);
}
