package ar.edu.uade.gym;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Usuario;

public class Sede {
    private final String ubicacion;
    private TipoNivel tipoNivel;
    private ArrayList<Articulo> stockArticulos;
    private ArrayList<Emplazamiento> emplazamientosDisponibles;
    private ArrayList<Clase> listaClases;
    private ArrayList<Ejercicio> ejerciciosDisponibles;



	public Sede(String ubicacion, TipoNivel tipoNivel, ArrayList<Emplazamiento> emplazamientos, ArrayList<Ejercicio> ejercicios) {
    	this.ubicacion = ubicacion.toLowerCase();
    	this.tipoNivel = tipoNivel;
    	this.listaClases = new ArrayList<Clase>();
    	this.stockArticulos = new ArrayList<Articulo>();

        if (emplazamientos == null)
        	this.emplazamientosDisponibles = new ArrayList<Emplazamiento>();
        else
        	this.emplazamientosDisponibles = emplazamientos;

        if (ejerciciosDisponibles == null)
	        this.ejerciciosDisponibles = new ArrayList<Ejercicio>();
        else
        	this.ejerciciosDisponibles = ejercicios;

    }

	/* =======================================================
	 *                    METODOS DE PROFESORES
	 * =======================================================
	 */
    
	private void validarProfesor(Clase claseNueva, Usuario profesor) throws GymException {
		if (!profesor.soyProfesor())
			throw new GymException("El Usuario no es un profesor.");

		ArrayList<Clase> listaClasesMismoDia = getListaClases();
		listaClasesMismoDia.removeIf(clase -> (clase.getFecha() == claseNueva.getFecha()) );

		// Chequear que el profesor no tenga otra clase en el mismo horario.
		for (Clase clase: listaClasesMismoDia) {
			Profesor profeAsignado = clase.getProfesor();
			if (profeAsignado.equals(profesor) && clase.getFecha() == clase.getFecha()
					&& clase.getHorario() == claseNueva.getHorario()) {
				throw new GymException("El profesor tiene una clase asignada en el mismo horario.");
			}
		}

		// Chequear que el profesor no tenga un intervalo menor a 3 hs entre clase y clase.
		for (Clase clase: listaClasesMismoDia) {
			long tiempoEntreClases = clase.getHorario().until(claseNueva.getHorario(), ChronoUnit.MINUTES);
			if (Math.abs(tiempoEntreClases) >= 180) {
				throw new GymException("El profesor debe tener un intervalo de 3hs entre clase y clase.");
			}
		}
	}

	public void asignarProfesor(Clase clase, Usuario profesor) throws GymException {
		validarProfesor(clase, profesor);
		clase.asignarProfesor((Profesor) profesor);
	}

	/* =======================================================
	 *                    METODOS DE CLIENTES
	 * =======================================================
	 */

	private void validarClaseDiariaAlumno(Clase claseNueva, Cliente alumno) throws GymException {
		for (Clase clase: this.listaClases) {
			if (clase.equals(claseNueva)) {
				throw new GymException("El alumno ya esta anotado en esta clase.");
			} else {
				if (clase.getFecha() == claseNueva.getFecha())
					if (clase.getListaAlumnos().contains(alumno))
						throw new GymException("El alumno ya esta anotado en una clase este mismo dia.");
			}
		}
	}

	private void validarListaAlumnos(Clase clase, ArrayList<Cliente> listaAlumnos) throws GymException {
		for (Cliente alumno: listaAlumnos) {
			validarClaseDiariaAlumno(clase, alumno);
		}
	}

	private void validarAlumno(Clase clase, Cliente alumno) throws GymException {
		ArrayList<Cliente> listaAlumnos = new ArrayList<Cliente>();
		listaAlumnos.add(alumno);
		validarListaAlumnos(clase, listaAlumnos);
	}


	public void agregarAlumno(Clase clase, Cliente cliente) throws GymException {
		validarAlumno(clase, cliente);
		clase.agregarAlumno(cliente, this.tipoNivel);
		//clase.validarEstado();  TO-DO -> VALIDAR ESTADO POR PROFESOR ASIGNADO, RENTABILIDAD Y STOCK
	}

	/* =======================================================
	 *                    METODOS DE CLASE
	 * =======================================================
	 */

    public void agregarClase(Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos, LocalDate fecha,
							 LocalTime horario, Emplazamiento emplazamiento, ArrayList<Articulo> listaArticulos,
							 boolean esVirtual) throws GymException {

		Clase newClase = new Clase(profesor, ejercicio, listaAlumnos, this.getTipoNivel(),
				emplazamiento, listaArticulos, esVirtual);
		validarProfesor(newClase, profesor);
    	validarEmplazamientoDisponible(emplazamiento, fecha, horario);
    	this.listaClases.add(newClase);
    }

	public void finalizarClase(Clase clase) {
		clase.setEstadoFinalizado();
		//clase.validarEstado();  TO-DO -> VALIDAR ESTADO POR PROFESOR ASIGNADO, RENTABILIDAD Y STOCK
	}


	/* =======================================================
	 *                    METODOS DE EJERCICIOS
	 * =======================================================
	 */
	public void agregarEjerciciosDisponibles(Ejercicio ejercicio) {
		this.ejerciciosDisponibles.add(ejercicio);
	}

	/* =======================================================
	 *                    METODOS DE EMPLAZAMIENTOS
	 * =======================================================
	 */
	public void crearEmplazamiento(TipoEmplazamiento tipoEmplazamiento, int capacidad, int metrosCuadrados) {
		this.emplazamientosDisponibles.add(new Emplazamiento(tipoEmplazamiento, capacidad, metrosCuadrados));
	}


	public void validarEmplazamientoDisponible(Emplazamiento emplazamiento, LocalDate fecha, LocalTime horario) throws GymException{
		for (Clase clase: this.listaClases) {
			if (fecha.isEqual(clase.getFecha()) && horario.compareTo(clase.getHorario()) == 0)
					if (clase.getEmplazamiento().equals(emplazamiento))
						throw new GymException("El emplazamiento no esta disponible en ese horario.");
		}
	}


	public ArrayList<Emplazamiento> getListaEmplazamientos(TipoEmplazamiento tipoEmplazamiento) {
		ArrayList<Emplazamiento> lista = new ArrayList<Emplazamiento>();
		for (Emplazamiento emplazamiento: emplazamientosDisponibles) {
			if (tipoEmplazamiento.equals(emplazamiento.getTipoEmplazamiento()))
				lista.add(emplazamiento);
		}
		return lista;
	}


	/* =======================================================
	 *                    METODOS DE ARTICULOS
	 * =======================================================
	 */

	public void agregarArticulo(Articulo newArticulo) {
		this.stockArticulos.add(newArticulo);
	}
	public int getDesgasteArticulo() {
		// Code for getting article wear and tear
		return 0;
	}

	/* =======================================================
	 *                    METODOS DE RENTABILIDAD
	 * =======================================================
	 */
    public int calcularRentabilidad() {
        // Code for calculating profitability
        return 0;
    }

	public ArrayList<Ejercicio> getEjerciciosDisponibles() {
		return this.ejerciciosDisponibles;
	}
    
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public TipoNivel getTipoNivel() {
        return this.tipoNivel;
    }
    
    public ArrayList<Clase> getListaClases() {
        return this.listaClases;
    }


	public ArrayList<Articulo> getListaArticulos() {
		return this.stockArticulos;
	}
    
    @Override
    public String toString() {
    	return "["+this.ubicacion+","+this.tipoNivel.toString()+"]";
    }

}
