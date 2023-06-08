package ar.edu.uade.gym;

import java.util.ArrayList;
import ar.edu.uade.articulos.Articulo;

public class Sede {
    private String ubicacion;
    private TipoNivel tipoNivel;
    private ArrayList<Articulo> stockArticulos;
    private ArrayList<Emplazamiento> emplazamientosDisponibles;
    private ArrayList<Clase> ArrayListaClases;
    private ArrayList<Ejercicio> ejerciciosDisponibles;

    public Sede(String ubicacion, TipoNivel tipoNivel, ArrayList<Emplazamiento> emplazamientos, ArrayList<Ejercicio> ejercicios) {
    	this.ubicacion = ubicacion.toLowerCase();
    	this.tipoNivel = tipoNivel;
    	this.emplazamientosDisponibles = emplazamientos;
    	this.ejerciciosDisponibles = ejercicios;
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
    
    @Override
    public String toString() {
    	return "["+this.ubicacion+","+this.tipoNivel.toString()+"]";
    }
}
