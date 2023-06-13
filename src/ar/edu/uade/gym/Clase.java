package ar.edu.uade.gym;

import java.util.ArrayList;
import java.util.Date;

import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Usuario;


public class Clase {
	
    static int MAX_ALUMNOS_POR_CLASE = 30;

    private Profesor profesorAsignado;
    private Date horario;
    private int duracion;
    private Ejercicio ejercicio;
    private EstadoClase estado;
    private ArrayList<Cliente> listaAlumnos;
    private Emplazamiento emplazamiento;
    private ArrayList<Articulo> listaArticulos;
    private boolean esVirtual = false;

    public Clase(Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos, TipoNivel tipoNivel,
    		Emplazamiento emplazamiento, ArrayList<Articulo> listaArticulos, boolean esVirtual) throws GymException {
    	
    	validarProfesor(profesor);
    	this.profesorAsignado = profesor;
        this.ejercicio = ejercicio;
        this.estado = EstadoClase.AGENDADA;
        this.emplazamiento = emplazamiento;
        this.esVirtual = esVirtual;

        if (listaAlumnos == null)
        	this.listaAlumnos = new ArrayList<Cliente>();
        else
        	validarListaAlumnos(listaAlumnos, tipoNivel);
        	this.listaAlumnos = listaAlumnos;
        
        if (listaArticulos == null)
        	this.listaArticulos = new ArrayList<Articulo>();
        else
        	this.listaArticulos = listaArticulos;
    }

    
    public void validarProfesor(Usuario usuario) throws GymException {
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
    
    public void agregarAlumnos(ArrayList<Cliente> listaAlumnosNuevos, TipoNivel nivelSede) throws GymException {
    	validarListaAlumnos(listaAlumnosNuevos, nivelSede);
    	validarEmplazamiento(listaAlumnosNuevos);
    	this.listaAlumnos.addAll(listaAlumnosNuevos);
    }
    
    public int calcularDesgaste() {
    	// TO-DO
        return 0;
    }
    
    public Ejercicio getEjercicio() {
    	return this.ejercicio;
    }
    
    public void agregarAlumno(Cliente alumno, TipoNivel nivelSede) throws GymException {
    	ArrayList<Cliente> listaAlumnosNuevos = new ArrayList<Cliente>();
    	listaAlumnosNuevos.add(alumno);
    	validarListaAlumnos(listaAlumnosNuevos, nivelSede);
    	validarEmplazamiento(listaAlumnosNuevos);
    	this.listaAlumnos.add(alumno);
    }
    
    public void setEstadoFinalizado() {
    	this.estado = EstadoClase.FINALIZADA;
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


	public Usuario getProfesor() {
		return this.profesorAsignado;
	}
	
	public Date getHorario() {
		return this.horario;
	}


	//public TipoEmplazamiento getTipoEmplazamiento() {
	//	return this.tipoEmplazamiento;
	//}


	public Object getEmplazamiento() {
		return this.emplazamiento;
	}
}
