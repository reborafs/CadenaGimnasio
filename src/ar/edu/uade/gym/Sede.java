package ar.edu.uade.gym;

import java.util.ArrayList;
import java.util.Arrays;

import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;

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
    	this.emplazamientosDisponibles = emplazamientos;
    	this.ejerciciosDisponibles = ejercicios;
    	this.listaClases = new ArrayList<Clase>();
    	this.stockArticulos = new ArrayList<Articulo>();
    }
    
    
    public void agregarClase(Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos, 
    		TipoEmplazamiento tipoEmplazamiento, ArrayList<Articulo> listaArticulos, boolean esVirtual) throws GymException {
    	
    	Clase newClase = new Clase(profesor, ejercicio, listaAlumnos, tipoEmplazamiento, listaArticulos, esVirtual);
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

    
    @Override
    public String toString() {
    	return "["+this.ubicacion+","+this.tipoNivel.toString()+"]";
    }


	public ArrayList<Articulo> getListaArticulos() {
		return this.stockArticulos;
	}

}
