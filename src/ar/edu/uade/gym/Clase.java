package ar.edu.uade.gym;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;


public class Clase {
	
    static int MAX_ALUMNOS_POR_CLASE = 30;

    private Profesor profesorAsignado;
    private final LocalDate fecha;
	private final LocalTime horarioInicio;
	//private final LocalTime horarioFin;
    private final Ejercicio ejercicio;
    private EstadoClase estado;
    private ArrayList<Cliente> listaAlumnos;
    private final Emplazamiento emplazamiento;
    private ArrayList<Articulo> listaArticulos;
    private boolean esVirtual = false;

    public Clase(Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos, TipoNivel tipoNivel,
				 LocalDate fecha, LocalTime horarioInicio, Emplazamiento emplazamiento,
				 ArrayList<Articulo> listaArticulos, boolean esVirtual) throws GymException {
    	
    	asignarProfesor(profesor);
        this.ejercicio = ejercicio;
        this.estado = EstadoClase.AGENDADA;
        this.emplazamiento = emplazamiento;
        this.esVirtual = esVirtual;
		this.fecha = fecha;
		this.horarioInicio = horarioInicio;
		//this.horarioFin = horarioFin;

        if (listaAlumnos == null) {this.listaAlumnos = new ArrayList<Cliente>();}
        else { agregarAlumnos(listaAlumnos, tipoNivel); }

        if (listaArticulos == null) {this.listaArticulos = new ArrayList<Articulo>();}
        else { this.listaArticulos = listaArticulos; }
    }

	/* =======================================================
	 *                    VALIDACIONES
	 * =======================================================
	 */
    private void validarProfesor(Profesor usuario) throws GymException {
    	if (!usuario.soyProfesor()) {
    		throw new GymException("El usuario no es un profesor.");
    	}
    }
    
    private void validarListaAlumnos(ArrayList<Cliente> listaAlumnos, TipoNivel nivelSede) throws GymException {
    	ArrayList<Cliente> alumnosHabilitados = new ArrayList<Cliente>();
    	ArrayList<Cliente> alumnosInhabilitados = new ArrayList<Cliente>();
    	
    	if (listaAlumnos != null) {
        	if (!listaAlumnos.isEmpty()) {

	        	for  (Cliente alumno: listaAlumnos) 
	        		if (alumno.getTipoNivel().getValue() >= nivelSede.getValue()) {
	        			alumnosHabilitados.add(alumno);
	        		} else {
	        			alumnosInhabilitados.add(alumno);
	        		}
	        	
	        	if (!alumnosInhabilitados.isEmpty()) 
	        		throw new GymException("No se puede agendar la clase ya que hay alumnos de menor nivel al necesario. "
	        				+ "Los alumnos son: " + alumnosInhabilitados.toString());
	        	
	        	if (alumnosHabilitados.size() >= MAX_ALUMNOS_POR_CLASE) 
	        		throw new GymException("No es posible agendar la clase debido a que el maximo de alumnos por clase es 30.");
        	}
    	}    	
    }

    private void validarEmplazamiento(ArrayList<Cliente> listaAlumnosNuevos) throws GymException {
    	
    	if (listaAlumnos != null) {
        	if (!listaAlumnos.isEmpty()) {     	
	        	// TO-DO CHEQUEAR CAPACIDAD DEL EMPLAZAMIENTO
        		int cantidadAlumnos = listaAlumnosNuevos.size() + this.listaAlumnos.size();
	        	if (cantidadAlumnos > this.emplazamiento.getCapacidad()) {
	        		throw new GymException("No es posible agendar la clase debido a que el maximo de alumnos es 30.");
	        	}
        	}
    	}    	
    }


	public void confirmarClase() {
		this.estado = EstadoClase.CONFIRMADA;
	}


	/* =======================================================
	 *                    METODOS DE PROFESORES
	 * =======================================================
	 */
	public void asignarProfesor(Profesor profesor) throws GymException {
		validarProfesor(profesor);
		this.profesorAsignado = profesor;
	}


	/* =======================================================
	 *                    METODOS DE CLIENTES
	 * =======================================================
	 */

    public void agregarAlumnos(ArrayList<Cliente> listaAlumnosNuevos, TipoNivel nivelSede) throws GymException {
		if (this.listaAlumnos == null) {
			this.listaAlumnos = new ArrayList<Cliente>();
		}
    	validarListaAlumnos(listaAlumnosNuevos, nivelSede);
    	validarEmplazamiento(listaAlumnosNuevos);
    	this.listaAlumnos.addAll(listaAlumnosNuevos);
    }

	public void agregarAlumno(Cliente alumno, TipoNivel nivelSede) throws GymException {
		ArrayList<Cliente> listaAlumnosNuevos = new ArrayList<Cliente>();
		listaAlumnosNuevos.add(alumno);
		agregarAlumnos(listaAlumnosNuevos, nivelSede);
	}

	/* =======================================================
	 *                    METODOS DE ARTICULOS
	 * =======================================================
	 */

	public void agregarArticulo(Articulo articulo) {
		this.listaArticulos.add(articulo);
	}

	/* =======================================================
	 *                    OTROS
	 * =======================================================
	 */
    
    public Ejercicio getEjercicio() {
    	return this.ejercicio;
    }

    public void finalizarClase() {
		this.estado = EstadoClase.FINALIZADA;
    }


	public Profesor getProfesor() {
		return this.profesorAsignado;
	}

	public LocalTime getHorarioInicio() {
		return this.horarioInicio;
	}


	public LocalDate getFecha() {
		return this.fecha;
	}

	public long getDuracionHoras() {
		return 1;
		//return this.horarioInicio.until(horarioFin, ChronoUnit.HOURS);
	}

	//public long getDuracionMinutos() {
	//	return this.horarioInicio.until(horarioFin, ChronoUnit.MINUTES);
	//}

	public ArrayList<Cliente> getListaAlumnos() {
		return this.listaAlumnos;
	}

	public Emplazamiento getEmplazamiento() {
		return this.emplazamiento;
	}

	public ArrayList<Articulo> getListaArticulos() {
		return listaArticulos;
	}

	@Override
	public String toString() {
		return "{" +
				"Profesor: " + this.profesorAsignado + ", " +
				"Ejercicio: " + this.ejercicio + ", " +
				"Estado: " + this.estado + ", " +
				"CantidadAlumnos: " + this.listaAlumnos.size() + ", " +
				"Emplazamiento: " + this.emplazamiento + ", " +
				"esVirtual: " + this.esVirtual +
				"}";
	}

	public ArrayList<String> getInfo() {
		ArrayList<String> array = new ArrayList<>();
		array.add(profesorAsignado.getNombre());
		array.add(fecha.toString());
		array.add(horarioInicio.toString());
		array.add(ejercicio.getNombre());
		array.add(estado.toString());
		array.add(String.valueOf(listaAlumnos.size()));
		array.add(emplazamiento.getTipoEmplazamiento().toString());
		array.add(String.valueOf(listaArticulos.size()));
		if (esVirtual) {array.add("Si");} else {array.add("no");};

		return array;
	}
}
