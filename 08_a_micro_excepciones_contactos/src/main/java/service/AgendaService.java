package service;

import java.util.List;

import model.Contacto;

public interface AgendaService {

	void agregarContacto(Contacto contacto) throws Exception;
	
	List<Contacto> recuperarContactos();
	
	void actualizarContactos(Contacto contacto);
	
	boolean eliminarContacto(int idContacto);
	
	Contacto buscarContacto(int idContacto);
}
