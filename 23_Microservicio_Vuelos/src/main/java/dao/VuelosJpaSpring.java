package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Vuelo;

public interface VuelosJpaSpring extends JpaRepository<Vuelo, Integer> {
	
}
