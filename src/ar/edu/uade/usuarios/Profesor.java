package ar.edu.uade.usuarios;

import java.util.ArrayList;
import ar.edu.uade.gym.Clase;

public class Profesor extends Usuario {

    public Profesor(String nombre) {
    	super(nombre);
    }
	
	public ArrayList<Clase> getClasesAsignadas() {
        // Code for getting assigned classes
        return null;
    }
	
	@Override
	public String toString() {
		return this.getNombre();
	}
}
