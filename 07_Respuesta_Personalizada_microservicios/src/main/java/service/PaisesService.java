package service;

import java.util.List;

import model.Paises;

public interface PaisesService {
	List<Paises> obtenerPaises();
	List<Paises> buscarPaises(String name);
}
