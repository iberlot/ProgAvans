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

package controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import funciones.Archivos;
import funciones.Fechas;
import funciones.Funciones;
import modelos.Comidas;
import modelos.Pacientes;
import modelos.Productos;
import modelos.TratEsteticos;
import modelos.TratNutricion;
import modelos.Tratamientos;
import modelos.Visitas;
import vistas.Menus;

/**
 * @author iberlot <@> ivanberlot@gmail.com
 *
 */
public class Principal {

	static boolean INICIALIZADO = false;
	
	public Principal() {
		try {
			if (INICIALIZADO == false)
			{
				inicializar();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pacientes> getPacientes()
	{
		return pacientes;
	}

	public void setPacientes(ArrayList<Pacientes> pacientes)
	{
		this.pacientes = pacientes;
	}

	public ArrayList<Tratamientos> getTratamientos()
	{
		return tratamientos;
	}

	public void setTratamientos(ArrayList<Tratamientos> tratamientos)
	{
		this.tratamientos = tratamientos;
	}

	public ArrayList<Productos> getProductos()
	{
		return productos;
	}

	public void setProductos(ArrayList<Productos> productos)
	{
		this.productos = productos;
	}

	public ArrayList<Comidas> getComidas()
	{
		return comidas;
	}

	public void setComidas(ArrayList<Comidas> comidas)
	{
		this.comidas = comidas;
	}

	public ArrayList<Visitas> getVisitas()
	{
		return visitas;
	}

	public void setVisitas(ArrayList<Visitas> visitas)
	{
		this.visitas = visitas;
	}

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

	public void listar_visita() {
		// TODO Auto-generated method stub

	}

	/**
	 * Pasandole la posicion del Arraylist de un paciente da de alta la visita correspondiente y se la asocia.
	 * 
	 * @param int idexPaciente - Indice del ArrayList pacientes donde se encuentra el paciente al que se le va a asociar la visita.
	 * @throws Exception
	 */
	public void alta_visita(int idexPaciente) throws Exception {
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

	}


	/**
	 * Muestra por pantalla un listado de los productos registrados.
	 */
	public void  listar_productos() {	
		if (productos.isEmpty() == true) {
		System.out.println("No hay productos registrados.");
	} else {
		System.out.printf("%-5s%-15s%-40s\n", "ID", "Nombre", "Precio");

		for (int i = 0; i < productos.size(); i++) {
			System.out.printf("%-5s%-15s%-40s\n", i, productos.get(i).getNombre(), productos.get(i).getPrecio());
		}

//		int idBuscCom = Funciones.pedirEnteroPositivo("Ingrese el id para ver la receta o 66 para continuar");
//		if (idBuscCom != 66) {
//			System.out.println(comidas.get(idBuscCom));
//		}

	}

	}

	/**
	 * pregunta si se decea crear un nuevo objeto producto y lo agrega al arraylist de estos, luego lo agrega al archivo de datos.
	 * 
	 * @throws IOException
	 */
	public void  alta_productos() throws IOException {
		if (Funciones.pedirBooleano("Decea dar de alta un nuevo producto? s/n", "s", "n") == true) {

			productos.add(new Productos(productos.size(), Funciones.pedirString("Nombre del producto: "),
					Funciones.pedirFloat("Ingrese el precio: ")));

			cargar_archivo(productos.get(productos.size() - 1));
		}
	}

	

	/**
	 * Imprime por pantalla un listado de los pacientes
	 * 
	 * @throws Exception
	 */
	public void listar_pacientes() throws Exception {

		System.out.printf("%-5s%-15s%-40s\n", "ID", "DNI", "Apellido y Nombre");

		for (int i = 0; i < pacientes.size(); i++) {
			System.out.printf("%-5s%-15s%-40s\n", i, pacientes.get(i).getDocumento(),
					pacientes.get(i).getApellido() + ", " + pacientes.get(i).getNombre());
		}

	}

	/**
	 * Pregunta el id del paciente y muestra la infromacion de este por pantalla
	 */
	public void  ver_paciente() {

		System.out.println(pacientes.get(Funciones.pedirEnteroPositivo("Ingrese el ID del paciente")));

	}

	/**
	 * Pide los datos correspondientes al paciente y los agraga a la lista de estos
	 * 
	 * @throws Exception
	 * 
	 */
	public void alta_pacientes() throws Exception {

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
	}


	public void  listarComidas() {

		for (int i = 0; i < comidas.size(); i++) {
			System.out.println(comidas.get(i).getId() + " - " + comidas.get(i).getNombre());
		}
	}

	public void  listarComidas(float menores) {

		for (int i = 0; i < comidas.size(); i++) {
			if (comidas.get(i).getCalorias() < menores) {
				System.out.println(comidas.get(i).getId() + " - " + comidas.get(i).getNombre());
			}
		}
	}

	public void  listarComidas(float menores, float mayores) {
		if (menores > mayores) {
			System.out.println("El limite inferior no puede ser mayor al superios");
		}
		for (int i = 0; i < comidas.size(); i++) {
			if (comidas.get(i).getCalorias() < menores && comidas.get(i).getCalorias() > mayores) {
				System.out.println(comidas.get(i).getId() + " - " + comidas.get(i).getNombre());
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
	public ArrayList<Integer> buscar_pacientes_nombreYApellido(String nombreYApellido) {

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
	public ArrayList<Integer> buscar_pacientes_fechaN(Calendar fecha) {

		ArrayList<Integer> idPaciente = new ArrayList<Integer>();

		for (int i = 0; i < pacientes.size(); i++) {
			if (funciones.Fechas.fechaAString(pacientes.get(i).getfNacimiento(), '/').equals(Fechas.fechaAString(fecha, '/'))) {
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

		ArrayList<String[]> produc = funciones.Archivos.traeLineasParceadas("Datos/Productos", "|");

		for (String[] datos : produc) {
			productos.add(new Productos(Integer.parseInt(datos[0]), datos[1], Float.parseFloat(datos[2])));
		}

		ArrayList<String[]> visitaz = funciones.Archivos.traeLineasParceadas("Datos/Visitas", "|");

		for (String[] datos : visitaz) {
			visitas.add(
					new Visitas(Integer.parseInt(datos[0]), Fechas.stringToCalendar(datos[1], "dd/MM/yyyy"), datos[2]));
		}

		ArrayList<String[]> productoz = funciones.Archivos.traeLineasParceadas("Datos/ProductosXVisitas", "|");

		for (String[] datos : productoz) {
			visitas.get(Integer.parseInt(datos[0])).getProductos().add(productos.get(Integer.parseInt(datos[1])));
		}

		ArrayList<String[]> PacientesXVisitas = funciones.Archivos.traeLineasParceadas("Datos/PacientesXVisitas", "|");

		for (String[] datos : PacientesXVisitas) {
			pacientes.get(Integer.parseInt(datos[0])).getVisitas().add(visitas.get(Integer.parseInt(datos[1])));
		}

		ArrayList<String[]> comidaz = funciones.Archivos.traeLineasParceadas("Datos/Comidas", "|");

		for (String[] datos : comidaz) {
			comidas.add(new Comidas(Integer.parseInt(datos[0]), datos[1], datos[2], Float.parseFloat(datos[3])));
		}

		ArrayList<String[]> nutri = funciones.Archivos.traeLineasParceadas("Datos/TratNutricion", "|");

		for (String[] datos : nutri) {
//			tratamientos.add(new TratNutricion(Integer.parseInt(datos[0]), datos[1], Float.parseFloat(datos[2]),

			tratamientos.add(new TratNutricion(Integer.parseInt(datos[0]), datos[1],
					Float.parseFloat(datos[2]), Float.parseFloat(datos[3])));
		}

		ArrayList<String[]> ComidasXTratamiento = funciones.Archivos.traeLineasParceadas("Datos/ComidasXTratamiento",
				"|");

		for (String[] datos : ComidasXTratamiento) {
			System.out.println(tratamientos.get(Integer.parseInt(datos[0])));

			((TratNutricion) tratamientos.get(Integer.parseInt(datos[0]))).getcomidasPermitidas()
					.add(comidas.get(Integer.parseInt(datos[1])));
		}

		ArrayList<String[]> esteti = funciones.Archivos.traeLineasParceadas("Datos/TratEsteticos", "|");

		for (String[] datos : esteti) {
			tratamientos.add(new TratEsteticos(Integer.parseInt(datos[0]), datos[1],
					Integer.parseInt(datos[2]), Float.parseFloat(datos[3])));

			TratEsteticos.aumentarCantTrarEst();
		}

		ArrayList<String[]> PacientesXTratamiento = funciones.Archivos
				.traeLineasParceadas("Datos/PacientesXTratamiento", "|");

		for (String[] datos : PacientesXTratamiento) {
			pacientes.get(Integer.parseInt(datos[0])).getTratamientos()
					.add(tratamientos.get(Integer.parseInt(datos[1])));

		}
		
		INICIALIZADO = true;

	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	public void cargar_archivo(Pacientes paciente) throws IOException {
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
	public void  cargar_archivo(Productos producto) throws IOException {

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
	public void  cargar_archivo(Visitas visita) throws IOException {

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
	public void  cargar_archivo(Visitas visita, Productos producto) throws IOException {

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
	public void  cargar_archivo(Visitas visita, Pacientes paciente) throws IOException {

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
	public void  cargar_archivo(Tratamientos tratamiento, Pacientes paciente) throws IOException {

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
	public void  cargar_archivo(TratNutricion tratamiento, Comidas comida) throws IOException {

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
	public void  cargar_archivo(TratNutricion tratamientos) throws IOException {

		String[] info = new String[4];

		info[0] = Integer.toString(tratamientos.getNumero());
		info[1] = tratamientos.getNombre();
		info[2] = Float.toString(tratamientos.getCaloriasMaximas());
		info[3] = Float.toString(tratamientos.getCosto());

		File archivo = new File("Datos/TratNutricion");
		Archivos.escribeCamposPipe(archivo, info);
	}



}
