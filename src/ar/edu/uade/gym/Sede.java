package ar.edu.uade.gym;

import java.util.ArrayList;
import ar.edu.uade.articulos.Articulo;

public class Sede {
    private String ubicacion;
    private TipoNivel tipoNivel;
    private int capacidad;
    private ArrayList<Articulo> stockArticulos;
    private ArrayList<Clase> ArrayListaClases;
    private ArrayList<Ejercicio> ejerciciosDisponibles;

    public Sede(String ubicacion, TipoNivel tipoNivel, int capacidad) {
    	this.ubicacion = ubicacion;
    	this.tipoNivel = tipoNivel;
    	this.capacidad = capacidad;
    }
    
    public int calcularRentabilidad() {
        // Code for calculating profitability
        return 0;
    }

    public int getDesgasteArticulo() {
        // Code for getting article wear and tear
        return 0;
    }
}
