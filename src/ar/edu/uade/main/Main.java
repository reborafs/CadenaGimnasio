package ar.edu.uade.main;

import java.util.ArrayList;
import java.util.Date;

import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.articulos.TipoArticulo;
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
	
	/**
	 * ==============================================================================
	 * 							HELPER METHODS
	 * ==============================================================================
	 */
	static public void println(String str) {
		System.out.println(str);
	}
	static public void printArrayList(ArrayList<?> lista) {
		for (Object elem: lista) {
			System.out.println(elem);
		}
	}
	
	
	/**
	 * ==============================================================================
	 * 							TEST - CREAR SEDE
	 * ==============================================================================
	 * Descripcion: Solo se puede crear si no hay una sede en el mismo barrio Se debe
	   crear con un nivel de aceptacion(Black, Oro, Platinum). El mismo debe impedir
	   que un cliente con nivel bajo asista a esta sede. Por Ej: cliente Black no
	   puede acceder a Oro  
	 */
	static public void testAgregarSede(CadenaGimnasio gym) {

		// AGREGAR SEDE, VALIDA SI YA EXISTE
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
	}

	
	/**
	 * ==============================================================================
	 * 							TEST - ABM CLASE
	 * ==============================================================================
	 * Descripcion: El sistema debe permitir realizar altas, bajas y modificaciones 
	 * de las clases disponibles en el gimnasio, teniendo en cuenta que una clase se 
	 * crea cuando es rentable. */
	static public void testAgregarClase(CadenaGimnasio gym) {
		Sede sedeBelgrano = gym.getSede("Belgrano");

		// AGREGAR CLASE
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
		
		// MODIFICAR CLASE

		
		// ELIMINAR CLASE

	}

	
	/*==============================================================================
	* 							TEST - ABM USUARIOS
	* ==============================================================================
	* Descripcion:  El Sistema debe permitir realizar altas, bajas y modificaciones 
	* de usuarios (Soporte Técnico, Administrativo, Cliente Y Profesor). */
	static public void testCrearUsuario(CadenaGimnasio gym) {
		// AGREGAR USUARIO 
		// MODIFICAR USUARIO
		// ELIMINAR USUARIO
	}


	/*==============================================================================
	* 							TEST - ALMACENAR CLASE EN BBDD
	* ==============================================================================
	* Descripcion:  El sistema debe permitir transmitir en vivo las clases online. 
	* Debe permitir almacenar las ultimas 15 clases de postural y las ultimas 10 
	* clases de yoga. Al almacenar una clase 16 de postural, se debe eliminar la 
	* mas antigua. Debe poder agregarse mas tipos de clases virtuales. */
	static public void testAlmacenarClase(CadenaGimnasio gym) {
		
	}
	
	
	/*==============================================================================
	* 							TEST - GESTION DE ARTICULOS
	* ==============================================================================
	* Descripcion: El sistema debe permitir registrar y gestionar los artículos 
	* utilizados en las clases. */
	static public void testCrearTipoArticulo(CadenaGimnasio gym) {
		try {
			gym.agregarTipoArticuloPorFecha("Colchoneta", "Pepito", "Colchoneta de 2m x 0.75m", 200);
			gym.agregarTipoArticuloPorUso("Pesa", "Pepito", "Pesa marca Pepito de 20kg", 50);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		println("\nLista de Tipos Articulo:");
		printArrayList(gym.getCatalogoDeArticulos());
	}
	
	static public void testCrearArticulo(CadenaGimnasio gym) {
		Sede sedeBelgrano = gym.getSede("Belgrano");

		try {
			TipoArticulo tipoArticulo = gym.getCatalogoDeArticulos().get(0);
			gym.agregarArticulo(sedeBelgrano, tipoArticulo, new Date(), new Date());
			gym.agregarArticulo(sedeBelgrano, tipoArticulo, new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		println("\nLista de Articulos:");
		printArrayList(sedeBelgrano.getListaArticulos());
	}
	
	
	/*==============================================================================
	* 							TEST - AGREGAR TIPOS DE EJERCICIO
	* ==============================================================================
	* Descripcion: El sistema debe permitir crear un nuevo tipo ejercicio.*/
	static public void testCrearEjercicio(CadenaGimnasio gym) {

		
		try {
			gym.agregarEjercicio("Crossfit", false, null);
			gym.agregarEjercicio("Yoga", true, null);
			//gym.agregarEjercicio("Crossfit", true, null);
		} catch (GymException e) {
			e.printStackTrace();
		}
		println("\nLista de Ejercicios:");
		printArrayList(gym.getListaEjercicios());
	}
	
	
	/*==============================================================================
	* 							TEST - CALCULO DE RENTABILIDAD
	* ==============================================================================
	* Descripcion: El sistema debe permitir calcular la rentabilidad de una clase.*/
	static public void testCalcularRentabilidad(CadenaGimnasio gym) {
		
	}

	
	public static void main(String[] args) {
		// INICIO CADENA GIMNASIO
		CadenaGimnasio gym = CadenaGimnasio.getInstance();
		
		testAgregarSede(gym); 
		testAgregarClase(gym);
		//testCrearUsuario(gym);
		//testAlmacenarClase(gym);
		testCrearTipoArticulo(gym);
		testCrearArticulo(gym);
		testCrearEjercicio(gym);
		testCalcularRentabilidad(gym);
		
	}

}
