package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Contacto;

@Repository
public class AgendaDAOImpl implements AgendaDAO {

	/*
	 * Spring inyectará una implementacion de AgendaJPASpring.
	 * Algunos metodos son heredados de JPARepository pero
	 * pero otros mpetodos tenemos que definirlos nosotros
	 */
	@Autowired
	AgendaJPASpring agenda;
	
	//HEREDADO DE JPA
	@Override
	public void agregarContacto(Contacto contacto) {
		agenda.save(contacto);
	}

	//DEFINIDO POR MÍ
	@Override
	public Contacto recuperarContacto(String email) {
		return agenda.findByEmail(email);
	}

	//DEFINIDO POR MI
	@Override
	public void eliminarContacto(String email) {
		agenda.eliminarPorEmail(email);
	}

	//HEREDADO DE JPA
	@Override
	public List<Contacto> devolverContactos() {
		return agenda.findAll();
	}

	//HEREDADO DE JPA
	@Override
	public void eliminarContacto(int idContacto) {
		agenda.deleteById(idContacto);
	}

	//HEREDADO DE JPA
	@Override
	public Contacto recuperarContacto(int idContacto) {
		//Eso devuelve un Optional
		return agenda.findById(idContacto).orElse(null);
	}

	//HEREDADO DE JPA
	@Override
	public void actualizarContacto(Contacto contacto) {
		//El metodo save() también hace de update()
		agenda.save(contacto);
	}

}
