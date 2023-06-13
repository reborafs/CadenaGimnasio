package ar.edu.uade.usuarios;

public class NumeroIdentificador {
    private static NumeroIdentificador instancia;
    private int ultimoId;

    public NumeroIdentificador() {
    	this.ultimoId = 0;
    }
    
    public static NumeroIdentificador getInstance() {
        if (instancia == null) {
            instancia = new NumeroIdentificador();
        }
        return instancia;
    }
    
    public int generarNuevoId() {
        // Code for generating new ID
        return ultimoId++;
    }
}
