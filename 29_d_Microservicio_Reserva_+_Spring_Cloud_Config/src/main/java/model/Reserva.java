package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reservas database table.
 * 
 */
@Entity
@Table(name="reservas")
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReserva;

	private String dni;

	private int hotel;

	private String nombre;

	private int vuelo;

	public Reserva() {
	}

	public int getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getHotel() {
		return this.hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVuelo() {
		return this.vuelo;
	}

	public void setVuelo(int vuelo) {
		this.vuelo = vuelo;
	}

}