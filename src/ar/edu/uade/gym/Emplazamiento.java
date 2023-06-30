package ar.edu.uade.gym;

public class Emplazamiento {
	
    private TipoEmplazamiento tipoEmplazamiento;
    private int capacidad;
    private int metrosCuadrados;
	
    public Emplazamiento(TipoEmplazamiento tipoEmplazamiento, int capacidad, int metrosCuadrados) {
    	this.setTipoEmplazamiento(tipoEmplazamiento);
        this.setCapacidad(capacidad);
        this.setMetrosCuadrados(metrosCuadrados);
    }

	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public TipoEmplazamiento getTipoEmplazamiento() {
		return tipoEmplazamiento;
	}

	private void setTipoEmplazamiento(TipoEmplazamiento tipoEmplazamiento) {
		this.tipoEmplazamiento = tipoEmplazamiento;
	}

	public String toString() {
		return 	this.tipoEmplazamiento + " para " + this.capacidad + " personas de " + 	this.metrosCuadrados + 	"m2";
	}
}
