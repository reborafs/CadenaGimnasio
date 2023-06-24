package ar.edu.uade.usuarios;

public abstract class Usuario {
    private String nombre;
    private String contrasenia;
    final private int id;
    
    public Usuario(String nombre, String contrasenia) {
    	this.nombre = nombre;
    	this.contrasenia = contrasenia;
    	this.id = NumeroIdentificador.getInstance().generarNuevoId();
    }

    public String getNombre() {
        return this.nombre;
    }
    public String getContrasenia() {
        return this.contrasenia;
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

    public void setNombre(String nuevoNombre) {
        this.nombre = nombre;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
    	return "{" + "id: " + this.id + ", " + "nombre: " + this.nombre + ", " + "tipoUsuario: " + this.getStringTipoUsuario() + "}";
    }
}
