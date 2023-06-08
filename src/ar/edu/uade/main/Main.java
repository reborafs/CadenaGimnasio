package ar.edu.uade.main;

import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.TipoNivel;
import ar.edu.uade.usuarios.Usuario;

public class Main {

	public static void main(String[] args) {
		
		// INICIO CADENA GIMNASIO
		CadenaGimnasio gym = CadenaGimnasio.getInstance();
		
		try {
			// ESTAS SEDE SE AGREGAN BIEN
			gym.agregarSede("Caballito", TipoNivel.BLACK, null, null);
			gym.agregarSede("Belgrano", TipoNivel.ORO, null, null);		
			
			// ESTAS SEDES VAN A FALLAR PORQUE CABALLITO YA EXISTE
			gym.agregarSede("Caballito", TipoNivel.ORO, null, null);
			gym.agregarSede("Palermo", TipoNivel.PLATINUM, null, null);
		} catch (GymException e) {
			e.printStackTrace();
		}
		
		System.out.println(gym.getStringListaSedes());
	}

}
