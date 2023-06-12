package ar.edu.uade.gym;

import java.util.ArrayList;

import ar.edu.uade.articulos.TipoArticulo;

public class Ejercicio {
    private String nombre;
    private boolean puedeSerVirtual = false;
    private ArrayList<TipoArticulo> listaArticulosNecesarios;


    public Ejercicio(String nombre, boolean puedeSerVirtual, ArrayList<TipoArticulo> listaArticulosNecesarios) {
    	this.nombre = nombre.toLowerCase();
    	this.puedeSerVirtual = puedeSerVirtual;    	
        if (listaArticulosNecesarios == null)
        	this.listaArticulosNecesarios = new ArrayList<TipoArticulo>();
        else
        	this.listaArticulosNecesarios = listaArticulosNecesarios;
    }
    
    public void setArticulosNecesarios(ArrayList<TipoArticulo> listaArticulosNecesarios) {
    	this.listaArticulosNecesarios = listaArticulosNecesarios;
    }

    public void addArticuloNecesario(TipoArticulo tipoArticulo) {
    	this.listaArticulosNecesarios.add(tipoArticulo);
    }
    
    public String getNombre() {
    	return this.nombre;
    }

    public boolean isVirtual() {
    	return this.puedeSerVirtual;
    }
    
    public String toString() {
    	return "{nombre: " + this.nombre + ", " + "esVirtual: " + this.puedeSerVirtual + "}";
    }
}
