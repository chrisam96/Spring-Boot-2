package service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import model.Persona;

public interface AccesoService {
	public CompletableFuture<List<Persona>> llamadaServicioAsync(Persona persona);
}
