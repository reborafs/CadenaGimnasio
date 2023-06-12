package ar.edu.uade.gym;

public enum TipoNivel {
	BLACK(1),
	ORO(2),
	PLATINUM(3);
	
    private final int intNivel;

	TipoNivel(int num) {
		this.intNivel = num;
	}
	
	public int getValue() {
		return this.intNivel;
	}
}
