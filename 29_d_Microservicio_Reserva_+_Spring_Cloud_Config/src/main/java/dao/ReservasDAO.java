package dao;

import java.util.List;

import model.Reserva;

public interface ReservasDAO {
	
	public void generarReserva(Reserva reserva);
	
	List<Reserva> getReservas();
}
