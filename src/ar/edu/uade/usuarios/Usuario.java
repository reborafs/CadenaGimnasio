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
}
