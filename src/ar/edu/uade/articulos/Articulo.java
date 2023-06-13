package ar.edu.uade.articulos;

import java.util.Date;

public class Articulo {
	
    private static int nextID = 0;
    private final int id;
    private TipoArticulo tipoArticulo;
    private Date fechaCompra;
    private Date fechaFabricacion;
    private int usos;
    private boolean flagDesgastado;
	
    public Articulo(TipoArticulo tipoArticulo, Date fechaCompra, Date fechaFabricacion) {
        this.id = nextID++;
        this.tipoArticulo = tipoArticulo;
        this.usos = 0;
        this.flagDesgastado = false;
        
        if (fechaCompra == null) 
        	this.fechaCompra = new Date(0);
        else 
        	this.fechaCompra = fechaCompra;
        
        if (fechaFabricacion == null) 
        	this.fechaFabricacion = new Date(0);
        else 
        	this.fechaFabricacion = fechaFabricacion;
    }

    public FormaAmortizacion getFormaAmortizacion() {
        // Code for getting amortization method
        return this.tipoArticulo.getFormaAmortizacion();
    }

    private int getUsosRestantes() {
    	int usosRestantes = (this.tipoArticulo.getCantidadAmortizacion() - this.usos);
    	return usosRestantes;
    }
	
    private int getDiasRestantes() {
    	Date now = new Date();
    	int diasRestantes = (int)( (now.getTime() - fechaFabricacion.getTime()) / (1000 * 60 * 60 * 24) );
        this.tipoArticulo.getCantidadAmortizacion();
        return diasRestantes;
    }

    public int getDesgaste() {
    	int desgaste = -1;
    	
    	if (this.getFormaAmortizacion() == FormaAmortizacion.POR_USO) 
    		desgaste = getUsosRestantes();
    
    	if (this.getFormaAmortizacion() == FormaAmortizacion.FECHA_FABRICACION) 
    		desgaste = getDiasRestantes();    	
    	
    	return desgaste;
    }

    public void setDesgaste() {
    	this.usos++;
    	if (this.getDesgaste() <= 0)
    		this.flagDesgastado = true;
    }

    public boolean isDesgastado() {
        return this.flagDesgastado;
    }
    
    public int getID() {
    	return this.id;
    }
    
    @Override
    public String toString() {
    	return "{" +
    			"id: " + this.id + ", " +
    			"tipoArticulo: " + this.tipoArticulo + ", " +
    			"fechaCompra: " + this.fechaCompra + ", " +
    			"fechaFabricacion: " + this.fechaFabricacion + ", " +
    			"usos: " + this.usos + ", " +
    			"flagDesgastado: " + this.flagDesgastado + 
    			"}";
    }
    
}
