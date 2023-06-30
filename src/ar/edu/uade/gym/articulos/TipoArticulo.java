package ar.edu.uade.gym.articulos;

public class TipoArticulo {

    private static int nextID = 0;
    private final int id;
    private final String nombre;
    private final CategoriaArticulo categoria;
    private final String marca;
    private final String descripcion;
    private final FormaAmortizacion formaAmortizacion;
    private final int cantidadAmortizacion;

    public TipoArticulo(String nombre, CategoriaArticulo categoria, String marca, String descripcion, boolean flagAmortizacionPorFecha, int cantidad) {
        this.id = nextID++;
        this.nombre = nombre;
        this.categoria = categoria;
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
		return  "id: " + this.id + ", " +
				"categoria: " + this.nombre + ", " +
				"marca: " + this.marca + ", " + 
				"descripcion: " + this.descripcion + ", " + 
				"formaAmortizacion: " + this.formaAmortizacion + ", " + 
				"cantidad: " + this.cantidadAmortizacion;
	}


    public String[] getInfo() {
        String[] array = new String[7];
        array[0] = String.valueOf(id);
        array[1] = this.nombre;
        array[2] = categoria.name();
        array[3] = marca;
        array[4] = descripcion;
        array[5] = formaAmortizacion.getDescripction();
        array[6] = String.valueOf(cantidadAmortizacion);
        return array;
    }

    public CategoriaArticulo getCategoriaArticulo() {
        return this.categoria;
    }
}
