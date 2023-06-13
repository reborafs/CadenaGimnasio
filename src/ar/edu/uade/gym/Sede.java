package ar.edu.uade.gym;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Usuario;

public class Sede {
    private String ubicacion;
    private TipoNivel tipoNivel;
    private ArrayList<Articulo> stockArticulos;
    private ArrayList<Emplazamiento> emplazamientosDisponibles;
    private ArrayList<Clase> listaClases;
    private ArrayList<Ejercicio> ejerciciosDisponibles;

    public Sede(String ubicacion, TipoNivel tipoNivel, ArrayList<Emplazamiento> emplazamientos, ArrayList<Ejercicio> ejercicios) {
    	this.ubicacion = ubicacion.toLowerCase();
    	this.tipoNivel = tipoNivel;
        if (emplazamientos == null)
        	this.emplazamientosDisponibles = new ArrayList<Emplazamiento>();
        else
        	this.emplazamientosDisponibles = emplazamientos;
    	this.ejerciciosDisponibles = ejercicios;
    	this.listaClases = new ArrayList<Clase>();
    	this.stockArticulos = new ArrayList<Articulo>();
    }
    
    
    public void validarProfesor(Usuario profesor) throws GymException {
    	if (!profesor.soyProfesor())
    		throw new GymException("El Usuario no es un profesor.");
    	
    	// TO-DO chequear que el profesor no tenga otra clase en el mismo horario.
    	for (Clase clase: this.listaClases) {
    		Usuario user = clase.getProfesor();
    		//if (user == profesor && )
    	}
    }
    
    public void agregarClase(Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos, Date horario,
    		Emplazamiento emplazamiento, ArrayList<Articulo> listaArticulos, boolean esVirtual) throws GymException {
    	validarProfesor(profesor);
    	validarEmplazamientoDisponible(emplazamiento, horario);
    	Clase newClase = new Clase(profesor, ejercicio, listaAlumnos, this.getTipoNivel(), 
    			emplazamiento, listaArticulos, esVirtual);
    	this.listaClases.add(newClase);
    }
    
    public int calcularRentabilidad() {
        // Code for calculating profitability
        return 0;
    }

    public int getDesgasteArticulo() {
        // Code for getting article wear and tear
        return 0;
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
    
	public void agregarArticulo(Articulo newArticulo) {
		this.stockArticulos.add(newArticulo);
	}

	public ArrayList<Articulo> getListaArticulos() {
		return this.stockArticulos;
	}
    
    @Override
    public String toString() {
    	return "["+this.ubicacion+","+this.tipoNivel.toString()+"]";
    }


	public void crearEmplazamiento(TipoEmplazamiento tipoEmplazamiento, int capacidad, int metrosCuadrados) {
		this.emplazamientosDisponibles.add(new Emplazamiento(tipoEmplazamiento, capacidad, metrosCuadrados));
	}


	public void validarEmplazamientoDisponible(Emplazamiento emplazamiento, Date horario) throws GymException{		
		for (Clase clase: this.listaClases) {
			if (horario.compareTo(clase.getHorario()) == 0) 
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


	public void agregarAlumno(Clase clase, Cliente cliente) throws GymException {
		clase.agregarAlumno(cliente, this.tipoNivel);
	}


	public void finalizarClase(Clase clase) {
		clase.setEstadoFinalizado();
	}


}
