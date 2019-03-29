/**
 * 
 */
package vistas;

import java.util.ArrayList;
import java.util.Scanner;

import controladores.CTratEsteticos;
import funciones.Funciones;
import modelos.Comidas;
import modelos.TratEsteticos;
import modelos.TratNutricion;

/**
 * @author iberlot
 *
 */
public abstract class VTratamientos {

	/* (non-Javadoc)
	 * @see vistas.IvABMs#menu()
	 */
	public static void menu() throws Exception
	{
		System.out.println("ABM de tratamientos");
		System.out.println("1 - Altas de tratamientos");
		System.out.println("2 - Listado de tratamientos");
		System.out.println("3 - Cantidad de tratamientos esteticos");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			m_alta();
			
			break;

		case 2:
			v_listar();
			break;

		case 3:
			System.out.println("Hay un total de " + TratEsteticos.getCantTrarEst() + " tratamientos esteticos");
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
	public void v_alta()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_baja()
	 */
	public void v_baja()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_modificacion()
	 */
	public void v_modificacion()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_alta()
	 */
	public static void m_alta()
	{
		if (Funciones.pedirBooleano("Desea dar de alta un nuevo tratamiento? s/n", "s", "n") == true) {

			if (Funciones.pedirBooleano("Va a ser un tratamiento estetico(1) o nutricional (2)?", "1", "2") == true) {

				if(CTratEsteticos.alta(Menus.getBase().getTratamientos().size(),
						Funciones.pedirString("Ingrese el nombre del nuevo tratamiento: "),
						Funciones.pedirEnteroPositivo("Ingrese la cantidad de seciones de las que constara: "),
						Funciones.pedirFloat("Ingrese el precio por secion: "))==true) {
							
							System.out.println("La carga se hizo correctamente.");
						}
						else 
						{
							System.out.println("Hubo un error durante la carga.");
						}

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
		}
	}

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

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_buscar()
	 */
	public static void m_buscar()
	{
		// TODO Auto-generated method stub

	}
	
	/**
	 * Comprueba de que haya tratamientos cargados, si no lo hay pregunta si desea
	 * dar de alta uno nuevo. Si los hay muestra un listado con el id y el nombre.
	 * 
	 * FIXME creo que deberia mostrar tambien el tipo o mostrar primero uno y luego
	 * el otro.
	 * 
	 * @throws Exception
	 * @see vistas.IvABMs#v_listar()
	 */
	public static void v_listar()
	{
	
			if (Menus.base.getTratamientos().isEmpty() == true) {
				System.out.println("No hay ningun tratamiento cargado.");
				m_alta();
			}

			for (int i = 0; i < tratamientos.size(); i++) {
				System.out.println(i + " - " + tratamientos.get(i).getNombre());
			}

		}
}
