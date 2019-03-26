/**
 * @file Pacientes.java
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import funciones.Fechas;

/**
 * @author iberlot
 *
 */
public class Pacientes implements ICalculable {
	protected int documento;
	protected Calendar fNacimiento;
	protected String nombre;
	protected String apellido;
	protected String sexo;
	protected long telefono;
	protected boolean obraSocial;
	protected ArrayList<Tratamientos> tratamientos;
	protected ArrayList<Visitas> visitas;

	/**
	 * 
	 */
	public Pacientes() {
	}

	/**
	 * @param documento
	 * @param fNacimiento
	 * @param nombre
	 * @param apellido
	 * @param sexo
	 * @param telefono
	 * @param obraSocial
	 * @param tratamientos
	 * @param visitas
	 */
	public Pacientes(int documento, Calendar fNacimiento, String nombre, String apellido, String sexo, long telefono,
			boolean obraSocial) {
		this.documento = documento;
		this.fNacimiento = fNacimiento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.telefono = telefono;
		this.obraSocial = obraSocial;
		this.tratamientos = new ArrayList<Tratamientos>();
		this.visitas = new ArrayList<Visitas>();
	}

	/**
	 * @param documento
	 * @param fNacimiento
	 * @param nombre
	 * @param apellido
	 * @param sexo
	 * @param telefono
	 * @param obraSocial
	 * @param tratamientos
	 * @param visitas
	 */
	public Pacientes(int documento, Calendar fNacimiento, String nombre, String apellido, String sexo, long telefono,
			boolean obraSocial, ArrayList<Tratamientos> tratamientos, ArrayList<Visitas> visitas) {
		this.documento = documento;
		this.fNacimiento = fNacimiento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.telefono = telefono;
		this.obraSocial = obraSocial;
		this.tratamientos = tratamientos;
		this.visitas = visitas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " Ficha de Paciente\n\n " + apellido + ", " + nombre + "\n documento: " + documento
				+ "\n fecha de nacimiento: " + Fechas.fechaAString(fNacimiento, '/') + "\t sexo: " + sexo
				+ "\n telefono: " + telefono + "\n obraSocial: " + obraSocial + "\n";
	}

	/**
	 * Comprueba que el dato de fecha de nacimiento sea mayor a 15
	 * 
	 * @return boolean
	 * @throws ParseException
	 */
	public boolean comprobar_fNac() throws ParseException {
		Calendar fechaActual = Calendar.getInstance();

		if (Fechas.diferencia_anios(fechaActual, fNacimiento) > 15) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the documento
	 */
	public int getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(int documento) {
		this.documento = documento;
	}

	/**
	 * @return the fNacimiento
	 */
	public Calendar getfNacimiento() {
		return fNacimiento;
	}

	/**
	 * @param fNacimiento the fNacimiento to set
	 */
	public void setfNacimiento(Calendar fNacimiento) {
		this.fNacimiento = fNacimiento;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the telefono
	 */
	public long getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the obraSocial
	 */
	public boolean isObraSocial() {
		return obraSocial;
	}

	/**
	 * @param obraSocial the obraSocial to set
	 */
	public void setObraSocial(boolean obraSocial) {
		this.obraSocial = obraSocial;
	}

	/**
	 * @return the tratamientos
	 */
	public ArrayList<Tratamientos> getTratamientos() {
		return tratamientos;
	}

	/**
	 * @param tratamientos the tratamientos to set
	 */
	public void setTratamientos(ArrayList<Tratamientos> tratamientos) {
		this.tratamientos = tratamientos;
	}

	/**
	 * @return the visitas
	 */
	public ArrayList<Visitas> getVisitas() {
		return visitas;
	}

	/**
	 * @param visitas the visitas to set
	 */
	public void setVisitas(ArrayList<Visitas> visitas) {
		this.visitas = visitas;
	}

	@Override
	public double calc_precio() {
		// TODO Auto-generated method stub
		return 0;
	}

}