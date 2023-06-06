package ar.edu.uade.gym;

import java.util.ArrayList;
import ar.edu.uade.articulos.TipoArticulo;

public class Ejercicio {
    private String nombre;
    private boolean puedeSerVirtual = false;
    private ArrayList<TipoArticulo> listaArticulosNecesarios;

    public Ejercicio(String nombre) {
    	this.nombre = nombre;
    }
    
    public Ejercicio(String nombre, boolean puedeSerVirtual) {
    	this.nombre = nombre;
    	this.puedeSerVirtual = true;
    }

    public Ejercicio(String nombre, boolean puedeSerVirtual, ArrayList<TipoArticulo> listaArticulosNecesarios) {
    	this.nombre = nombre;
    	this.puedeSerVirtual = true;
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

}
