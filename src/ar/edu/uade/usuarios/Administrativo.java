package ar.edu.uade.usuarios;

import java.util.ArrayList;

import ar.edu.uade.gym.Sede;

public class Administrativo extends Usuario {
    private ArrayList<Sede> sedesAsignadas;
    
	public Administrativo(String nombre, String contrasenia) {
		super(nombre, contrasenia);
        this.sedesAsignadas = new ArrayList<Sede>();
	}

	public Administrativo(String nombre, String contrasenia, Sede sedeAsignada) {
		super(nombre, contrasenia);
        this.sedesAsignadas = new ArrayList<Sede>();
        this.sedesAsignadas.add(sedeAsignada);
	}
	
	public void asignarSede(Sede sede) {
		this.sedesAsignadas.add(sede);
	}
	
	public ArrayList<Sede> getSedesAsignadas() {
		return this.sedesAsignadas;
	}
	
    public void agendarClasesDisponibles() {
        // Code for scheduling available classes
    }

    public void cambiarEstado() {
        // Code for changing state
    }

    public void agregarArticulos() {
        // Code for adding articles
    }

    public void ABPclientes() {
        // Code for ABPclientes
    }

    public void eliminarArticulosDesgastados(Sede sede) {
        // Code for removing worn-out articles in a sede
    }

    public void darAltaCliente(Cliente cliente) {
        // Code for adding a client
    }

    public void darBajaCliente(Cliente cliente) {
        // Code for removing a client
    }

    public void modificarCliente(Cliente cliente) {
        // Code for modifying a client
    }

    public void darAltaProfesor(Profesor profesor) {
        // Code for adding a professor
    }

    public void darBajaProfesor(Profesor profesor) {
        // Code for removing a professor
    }

    public void modificarProfesor(Profesor profesor) {
        // Code for modifying a professor
    }
    
    @Override
    public boolean soyAdministrativo() {
    	return true;
    }
    
}
