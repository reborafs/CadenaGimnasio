package ar.edu.uade.soporteTecnico;

import ar.edu.uade.gym.*;
import ar.edu.uade.gym.articulos.TipoArticulo;
import ar.edu.uade.usuarios.SoporteTecnico;

import javax.swing.*;
import java.awt.*;
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

    public void agregarCliente(String nombre, String contrasena, String nivel) throws GymException{
        gym.agregarCliente(nombre,contrasena,nivel);
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

    public ArrayList<String> getListaNombresEjercicios() {
        return this.gym.getListaNombresEjercicios();
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
        if (id == this.usuario.getID())
            throw new GymException("No puede borrarse a si mismo.");
        gym.eliminarUsuario(id);
    }

    public ArrayList<String> getListaSedes() {
        return gym.getListaNombresSedes();
    }

    public ArrayList<String[]> getListaInfoSedes() {
        return gym.getListaInfoSedes();
    }


    public void agregarSede(String ubicacion, String tipoNivel, ArrayList<String[]> emplazamientos,
                            ArrayList<String> ejerciciosDisponibles, double alquilerSede) throws GymException {
        gym.agregarSedeString(ubicacion, tipoNivel, emplazamientos, ejerciciosDisponibles, alquilerSede);
    }

    public ArrayList<String> getTiposMembresias() {
        return gym.getListaNiveles();
    }

    public String[] getListaTiposUsuarios() {
        return new String[]{"Administrativo", "Cliente", "Profesor", "Soporte Tecnico"};
    }


    public void agregarAdministrativo(String nombre, String contrasena, ArrayList<String> listaSedes) throws GymException {
        gym.agregarAdministrativo(nombre, contrasena, listaSedes);
    }

    public void agregarProfesor(String nombre, String contrasena, double sueldo) throws GymException {
        gym.agregarProfesor(nombre, contrasena, sueldo);
    }

    public void agregarSoporteTecnico(String nombre, String contrasena) throws GymException {
        gym.agregarSoporteTecnico(nombre,contrasena);
    }

    public void modificarAdministrativo(int id, ArrayList<String> selectedSedes) throws GymException {
        gym.asignarNuevaListaSedes(id, selectedSedes);
    }

    public void agregarEjercicio(String nombre, int numVirtuales, String articulos, boolean flagVirtual) throws GymException {
        String[] articulosArray = articulos.trim().split(",");
        ArrayList<String> articulosList = new ArrayList<>();
        for (String art : articulosArray)
            articulosList.add(art);

        gym.agregarEjercicio(nombre, flagVirtual, numVirtuales, articulosList);
    }

    public ArrayList<String[]> getListaInfoEjercicios() {
        return gym.getListaEjercicios();
    }

    public void eliminarSede(String ubicacion) throws GymException {
        gym.eliminarSede(ubicacion);
    }

    public void eliminarEjercicio(String nombreEjercicio) throws GymException {
        gym.eliminarEjercicio(nombreEjercicio);
    }

    public ArrayList<String> getListaTiposEmplazamientos() {
        return gym.getListaTiposEmplazamientos();
    }

    public void eliminarTipoArticulo(int idArticulo) throws GymException {
        gym.eliminarTipoArticulo(idArticulo);
    }
}



