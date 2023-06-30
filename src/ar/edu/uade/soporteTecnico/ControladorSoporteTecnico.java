package ar.edu.uade.soporteTecnico;

import ar.edu.uade.gym.*;
import ar.edu.uade.usuarios.SoporteTecnico;

import javax.swing.*;
import java.util.ArrayList;

public class ControladorSoporteTecnico {

    private static ControladorSoporteTecnico instancia;
    private final CadenaGimnasio gym;
    private SoporteTecnico usuario;

    private ControladorSoporteTecnico() {
        this.gym = CadenaGimnasio.getInstance();
    }

    public static ControladorSoporteTecnico getInstance() {
        if (instancia == null) {
            instancia = new ControladorSoporteTecnico();
        }
        return instancia;
    }

    public void setUsuario(SoporteTecnico usuario) {
        this.usuario = usuario;
    }
    
    public SoporteTecnico getUsuario() {
        return this.usuario;
    }

    public void abrirVistaPrincipal(SoporteTecnico usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalGestionSedes vistaAdmin = new VistaPrincipalGestionSedes();;
            vistaAdmin.setVisible(true);
        });
    }
    
    public void abrirVistaArticulos() {
        SwingUtilities.invokeLater(() -> {
            VistaGestionTipoArticulos vistaArticulos = new VistaGestionTipoArticulos();;
            vistaArticulos.setVisible(true);
        });
    }
    
    public void abrirVistaClases() {
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalGestionSedes vistaClases = new VistaPrincipalGestionSedes();;
            vistaClases.setVisible(true);
        });
    }
    
    public void abrirVistaUsuarios() {
        SwingUtilities.invokeLater(() -> {
        	VistaGestionUsuarios vistaClientes = new VistaGestionUsuarios();;
        	vistaClientes.setVisible(true);
        });
    }
    
    public void abrirVistaProfesores() {
        SwingUtilities.invokeLater(() -> {
        	VistaGestionTipoEjercicio vistaProfesores = new VistaGestionTipoEjercicio();;
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

    public ArrayList<String[]> getListaUsuarios() {
        return gym.getListaStringUsuarios();
    }

    public void eliminarCliente(int id) throws GymException {
        gym.eliminarCliente(id);
    }

    public void modificarCliente(int id, String nombre, String contrasenia, String nivel) throws GymException {
        gym.modificarCliente(id, nombre, contrasenia, nivel);
    }


    public void agregarClase(String sede, String profesor, String ejercicio, String listaAlumnos, String fecha,
                             String horarioInicio, String emplazamiento, String listaArticulos, boolean esVirtual) {
        //gym.agendarClase(sede, profesor, ejercicio, listaAlumnos, fecha, horarioInicio, emplazamiento, listaArticulos, esVirtual);
        System.out.println(sede);
    }

    public ArrayList<String> getListaEjercicios(String sede) {
        return this.gym.getEjerciciosDisponiblesSede(sede);
    }

    public ArrayList<String> getListaNiveles() {
        return gym.getListaNiveles();
    }

    public ArrayList<String[]> getListaProfesores() {
        return gym.getListaProfesores();
    }

    public ArrayList<String>  getCategoriasArticulos() {
        return gym.getCategoriasArticulos();
    }


    public void agregarUsuario(String nombre, String contrasena, String nivel) {
        //gym.agregarProfesor(nombre, contrasena);
    }

    public void eliminarUsuario(int id) throws GymException {
        gym.eliminarUsuario(id);
    }

    public void modificarUsuario(int id, String nombre, String contrasena, String nivel) {
        //gym.modificarProfesor(id, nombre, contrasena);
    }

    public ArrayList<String> getListaSedes() {
        return gym.getListaNombresSedes();
    }

    public ArrayList<String[]> getListaInfoSedes() {
        return gym.getListaInfoSedes();
    }


    public void agregarSede(String ubicacion, String tipoNivel, String emplazamientos, String ejerciciosDisponibles, String alquilerSede) throws GymException {
        gym.agregarSedeString(ubicacion, tipoNivel, emplazamientos, ejerciciosDisponibles, alquilerSede);
    }

    public ArrayList<String> getTiposMembresias() {
        return gym.getListaNiveles();
    }

    public String[] getListaTiposUsuarios() {
        return new String[]{"Administrativo", "Cliente", "Profesor", "Soporte Tecnico"};
    }


}



