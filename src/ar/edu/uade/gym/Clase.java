package ar.edu.uade.gym;

import java.util.ArrayList;

import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;


public class Clase {
    private Profesor profesorAsignado;
    private Ejercicio ejercicio;
    private EstadoClase estado;
    private ArrayList<Cliente> listaAlumnos;
    
    private boolean esVirtual = false;
    private boolean profesorDisponible;
    private TipoEmplazamiento tipoEmplazamiento;
    private ArrayList<Articulo> articulo;
    
    public Clase(Profesor profesor, Ejercicio ejercicio) {
    	this.profesorAsignado = profesor;
    	this.profesorDisponible = true;
    }

    public Clase(Ejercicio ejercicio) {
    	this.profesorDisponible = false;
    }
    
    public int calcularDesgaste() {
        // Code for calculating wear and tear
        return 0;
    }
    
    public Ejercicio getEjercicio() {
    	return this.ejercicio;
    }
    
    public void agregarAlumno(Cliente alumno) {
    	this.listaAlumnos.add(alumno);
    }
}
