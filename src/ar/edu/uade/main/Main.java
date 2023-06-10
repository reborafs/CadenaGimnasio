package ar.edu.uade.main;

import java.util.ArrayList;

import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.gym.Ejercicio;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.Sede;
import ar.edu.uade.gym.TipoEmplazamiento;
import ar.edu.uade.gym.TipoNivel;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Usuario;

public class Main {
	
	static public void println(String str) {
		System.out.println(str);
	}
	
	static public void printArrayList(ArrayList<?> lista) {
		for (Object elem: lista) {
			System.out.println(elem);
		}
	}

	public static void main(String[] args) {
		// INICIO CADENA GIMNASIO
		CadenaGimnasio gym = CadenaGimnasio.getInstance();
		
		try {
			// ESTAS SEDE SE AGREGAN BIEN
			gym.agregarSede("Caballito", TipoNivel.BLACK, null, null);
			gym.agregarSede("Belgrano", TipoNivel.ORO, null, null);		
			
			// ESTAS SEDES VAN A FALLAR PORQUE CABALLITO YA EXISTE
			gym.agregarSede("Caballito", TipoNivel.ORO, null, null);
			gym.agregarSede("Palermo", TipoNivel.PLATINUM, null, null);
		} catch (GymException e) {
			e.printStackTrace();
		}
		
		println("Lista de Sedes:");
		printArrayList(gym.getListaSedes());
		
		// TEST DE AGREGAR CLASE
		Sede sedeBelgrano = gym.getSede("Belgrano");

		try {
			Profesor profesor = new Profesor("Carlos") ;
			Ejercicio ejercicio = null;
			ArrayList<Cliente> listaAlumnos = null;
			TipoEmplazamiento tipoEmplazamiento = null;
			ArrayList<Articulo> listaArticulos = null;
			boolean esVirtual = false;
			sedeBelgrano.agregarClase(profesor, ejercicio, listaAlumnos, tipoEmplazamiento, listaArticulos, esVirtual);
		} catch (GymException e) {
			e.printStackTrace();
		}
		
		println("\nLista de Clases:");
		printArrayList(sedeBelgrano.getListaClases());

	}

}
