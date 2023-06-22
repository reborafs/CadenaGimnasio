package ar.edu.uade.articulos;

public class TipoArticulo {

	private final String nombre;
    private final String marca;
    private final String descripcion;
    private final FormaAmortizacion formaAmortizacion;
    private final int cantidadAmortizacion;

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
