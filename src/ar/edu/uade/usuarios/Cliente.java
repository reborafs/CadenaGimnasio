package ar.edu.uade.usuarios;

import ar.edu.uade.gym.TipoNivel;

public class Cliente extends Usuario {
	
    private TipoNivel tipoNivel;
    
    public Cliente(String nombre, String contrasenia, TipoNivel nivel) {
    	super(nombre, contrasenia);
    	this.tipoNivel = nivel;
    }
    
    public TipoNivel getTipoNivel() {
    	return this.tipoNivel;
    }

    public void setTipoNivel(TipoNivel tipoNivel) {
        this.tipoNivel = tipoNivel;
    }

    public void realizarReservaClase() {
        // TO-DO Code for making a class reservation
    }

    public double getCostoMembresia() {
        return this.tipoNivel.getCostoMembresia();
    }

    @Override
    public boolean soyCliente() {
    	return true;
    }

    public String[] getInfo() {
        String[] array = new String[3];
        array[0] = String.valueOf(getID());
        array[1] = getNombre();
        array[2] = getTipoNivel().toString();
        return array;
    }
}
