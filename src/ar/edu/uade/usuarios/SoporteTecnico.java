package ar.edu.uade.usuarios;

public class SoporteTecnico extends Usuario {
	
	public SoporteTecnico(String nombre, String contrasenia) {
		super(nombre, contrasenia);
	}
	
    public void crearSede() {
        // Code for creating a new sede
    }

    public void crearUsuario() {
        // Code for creating a new user
    }

    public void cargarEjercicioDisponible() {
        // Code for loading available exercises
    }

    public void crearArticulos() {
        // Code for creating new articles
    }

    public void darAltaAdministrativo(Administrativo administrativo) {
        // Code for adding an administrative user
    }

    public void darBajaAdministrativo(Administrativo administrativo) {
        // Code for removing an administrative user
    }

    public void modificarAdministrativo(Administrativo administrativo) {
        // Code for modifying an administrative user
    }

    @Override
    public boolean soySoporteTecnico() {
    	return true;
    }
}
