/**
 * @file TratNutricion.java
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

/**
 * @author iberlot
 *
 */
public class TratNutricion extends Tratamientos {

	private float caloriasMaximas;
	private ArrayList<Comidas> camidasPermitidas;
	private float costo;

	/**
	 * 
	 */
	public TratNutricion() {
		super();
	}

	/**
	 * @param caloriasMaximas
	 * @param camidasPermitidas
	 * @param costo
	 */
	public TratNutricion(float caloriasMaximas, ArrayList<Comidas> camidasPermitidas, float costo) {
		super();
		this.caloriasMaximas = caloriasMaximas;
		this.camidasPermitidas = camidasPermitidas;
		this.costo = costo;
	}

	/**
	 * @param numero
	 * @param nombre
	 */
	public TratNutricion(int numero, String nombre, float caloriasMaximas, ArrayList<Comidas> camidasPermitidas,
			float costo) {
		super(numero, nombre);

		this.caloriasMaximas = caloriasMaximas;
		this.camidasPermitidas = camidasPermitidas;
		this.costo = costo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TratNutricion [numero=" + numero + ", nombre=" + nombre + "caloriasMaximas=" + caloriasMaximas
				+ ", camidasPermitidas=" + camidasPermitidas + ", costo=" + costo + "]";
	}

	/**
	 * @return the caloriasMaximas
	 */
	public float getCaloriasMaximas() {
		return caloriasMaximas;
	}

	/**
	 * @param caloriasMaximas the caloriasMaximas to set
	 */
	public void setCaloriasMaximas(float caloriasMaximas) {
		this.caloriasMaximas = caloriasMaximas;
	}

	/**
	 * @return the camidasPermitidas
	 */
	public ArrayList<Comidas> getCamidasPermitidas() {
		return camidasPermitidas;
	}

	/**
	 * @param camidasPermitidas the camidasPermitidas to set
	 */
	public void setCamidasPermitidas(ArrayList<Comidas> camidasPermitidas) {
		this.camidasPermitidas = camidasPermitidas;
	}

	/**
	 * @return the costo
	 */
	public float getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(float costo) {
		this.costo = costo;
	}

	@Override
	public double calc_precio() {
		// TODO Auto-generated method stub
		return 0;
	}

}
