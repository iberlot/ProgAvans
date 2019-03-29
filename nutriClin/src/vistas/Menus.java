/**
 * 
 */
package vistas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controladores.Principal;
import funciones.Funciones;
import modelos.TratEsteticos;

/**
 * @author iberlot
 *
 */
public abstract class Menus {
	
	private static Principal base = new Principal();
	

	/**
	 * Muestra en pantalla el menu principal de la aplicacion. Captura por teclado
	 * la seleccion de opcion y ejecuta la funcion correspondiente. Luego de
	 * ejecutada vuelve a mostrar el menu.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void imprimirMenu() throws Exception {
		System.out.println("MENU del programa");
		System.out.println("1 - ABM de Pacientes");
		System.out.println("2 - ABM de Tratamientos");
		System.out.println("3 - ABM de Productos");
		System.out.println("4 - ABM de Comidas");
		System.out.println("5 - ABM de visita");
		System.out.println("6 - Crear archivos");
		System.out.println("66 - SALIR");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			VPacientes.menu();
			break;

		case 2:
			VTratamientos.menu();
			break;

		case 3:
			VProductos.menu();
			break;

		case 4:
			VComidas.menu();
			break;

		case 5:
			VVisitas.menu();
			break;
		case 6:
			funciones.Archivos.crearArchivo("Datos/Pacientes");
			funciones.Archivos.crearArchivo("Datos/Tratamientos");
			funciones.Archivos.crearArchivo("Datos/Productos");
			funciones.Archivos.crearArchivo("Datos/Comidas");
			funciones.Archivos.crearArchivo("Datos/Visitas");

		case 66:
			System.exit(0);
			break;

		default:
			imprimirMenu();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		imprimirMenu();

	}

	

	/**
	 * Imprime por pantalla un listado de los pacientes y el menu correspondiente
	 * 
	 * @throws Exception
	 */
	private static void m_listar_pacientes() throws Exception {

		base.listar_pacientes();

		System.out.println("\n");
		System.out.println("Que desea hacer?");
		System.out.println("1 - Buscar un paciente");
		System.out.println("2 - Ver informacion de un paciente");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			VPacientes.m_buscar();
			m_listar_pacientes();
			break;

		case 2:
			base.ver_paciente();
			break;

		case 66:
			VPacientes.menu();
			break;

		default:
			base.listar_pacientes();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		base.listar_pacientes();

	}



	public static Principal getBase()
	{
		return base;
	}



	public static void setBase(Principal base1)
	{
		base = base1;
	}




}
