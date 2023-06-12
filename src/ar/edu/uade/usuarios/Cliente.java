package ar.edu.uade.usuarios;

import ar.edu.uade.gym.TipoNivel;

public class Cliente extends Usuario {
	
    private TipoNivel tipoNivel;
    
    public Cliente(String nombre, TipoNivel nivel) {
    	super(nombre);
    	this.tipoNivel = nivel;
    }
    
    public TipoNivel getTipoNivel() {
    	return this.tipoNivel;
    }
    
    public int getPagoMensual() {
        // TO-DO Code for calculating monthly payment
        return 0;
    }

    public void realizarReservaClase() {
        // TO-DO Code for making a class reservation
    }
    
    @Override
    public boolean soyCliente() {
    	return true;
    }
    
}
