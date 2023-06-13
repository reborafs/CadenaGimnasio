package ar.edu.uade.bbdd;

import java.util.ArrayList;
import java.util.HashMap;
import ar.edu.uade.gym.Clase;
import ar.edu.uade.gym.Ejercicio;

public class BaseDeDatos {
    private static BaseDeDatos instancia;
    private HashMap<Ejercicio, ArrayList<Clase>> listaClasesVirtuales;
    
    public BaseDeDatos() {
    	this.listaClasesVirtuales = new HashMap<Ejercicio, ArrayList<Clase>>();
    }

    public static BaseDeDatos getInstance() {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    public void agregarClaseVirtual(Clase clase) {
    	Ejercicio ejercicio = clase.getEjercicio();
    	if (ejercicio.puedeSerVirtual()) {
    		ArrayList<Clase> clasesVirtuales = listaClasesVirtuales.get(ejercicio);
    		if (clasesVirtuales == null) {
    			ArrayList<Clase> newList = new ArrayList<Clase>();
    			newList.add(clase);
    			listaClasesVirtuales.put(ejercicio, newList);
    		} else {
    			clasesVirtuales.add(clase);
        		if (clasesVirtuales.size() > ejercicio.getMaxCantidadClasesVirtuales()) {
        			clasesVirtuales.remove(0);
        		}    			
    		}
	}
	}

    public void eliminarClaseVirtual(Clase clase) {
    	
    }

	public ArrayList<Clase> getClasesVirtuales() {
		ArrayList<Clase> flatClases = new ArrayList<Clase>();
		
		for (ArrayList<Clase> clases : this.listaClasesVirtuales.values()) 
			flatClases.addAll(clases);
		
		return flatClases;
	}
}
