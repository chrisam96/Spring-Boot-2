package model;

public class Candidato {
	public String nombre; 
	public int dni; 
	public String puesto;
	
	public Candidato() {
	}

	public Candidato(String nombre, int dni, String puesto) {
		this.nombre = nombre;
		this.dni = dni;
		this.puesto = puesto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	
}
