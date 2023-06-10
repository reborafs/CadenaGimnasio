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
    private TipoEmplazamiento tipoEmplazamiento;
    private ArrayList<Articulo> listaArticulos;
    private boolean esVirtual = false;
    private boolean profesorDisponible;

    public Clase(Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos, 
    		TipoEmplazamiento tipoEmplazamiento, ArrayList<Articulo> listaArticulos, boolean esVirtual) {
    	this.profesorAsignado = profesor;
    	this.profesorDisponible = true;
        this.ejercicio = ejercicio;
        this.estado = EstadoClase.AGENDADA;
        this.tipoEmplazamiento = tipoEmplazamiento;
        this.esVirtual = esVirtual;

        if (listaAlumnos == null)
        	this.listaAlumnos = new ArrayList<Cliente>();
        else
        	this.listaAlumnos = listaAlumnos;
        
        if (listaArticulos == null)
        	this.listaArticulos = new ArrayList<Articulo>();
        else
        	this.listaArticulos = listaArticulos;
    }

    public void agregarAlumnos(ArrayList<Cliente> listaAlumnos) {
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
    
    @Override
    public String toString() {
    	return "{" +
    			"Profesor: " + this.profesorAsignado + ", " +
    			"Ejercicio: " + this.ejercicio + ", " +
    			"Estado: " + this.estado + ", " +
    			"CantidadAlumnos: " + this.listaAlumnos.size() + ", " +
    			"TipoEmplazamiento: " + this.tipoEmplazamiento + ", " +
    			"esVirtual: " + this.esVirtual + 
    			"}";
    }
}
