/**
 * 
 */
package vistas;

import java.util.Scanner;

import funciones.Funciones;

/**
 * @author iberlot
 *
 */
public abstract class VVisitas {

	/* (non-Javadoc)
	 * @see vistas.IvABMs#menu()
	 */
	public static void menu() throws Exception
	{
		System.out.println("ABM de visitas");
		System.out.println("1 - Nueva visita");
		System.out.println("2 - Listar visitas");
		System.out.println("66 - Menu anterior");

		int respuesta = Funciones.pedirEnteroPositivo("");

		switch (respuesta) {
		case 1:
			m_alta();

			break;

		case 2:
			v_listar();
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

	public static void m_alta() throws Exception
	{
		if (Funciones.pedirBooleano("Desea buscar a la persona asociada a la nueva visita? s/n", "s", "n")) {
			VPacientes.m_buscar();
		}
		Menus.base.alta_visita(Funciones.pedirEnteroPositivo("Ingrese el codigo de paciente"));

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

	/* (non-Javadoc)
	 * @see vistas.IvABMs#v_listar()
	 */

	public static void v_listar()
	{
		// TODO Auto-generated method stub

	}

}
