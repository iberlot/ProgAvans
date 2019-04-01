/**
 * @file Visitas.java
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

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author IVANB
 *
 */
public final class Visitas {
	private int numero;
	private Calendar fecha = Calendar.getInstance();
	private String comentarios;
	private String profecional;
	private boolean pago = false;
	private int idTratamiento;

	private ArrayList<Productos> productos = new ArrayList<Productos>();

	/**
	 * @return
	 */
	public int siguiente_numero() {
		return numero + 1;
	}

	/**
	 * 
	 */
	public Visitas() {
		super();
	}

	/**
	 * @param numero
	 * @param fecha
	 * @param comentarios
	 * @param productos
	 */
	public Visitas(int numero, Calendar fecha, String comentarios, ArrayList<Productos> productos) {
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.comentarios = comentarios;
		this.productos = productos;
	}

	public Visitas(int numero, Calendar fecha, String comentarios) {

		this.numero = numero;
		this.fecha = fecha;
		this.comentarios = comentarios;
	}

	public Visitas(int numero, Calendar fecha, String comentarios, String profe) {

		this.numero = numero;
		this.fecha = fecha;
		this.comentarios = comentarios;
		this.profecional = profe;
	}

	public Visitas(int numero, Calendar fecha, String comentarios, ArrayList<Productos> productos, String profe) {

		this.numero = numero;
		this.fecha = fecha;
		this.comentarios = comentarios;
		this.profecional = profe;
		this.productos = productos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Visitas [numero=" + numero + ", fecha=" + fecha + ", comentarios=" + comentarios + ", productos="
				+ productos + "]";
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the fecha
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the productos
	 */
	public ArrayList<Productos> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(ArrayList<Productos> productos) {
		this.productos = productos;
	}

	/**
	 * @return El valor de profecional
	 */
	public String getProfecional() {
		return profecional;
	}

	/**
	 * @param profecional para cargar en profecional
	 */
	public void setProfecional(String profecional) {
		this.profecional = profecional;
	}

	/**
	 * @return El valor de pago
	 */
	public boolean isPago() {
		return pago;
	}

	/**
	 * @param pago para cargar en pago
	 */
	public void setPago(boolean pago) {
		this.pago = pago;
	}

	/**
	 * @return El valor de idTratamiento
	 */
	public int getIdTratamiento() {
		return idTratamiento;
	}

	/**
	 * @param idTratamiento para cargar en idTratamiento
	 */
	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}
}
