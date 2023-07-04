package dao;

import java.util.List;

import model.Vuelo;

public interface VuelosDAO {
	
	public List<Vuelo> devolverVuelos ();
	
	public Vuelo devolverVuelo (int idVuelo);
	
	public void actualizarVuelo (Vuelo vuelo);
}
