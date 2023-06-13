package ar.edu.uade.usuarios;

public abstract class Usuario {
    private String nombre;
    //private String contrasenia;
    private int id;
    
    public Usuario(String nombre) {
    	this.nombre = nombre;
    	//this.contrasenia = contrasenia;
    	this.id = NumeroIdentificador.getInstance().generarNuevoId();
    }
    
    public String getNombre() {
    	return this.nombre;
    }
    
    public int getID() {
    	return this.id;
    }
    
    public String getStringTipoUsuario() {
    	String tipoCliente = null;
    	
    	if (soyAdministrativo())
    		tipoCliente = "Administrativo";
    	if (soyCliente())
    		tipoCliente = "Cliente";
    	if (soySoporteTecnico())
    		tipoCliente = "SoporteTecnico";
    	if (soyProfesor())
    		tipoCliente = "Profesor";
    	
		return tipoCliente;
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
    	return "{" + "id: " + this.id + ", " + "nombre: " + this.nombre + ", " + "tipoUsuario: " + this.getStringTipoUsuario() + "}";
    }
}
