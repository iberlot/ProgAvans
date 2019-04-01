/**
 * @file TratEsteticos.java
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

/**
 * @author IVANB
 *
 */
public class TratEsteticos extends Tratamientos {

	private int cantidadSeciones;
	private float precioSecion;

	private static int cantTrarEst = 0;

	public TratEsteticos() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numero
	 * @param nombre
	 */
	public TratEsteticos(int numero, String nombre) {
		super(numero, nombre);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cantidadSeciones
	 * @param precioSecion
	 */
	public TratEsteticos(int numero, String nombre, int cantidadSeciones, float precioSecion) {

		super(numero, nombre);
		this.cantidadSeciones = cantidadSeciones;
		this.precioSecion = precioSecion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TratEsteticos [cantidadSeciones=" + cantidadSeciones + ", precioSecion=" + precioSecion + ", numero="
				+ numero + ", nombre=" + nombre + "]";
	}

	@Override
	public double calc_precio() {
		return cantidadSeciones * precioSecion;
	}

	/**
	 * @return the cantidadSeciones
	 */
	public int getCantidadSeciones() {
		return cantidadSeciones;
	}

	/**
	 * @param cantidadSeciones the cantidadSeciones to set
	 */
	public void setCantidadSeciones(int cantidadSeciones) {
		this.cantidadSeciones = cantidadSeciones;
	}

	/**
	 * @return the precioSecion
	 */
	public float getPrecioSecion() {
		return precioSecion;
	}

	/**
	 * @param precioSecion the precioSecion to set
	 */
	public void setPrecioSecion(float precioSecion) {
		this.precioSecion = precioSecion;
	}

	/**
	 * @return El valor de cantTrarEst
	 */
	public static int getCantTrarEst() {
		return cantTrarEst;
	}

	/**
	 * @param cantTrarEst para cargar en cantTrarEst
	 */
	public static void setCantTrarEst(int cantTrarEst) {
		TratEsteticos.cantTrarEst = cantTrarEst;
	}

	/**
	 * @param cantTrarEst para cargar en cantTrarEst
	 */
	public static void aumentarCantTrarEst() {
		TratEsteticos.cantTrarEst++;
	}
}
