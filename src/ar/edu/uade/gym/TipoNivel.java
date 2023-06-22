package ar.edu.uade.gym;

public enum TipoNivel {
	BLACK(1, 5000),
	ORO(2, 7000),
	PLATINUM(3, 9000);

	private final int intNivel;
	private final double costoMembresia;

	TipoNivel(int num, double costoMembresia) {
		this.intNivel = num;
		this.costoMembresia = costoMembresia;
	}
	
	public int getValue() {
		return this.intNivel;
	}
	public double getCostoMembresia() {
		return this.costoMembresia;
	}
}
