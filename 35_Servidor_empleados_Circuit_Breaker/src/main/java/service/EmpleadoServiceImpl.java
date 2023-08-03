package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	//Lista que simula datos cargados de alguna BD
	List<Empleado> empleados = Arrays.asList(
		new Empleado("Empl1",1111,"programador"),
		new Empleado("Empl2",2222,"comercial"),
		new Empleado("Empl3",3333,"contable"),
		new Empleado("Empl4",4444,"programador"),
		new Empleado("Empl5",5555,"programador"),
		new Empleado("Empl6",6666,"comercial")
	);
	
	@Override
	public List<Empleado> empleados() {
		return empleados;
	}

}
