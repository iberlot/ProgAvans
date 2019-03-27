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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import funciones.Archivos;
import funciones.Fechas;
import funciones.Funciones;

/**
 * @author IVANB
 *
 */
public class Principal {

	private ArrayList<Pacientes> pacientes = new ArrayList<Pacientes>();
	private ArrayList<Tratamientos> tratamientos = new ArrayList<Tratamientos>();
	private ArrayList<Productos> productos = new ArrayList<Productos>();
	private ArrayList<Comidas> comidas = new ArrayList<Comidas>();
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

	private void m_abm_visitas() throws Exception {
		System.out.println("ABM de visitas");
		System.out.println("1 - Nueva visita");
		System.out.println("2 - Listar visitas");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			alta_visita();
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

	private void alta_visita(int idexPaciente) throws Exception {
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
				if (respuesta > (productos.size() - 1)) {
					System.out.println("El valor ingresado no corresponde a un producto valido");
				} else {
					if (respuesta != 99) {

						productosV.add(productos.get(respuesta));
					}
				}
			} while (respuesta != 99);
		}
		Calendar fecha;

		if (Funciones.pedirBooleano("La visita se realizo hoy? s/n", "s", "n")) {
			fecha = Calendar.getInstance();
		} else {
			fecha = Funciones.pedirFecha("Cuando se realizo la visita");
		}

		visitas.add(new Visitas(visitas.size(), fecha, comentarios, productosV));

		cargar_archivo(visitas.get(visitas.size() - 1));

		for (int i = 0; i < productosV.size(); i++) {
			cargar_archivo(visitas.get(visitas.size() - 1), productosV.get(i));
		}

		cargar_archivo(visitas.get(visitas.size() - 1), pacientes.get(idexPaciente));

		pacientes.get(idexPaciente).getVisitas().add(visitas.get(visitas.size() - 1));

		m_abm_visitas();
	}

	private void m_abm_comidas() throws Exception {
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
			break;

		case 66:
			imprimirMenu();
			break;

		default:
			m_abm_comidas();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		imprimirMenu();

	}

	private void listar_comidas() {
		// TODO Auto-generated method stub

	}

	private void alta_comida() throws IOException {
		if (Funciones.pedirBooleano("Decea dar de alta una nueva comida? s/n", "s", "n") == true) {
			comidas.add(new Comidas(comidas.size(), Funciones.pedirString("Nombre de la comida"),
					Funciones.pedirString("Ingrese la receeta"),
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
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			alta_tratamientos();
			break;

		case 3:
			m_listar_tratamientos();
			break;

		case 66:
			imprimirMenu();
			break;

		default:
			m_abm_tratamientos();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		imprimirMenu();

	}

	private void m_listar_tratamientos() {
		// TODO Auto-generated method stub

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
		System.out.println("66 - Menu anterior");

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
		imprimirMenu();

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

		default:
			m_listar_pacientes();
		}
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		m_listar_pacientes();

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
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			m_buscar_pacientes();
			break;

		case 2:
			ver_paciente();
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

				cargar_archivo((TratEsteticos) tratamientos.get(tratamientos.size() - 1));

			} else {
				String nombre = Funciones.pedirString("Ingrese el nombre del nuevo tratamiento: ");
				float precio = Funciones.pedirFloat("Ingrese el precio por secion: ");
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
					if (respuesta > (comidas.size() - 1)) {
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
		}
		m_abm_tratamientos();
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

	void inicializar() throws Exception {
		ArrayList<String[]> personas = funciones.Archivos.traeLineasParceadas("Datos/Pacientes", "|");

		for (String[] datos : personas) {
			pacientes.add(new Pacientes(Integer.parseInt(datos[0]), Fechas.stringToCalendar(datos[1], "dd/MM/yyyy"),
					datos[2], datos[3], datos[4], Long.parseLong(datos[5]), Boolean.parseBoolean(datos[6])));
		}

//		ArrayList<String[]> prods = funciones.Archivos.traeLineasParceadas("Datos/Productos", "|");
//
//		for (String[] datos : prods) {
//			pacientes.add(new Pacientes(Integer.parseInt(datos[0]), Fechas.stringToCalendar(datos[1], "dd/MM/yyyy"),
//					datos[2], datos[3], datos[4], Long.parseLong(datos[5]), Boolean.parseBoolean(datos[6])));
//		}
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

		String[] info = new String[3];

		info[0] = Integer.toString(visita.getNumero());
		info[1] = visita.getFecha().get(Calendar.DATE) + "/" + (visita.getFecha().get(Calendar.MONTH) + 1) + "/"
				+ visita.getFecha().get(Calendar.YEAR);
		info[2] = visita.getComentarios();

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

}
