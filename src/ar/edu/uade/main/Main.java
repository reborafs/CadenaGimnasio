package ar.edu.uade.main;

import java.util.ArrayList;

import java.util.Date;

import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.articulos.TipoArticulo;
import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.gym.Clase;
import ar.edu.uade.gym.Ejercicio;
import ar.edu.uade.gym.Emplazamiento;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.Sede;
import ar.edu.uade.gym.TipoEmplazamiento;
import ar.edu.uade.gym.TipoNivel;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.SoporteTecnico;
import ar.edu.uade.usuarios.Usuario;
import java.text.SimpleDateFormat;


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
			gym.agregarSede("Palermo", TipoNivel.PLATINUM, null, null);

			// ESTAS SEDES VAN A FALLAR PORQUE CABALLITO YA EXISTE
			//gym.agregarSede("Caballito", TipoNivel.ORO, null, null);
		} catch (GymException e) {
			e.printStackTrace();
		}
		
		println("Lista de Sedes:");
		printArrayList(gym.getListaSedes());
	}

	
	/*==============================================================================
	* 							TEST - CREAR USUARIOS
	* ==============================================================================
	* Descripcion:  El Sistema debe permitir realizar altas, bajas y modificaciones 
	* de usuarios (Soporte Técnico, Administrativo, Cliente Y Profesor). */
	static public void testCrearUsuario(CadenaGimnasio gym) {
		// AGREGAR USUARIO 
		Usuario admin = new Administrativo("Sebastian");
		Usuario cliente1 = new Cliente("Gabriel", TipoNivel.PLATINUM);
		Usuario cliente2 = new Cliente("Ramona", TipoNivel.BLACK);
		Usuario profe = new Profesor("Jorge");		
		Usuario soporte = new SoporteTecnico("Cecilia");

		gym.agregarUsuario(admin);
		gym.agregarUsuario(cliente1);
		gym.agregarUsuario(cliente2);
		gym.agregarUsuario(profe);
		gym.agregarUsuario(soporte);

		println("\nLista de Usuarios: ");
		printArrayList(gym.getListaUsuarios());
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaFabricacion = sdf.parse("2023-04-10");
			
			gym.agregarArticulo(sedeBelgrano, tipoArticulo, new Date(), fechaFabricacion);
			gym.agregarArticulo(sedeBelgrano, tipoArticulo, new Date(), fechaFabricacion);
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
			TipoArticulo tipoArticulo1 = gym.getCatalogoDeArticulos().get(0);
			TipoArticulo tipoArticulo2 = gym.getCatalogoDeArticulos().get(1);
			gym.agregarEjercicio("Crossfit", true, 10, tipoArticulo1);
			gym.agregarEjercicio("Yoga", true, 15 ,tipoArticulo2);
		} catch (GymException e) {
			e.printStackTrace();
		}
		println("\nLista de Ejercicios:");
		printArrayList(gym.getListaEjercicios());
	}
	
	/*==============================================================================
	* 							TEST - AGREGAR EMPLAZAMIENTO
	* ==============================================================================
	* Descripcion: El sistema debe permitir crear un nuevo tipo de Emplazamiento y 
	* asignarlo a una sede.*/
	static public void testCrearEmplazamiento(CadenaGimnasio gym) {
		Sede sedeBelgrano = gym.getSede("Belgrano");
		try {
			gym.crearEmplazamiento(sedeBelgrano,TipoEmplazamiento.SALON, 25, 30);
			gym.crearEmplazamiento(sedeBelgrano,TipoEmplazamiento.PILETA, 40, 50);

		} catch (Exception e) {
			e.printStackTrace();
		}
		println("\nLista de Ejercicios:");
		printArrayList(gym.getListaEjercicios());
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
			// Invento dos alumnos, uno con nivel suficiente y otro no.
			ArrayList<Cliente> listaAlumnos = new ArrayList<Cliente>();
			
			Cliente cliente1 = new Cliente("Ivan", TipoNivel.PLATINUM);
			gym.agregarUsuario(cliente1);
			listaAlumnos.add(cliente1);

			//Cliente cliente2 = new Cliente("Sabrina", TipoNivel.BLACK);
			//gym.agregarUsuario(cliente2);
			//listaAlumnos.add(cliente2);

			// Agendo la clase
			Profesor profesor = gym.getProfesor(3);
			Ejercicio ejercicio = gym.getEjercicio("Crossfit");
			Date horario = new Date();
			ArrayList<Emplazamiento> listaEmplazamientos = gym.getListaEmplazamientos(sedeBelgrano, TipoEmplazamiento.SALON);
			Emplazamiento emplazamiento = listaEmplazamientos.get(0);
			ArrayList<Articulo> listaArticulos = null;
			boolean esVirtual = true;
			gym.agendarClase(sedeBelgrano, profesor, ejercicio, listaAlumnos, horario, emplazamiento, listaArticulos, esVirtual);
		} catch (GymException e) {
			e.printStackTrace();
		}
		
		println("\nLista de Clases:");
		println("Agrego Clase:");
		printArrayList(sedeBelgrano.getListaClases());
		
		// MODIFICAR CLASE
		try {
			Clase clase = sedeBelgrano.getListaClases().get(0);
			Cliente cliente = new Cliente("Tomas", TipoNivel.PLATINUM);
			gym.agregarUsuario(cliente);
			sedeBelgrano.agregarAlumno(clase, cliente);
		
		} catch (GymException e) {
			e.printStackTrace();
		}
		
		println("\nAgrego Cliente a la Clase:");
		printArrayList(sedeBelgrano.getListaClases());
		
	}

	
	/*==============================================================================
	* 							TEST - ALMACENAR CLASE EN BBDD
	* ==============================================================================
	* Descripcion:  El sistema debe permitir transmitir en vivo las clases online. 
	* Debe permitir almacenar las ultimas 15 clases de postural y las ultimas 10 
	* clases de yoga. Al almacenar una clase 16 de postural, se debe eliminar la 
	* mas antigua. Debe poder agregarse mas tipos de clases virtuales. */
	static public void testAlmacenarClase(CadenaGimnasio gym) {
		Sede sedeBelgrano = gym.getSede("Belgrano");
		ArrayList<Clase> listaClases = sedeBelgrano.getListaClases();
		Clase clase = listaClases.get(0);
		gym.finalizarClase(sedeBelgrano, clase);
		println("\nFinalizo la Clase:");
		printArrayList(sedeBelgrano.getListaClases());
		
		ArrayList<Clase> clasesVirtuales = gym.getClasesVirtualesAlmacenadas();
		println("Lista de clases virtuales: ");
		printArrayList(clasesVirtuales);
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
		testCrearUsuario(gym);
		testCrearTipoArticulo(gym);
		testCrearArticulo(gym);
		testCrearEjercicio(gym);
		testCrearEmplazamiento(gym);
		testAgregarClase(gym);
		testAlmacenarClase(gym);
		//testCalcularRentabilidad(gym);
		
	}

}
