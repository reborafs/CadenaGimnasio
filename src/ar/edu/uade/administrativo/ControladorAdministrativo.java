package ar.edu.uade.administrativo;

import ar.edu.uade.gym.*;
import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.gym.articulos.CategoriaArticulo;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorAdministrativo {

    private static ControladorAdministrativo instancia;
    private final CadenaGimnasio gym;
    private Administrativo usuario;

    private ControladorAdministrativo() {
        this.gym = CadenaGimnasio.getInstance();
    }

    public static ControladorAdministrativo getInstance() {
        if (instancia == null) {
            instancia = new ControladorAdministrativo();
        }
        return instancia;
    }

    public void setUsuario(Administrativo usuario) {
        this.usuario = usuario;
    }
    
    public Administrativo getUsuario() {
        return this.usuario;
    }

    public void abrirVistaPrincipal(Administrativo usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalGestionClases vistaAdmin = new VistaPrincipalGestionClases();;
            vistaAdmin.setVisible(true);
        });
    }
    
    public void abrirVistaArticulos() {
        SwingUtilities.invokeLater(() -> {
            VistaGestionArticulos vistaArticulos = new VistaGestionArticulos();;
            vistaArticulos.setVisible(true);
        });
    }
    
    public void abrirVistaClases() {
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalGestionClases vistaClases = new VistaPrincipalGestionClases();;
            vistaClases.setVisible(true);
        });
    }
    
    public void abrirVistaClientes() {
        SwingUtilities.invokeLater(() -> {
        	VistaGestionClientes vistaClientes = new VistaGestionClientes();;
        	vistaClientes.setVisible(true);
        });
    }
    
    public void abrirVistaProfesores() {
        SwingUtilities.invokeLater(() -> {
        	VistaGestionProfesores vistaProfesores = new VistaGestionProfesores();;
        	vistaProfesores.setVisible(true);
        });
    }

    public void agregarTipoArticulo(String nombre, String categoriaArticulo, String marca, String descripcion,
                                    int usosAmortizacion, boolean flagFecha) {
        try {
            if (flagFecha) {
                gym.agregarTipoArticuloPorFecha(nombre, categoriaArticulo, marca, descripcion, usosAmortizacion);
            } else {
                gym.agregarTipoArticuloPorUso(nombre, categoriaArticulo, marca, descripcion, usosAmortizacion);
            }
        } catch (GymException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String[]> getListaTiposArticulos() {
        return gym.getListaCatalogoDeArticulos();
    }

    public void agregarCliente(String nombre, String contrasena, String nivel) {
        try {
            gym.agregarCliente(nombre,contrasena,nivel);
        } catch (GymException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getListaClientes() {
        return gym.getListaClientes();
    }

    public void eliminarCliente(int id) throws GymException {
        gym.eliminarCliente(id);
    }

    public void modificarCliente(int id, String nombre, String contrasenia, String nivel) throws GymException {
        gym.modificarCliente(id, nombre, contrasenia, nivel);
    }

    public HashMap<LocalDate, ArrayList<LocalTime>> getClasesAsignadas() {
        return gym.getHorariosClasesAsignadasAdmin(this.usuario);
    }

    public void agregarClase(String sede, String profesor, String ejercicio, String listaAlumnos, String fecha,
                             String horarioInicio, String emplazamiento, String listaArticulos, boolean esVirtual) {
        //gym.agendarClase(sede, profesor, ejercicio, listaAlumnos, fecha, horarioInicio, emplazamiento, listaArticulos, esVirtual);
        System.out.println(sede);
    }

    public ArrayList<String> getListaSedes() {
        return this.usuario.getNombreSedesAsignadas();
    }

    public ArrayList<String> getListaEjercicios(String sede) {
        return this.gym.getEjerciciosDisponiblesSede(sede);
    }

    public ArrayList<String> getListaNiveles() {
        return gym.getListaNiveles();
    }

    public void agregarProfesor(String nombre, String contrasena, double sueldo) {
        gym.agregarProfesor(nombre, contrasena, sueldo);
    }

    public void modificarProfesor(int id, String nombre, String contrasena, Integer sueldo) throws GymException {
        gym.modificarProfesor(id, nombre, contrasena, sueldo);
    }

    public void eliminarProfesor(int id) throws GymException {
        gym.eliminarProfesor(id);
    }

    public ArrayList<String[]> getListaProfesores() {
        return gym.getListaProfesores();
    }

    public ArrayList<String>  getCategoriasArticulos() {
        return gym.getCategoriasArticulos();
    }

    public ArrayList<String[]> getListaClasesPorSede() {
        ArrayList<Sede> sedesAsignadas = this.usuario.getSedesAsignadas();
        return gym.getListaClasesPorSede(sedesAsignadas);
    }
}
