package ar.edu.uade.gym;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.gym.articulos.CategoriaArticulo;
import ar.edu.uade.gym.articulos.TipoArticulo;
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


public class TestBackMain {

	/* ==============================================================================
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
	
	
	/*==============================================================================
	 * 							TEST - CREAR SEDE
	 * ==============================================================================
	 * Descripcion: Solo se puede crear si no hay una sede en el mismo barrio Se debe
	   crear con un nivel de aceptacion(Black, Oro, Platinum). El mismo debe impedir
	   que un cliente con nivel bajo asista a esta sede. Por Ej: cliente Black no
	   puede acceder a Oro  */
	static public void testAgregarSede(CadenaGimnasio gym) {

		// AGREGAR SEDE, VALIDA SI YA EXISTE
		try {
			// ESTAS SEDE SE AGREGAN BIEN
			gym.agregarSede("Caballito", TipoNivel.BLACK, null, null, 80000);
			gym.agregarSede("Belgrano", TipoNivel.ORO, null, null, 100000);
			gym.agregarSede("Palermo", TipoNivel.PLATINUM, null, null, 120000);

			// ESTAS SEDES VAN A FALLAR PORQUE CABALLITO YA EXISTE
			//gym.agregarSede("Caballito", TipoNivel.ORO, null, null,15);
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
		Usuario admin = new Administrativo("Sebastian", "sebas");
		Usuario cliente1 = new Cliente("Gabriel","", TipoNivel.PLATINUM);
		Usuario cliente2 = new Cliente("Ramona","", TipoNivel.BLACK);
		Usuario profe = new Profesor("Jorge","", 50000);
		Usuario soporte = new SoporteTecnico("Cecilia","");

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
			gym.agregarTipoArticuloPorFecha("Colchoneta", CategoriaArticulo.COLCHONETA, "Pepito", "Colchoneta de 2m x 0.75m", 200);
			gym.agregarTipoArticuloPorUso("Pesa", CategoriaArticulo.PESA,"Pepito", "Pesa marca Pepito de 20kg", 50);
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

			LocalDate fechaFabricacion = LocalDate.of(2023,5,10);
			LocalDate fechaCompra = LocalDate.of(2023,6,10);

			gym.agregarArticulo(sedeBelgrano, tipoArticulo, 2000 , fechaCompra, fechaFabricacion);
			gym.agregarArticulo(sedeBelgrano, tipoArticulo, 1500 , fechaCompra, fechaFabricacion);
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
	
	
	/*==============================================================================
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
			
			Cliente cliente1 = new Cliente("Ivan","", TipoNivel.PLATINUM);
			gym.agregarUsuario(cliente1);
			listaAlumnos.add(cliente1);

			//Cliente cliente2 = new Cliente("Sabrina", TipoNivel.BLACK);
			//gym.agregarUsuario(cliente2);
			//listaAlumnos.add(cliente2);

			// Agendo la clase
			Profesor profesor = gym.getProfesor(3);
			Ejercicio ejercicio = gym.getEjercicio("Crossfit");
			LocalDate fecha = LocalDate.of(2023,7,1);
			LocalTime horarioInicio = LocalTime.of(19,0,0);
			ArrayList<Emplazamiento> listaEmplazamientos = gym.getListaEmplazamientos(sedeBelgrano, TipoEmplazamiento.SALON);
			Emplazamiento emplazamiento = listaEmplazamientos.get(0);
			ArrayList<Articulo> listaArticulos = null;
			boolean esVirtual = true;
			gym.agendarClase(sedeBelgrano, profesor, ejercicio, listaAlumnos, fecha, horarioInicio, emplazamiento, listaArticulos, esVirtual);
		} catch (GymException e) {
			e.printStackTrace();
		}
		
		println("\nLista de Clases:");
		println("Agrego Clase:");
		printArrayList(sedeBelgrano.getListaClases());
		
		// MODIFICAR CLASE
		try {
			Clase clase = sedeBelgrano.getListaClases().get(0);
			Cliente cliente = new Cliente("Tomas","", TipoNivel.PLATINUM);
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
