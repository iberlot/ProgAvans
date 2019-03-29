/**
 * 
 */
package controladores;

import java.io.File;
import java.io.IOException;

import funciones.Archivos;
import modelos.Comidas;
import vistas.Menus;

/**
 * @author iberlot
 *
 */
public abstract class CComida {

	public static boolean alta(int size, String pedirString, String pedirString2, float pedirFloat) 
	{
		try {

			Menus.getBase().getComidas().add(new Comidas(size, pedirString, pedirString2, pedirFloat));

			cargar_archivo(Menus.getBase().getComidas().get(Menus.getBase().getComidas().size() - 1));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Recive un paciente y lo graba parceado en el archivo de datos de estos.
	 * 
	 * @param paciente
	 * @throws IOException
	 */
	public static void cargar_archivo(Comidas comida) throws IOException
	{

		String[] info = new String[4];

		info[0] = Integer.toString(comida.getId());
		info[1] = comida.getNombre();
		info[2] = comida.getReceta();
		info[3] = Float.toString(comida.getCalorias());

		File archivo = new File("Datos/Comidas");
		Archivos.escribeCamposPipe(archivo, info);
	}
}
