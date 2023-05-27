package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HotelesDAO;
import model.Hotel;

@Service
public class HotelesServiceImpl implements HotelesService {
	
	@Autowired
	HotelesDAO hotelesDAO;
	
	@Override
	public List<Hotel> devolverHotelesDisponibles() {
		List<Hotel> hoteles = hotelesDAO.devolverHoteles();
		return hoteles
				.stream()
				.filter(hotel -> hotel.getDisponible() == 1 )
				.collect(Collectors.toList());
	}

}
