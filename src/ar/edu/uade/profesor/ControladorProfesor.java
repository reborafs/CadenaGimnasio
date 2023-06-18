package ar.edu.uade.profesor;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uade.articulos.TipoArticulo;
import ar.edu.uade.bbdd.BaseDeDatos;
import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.gym.Ejercicio;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.Sede;
import ar.edu.uade.gym.TipoNivel;
import ar.edu.uade.main.Main;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.SoporteTecnico;

import java.util.HashMap;

public class ControladorProfesor {


	private ArrayList<String> sedes;
	private HashMap<String, ArrayList<String>> sedeEjercicio;
	
//	String[] testColumna = sedes.toArray(String[]::new);
//	ArrayList<String> tipoEjercicio = new ArrayList<>(List.of("Yoga", "Crossfit", "Boxing", "Running"));
//	String[] testFila = tipoEjercicio.toArray(String[]::new);
	
	public ControladorProfesor(){
		this.sedeEjercicio = new HashMap<String, ArrayList<String>>();
		this.sedes = new ArrayList<String>();
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
		System.out.print(sedeEjercicio);
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
}
