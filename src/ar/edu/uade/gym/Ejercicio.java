package ar.edu.uade.gym;

import java.util.ArrayList;

import ar.edu.uade.gym.articulos.TipoArticulo;

public class Ejercicio {
    private String nombre;
    private boolean puedeSerVirtual = false;
    private int maxClasesVirtualesGuardadas = 10;
    private ArrayList<TipoArticulo> listaArticulosNecesarios;


    public Ejercicio(String nombre, boolean puedeSerVirtual, int maxClasesVirtualesGuardadas,
    		ArrayList<TipoArticulo> listaArticulosNecesarios) {
    	this.nombre = nombre.toLowerCase();
    	this.puedeSerVirtual = puedeSerVirtual;    	
        if (listaArticulosNecesarios != null)
        	this.maxClasesVirtualesGuardadas = maxClasesVirtualesGuardadas;
        
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

    public boolean puedeSerVirtual() {
    	return this.puedeSerVirtual;
    }
    
    public int getMaxCantidadClasesVirtuales() {
    	return this.maxClasesVirtualesGuardadas;
    }
    
    public String toString() {
    	return this.nombre;
    }

    public ArrayList<TipoArticulo> getArticuloNecesarios() {
        return this.listaArticulosNecesarios;
    }

    public String[] getInfo() {
        String[] array = new String[4];
        array[0] = nombre;

        if (puedeSerVirtual)
            array[1] = "SI";
        else
            array[1] = "NO";

        if (puedeSerVirtual)
            array[1] = "SI";
        else
            array[1] = "NO";

        if (puedeSerVirtual)
            array[2] = String.valueOf(maxClasesVirtualesGuardadas);
        else
            array[2] = "-";

        array[3] = listaArticulosNecesarios.toString();
        return array;
    }
}
