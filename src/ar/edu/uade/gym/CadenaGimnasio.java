package ar.edu.uade.gym;

import java.util.ArrayList;
import java.util.HashMap;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.SoporteTecnico;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.bbdd.BaseDeDatos;

class CadenaGimnasio {
    private static CadenaGimnasio instancia;
    private String nombre;
    private ArrayList<SoporteTecnico> usuariosSoporteTecnico;
    private ArrayList<Administrativo> usuariosAdministrativo;
    private ArrayList<Cliente> usuariosClientes;
    private ArrayList<Profesor> usuariosProfesores;
    private ArrayList<Articulo> catalogoDeArticulos;
    private HashMap<String, Sede> sedes;
    private BaseDeDatos baseDeClasesVirtuales;

    private CadenaGimnasio(String nombre) {
        this.nombre = nombre;
    }

    public static CadenaGimnasio getInstance() {
        if (instancia == null) {
            instancia = new CadenaGimnasio("Cadena de Gimnasio");
        }
        return instancia;
    }

    public void eliminarClasesVirtualesAntiguas() {
        // Code for eliminating old virtual classes
    }
}