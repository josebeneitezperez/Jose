package controlador;

import java.util.List;

import org.hibernate.Session;

import clasesDAO.EmpleadoDAO;
import clasesVO.Empleado;

public class ControladorEmpleado {

	public static List<Empleado> buscarTodos() {
		return EmpleadoDAO.findAll();
	}

	public static void insertar(Empleado empleado) {
		EmpleadoDAO.insert(empleado);
	}

	public static void Eliminar(Empleado empleado) {
		EmpleadoDAO.remove(empleado);
	}

	public static void modificar(Empleado empleado) {
		EmpleadoDAO.update(empleado);
	}
	
	public static Empleado get(int id) {
		return EmpleadoDAO.get(id);
	}
}
