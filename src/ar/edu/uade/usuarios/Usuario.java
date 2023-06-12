package ar.edu.uade.usuarios;

public abstract class Usuario {
    private String nombre;
    private int id;
    
    public Usuario(String nombre) {
    	this.nombre = nombre;
    }
    
    public String getNombre() {
    	return this.nombre;
    }
    
    public int getID() {
    	return this.id;
    }
    
    public boolean soyAdministrativo() {
    	return false;
    }
    
    public boolean soyCliente() {
    	return false;
    }
    
    public boolean soyProfesor() {
    	return false;
    }
    
    public boolean soySoporteTecnico() {
    	return false;
    }
    
    @Override
    public String toString() {
    	return "{" + "id: " + this.id + ", " + "nombre: " + this.nombre + "}";
    }
}
