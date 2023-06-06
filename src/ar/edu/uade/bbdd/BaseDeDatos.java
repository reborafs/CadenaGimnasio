package ar.edu.uade.bbdd;

import java.util.HashMap;
import ar.edu.uade.gym.Clase;
import ar.edu.uade.gym.Ejercicio;

public class BaseDeDatos {
    private static BaseDeDatos instancia;
    private HashMap<Ejercicio, Clase> listaClasesVirtuales;

    public static BaseDeDatos getInstance() {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    public BaseDeDatos agregarClaseVirtual(Clase clase) {
        listaClasesVirtuales.put(clase.getEjercicio(), clase);
        return null;
    }

    public BaseDeDatos eliminarClaseVirtual(Clase clase) {
        // Code for removing a virtual class
        return null;
    }
}
