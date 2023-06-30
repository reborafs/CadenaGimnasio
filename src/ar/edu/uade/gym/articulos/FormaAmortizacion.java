package ar.edu.uade.gym.articulos;

public enum FormaAmortizacion {
    POR_USO("Por uso."), FECHA_FABRICACION("Por fecha de fabricacion.");

    private String descripcion;
    FormaAmortizacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripction() {
        return descripcion;
    }
}
