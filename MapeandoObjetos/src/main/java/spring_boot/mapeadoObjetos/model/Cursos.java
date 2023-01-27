package spring_boot.mapeadoObjetos.model;

import org.springframework.stereotype.Component;

public class Cursos {
	
	private String nombre;
	private String horario;
	private int duracion;
	
	public Cursos() {
		super();
	}
	
	

	public Cursos(String nombre, String horario, int duracion) {
		this.nombre = nombre;
		this.horario = horario;
		this.duracion = duracion;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}



	@Override
	public String toString() {
		return "Cursos [nombre=" + nombre + ", horario=" + horario + ", duracion=" + duracion + "]";
	}
	
	
	
}
