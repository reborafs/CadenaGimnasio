package ar.edu.uade.gym.articulos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Articulo {
	
    private static int nextID = 0;
    private final int id;
    private TipoArticulo tipoArticulo;
    private double precio;
    private LocalDate fechaCompra;
    private LocalDate fechaFabricacion;
    private int usos;
    private boolean flagDesgastado;

    public Articulo(TipoArticulo tipoArticulo, double precio, LocalDate fechaCompra, LocalDate fechaFabricacion) {
        this.id = nextID++;
        this.tipoArticulo = tipoArticulo;
        this.precio = precio;
        this.usos = 0;
        this.flagDesgastado = false;

        if (fechaCompra == null) 
        	this.fechaCompra = LocalDate.now();
        else 
        	this.fechaCompra = fechaCompra;
        
        if (fechaFabricacion == null) 
        	this.fechaFabricacion = LocalDate.now();
        else 
        	this.fechaFabricacion = fechaFabricacion;
    }

    public double getCostoAmortizacion() {
        // SE CALCULA COMO EL PRECIO DIVIDIDO EL TOTAL DE USOS O DIAS DESDE SU FABRICACION
        return precio / this.tipoArticulo.getCantidadAmortizacion();
    }


    public FormaAmortizacion getFormaAmortizacion() {
        return this.tipoArticulo.getFormaAmortizacion();
    }

    private long getUsosRestantes() {
    	long cantidadLimiteUsos = this.tipoArticulo.getCantidadAmortizacion();
        long usosHastaHoy = this.usos;
    	return cantidadLimiteUsos - usosHastaHoy;
    }
	
    private long getDiasRestantes() {
        long diasHastaAmortizarse= this.tipoArticulo.getCantidadAmortizacion();
    	long diasDesdeFabricacion = fechaFabricacion.until(LocalDate.now(), ChronoUnit.DAYS);
        return diasHastaAmortizarse - diasDesdeFabricacion;
    }

    public long getDesgaste() {
    	long desgaste = -1;
    	
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

    public void darDeBaja() {
        this.flagDesgastado = true;
    }

    public boolean isDesgastado() {
        return this.flagDesgastado;
    }

    public int getID() {
        return this.id;
    }
    public double getPrecio() {
        return this.precio;
    }
    public void setPrecio(double precio) {
        if (precio >= 0)
            this.precio = precio;
    }
    public TipoArticulo getTipoArticulo() {
        return this.tipoArticulo;
    }

    public String[] getInfo() {
        String[] array = new String[7];
        array[0] = String.valueOf(id);
        array[1] = this.tipoArticulo.getInfo()[1];
        array[2] = String.valueOf(this.fechaCompra);
        array[3] = String.valueOf(this.fechaFabricacion);
        array[4] = String.valueOf(this.usos);
        array[5] = String.valueOf(this.flagDesgastado);
        return array;
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
