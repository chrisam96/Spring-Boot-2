package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AgendaDAO;
import model.Contacto;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaDAO dao;
	
	@Override
	public boolean agregarContacto(Contacto contacto) {
		//Añade el contacto si no existe
		if (dao.recuperarContacto(contacto.getIdContacto()) == null ) {
			dao.agregarContacto(contacto);
			return true;
		}
		return false;
	}

	@Override
	public List<Contacto> recuperarContactos() {
		/*Agregado para las pruebas asíncronas del proyecto 
		 * "06_micro_async_cliente_contactos" */
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		/*Agregado para las pruebas asíncronas del proyecto 
		 * "06_micro_async_cliente_contactos" */
		
		return dao.devolverContactos();
	}

	@Override
	public void actualizarContactos(Contacto contacto) {
		//actualiza contacto si existe
		if (dao.recuperarContacto(contacto.getIdContacto()) != null ) {
			dao.actualizarContacto(contacto);
			System.out.println("ACTUALIZADO");
			return;
		}
		System.out.println("NO SE ACTUALIZO");
	}

	@Override
	public boolean eliminarContacto(int idContacto) {
		//ELIMINA contacto si existe
		if (dao.recuperarContacto(idContacto) != null ) {
			dao.eliminarContacto(idContacto);
			System.out.println("ELIMINADO");
			return true;
		}
		System.out.println("NO SE ELIMINO");
		return false;
	}

	@Override
	public Contacto buscarContacto(int idContacto) {
		return dao.recuperarContacto(idContacto);
	}

}
