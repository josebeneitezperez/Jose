package clasesDAO;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Vista.Principal;
import clasesVO.Departamento;
import clasesVO.Empleado;

public class DepartamentoDAO {

	private static Logger logger = LogManager.getLogger(Departamento.class);
	private static Session sesion = null;

	public static List<Departamento> findAll() {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();

		String query = "from Departamento order by codigo";
		List<Departamento> lista = sesion.createQuery(query).list();

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

	public static void insert(Departamento departamento) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		sesion.save(departamento);

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo insertar el departamento con el codigo: " + departamento.getCodigo() + ", error: ",
					e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
	}

	public static void remove(Departamento departamento) {
		Session sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		sesion.remove(departamento);

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo borrar el departamento con el codigo: " + departamento.getCodigo() + " error: ",
					e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
	}

	public static void update(Departamento departamento) {
		Session sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		sesion.update(departamento);

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo actualizar el departamento " + departamento.getCodigo() + " error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
	}

	public static Departamento get(int id) {
		Session sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		Departamento unDepartamento = sesion.get(Departamento.class, id);

		try {
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo obtener el departamento con el codigo " + id + " error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
		return unDepartamento;
	}
}
