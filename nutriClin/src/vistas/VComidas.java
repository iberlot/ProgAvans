/**
 * 
 */
package vistas;

import java.util.Scanner;

import controladores.CComida;
import funciones.Funciones;
import modelos.Comidas;

/**
 * @author iberlot
 *
 */
public abstract class VComidas {

	public static void menu() throws Exception
	{

		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		System.out.println("ABM de comidas");
		System.out.println("1 - Altas de comida");
		System.out.println("2 - Listado de comidas");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			Menus.base.alta_comida();
			break;

		case 2:
			v_listar();
			v_receta();
			break;

		case 66:
			Menus.imprimirMenu();
			break;

		default:
			menu();
		}

		System.out.println("Precione una tecla para continuar...");
		stdin.nextLine();
		Menus.imprimirMenu();

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
	public void m_alta()
	{
		if (Funciones.pedirBooleano("Decea dar de alta una nueva comida? s/n", "s", "n") == true) {
			if(CComida.alta(Menus.getBase().getComidas().size(), Funciones.pedirString("Nombre de la comida"),
					Funciones.pedirString("Ingrese la receta"),
					Funciones.pedirFloat("Ingrese la cantidad de calorias"))==true) {
				
				System.out.println("La carga se hizo correctamente.");
			}
			else
			{
				System.out.println("Hubo un error durante la carga.");
			}
		}
	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_baja()
	 */
	public void m_baja()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_modificacion()
	 */
	public void m_modificacion()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_buscar()
	 */
	public void v_buscar()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see vistas.IvABMs#m_buscar()
	 */
	public void m_buscar()
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Si hay comidas cargadas en el ArrayList comidas muestra un listado de las
	 * mismas permitiendo seleccionar una para ver su receta. En caso de no haber
	 * comidas cargadas mustra un mensaje avisano que no las hay.
	 *
	 * @see vistas.IvABMs#v_listar()
	 */
	public static void v_listar()
	{

			if (Menus.base.getComidas().isEmpty() == true) {
				System.out.println("No hay comidas registradas.");
			} else {
				System.out.printf("%-5s%-15s%-40s\n", "ID", "Nombre", "Calorias");

				for (int i = 0; i < Menus.base.getComidas().size(); i++) {
					System.out.printf("%-5s%-15s%-40s\n", i, Menus.base.getComidas().get(i).getNombre(), Menus.base.getComidas().get(i).getCalorias());
				}


		}
	}
	
	public static void v_receta() {

		int idBuscCom = Funciones.pedirEnteroPositivo("Ingrese el id para ver la receta o 66 para continuar");
		if (idBuscCom != 66) {
			System.out.println(Menus.base.getComidas().get(idBuscCom));
		}
	}

}
