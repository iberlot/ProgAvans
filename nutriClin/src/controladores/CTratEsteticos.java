package controladores;

import java.io.File;
import java.io.IOException;

import funciones.Archivos;
import funciones.Funciones;
import modelos.TratEsteticos;
import vistas.Menus;

public class CTratEsteticos {

	public static boolean alta(int numero, String nombre, int cantidad, float precio)
	{
		try {

			Menus.getBase().getTratamientos().add(new TratEsteticos(numero, nombre, cantidad, precio));

			TratEsteticos.aumentarCantTrarEst();

			cargar_archivo((TratEsteticos) Menus.getBase().getTratamientos()
					.get(Menus.getBase().getTratamientos().size() - 1));
			
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
	public static void cargar_archivo(TratEsteticos tratamientos) throws IOException
	{

		String[] info = new String[4];

		info[0] = Integer.toString(tratamientos.getNumero());
		info[1] = tratamientos.getNombre();
		info[2] = Integer.toString(tratamientos.getCantidadSeciones());
		info[3] = Float.toString(tratamientos.getPrecioSecion());

		File archivo = new File("Datos/TratEsteticos");
		Archivos.escribeCamposPipe(archivo, info);
	}
}
