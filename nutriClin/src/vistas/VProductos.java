/**
 * 
 */
package vistas;

import java.io.*;
import java.util.Scanner;

import funciones.Funciones;

/**
 * @author iberlot
 *
 */
public abstract class VProductos {

	/* (non-Javadoc)
	 * @see vistas.IvABMs#menu()
	 */
	public static void menu() throws Exception
	{	
		System.out.println("ABM de productos");
	System.out.println("1 - Alta de productos");
	System.out.println("2 - Listado de Productos");
	System.out.println("66 - Menu anterior");

	int respuesta = Funciones.pedirEnteroPositivo("");

	switch (respuesta) {
	case 1:
		Menus.base.alta_productos();
		break;

	case 2:
		Menus.base.listar_productos();
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
	public void m_alta()
	{
		// TODO Auto-generated method stub

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
	public void v_listar()
	{
		// TODO Auto-generated method stub

	}

}
