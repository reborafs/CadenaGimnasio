package ar.edu.uade.gym;

import java.util.ArrayList;

import ar.edu.uade.gym.articulos.TipoArticulo;

public class Ejercicio {
    private static int nextID = 0;
    private final int id;
    private String nombre;
    private boolean puedeSerVirtual = false;
    private int maxClasesVirtualesGuardadas = 10;
    private ArrayList<TipoArticulo> listaArticulosNecesarios;


    public Ejercicio(String nombre, boolean puedeSerVirtual, int maxClasesVirtualesGuardadas,
    		ArrayList<TipoArticulo> listaArticulosNecesarios) {
        this.id = nextID++;
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
        String[] array = new String[5];
        array[0] = String.valueOf(id);
        array[1] = nombre;

        if (puedeSerVirtual)
            array[2] = "SI";
        else
            array[2] = "NO";

        if (puedeSerVirtual)
            array[3] = String.valueOf(maxClasesVirtualesGuardadas);
        else
            array[3] = "-";

        array[4] = listaArticulosNecesarios.toString();
        return array;
    }
}
