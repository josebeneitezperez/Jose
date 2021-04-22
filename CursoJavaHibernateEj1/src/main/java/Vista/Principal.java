package Vista;

import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesVO.Departamento;
import clasesVO.Empleado;
import controlador.ControladorDepartamento;
import controlador.ControladorEmpleado;
import controlador.HibernateUtil;

public class Principal {

	//private static String ruta = "C:\\Users\\josec\\workspace CursoJava\\CursoJavaHibernateEj1\\resources\\log4j.properties";
	private static String ruta = "C:\\Users\\Formacion\\workspace Jose\\CursoJavaHibernateEj1\\resources\\log4j.properties";
	private static Logger logger = LogManager.getLogger(Principal.class);

	public static SessionFactory sessionFactory;
	private static Scanner teclado = new Scanner(System.in);
	private static MenuGenerico menu;

	public static void main(String[] args) {

		PropertyConfigurator.configure(ruta);
		logger.info("Aplicación iniciada");

		// Session session = HibernateUtil.getSessionFactory().openSession();
		sessionFactory = HibernateUtil.getSessionFactory();

		String[] textoMenu = new String[] { "1) Insertar empleado", "2) Leer empleado", "3) Actualizar empleado",
				"4) Borrar empleado", "5) Insertar departamento", "6) Leer departamento", "7) Actualizar departamento",
				"8) Borrar departamento", "0) Salir" };

		menu = new MenuGenerico(textoMenu);

		int opcion = -2;
		do {
			menu.mostrarMenu();
			System.out.println("Elija una opción: ");
			try {

				opcion = Integer.parseInt(teclado.nextLine());

				if (opcion < 0 || opcion > menu.getNumOpciones()) {
					System.out.println("Error, la opción introducida no es válida");
				} else {
					opcion = seleccionarOpcion(opcion);
				}

			} catch (NumberFormatException e) {
				System.out.println("Error, introduzca un número");
			}

		} while (opcion != MenuGenerico.SALIR);
		
		logger.info("Se salió de la aplicación");
	}

	private static int seleccionarOpcion(int opcion) {

		switch (opcion) {

		case 0: {
			System.out.println("Saliendo de la aplicación");
			return opcion;
		}
		case 1: {
			Empleado empleado = pedirEmpleado();
			ControladorEmpleado.insertar(empleado);
			break;
		}
		case 2: {

			int id = pedirIdEmpleado();
			Empleado empleado = ControladorEmpleado.get(id);
			System.out.println("Leído empleado "+empleado.getNombre());
			break;
		}
		case 3: {
			System.out.println("Se modificará el empleado con el código que proporcione");
			Empleado empleado = pedirEmpleado();
			ControladorEmpleado.modificar(empleado);
			break;
		}
		case 4: {
			int id = pedirIdEmpleado();
			Empleado empleado = ControladorEmpleado.get(id);
			ControladorEmpleado.Eliminar(empleado);
			break;
		}
		case 5: {
			Departamento departamento = pedirDepartamento();
			ControladorDepartamento.insertar(departamento);
			break;
		}
		case 6: {
			int id = pedirIdDepartamento();
			Departamento departamento = ControladorDepartamento.get(id);
			System.out.println(departamento.toString());
			break;
		}
		case 7: {
			System.out.println("Se modificará el departamento con el código que proporcione");
			Departamento departamento = pedirDepartamento();
			ControladorDepartamento.modificar(departamento); // nota: ControladorDepartamento.modificar debería devolver
																// boolean
			break;
		}
		case 8: {
			Departamento departamento = pedirDepartamento();
			ControladorDepartamento.Eliminar(departamento);
			break;
		}
		}
		return 1;
	}

	private static Departamento pedirDepartamento() {

		boolean datosValidos;

		int codigo = -1;
		String nombre;
		int codResponsable = -1;

		do {
			datosValidos = true;
			System.out.println("Introduzca su código: ");
			codigo = teclado.nextInt();
			System.out.println("Introduzca su nombre: ");
			nombre = teclado.nextLine();
			System.out.println("Introduzca su código de responsable: ");
			codResponsable = teclado.nextInt();

			if (codigo == -1 || nombre.isEmpty() || codResponsable == -1) {
				System.out.println("Error, alguno de los campos está vacío");
				datosValidos = false;
			}
			teclado.nextLine(); // salto de línea del teclado.nextInt();
		} while (!datosValidos);

		return new Departamento(codigo, nombre, codResponsable);
	}

	private static int pedirIdEmpleado() {

		boolean datosValidos = true;
		int id = -1;
		String stringId = "";
		do {
			System.out.println("Introduzca el id del empleado: ");
			stringId = teclado.nextLine();

			try {
				id = Integer.parseInt(stringId);
				datosValidos = true;
			} catch (NumberFormatException e) {
				logger.error("Error al convertir de String a Int, error: ", e);
				datosValidos = false;
			}

		} while (!datosValidos);

		return id;
	}

	private static int pedirIdDepartamento() {

		boolean datosValidos = true;
		int id = -1;
		String stringId = "";
		do {
			System.out.println("Introduzca el id del departamento: ");
			stringId = teclado.nextLine();

			try {
				id = Integer.parseInt(stringId);
				datosValidos = true;
			} catch (NumberFormatException e) {
				logger.error("Error al convertir de String a Int, error: ", e);
				datosValidos = false;
			}

		} while (!datosValidos);

		return id;
	}

	private static Empleado pedirEmpleado() {

		boolean datosValidos;

		int codigo = -1;
		String nombre;
		String apellido1;
		String apellido2;
		String lugarNacimiento;
		String fechaNacimiento;
		String direccion;
		String telefono;
		String puesto;
		int codDepartamento = -1;

		do {
			datosValidos = true;

			System.out.println("Introduzca su código: ");
			codigo = teclado.nextInt();
			teclado.nextLine(); // salto de línea del teclado.nextInt();

			System.out.println("Introduzca su nombre: ");
			nombre = teclado.nextLine();
			System.out.println("Introduzca su primer apellido: ");
			apellido1 = teclado.nextLine();
			System.out.println("Introduzca su segundo apellido: ");
			apellido2 = teclado.nextLine();
			System.out.println("Introduzca su lugar de nacimiento: ");
			lugarNacimiento = teclado.nextLine();
			System.out.println("Introduzca su fecha de nacimiento: ");
			fechaNacimiento = teclado.nextLine();
			System.out.println("Introduzca su dirección: ");
			direccion = teclado.nextLine();
			System.out.println("Introduzca su teléfono: ");
			telefono = teclado.nextLine();
			System.out.println("Introduzca su puesto: ");
			puesto = teclado.nextLine();
			System.out.println("Introduzca su código de departamento: ");
			codDepartamento = teclado.nextInt();

			if (codigo == -1 || nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty()
					|| lugarNacimiento.isEmpty() || fechaNacimiento.isEmpty() || direccion.isEmpty()
					|| telefono.isEmpty() || puesto.isEmpty() || codDepartamento == -1) {
				System.out.println("Error, alguno de los campos está vacío");
				datosValidos = false;
			}
			teclado.nextLine(); // salto de línea del teclado.nextInt();
		} while (!datosValidos);

		return new Empleado(codigo, nombre, apellido1, apellido2, lugarNacimiento, fechaNacimiento, direccion, telefono,
				puesto, codDepartamento); // datos
	}
}
