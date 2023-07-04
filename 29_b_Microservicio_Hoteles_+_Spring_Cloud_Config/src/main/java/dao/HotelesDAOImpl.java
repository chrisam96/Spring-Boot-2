package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Hotel;

@Repository
public class HotelesDAOImpl implements HotelesDAO {

	@Autowired
	HotelesJPASpring hotelesJPA;
	
	@Override
	public List<Hotel> devolverHoteles() {
		return hotelesJPA.findAll();
	}

}
