package ar.edu.uade.profesor;

import java.util.ArrayList;

import ar.edu.uade.cliente.VistaEjerciciosPorSede;
import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.gym.Ejercicio;
import ar.edu.uade.gym.Sede;
import ar.edu.uade.usuarios.Profesor;

import javax.swing.*;
import java.util.HashMap;

public class ControladorProfesor {
	private static ControladorProfesor instancia;
	private final CadenaGimnasio gym;
	private Profesor usuario;
	private ArrayList<String> sedes;
	private HashMap<String, ArrayList<String>> sedeEjercicio;
	
//	String[] testColumna = sedes.toArray(String[]::new);
//	ArrayList<String> tipoEjercicio = new ArrayList<>(List.of("Yoga", "Crossfit", "Boxing", "Running"));
//	String[] testFila = tipoEjercicio.toArray(String[]::new);

	private ControladorProfesor(){
		this.gym = CadenaGimnasio.getInstance();
		this.sedeEjercicio = new HashMap<String, ArrayList<String>>();
		this.sedes = new ArrayList<String>();
	}

	public static ControladorProfesor getInstance() {
		if (instancia == null) {
			instancia = new ControladorProfesor();
		}
		return instancia;
	}

	public void setUsuario(Profesor usuario) {
		this.usuario = usuario;
	}
	
//	public void calcularSede(CadenaGimnasio gym) {
//		ArrayList<Sede> sedesGym = gym.getListaSedes();
//		for(Sede sede:sedesGym) {
//			sedes.add(sede.getUbicacion());
//		}
//	}
	
	public void calcularSedeEjercicio(CadenaGimnasio gym) {
		ArrayList<Sede> sedesGym = gym.getListaSedes();
		for(Sede sede:sedesGym) {
			ArrayList<String> ejerciciosDisponibles = new ArrayList<String>();
			ArrayList<Ejercicio> ejerciciosSede = sede.getEjerciciosDisponibles();
			for(Ejercicio ejercicio:ejerciciosSede) {
				ejerciciosDisponibles.add(ejercicio.getNombre());
			}
			sedeEjercicio.put(sede.getUbicacion(), ejerciciosDisponibles);		
		}
	}

	public double getSueldo() {
		return gym.getSueldo(usuario);
	}

	@Override
	public String toString() {
		return "ControladorProfesor [sedes=" + sedes + ", sedeEjercicio=" + sedeEjercicio + "]";
	}
	
    public static void main(String[] args) {
    	CadenaGimnasio gymnasio = CadenaGimnasio.getInstance();
    	gymnasio.llenarGym();
    	
    	ControladorProfesor tablaProfesor = new ControladorProfesor();
    	tablaProfesor.calcularSedeEjercicio(gymnasio);
//    	System.out.print(gymnasio.toString());
    }
	
//	public void agregarString(ArrayList<String> sedes) {
//        String[] nuevoArreglo = new String[testColumna.length + 1];
//        nuevoArreglo[0] = "Tipo de Ejercicio";
//        System.arraycopy(testColumna, 0, nuevoArreglo, 1, testColumna.length);
//        testColumna = nuevoArreglo;
//	}
	
//	public void agregarValorSedeEjercicio() {
////		ArrayList<Sede> sedes = CadenaGimnasio.getListaSedes();
////		
////		for(String sede:)
//	}
	
	
//	public String[] getSedes() {
//		agregarString(sedes);
//		return testColumna;
//	}
//
//	public String[] getTipoEjercicio() {
//		return testFila;
//	}

	public void abrirVistaPrincipal(Profesor usuario) {
		setUsuario(usuario);
		SwingUtilities.invokeLater(() -> {
			VistaClaseAsignada vistaProfe = new VistaClaseAsignada();;
			vistaProfe.setVisible(true);
		});
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
}

