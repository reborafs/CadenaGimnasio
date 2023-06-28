package ar.edu.uade.profesor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import ar.edu.uade.cliente.VistaEjerciciosPorSede;
import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.usuarios.Profesor;

import javax.swing.*;
import java.util.HashMap;

public class ControladorProfesor {
	private static ControladorProfesor instancia;
	private final CadenaGimnasio gym;
	private Profesor usuario;

	private ControladorProfesor(){
		this.gym = CadenaGimnasio.getInstance();
	}

	public static ControladorProfesor getInstance() {
		if (instancia == null) {
			instancia = new ControladorProfesor();
		}
		return instancia;
	}

	/* =======================================================
	 *                    METODOS
	 * =======================================================
	 */


	public ArrayList<String> getSedes(){
		return gym.getListaNombresSedes();
	}

	public HashMap<String, ArrayList<String>> getEjerciciosPorSede(){
		HashMap<String, ArrayList<String>> sedeEjercicio = new HashMap<>();
		for(String sede: getSedes()) {
			sedeEjercicio.put(sede, gym.getEjerciciosDisponiblesSede(sede));
		}
		return sedeEjercicio;
	}


	/* =======================================================
	 *                    ABRIR VISTAS
	 * =======================================================
	 */
	public void abrirVistaPrincipal(Profesor usuario) {
		setUsuario(usuario);
		abrirVistaClaseAsignada();
	}

	public void abrirVistaClaseAsignada() {
		SwingUtilities.invokeLater(() -> {
			VistaClaseAsignada vistaProfe = new VistaClaseAsignada();
			vistaProfe.setVisible(true);
		});
	}

	public void abrirVistaSueldo() {
		SwingUtilities.invokeLater(() -> {
			VistaSueldo vistaProfe = new VistaSueldo();
			vistaProfe.setVisible(true);
		});
	}

	/* =======================================================
	 *                    GETTERS / SETTERS
	 * =======================================================
	 */
	public double getSueldo() {
		return gym.getSueldo(usuario);
	}

	public void setUsuario(Profesor usuario) {
		this.usuario = usuario;
	}

	public HashMap<LocalDate, ArrayList<LocalTime>> getClasesAsignadas(String ubicacionSede) {
		return gym.getHorariosClasesAsignadasProfesor(ubicacionSede, this.usuario);
	}
}

