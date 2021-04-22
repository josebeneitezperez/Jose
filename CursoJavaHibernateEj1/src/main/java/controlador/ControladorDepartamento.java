package controlador;

import java.util.List;

import org.hibernate.Session;

import clasesDAO.DepartamentoDAO;
import clasesVO.Departamento;

public class ControladorDepartamento {

	public static List<Departamento> buscarTodos() {
		return DepartamentoDAO.findAll();
	}

	public static void insertar(Departamento departamento) {
		DepartamentoDAO.insert(departamento);
	}

	public static void Eliminar(Departamento departamento) {
		DepartamentoDAO.remove(departamento);
	}

	public static void modificar(Departamento departamento) {
		DepartamentoDAO.update(departamento);
	}
	
	public static Departamento get(int id) {
		return DepartamentoDAO.get(id);
	}
}
