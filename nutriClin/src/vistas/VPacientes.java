/**
 * 
 */
package vistas;

import java.util.ArrayList;
import java.util.Scanner;

import funciones.Funciones;

/**
 * @author iberlot
 *
 */
public abstract class VPacientes {


	/**
	 * Muestra por pantalla un menu de ABM de los pacientes y recoge la opcion
	 * seleccionada para derivar a la funcion que corresponda.
	 * @throws Exception 
	 */
	public static void menu() throws Exception {
		System.out.println("ABM de pacientes");
		System.out.println("1 - Altas de Pacientes");
		System.out.println("2 - Listado de Pacientes");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			Menus.base.alta_pacientes();
			menu();
			break;

		case 2:
			v_listar();
			break;

		case 3:
			m_buscar();
			menu();
			break;

		case 66:
			Menus.imprimirMenu();
			break;

		default:
			menu();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		menu();
	}

	
	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_alta()
	 */
	public static void v_alta()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_baja()
	 */
	public static void v_baja()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_modificacion()
	 */
	public static void v_modificacion()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_alta()
	 */
	public static void m_alta()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_baja()
	 */
	public static void m_baja()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_modificacion()
	 */
	public static void m_modificacion()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_buscar()
	 */
	public static void v_buscar()
	{
		// TODO Auto-generated method stub

	}

	/** 
	 * Muestra el menu de busqueda de pacientes y realiza las llamadas a las
	 * funciones.
	 * 
	 * @see vistas.IvABMs#m_buscar()
	 */
	public static void m_buscar()
	{

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();

		System.out.println("\n");
		System.out.println("Que desea hacer?");
		System.out.println("1 - Buscar un paciente por numero de documento.");
		System.out.println("2 - Buscar un paciente por nombre y apellido.");
		System.out.println("3 - Buscar un paciente por fecha de nacimiento.");
		System.out.println("66 - Cancelar");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			idPaciente = Menus.base.buscar_paciente_documento(
					Funciones.pedirEnteroPositivo("Ingrese el numero de documento a buscar"));

			if (!idPaciente.isEmpty()) {
				for (Integer integer : idPaciente) {
					System.out.println(Menus.base.getPacientes().get(integer));
				}
			} else {
				System.out.println("No se han encontrado pacientes con esos datos.");
			}
			break;

		case 2:
			idPaciente = Menus.base.buscar_pacientes_nombreYApellido(
					Funciones.pedirString("Ingrese el nombre y apellido a buscar."));

			if (!idPaciente.isEmpty()) {
				for (Integer integer : idPaciente) {
					System.out.println(Menus.base.getPacientes().get(integer));
				}
			} else {
				System.out.println("No se han encontrado pacientes con esos datos.");
			}
			break;

		case 3:
			idPaciente = Menus.base.buscar_pacientes_fechaN(Funciones.pedirFecha("Ingrese la fecha a buscar"));

			if (!idPaciente.isEmpty()) {
				for (Integer integer : idPaciente) {
					System.out.println(Menus.base.getPacientes().get(integer));
				}
			} else {
				System.out.println("No se han encontrado pacientes con esos datos.");
			}
			break;

		case 66:
			menu();
			break;

		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_listar()
	 */
	public static void v_listar()
	{
		// TODO Auto-generated method stub

	}

	
}
