package ar.edu.uade.articulos;

public class TipoArticulo {

	private String nombre;
    private String marca;
    private String descripcion;
    private FormaAmortizacion formaAmortizacion;
    private int cantidadAmortizacion;

    public TipoArticulo(String nombre, String marca, String descripcion, boolean flagAmortizacionPorFecha, int cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.cantidadAmortizacion = cantidad;
        if (flagAmortizacionPorFecha) 
            this.formaAmortizacion = FormaAmortizacion.FECHA_FABRICACION;
        else 
            this.formaAmortizacion = FormaAmortizacion.POR_USO;
    }

    
    public FormaAmortizacion getFormaAmortizacion() {
        return formaAmortizacion;
    }
    
    public String getNombre() {
		return nombre;
    }

	public String getMarca() {
		return marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCantidadAmortizacion() {
		return this.cantidadAmortizacion;
	}
	
	@Override
	public String toString() {
		return "{" +
				"nombre: " + this.nombre + ", " +
				"marca: " + this.marca + ", " + 
				"descripcion: " + this.descripcion + ", " + 
				"formaAmortizacion: " + this.formaAmortizacion + ", " + 
				"cantidadAmortizacion: " + this.cantidadAmortizacion + 
				"}";
	}
}
