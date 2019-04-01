/**
 * @file Principal.java
 * @author iberlot <@> ivanberlot@gmail.com
 * @todo 10 mar. 2019
 * @version 0.1 - Version de inicio
 */

/*
 * Querido programador:
 *
 * Cuando escribi este codigo, solo Dios y yo sabiamos como funcionaba.
 * Ahora, Solo Dios lo sabe!!!
 *
 * Asi que, si esta tratando de 'optimizar' esta rutina y fracasa (seguramente),
 * por favor, incremente el siguiente contador como una advertencia para el
 * siguiente colega:
 *
 * totalHorasPerdidasAqui = 1
 *
 */

package nuctriClin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import funciones.Archivos;
import funciones.Fechas;
import funciones.Funciones;

/**
 * @author iberlot <@> ivanberlot@gmail.com
 *
 */
public class Principal {

	private static String[] PARAMETROS;

	private static Calendar FECHAACTUAL = Calendar.getInstance();
	/**
	 * Listados de los pacientes que hay en el sistema.
	 * 
	 * @var ArrayList<Pacientes> pacientes
	 */
	private ArrayList<Pacientes> pacientes = new ArrayList<Pacientes>();

	/**
	 * Listados de los tratamientos que hay en el sistema.
	 * 
	 * @var ArrayList<Tratamientos> tratamientos
	 */
	private ArrayList<Tratamientos> tratamientos = new ArrayList<Tratamientos>();

	/**
	 * Listados de los productos que hay en el sistema.
	 * 
	 * @var ArrayList<Productos> productos
	 */
	private ArrayList<Productos> productos = new ArrayList<Productos>();
	/**
	 * Listados de las comidas que hay en el sistema.
	 * 
	 * @var ArrayList<Comidas> comidas
	 */
	private ArrayList<Comidas> comidas = new ArrayList<Comidas>();
	/**
	 * Listados de las visitas que hay en el sistema.
	 * 
	 * @var ArrayList<Visitas> visitas
	 */
	private ArrayList<Visitas> visitas = new ArrayList<Visitas>();

	/**
	 * Muestra en pantalla el menu principal de la aplicacion. Captura por teclado
	 * la seleccion de opcion y ejecuta la funcion correspondiente. Luego de
	 * ejecutada vuelve a mostrar el menu.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 * 
	 */
	public void imprimirMenu() throws Exception {
		System.out.println("MENU del programa");
		System.out.println("1 - ABM de Pacientes");
		System.out.println("2 - ABM de Tratamientos");
		System.out.println("3 - ABM de Productos");
		System.out.println("4 - ABM de Comidas");
		System.out.println("5 - ABM de visita");
		System.out.println("6 - Crear archivos");
		System.out.println("7 - Datos al azar");
		System.out.println("66 - SALIR");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			m_abm_pacientes();
			break;

		case 2:
			m_abm_tratamientos();
			break;

		case 3:
			m_abm_productos();
			break;

		case 4:
			m_abm_comidas();
			break;

		case 5:
			m_abm_visitas();
			break;

		case 6:
			funciones.Archivos.crearArchivo("Datos/Pacientes");
			funciones.Archivos.crearArchivo("Datos/Tratamientos");
			funciones.Archivos.crearArchivo("Datos/Productos");
			funciones.Archivos.crearArchivo("Datos/Comidas");
			funciones.Archivos.crearArchivo("Datos/Visitas");

		case 7:
			m_abm_visitas();
			break;

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
	 * Muestra el menu relacionado al ABM de visitas
	 * 
	 * @throws Exception
	 */
	private void m_abm_visitas() throws Exception {
		System.out.println("ABM de visitas");
		System.out.println("1 - Nueva visita");
		System.out.println("2 - Listar visitas");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			if (Funciones.pedirBooleano("Desea buscar a la persona asociada a la nueva visita? s/n", "s", "n")) {
				m_buscar_pacientes();
			}
			alta_visita(Funciones.pedirEnteroPositivo("Ingrese el codigo de paciente"));
			break;

		case 2:
			listar_visita();
			break;

		case 66:
			imprimirMenu();
			break;

		default:
			m_abm_visitas();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		imprimirMenu();

	}

	private void listar_visita() {
		// TODO Auto-generated method stub

	}

	private void alta_visita(int id) throws Exception {

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();
		idPaciente = buscar_paciente_documento(id);
		double pagar = 0;
		int idexPaciente = -1;

		if (!idPaciente.isEmpty()) {
			for (Integer integer : idPaciente) {
				idexPaciente = integer;
			}
		} else {
			System.out.println("No se han encontrado pacientes con esos datos.");
		}

		String comentarios = Funciones.pedirString("Ingrese los comentarios de la visita: ");

		ArrayList<Productos> productosV = new ArrayList<Productos>();

		if (Funciones.pedirBooleano("se entregaron productos s/n", "s", "n")) {

			System.out.println("Seleccione el producto: ");
			int respuesta;
			do {
				for (int i = 0; i < productos.size(); i++) {
					System.out.println(i + " - " + productos.get(i).getNombre());
				}
				respuesta = Funciones.pedirEnteroPositivo("99 - para continuar");
				if (respuesta > (productos.size() - 1) && (respuesta != 99)) {
					System.out.println("El valor ingresado no corresponde a un producto valido");
				} else {
					if (respuesta != 99) {
						pagar = pagar + productos.get(respuesta).getPrecio();

						productosV.add(productos.get(respuesta));
					}
				}
			} while (respuesta != 99);
		}
		Calendar fecha = Calendar.getInstance();

		if (Funciones.pedirBooleano("La visita se realizo hoy? s/n", "s", "n")) {
			fecha = Calendar.getInstance();
		} else {
			fecha = Funciones.pedirFecha("Cuando se realizo la visita");
		}

		String profe = Funciones.pedirString("Quien es el profecional que atiende?");

		visitas.add(new Visitas(visitas.size(), fecha, comentarios, productosV, profe));

		listar_tratamientos();
		visitas.get(visitas.size() - 1)
				.setIdTratamiento(Funciones.pedirEnteroPositivo("Ingrese el id del tratamiento"));

		cargar_archivo(visitas.get(visitas.size() - 1));

		for (int i = 0; i < productosV.size(); i++) {
			cargar_archivo(visitas.get(visitas.size() - 1), productosV.get(i));
		}

		cargar_archivo(visitas.get((visitas.size() - 1)), pacientes.get(idexPaciente));

		pacientes.get(idexPaciente).getVisitas().add(visitas.get(visitas.size() - 1));

		System.out.println("Debe abonar un total de: ");
		pagar = tratamientos.get(visitas.get(visitas.size() - 1).getIdTratamiento()).calc_precio() + pagar;
		float desc = (float) (pagar * Tratamientos.DESCUENTO / 100);

		if (desc > Tratamientos.TOPE) {
			desc = Tratamientos.TOPE;
		}
		NumberFormat importe = NumberFormat.getCurrencyInstance();
		System.out.println(importe.format(pagar) + " tiene un descuento por pago en efectivo de " + importe.format(desc)
				+ " con lo que le quedaria en " + importe.format((pagar - desc)));

		m_abm_visitas();
	}

	private void m_abm_comidas() throws Exception {

		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("ABM de comidas");
		System.out.println("1 - Altas de comida");
		System.out.println("2 - Listado de comidas");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			alta_comida();
			break;

		case 2:
			listar_comidas();

			System.out.println("Precione una tecla para continuar...");
			stdin.nextLine();
			m_abm_comidas();

			break;

		case 66:
			imprimirMenu();
			break;

		default:
			m_abm_comidas();
		}

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		imprimirMenu();

	}

	/**
	 * Si hay comidas cargadas en el ArrayList comidas muestra un listado de las
	 * mismas permitiendo seleccionar una para ver su receta. En caso de no haber
	 * comidas cargadas mustra un mensaje avisano que no las hay.
	 */
	private void listar_comidas() {
		if (comidas.isEmpty() == true) {
			System.out.println("No hay comidas registradas.");
		} else {
			System.out.printf("%-5s%-15s%-40s\n", "ID", "Nombre", "Calorias");

			for (int i = 0; i < comidas.size(); i++) {
				System.out.printf("%-5s%-15s%-40s\n", i, comidas.get(i).getNombre(), comidas.get(i).getCalorias());
			}

			int idBuscCom = Funciones.pedirEnteroPositivo("Ingrese el id para ver la receta o 66 para continuar");
			if (idBuscCom != 66) {
				System.out.println(comidas.get(idBuscCom));
			}

		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	private void alta_comida() throws IOException {
		if (Funciones.pedirBooleano("Decea dar de alta una nueva comida? s/n", "s", "n") == true) {
			comidas.add(new Comidas(comidas.size(), Funciones.pedirString("Nombre de la comida"),
					Funciones.pedirString("Ingrese la receta"),
					Funciones.pedirFloat("Ingrese la cantidad de calorias")));

			cargar_archivo(comidas.get(comidas.size() - 1));
		}
	}

	private void m_abm_productos() throws Exception {
		System.out.println("ABM de productos");
		System.out.println("1 - Alta de productos");
		System.out.println("2 - Listado de Productos");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			alta_productos();
			break;

		case 2:
			listar_productos();
			break;

		case 66:
			imprimirMenu();
			break;

		default:
			m_abm_productos();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		imprimirMenu();

	}

	private void listar_productos() {
		// TODO Auto-generated method stub

	}

	private void alta_productos() throws IOException {
		if (Funciones.pedirBooleano("Decea dar de alta un nuevo producto? s/n", "s", "n") == true) {

			productos.add(new Productos(productos.size(), Funciones.pedirString("Nombre del producto: "),
					Funciones.pedirFloat("Ingrese el precio: ")));

			cargar_archivo(productos.get(productos.size() - 1));
		}
	}

	private void m_abm_tratamientos() throws Exception {
		System.out.println("ABM de tratamientos");
		System.out.println("1 - Altas de tratamientos");
		System.out.println("2 - Listado de tratamientos");
		System.out.println("3 - Cantidad de tratamientos esteticos");
		System.out.println("4 - Detalles de tratamientos esteticos");
		System.out.println("5 - Buscar info por trat");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			alta_tratamientos();
			m_abm_tratamientos();
			break;

		case 2:
			listar_tratamientos();
			break;

		case 3:
			System.out.println("Hay un total de " + TratEsteticos.getCantTrarEst() + " tratamientos esteticos");
			break;

		case 4:
			detallar_trar_estetic();
			break;

		case 5:
			listar_datos_trat();
			break;

		case 66:
			imprimirMenu();
			break;

		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		m_abm_tratamientos();
	}

	public void listar_datos_trat() {
		for (Tratamientos trato : buscar_trat_nombre_inicio(
				Funciones.pedirString("Intrese las primeras letras del nombre del tratamiento"), 2)) {
			for (Pacientes pacis : buscar_pacientes_tratamiento(trato)) {
				System.out.println(pacis.getDocumento() + " " + pacis.getApYNom());
				System.out.println("vino los dias");
				for (Visitas vici : pacis.getVisitas()) {
					System.out.println(Fechas.fechaAString(vici.getFecha(), '/'));
				}
			}
		}
	}

	public ArrayList<Tratamientos> buscar_trat_nombre_inicio(String nombre, int tam) {

		ArrayList<Tratamientos> tratos = new ArrayList<Tratamientos>();

		for (int i = 0; i < tratamientos.size(); i++) {
			if (tratamientos.get(i).getNombre().substring(0, tam).toUpperCase()
					.equals(nombre.substring(0, tam).toUpperCase())) {
				tratos.add(tratamientos.get(i));
			}
		}

		return tratos;
	}

	public ArrayList<Pacientes> buscar_pacientes_tratamiento(Tratamientos trat) {

		ArrayList<Pacientes> pacos = new ArrayList<Pacientes>();

		for (Pacientes paci : pacientes) {

			for (Visitas visi : paci.getVisitas()) {
				if (visi.getIdTratamiento() == trat.getNumero()) {
					pacos.add(paci);
				}
			}
		}

		return pacos;
	}

	private void detallar_trar_estetic() {
		for (Tratamientos tratamiento : tratamientos) {
			if (tratamiento instanceof TratEsteticos) {

				NumberFormat importe = NumberFormat.getCurrencyInstance();
				// Si se desea forzar el formato español:
				// formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es","ES"));
				System.out.printf("%-35s%-15s\n", "Tratamiento: " + tratamiento.getNombre(),
						importe.format(tratamiento.calc_precio()));

				if (tratamiento.getProductos().isEmpty() == false) {
					System.out.println("Productos asociados");
					for (Productos producto : tratamiento.getProductos()) {
						System.out.printf("%-35s%-15s\n", " * " + producto.getNombre(),
								importe.format(producto.getPrecio()));
					}
				}

				for (Visitas visita : visitas) {
					if (visita.getIdTratamiento() == tratamiento.getNumero()) {
						Pacientes paci = buscar_paciente_visita(visita.getNumero());
						System.out.printf("%-35s%-15s\n", " * " + paci.getApYNom(), paci.getSexo());
					}

				}
			}
		}
	}

	/**
	 * Comprueba de que haya tratamientos cargados, si no lo hay pregunta si desea
	 * dar de alta uno nuevo. Si los hay muestra un listado con el id y el nombre.
	 * 
	 * FIXME creo que deberia mostrar tambien el tipo o mostrar primero uno y luego
	 * el otro.
	 * 
	 * @throws Exception
	 */
	private void listar_tratamientos() throws Exception {
		if (tratamientos.isEmpty() == true) {
			System.out.println("No hay ningun tratamiento cargado.");
			if (Funciones.pedirBooleano("Desea dar de alta un nuevo tratamiento? s/n", "s", "n") == true) {
				alta_tratamientos();
			}
		}

		for (int i = 0; i < tratamientos.size(); i++) {
			System.out.println(i + " - " + tratamientos.get(i).getNombre());
		}

	}

	/**
	 * Muestra por pantalla un menu de ABM de los pacientes y recoge la opcion
	 * seleccionada para derivar a la funcion que corresponda.
	 * 
	 * @throws Exception
	 */
	/**
	 * @throws Exception
	 */
	private void m_abm_pacientes() throws Exception {
		System.out.println("ABM de pacientes");
		System.out.println("1 - Altas de Pacientes");
		System.out.println("2 - Listado de Pacientes");
		System.out.println("3 - Buscar paciente");
		System.out.println("4 - Buscar y modificar");
		System.out.println("66 - Menu anterior");

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			alta_pacientes();
			break;

		case 2:
			m_listar_pacientes();
			break;

		case 3:
			m_buscar_pacientes();
			break;

		case 4:
			idPaciente = buscar_pacientes_nombreYApellido(
					Funciones.pedirString("Ingrese el nombre y apellido a buscar."));

			if (idPaciente.isEmpty()) {
				System.out.println("No se han encontrado pacientes con esos datos.");

			} else {

				for (Integer integer : idPaciente) {
					System.out.println(pacientes.get(integer));

					int anos = FECHAACTUAL.get(Calendar.YEAR)
							- pacientes.get(integer).getfNacimiento().get(Calendar.YEAR);

					System.out.println(("Su edad es de " + ((FECHAACTUAL.get(Calendar.MONTH)
							- pacientes.get(integer).getfNacimiento().get(Calendar.MONTH)) < 0 ? (anos) : (anos + 1))));

					if (Funciones.pedirBooleano("Desea cambiar su estado de obra social? s/n", "s", "n") == true) {
						if (pacientes.get(integer).isObraSocial() == true) {
							pacientes.get(integer).setObraSocial(false);
						} else {
							pacientes.get(integer).setObraSocial(true);
						}
					}

					if (Funciones.pedirBooleano("Desea cambiar su telefono de contacto? s/n", "s", "n") == true) {
						pacientes.get(integer)
								.setTelefono(Funciones.pedirLong("Ingrese el nuevo numero de telefono de contacto"));
					}
				}
			}

			break;

		case 66:
			imprimirMenu();
			break;

		default:
			m_abm_pacientes();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		m_abm_pacientes();

	}

	/**
	 * Muestra el menu de busqueda de pacientes y realiza las llamadas a las
	 * funciones.
	 * 
	 * @throws Exception
	 */
	private void m_buscar_pacientes() throws Exception {

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
			idPaciente = buscar_paciente_documento(
					Funciones.pedirEnteroPositivo("Ingrese el numero de documento a buscar"));

			if (!idPaciente.isEmpty()) {
				for (Integer integer : idPaciente) {
					System.out.println(pacientes.get(integer));
				}
			} else {
				System.out.println("No se han encontrado pacientes con esos datos.");
			}
			break;

		case 2:
			idPaciente = buscar_pacientes_nombreYApellido(
					Funciones.pedirString("Ingrese el nombre y apellido a buscar."));

			if (!idPaciente.isEmpty()) {
				for (Integer integer : idPaciente) {
					System.out.println(pacientes.get(integer));
				}
			} else {
				System.out.println("No se han encontrado pacientes con esos datos.");
			}
			break;

		case 3:
			idPaciente = buscar_pacientes_fechaN(Funciones.pedirFecha("Ingrese la fecha a buscar"));

			if (!idPaciente.isEmpty()) {
				for (Integer integer : idPaciente) {
					System.out.println(pacientes.get(integer));
				}
			} else {
				System.out.println("No se han encontrado pacientes con esos datos.");
			}
			break;

		case 66:
			m_abm_pacientes();
			break;

//		default:
//			m_listar_pacientes();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
//		m_listar_pacientes();

	}

	/**
	 * Imprime por pantalla un listado de los pacientes
	 * 
	 * @throws Exception
	 */
	private void listar_pacientes() throws Exception {

		System.out.printf("%-5s%-15s%-40s\n", "ID", "DNI", "Apellido y Nombre");

		for (int i = 0; i < pacientes.size(); i++) {
			System.out.printf("%-5s%-15s%-40s\n", i, pacientes.get(i).getDocumento(),
					pacientes.get(i).getApellido() + ", " + pacientes.get(i).getNombre());
		}

	}

	/**
	 * Imprime por pantalla un listado de los pacientes y el menu correspondiente
	 * 
	 * @throws Exception
	 */
	private void m_listar_pacientes() throws Exception {

		listar_pacientes();

		System.out.println("\n");
		System.out.println("Que desea hacer?");
		System.out.println("1 - Buscar un paciente");
		System.out.println("2 - Ver informacion de un paciente");
		System.out.println("3 - Ver pacientes que hayan venido el mes pasado");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			m_buscar_pacientes();
			m_listar_pacientes();
			break;

		case 2:
			ver_paciente();
			break;
		case 3:
			ver_paciente_mes_pasado(false);
			break;

		case 66:
			m_abm_pacientes();
			break;

		default:
			listar_pacientes();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		listar_pacientes();

	}

	private void ver_paciente_mes_pasado(boolean os) {

		System.out.printf("%-5s%-15s%-40s\n", "ID", "DNI", "Apellido y Nombre");
		int cant = 0;
		int mes = ((FECHAACTUAL.get(Calendar.MONTH) == 11) ? 0 : (FECHAACTUAL.get(Calendar.MONTH) - 1));

		for (int i = 0; i < pacientes.size(); i++) {
			for (int f = 0; f < pacientes.get(i).getVisitas().size(); f++) {
				if ((pacientes.get(i).isObraSocial() == os)
						&& (pacientes.get(i).getVisitas().get(f).getFecha().get(Calendar.MONTH) == mes)) {
					System.out.printf("%-5s%-15s%-40s\n", i, pacientes.get(i).getDocumento(),
							pacientes.get(i).getApellido() + ", " + pacientes.get(i).getNombre());
					cant++;
				}
			}
		}

		if (cant > 0) {
			System.out.println("Un total de " + cant + " paciente sin OS vinieron el mes pasado.");
		} else {
			System.out.println("\n" + "No hubo pacientes sin OS que vinieran el mes pasado.");

		}
	}

	/**
	 * Pregunta el id del paciente y muestra la infromacion de este por pantalla
	 */
	private void ver_paciente() {

		System.out.println(pacientes.get(Funciones.pedirEnteroPositivo("Ingrese el ID del paciente")));

	}

	/**
	 * Pide los datos correspondientes al paciente y los agraga a la lista de estos
	 * 
	 * @throws Exception
	 * 
	 */
	private void alta_pacientes() throws Exception {

		if (Funciones.pedirBooleano("Decea dar de alta un paciente? s/n", "s", "n") == true) {

			int documento = Funciones.pedirEnteroPositivo("Ingrese el numero de documento");

			if (existe_documento(documento)) {
				System.out.println("El numero ingresado ya se encuentra registrado.");

				@SuppressWarnings("resource")
				Scanner stdin = new Scanner(System.in);

				System.out.println("Precione una tecla para continuar...");
				stdin.nextLine();
			} else {

				pacientes.add(new Pacientes(documento, Funciones.pedirFecha("Ingrese la fecha de nacimiento"),
						Funciones.pedirString("Nombre del paciente"), Funciones.pedirString("Apellido del paciente"),
						Funciones.pedirString("Sexo del paciente"),
						Funciones.pedirLong("Ingrese el numero de Telefono"),
						Funciones.pedirBooleano("Posee obra social? s/n", "s", "n")));

				cargar_archivo(pacientes.get(pacientes.size() - 1));
			}
		}
		m_abm_pacientes();
	}

	/**
	 * Pide los datos correspondientes al paciente y los agraga a la lista de estos
	 * 
	 * @throws Exception
	 * 
	 */
	private void alta_tratamientos() throws Exception {

		if (Funciones.pedirBooleano("Decea dar de alta un nuevo tratamiento? s/n", "s", "n") == true) {

			if (Funciones.pedirBooleano("Va a ser un tratamiento estetico(1) o nutricional (2)?", "1", "2") == true) {

				tratamientos.add(new TratEsteticos(tratamientos.size(),
						Funciones.pedirString("Ingrese el nombre del nuevo tratamiento: "),
						Funciones.pedirEnteroPositivo("Ingrese la cantidad de seciones de las que constara: "),
						Funciones.pedirFloat("Ingrese el precio por secion: ")));

				TratEsteticos.aumentarCantTrarEst();

				cargar_archivo((TratEsteticos) tratamientos.get(tratamientos.size() - 1));

			} else {
				String nombre = Funciones.pedirString("Ingrese el nombre del nuevo tratamiento: ");
				float precio = Funciones.pedirFloat("Ingrese el precio: ");
				float calorias = Funciones.pedirFloat("Ingrese la cantidad maxima de calorias: ");

				ArrayList<Comidas> comidasP = new ArrayList<Comidas>();
				System.out.println("Seleccione las comidas permitidas: ");
				int respuesta;
				do {
					for (int i = 0; i < comidas.size(); i++) {
						if (!comidasP.contains(comidas.get(i))) {
							if (comidas.get(i).getCalorias() < calorias) {
								System.out.println(i + " - " + comidas.get(i).getNombre());
							}
						}
					}
					respuesta = Funciones.pedirEnteroPositivo("99 - para continuar");
					if (respuesta != 99 && respuesta > (comidas.size() - 1)) {
						System.out.println("El valor ingresado no corresponde a un menu valido");
					} else {
						if (respuesta != 99) {
							comidasP.add(comidas.get(respuesta));
						}
					}
				} while (respuesta != 99);
				tratamientos.add(new TratNutricion(tratamientos.size(), nombre, calorias, comidasP, precio));

				cargar_archivo((TratNutricion) tratamientos.get(tratamientos.size() - 1));
				for (int i = 0; i < comidasP.size(); i++) {
					cargar_archivo((TratNutricion) tratamientos.get(tratamientos.size() - 1), comidasP.get(i));
				}
			}

			if (Funciones.pedirBooleano("El tratamiento tiene productos asociados? s/n", "s", "n") == true) {

				System.out.println("Seleccione los productos asociasdos: ");

				int respuesta;
				do {
					for (int i = 0; i < productos.size(); i++) {
						if (!tratamientos.get(tratamientos.size() - 1).getProductos().contains(productos.get(i))) {
							System.out.println(i + " - " + productos.get(i).getNombre());
						}
					}
					respuesta = Funciones.pedirEnteroPositivo("99 - para continuar");
					if (respuesta != 99 && respuesta > (productos.size() - 1)) {
						System.out.println("El valor ingresado no corresponde a un menu valido");
					} else {
						if (respuesta != 99) {
							tratamientos.get(tratamientos.size() - 1).getProductos().add(productos.get(respuesta));

							cargar_archivo(tratamientos.get(tratamientos.size() - 1), productos.get(respuesta));

						}
					}
				} while (respuesta != 99);
			}
		}
	}

	private void listarComidas() {

		for (int i = 0; i < comidas.size(); i++) {
			System.out.println(i + " - " + comidas.get(i).getNombre());
		}
	}

	private void listarComidas(float menores) {

		for (int i = 0; i < comidas.size(); i++) {
			if (comidas.get(i).getCalorias() < menores) {
				System.out.println(i + " - " + comidas.get(i).getNombre());
			}
		}
	}

	private void listarComidas(float menores, float mayores) {
		if (menores > mayores) {
			System.out.println("El limite inferior no puede ser mayor al superios");
		}
		for (int i = 0; i < comidas.size(); i++) {
			if (comidas.get(i).getCalorias() < menores && comidas.get(i).getCalorias() > mayores) {
				System.out.println(i + " - " + comidas.get(i).getNombre());
			}
		}
	}

	/**
	 * Recorre la lista de pacientes para verificar si existe o no un numero de
	 * documento.
	 * 
	 * @param doc Numero de documeto a buscar
	 * @return booleano
	 */
	public boolean existe_documento(int doc) {
		for (Pacientes paciente : pacientes) {
			if (paciente.getDocumento() == doc) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Recorre la lista de comidas para verificar si existe o no un numero de
	 * identificacion. En caso de existir retorna el indice de la lista.
	 * 
	 * @param id Numero de documeto a buscar
	 * @return una lista con los numeros de id de los pacientes
	 */
	public ArrayList<Integer> buscar_comida_id(int id) {
		ArrayList<Integer> idComidas = new ArrayList<Integer>();

		for (int i = 0; i < comidas.size(); i++) {

			if (comidas.get(i).getId() == id) {
				idComidas.add(i);
			}
		}
		return idComidas;
	}

	/**
	 * Recorre la lista de tratamientos para verificar si existe o no un numero de
	 * identificacion. En caso de existir retorna el indice de la lista.
	 * 
	 * @param id Numero de tratamiento a buscar
	 * @return int con el numero de index
	 */
	public int buscar_tratamiento(int id) {
		int index = -1;
		for (int i = 0; i < tratamientos.size(); i++) {

//			System.out.println(id + " <==> " + tratamientos.get(i).getNumero());

			if (tratamientos.get(i).getNumero() == id) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Recorre la lista de productos para verificar si existe o no un numero de
	 * identificacion. En caso de existir retorna el indice de la lista.
	 * 
	 * @param id Numero de identificacion del producto a buscar
	 * @return int con el numero de index
	 */
	public int buscar_producto(int id) {
		int index = -1;
		for (int i = 0; i < productos.size(); i++) {

//			System.out.println(id + " <==> " + tratamientos.get(i).getNumero());

			if (productos.get(i).getCodigo() == id) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Recorre la lista de visitas para verificar si existe o no un numero de
	 * identificacion. En caso de existir retorna el indice de la lista.
	 * 
	 * @param id Numero de identificacion de la visita a buscar
	 * @return int con el numero de index
	 */
	public int buscar_visita(int id) {
		int index = -1;
		for (int i = 0; i < visitas.size(); i++) {

//			System.out.println(id + " <==> " + tratamientos.get(i).getNumero());

			if (visitas.get(i).getNumero() == id) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Recorre la lista de pacientes para verificar si existe o no un numero de
	 * documento. En caso de existir retorna el objeto.
	 * 
	 * @param doc Numero de documeto a buscar
	 * @return una lista con los numeros de id de los pacientes
	 */
	public ArrayList<Integer> buscar_paciente_documento(int doc) {

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();

		for (int i = 0; i < pacientes.size(); i++) {

			if (pacientes.get(i).getDocumento() == doc) {
				idPaciente.add(i);
			}
		}
		return idPaciente;
	}

	/**
	 * Recorre la lista de pacientes para verificar si existe o no un numero de
	 * documento. En caso de existir retorna el index.
	 * 
	 * @param doc Numero de documeto a buscar
	 * @return el index asociado.
	 */
	public int buscar_paciente_index_doc(int id) {

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();
		idPaciente = buscar_paciente_documento(id);

		int idexPaciente = -1;

		if (!idPaciente.isEmpty()) {
			for (Integer integer : idPaciente) {
				idexPaciente = integer;
			}
		}
		return idexPaciente;
	}

	/**
	 * Busca los pacientes que coincidan con determinada cadena en los campos
	 * nopmbre y apellido No realiza busquedas parciales dentro del nombre, por
	 * ejemplo para encontrar a pepe sapo siento este sus dos nombres hace falta
	 * escribir ambos.
	 * 
	 * @param nombreYApellido String con los nombre3s y apellidos a buscar.
	 * @return
	 */
	private ArrayList<Integer> buscar_pacientes_nombreYApellido(String nombreYApellido) {

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();

		for (int i = 0; i < pacientes.size(); i++) {
			if (pacientes.get(i).getNombre().toUpperCase().equals(nombreYApellido.toUpperCase())) {
				idPaciente.add(i);
			} else if (pacientes.get(i).getApellido().toUpperCase().equals(nombreYApellido.toUpperCase())) {
				idPaciente.add(i);
			} else if ((pacientes.get(i).getApellido() + " " + pacientes.get(i).getNombre()).toUpperCase()
					.equals(nombreYApellido.toUpperCase())) {
				idPaciente.add(i);
			} else if ((pacientes.get(i).getNombre() + " " + pacientes.get(i).getApellido()).toUpperCase()
					.equals(nombreYApellido.toUpperCase())) {
				idPaciente.add(i);
			} else if ((pacientes.get(i).getApellido() + ", " + pacientes.get(i).getNombre()).toUpperCase()
					.equals(nombreYApellido.toUpperCase())) {
				idPaciente.add(i);
			}
		}

		return idPaciente;
	}

	/**
	 * Compara la igualdad de la fecha de nacimiento con la fecha pasada
	 * 
	 * @param fecha
	 * @return
	 */
	private ArrayList<Integer> buscar_pacientes_fechaN(Calendar fecha) {

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();

		for (int i = 0; i < pacientes.size(); i++) {
			if (Fechas.fechaAString(pacientes.get(i).getfNacimiento(), '/').equals(Fechas.fechaAString(fecha, '/'))) {
				idPaciente.add(i);
			}
		}

		return idPaciente;

	}

	public Pacientes buscar_paciente_visita(int idVisita) {

		for (int i = 0; i < pacientes.size(); i++) {
			for (int j = 0; j < pacientes.get(i).getVisitas().size(); j++) {
				if (pacientes.get(i).getVisitas().get(j).getNumero() == idVisita) {
					return pacientes.get(i);
				}
			}
		}
		return null;

	}

	public void datos_al_azar() {

		for (int f = 0; f < visitas.size(); f++) {

			int indexTrat = visitas.get(f).getIdTratamiento();

			if (tratamientos.get(indexTrat) instanceof TratNutricion) {
				if (((TratNutricion) tratamientos.get(indexTrat)).getCaloriasMaximas() == Float
						.parseFloat(PARAMETROS[0])) {

					double precio = tratamientos.get(indexTrat).calc_precio();

					if (visitas.get(f).getProductos().isEmpty() == false) {
						for (Productos prod : visitas.get(f).getProductos()) {
							precio = precio + prod.getPrecio();
						}
					}

					if ((int) (Math.random() * 1000) < precio) {
						System.out.printf("%-15s%-15s%-5s%-20s%-40s\n",
								buscar_paciente_visita(visitas.get(f).getNumero()).getApYNom(),
								Fechas.fechaAString(visitas.get(f).getFecha(), '/'), visitas.get(f).getNumero(),
								visitas.get(f).getProfecional(), visitas.get(f).getComentarios());
					}
				}
			}
		}
	}

	void inicializar() throws Exception {
		ArrayList<String[]> personas = funciones.Archivos.traeLineasParceadas("Datos/Pacientes", "|");

		for (String[] datos : personas) {
			pacientes.add(new Pacientes(Integer.parseInt(datos[0]), Fechas.stringToCalendar(datos[1], "dd/MM/yyyy"),
					datos[2], datos[3], datos[4], Long.parseLong(datos[5]), Boolean.parseBoolean(datos[6])));
		}

		ArrayList<String[]> produc = funciones.Archivos.traeLineasParceadas("Datos/Productos", "|");

		for (String[] datos : produc) {
			productos.add(new Productos(Integer.parseInt(datos[0]), datos[1], Float.parseFloat(datos[2])));
		}

		ArrayList<String[]> visitaz = funciones.Archivos.traeLineasParceadas("Datos/Visitas", "|");

		for (String[] datos : visitaz) {
			visitas.add(new Visitas(Integer.parseInt(datos[0]), Fechas.stringToCalendar(datos[1], "dd/MM/yyyy"),
					datos[2], datos[3]));

			visitas.get((visitas.size() - 1)).setPago(Boolean.parseBoolean(datos[4]));
			visitas.get((visitas.size() - 1)).setIdTratamiento(Integer.parseInt(datos[5]));

		}

		ArrayList<String[]> productoz = funciones.Archivos.traeLineasParceadas("Datos/ProductosXVisitas", "|");

		for (String[] datos : productoz) {
			visitas.get(buscar_visita(Integer.parseInt(datos[0]))).getProductos()
					.add(productos.get(buscar_producto(Integer.parseInt(datos[1]))));
		}

		ArrayList<String[]> PacientesXVisitas = funciones.Archivos.traeLineasParceadas("Datos/PacientesXVisitas", "|");

		for (String[] datos : PacientesXVisitas) {
			pacientes.get(buscar_paciente_index_doc(Integer.parseInt(datos[0]))).getVisitas()
					.add(visitas.get(buscar_visita(Integer.parseInt(datos[1]))));
		}

		ArrayList<String[]> comidaz = funciones.Archivos.traeLineasParceadas("Datos/Comidas", "|");

		for (String[] datos : comidaz) {
			comidas.add(new Comidas(Integer.parseInt(datos[0]), datos[1], datos[2], Float.parseFloat(datos[3])));
		}

		ArrayList<String[]> nutri = funciones.Archivos.traeLineasParceadas("Datos/TratNutricion", "|");

		for (String[] datos : nutri) {
			tratamientos.add(new TratNutricion(Integer.parseInt(datos[0]), datos[1], Float.parseFloat(datos[2]),
					Float.parseFloat(datos[3])));
		}

		ArrayList<String[]> ComidasXTratamiento = funciones.Archivos.traeLineasParceadas("Datos/ComidasXTratamiento",
				"|");

		for (String[] datos : ComidasXTratamiento) {

			int indexTrat = buscar_tratamiento(Integer.parseInt(datos[0]));

			if (indexTrat == -1) {
				System.out.println("error index no encontrado");

				for (int i = 0; i < tratamientos.size(); i++) {

					System.out.println(i);
					System.out.println(tratamientos.get(i));
				}

			}

			for (int index : buscar_comida_id(Integer.parseInt(datos[1]))) {

				((TratNutricion) tratamientos.get(indexTrat)).getcomidasPermitidas().add(comidas.get(index));
			}
		}

		ArrayList<String[]> esteti = funciones.Archivos.traeLineasParceadas("Datos/TratEsteticos", "|");

		for (String[] datos : esteti) {
			tratamientos.add(Integer.parseInt(datos[0]), new TratEsteticos(Integer.parseInt(datos[0]), datos[1],
					Integer.parseInt(datos[2]), Float.parseFloat(datos[3])));

			TratEsteticos.aumentarCantTrarEst();
		}

		ArrayList<String[]> PacientesXTratamiento = funciones.Archivos
				.traeLineasParceadas("Datos/PacientesXTratamiento", "|");

		for (String[] datos : PacientesXTratamiento) {
			pacientes.get(Integer.parseInt(datos[0])).getTratamientos()
					.add(tratamientos.get(Integer.parseInt(datos[1])));

		}

	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Pacientes paciente) throws IOException {
//documento|fNacimiento|nombre|apellido|sexo|telefono|obraSocial|

		String[] info = new String[7];

		info[0] = Integer.toString(paciente.getDocumento());
		info[1] = paciente.getfNacimiento().get(Calendar.DATE) + "/"
				+ (paciente.getfNacimiento().get(Calendar.MONTH) + 1) + "/"
				+ paciente.getfNacimiento().get(Calendar.YEAR);
		info[2] = paciente.getNombre();
		info[3] = paciente.getApellido();
		info[4] = paciente.getSexo();
		info[5] = Long.toString(paciente.getTelefono());
		info[6] = Boolean.toString(paciente.isObraSocial());

		File archivo = new File("Datos/Pacientes");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Productos producto) throws IOException {

		String[] info = new String[3];

		info[0] = Integer.toString(producto.getCodigo());
		info[1] = producto.getNombre();
		info[2] = Float.toString(producto.getPrecio());

		File archivo = new File("Datos/Productos");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Comidas comida) throws IOException {

		String[] info = new String[4];

		info[0] = Integer.toString(comida.getId());
		info[1] = comida.getNombre();
		info[2] = comida.getReceta();
		info[3] = Float.toString(comida.getCalorias());

		File archivo = new File("Datos/Comidas");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Visitas visita) throws IOException {

		String[] info = new String[6];

		info[0] = Integer.toString(visita.getNumero());
		info[1] = visita.getFecha().get(Calendar.DATE) + "/" + (visita.getFecha().get(Calendar.MONTH) + 1) + "/"
				+ visita.getFecha().get(Calendar.YEAR);
		info[2] = visita.getComentarios();
		info[3] = visita.getProfecional();
		info[4] = Boolean.toString(visita.isPago());
		info[5] = Integer.toString(visita.getIdTratamiento());

		File archivo = new File("Datos/Visitas");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Visitas visita, Productos producto) throws IOException {

		String[] info = new String[2];

		info[0] = Integer.toString(visita.getNumero());
		info[1] = Integer.toString(producto.getCodigo());

		File archivo = new File("Datos/ProductosXVisitas");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Visitas visita, Pacientes paciente) throws IOException {

		String[] info = new String[2];

		info[0] = Integer.toString(paciente.getDocumento());
		info[1] = Integer.toString(visita.getNumero());

		File archivo = new File("Datos/PacientesXVisitas");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Tratamientos tratamiento, Pacientes paciente) throws IOException {

		String[] info = new String[2];

		info[0] = Integer.toString(paciente.getDocumento());
		info[1] = Integer.toString(tratamiento.getNumero());

		File archivo = new File("Datos/PacientesXTratamiento");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(TratNutricion tratamiento, Comidas comida) throws IOException {

		String[] info = new String[2];

		info[0] = Integer.toString(tratamiento.getNumero());
		info[1] = Integer.toString(comida.getId());

		File archivo = new File("Datos/ComidasXTratamiento");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(Tratamientos tratamiento, Productos producto) throws IOException {

		String[] info = new String[2];

		info[0] = Integer.toString(tratamiento.getNumero());
		info[1] = Integer.toString(producto.getCodigo());

		File archivo = new File("Datos/ProductosXTratamiento");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(TratNutricion tratamientos) throws IOException {

		String[] info = new String[4];

		info[0] = Integer.toString(tratamientos.getNumero());
		info[1] = tratamientos.getNombre();
		info[2] = Float.toString(tratamientos.getCaloriasMaximas());
		info[3] = Float.toString(tratamientos.getCosto());

		File archivo = new File("Datos/TratNutricion");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	private void cargar_archivo(TratEsteticos tratamientos) throws IOException {

		String[] info = new String[4];

		info[0] = Integer.toString(tratamientos.getNumero());
		info[1] = tratamientos.getNombre();
		info[2] = Integer.toString(tratamientos.getCantidadSeciones());
		info[3] = Float.toString(tratamientos.getPrecioSecion());

		File archivo = new File("Datos/TratEsteticos");
		Archivos.escribeCamposPipe(archivo, info);
	}

	/**
	 * @return El valor de pARAMETROS
	 */
	public static String[] getPARAMETROS() {
		return PARAMETROS;
	}

	/**
	 * @param pARAMETROS para cargar en pARAMETROS
	 */
	public static void setPARAMETROS(String[] pARAMETROS) {
		PARAMETROS = pARAMETROS;
	}

	/**
	 * @return El valor de fECHAACTUAL
	 */
	public static Calendar getFECHAACTUAL() {
		return FECHAACTUAL;
	}

	/**
	 * @param fECHAACTUAL para cargar en fECHAACTUAL
	 */
	public static void setFECHAACTUAL(Calendar fECHAACTUAL) {
		FECHAACTUAL = fECHAACTUAL;
	}

	/**
	 * @return El valor de pacientes
	 */
	public ArrayList<Pacientes> getPacientes() {
		return pacientes;
	}

	/**
	 * @param pacientes para cargar en pacientes
	 */
	public void setPacientes(ArrayList<Pacientes> pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * @return El valor de tratamientos
	 */
	public ArrayList<Tratamientos> getTratamientos() {
		return tratamientos;
	}

	/**
	 * @param tratamientos para cargar en tratamientos
	 */
	public void setTratamientos(ArrayList<Tratamientos> tratamientos) {
		this.tratamientos = tratamientos;
	}

	/**
	 * @return El valor de productos
	 */
	public ArrayList<Productos> getProductos() {
		return productos;
	}

	/**
	 * @param productos para cargar en productos
	 */
	public void setProductos(ArrayList<Productos> productos) {
		this.productos = productos;
	}

	/**
	 * @return El valor de comidas
	 */
	public ArrayList<Comidas> getComidas() {
		return comidas;
	}

	/**
	 * @param comidas para cargar en comidas
	 */
	public void setComidas(ArrayList<Comidas> comidas) {
		this.comidas = comidas;
	}

	/**
	 * @return El valor de visitas
	 */
	public ArrayList<Visitas> getVisitas() {
		return visitas;
	}

	/**
	 * @param visitas para cargar en visitas
	 */
	public void setVisitas(ArrayList<Visitas> visitas) {
		this.visitas = visitas;
	}

}
