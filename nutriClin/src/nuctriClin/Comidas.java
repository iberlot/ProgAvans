/**
 * @file Comidas.java
 * @author iberlot <@> ivanberlot@gmail.com
 * @since 10 mar. 2019
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
public class Comidas {
	protected int id;
	protected String nombre;
	protected String receta;
	protected float calorias;

	/**
	 * 
	 */
	public Comidas() {
	}

	/**
	 * @param id
	 * @param nombre
	 * @param receta
	 * @param calorias
	 */
	public Comidas(int id, String nombre, String receta, float calorias) {
		this.id = id;
		this.nombre = nombre;
		this.receta = receta;
		this.calorias = calorias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " Receta: \n\n " + id + " - " + nombre.toUpperCase() + "\n Calorias: " + calorias + "\n\n  " + receta
				+ "\n";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the receta
	 */
	public String getReceta() {
		return receta;
	}

	/**
	 * @param receta the receta to set
	 */
	public void setReceta(String receta) {
		this.receta = receta;
	}

	/**
	 * @return the calorias
	 */
	public float getCalorias() {
		return calorias;
	}

	/**
	 * @param calorias the calorias to set
	 */
	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}

}
