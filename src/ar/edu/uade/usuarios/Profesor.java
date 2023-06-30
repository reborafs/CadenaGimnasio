package ar.edu.uade.usuarios;

import java.util.ArrayList;
import ar.edu.uade.gym.Clase;

public class Profesor extends Usuario {

    private double sueldo;

    public Profesor(String nombre, String contrasenia, double sueldo) {
    	super(nombre, contrasenia);
        this.sueldo = sueldo;
    }
	
	public ArrayList<Clase> getClasesAsignadas() {
        // Code for getting assigned classes
        return null;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return this.sueldo;
    }
	
	@Override
    public boolean soyProfesor() {
    	return true;
    }


    public String[] getInfo() {
        String[] array = new String[3];
        array[0] = String.valueOf(getID());
        array[1] = getNombre();
        array[2] = String.valueOf(getSueldo());
        return array;
    }
}
