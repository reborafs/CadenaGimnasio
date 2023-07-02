package ar.edu.uade.administrativo;

import ar.edu.uade.gym.*;
import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.gym.articulos.CategoriaArticulo;
import ar.edu.uade.gym.articulos.TipoArticulo;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;

import javax.swing.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public void agregarArticulo(int cantidad, String nombreSede, String nomnreTipoArticulo, double precio, String fechaCompra, String fechaFabricacion) {
        Sede sede = gym.getSede(nombreSede);
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaCompraFormato = LocalDate.parse(fechaCompra,formatoFecha);
        LocalDate fechaFabricacionFormato = LocalDate.parse(fechaFabricacion,formatoFecha);
        TipoArticulo tipoArticulo = null;
        for(TipoArticulo articulo: gym.getCatalogoDeArticulos()){
            if(nomnreTipoArticulo.equals(articulo.getInfo()[1])){
                tipoArticulo = articulo;
            }
        }
        for(int i = 0; i < cantidad; i++)
        {
            if (fechaCompra.equals("")) {
                gym.agregarArticulo(sede, tipoArticulo, precio, fechaCompraFormato, fechaFabricacionFormato);
            } else {
                gym.agregarArticulo(sede, tipoArticulo, precio, fechaCompraFormato, fechaFabricacionFormato);
            }
        }
    }

    public void eliminarArticulo(int id) throws GymException {
        gym.darDeBajaArticulo(id);
    }

    public ArrayList<String[]> getListaTiposArticulos() {
        return gym.getListaCatalogoDeArticulos();
    }

    public HashMap<String,ArrayList<String[]>> getListaArticulos() {
        return gym.getListaArticulos();
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

    public void agregarClase(String sede, String idProfesor, String ejercicio, ArrayList<Cliente> listaAlumnos, String fecha,
                             String horarioInicio, String emplazamiento, ArrayList<Articulo> listaArticulos, boolean esVirtual) throws GymException {
        Sede sedeFormato = gym.getSede(sede);
        Profesor profesorFormato = gym.getProfesor(Integer.valueOf(idProfesor));
        Ejercicio ejercicioFormato = gym.getEjercicio(ejercicio);
        ArrayList<Cliente> listaAlumnosFormato = listaAlumnos;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaFormato = LocalDate.parse(fecha,formatoFecha);
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horarioInicioFormato = LocalTime.parse(horarioInicio, formatoHora);
        Emplazamiento emplazamientoFormato = getEmplazamiento(sedeFormato, emplazamiento);
        ArrayList<Articulo> listaArticulosFormato = null;
        //Boolean esVirtual = false;


        gym.agendarClase(sedeFormato, profesorFormato, ejercicioFormato, listaAlumnosFormato, fechaFormato, horarioInicioFormato, emplazamientoFormato, listaArticulosFormato, esVirtual);
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

    public ArrayList<Emplazamiento> getEmplazamiento(String sede) {
        return gym.getSede(sede).getEmplazamientosDisponibles();
    }

    public void agregarProfesor(String nombre, String contrasena, double sueldo) throws GymException {
        gym.agregarProfesor(nombre, contrasena, sueldo);
    }

    public void modificarProfesor(int id, String nombre, String contrasena, Double sueldo) throws GymException {
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

    private Emplazamiento getEmplazamiento(Sede sede, String valor){
        ArrayList<Emplazamiento> lista = sede.getEmplazamientosDisponibles();
        for(Emplazamiento emplazmiento : lista){
            if(valor.equals(emplazmiento.toString())){
                return emplazmiento;
            }
        }
        return null;
    }

    public void cambiarEstadoClase(String sede, String claseId, String estado) throws GymException {
        gym.cambiarEstadoClase(sede, Integer.parseInt(claseId) ,estado);
    }
}
