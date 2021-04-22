package clasesDAO;

import java.util.List; 

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Vista.Principal;
import clasesVO.Empleado;

public class EmpleadoDAO {

	private static Logger logger = LogManager.getLogger(EmpleadoDAO.class);
	private static Session sesion = null;

	public static List<Empleado> findAll() {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();

		String query = "from Empleado order by codigo";
		List<Empleado> lista = sesion.createQuery(query).list();

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("Error al ejecutar la Query " + query + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
		return lista;
	}

	public static void insert(Empleado empleado) {

		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		sesion.save(empleado);

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo insertar el empleado con el codigo: " + empleado.getCodigo() + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
	}

	public static void remove(Empleado empleado) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		sesion.remove(empleado);

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo borrar el empleado con el codigo: " + empleado.getCodigo() + " error: ", e);
		}
	}

	public static void update(Empleado empleado) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		sesion.update(empleado);

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo actualizar el empleado " + empleado.getCodigo() + " error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
	}

	public static Empleado get(int id) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		
		System.out.println("ID vale: "+id);
		Empleado unEmpleado = sesion.get(Empleado.class, id);
		
		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo obtener el empleado con el id: " + id + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
		return unEmpleado;
	}
}
