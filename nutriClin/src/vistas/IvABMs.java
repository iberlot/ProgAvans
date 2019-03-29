/**
 * @file IABMs.java
 * @author iberlot <@> ivanberlot@gmail.com
 * @todo 20 mar. 2019
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

package vistas;

/**
 * Necesarios basicos para las vistas de un abm
 * 
 * @file IABMs.java
 * @author iberlot <@> ivanberlot@gmail.com
 * @since 20 mar. 2019
 * @version 0.1 - Version de inicio
 *
 */
public interface IvABMs {
	
	public void menu();

	public void v_alta();

	public void v_baja();

	public void v_modificacion();

	public void m_alta();

	public void m_baja();

	public void m_modificacion();

	public void v_buscar();

	public void m_buscar();

	public void v_listar();

	public boid m_listar();
}
