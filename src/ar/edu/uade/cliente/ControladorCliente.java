package ar.edu.uade.cliente;

import ar.edu.uade.gym.*;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class ControladorCliente {
    private static ControladorCliente instancia;
    private final CadenaGimnasio gym;
    private Cliente usuario;

    private ControladorCliente() {
        this.gym = CadenaGimnasio.getInstance();
    }

    public static ControladorCliente getInstance() {
        if (instancia == null) {
            instancia = new ControladorCliente();
        }
        return instancia;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public void abrirVistaPrincipal(Cliente usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalClaseAsignada vistaCliente = new VistaPrincipalClaseAsignada();
            vistaCliente.setVisible(true);
        });
    }

    public void abrirVistaMembresia() {
        SwingUtilities.invokeLater(() -> {
            VistaMembresia vistaCliente = new VistaMembresia();
            vistaCliente.setVisible(true);
        });
    }

    public void abrirVistaClaseAsignada() {
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalClaseAsignada vistaCliente = new VistaPrincipalClaseAsignada();
            vistaCliente.setVisible(true);
        });
    }

    public void abrirVistaEjercicioPorSede() {
        SwingUtilities.invokeLater(() -> {
            VistaEjerciciosPorSede vistaCliente = new VistaEjerciciosPorSede();
            vistaCliente.setVisible(true);
        });
    }

    /* =======================================================
     *                    MEMBRESIA
     * =======================================================
     */

    public HashMap<String, String> getMembresias(){

        HashMap<String, String>  membresias = new HashMap<String, String>();

        String blackDesc = "La membresia basica solo puede ingresar a sedes Black";
        String oroDesc = "La membresia intermedia solo puede ingresar a todas las sedes Black y oro";
        String platinumDesc = "La membresia premiun puede ingresar a cualquiera de nuestars sedes";

        membresias.put("Black", blackDesc);
        membresias.put("Oro", oroDesc);
        membresias.put("Platinum", platinumDesc);
        return membresias;
    }

    /* =======================================================
     *                  EJERCIOS POR SEDE
     * =======================================================
     */
    public HashMap<String, ArrayList<String>> getEjerciciosPorSede(){
        HashMap<String, ArrayList<String>> ejerciciosPorSede = new HashMap<String, ArrayList<String>>();
        ArrayList<Sede> sedes = gym.getListaSedes();
        for(Sede sede : sedes){
            ArrayList<Ejercicio> ejercicios = sede.getEjerciciosDisponibles();
            System.out.print("aqui -->" +ejercicios.toString());
            ArrayList<String> ejerciciosDisponibles = new ArrayList<>();
            for(Ejercicio ejercicio: ejercicios) {
                if (ejercicio != null) {
                    ejerciciosDisponibles.add(ejercicio.getNombre());
                }
            }
            ejerciciosPorSede.put(sede.getUbicacion(),ejerciciosDisponibles);
        }
        return ejerciciosPorSede;
    }

    public boolean estaEnLista(String valor, ArrayList<String> valores){
        for(int i = 0; i < valores.size(); i++){
            if(valor.equals(valores.get(i))){
                return true;
            }
        }
        return false;
    }

    /* =======================================================
     *                  CLASES POR SEDE
     * =======================================================
     */

    //arreglar problema de sobreescritura de la key, porque pueden haber más de una clase con el mismo ejercicio
    public HashMap<String, ArrayList<LocalDateTime>> getClasesPorSede(String sede){
        HashMap<String, ArrayList<LocalDateTime>> clasesPorSede = new HashMap<String, ArrayList<LocalDateTime>>();
        ArrayList<Clase> clases = gym.getSede(sede).getListaClases();
        for(Clase clase : clases){
            System.out.print("aqui -->" + clases.toString());
            LocalDateTime horariosDisponibles = clase.getHorarioInicioFecha();
            String key = clase.getEjercicio().getNombre();
            agregarValor(clasesPorSede, key, horariosDisponibles);
        }
        return clasesPorSede;
    }

    public static void agregarValor(HashMap<String, ArrayList<LocalDateTime>> hashMap, String key, LocalDateTime value) {
        if (hashMap.containsKey(key)) {
            ArrayList<LocalDateTime> values = hashMap.get(key);
            values.add(value);
        } else {
            ArrayList<LocalDateTime> values = new ArrayList<>();
            values.add(value);
            hashMap.put(key, values);
        }
    }

    public void inscibirAlumno(String claseElegida, String horarioSeleccionado, String sede) throws GymException {
        Sede sedeClase = gym.getSede(sede);
        ArrayList<Clase> clases = sedeClase.getListaClases();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        LocalDateTime fechaHoraDesformateada = LocalDateTime.parse(horarioSeleccionado, formato);
        LocalDateTime fechaHorario = fechaHoraDesformateada;
        for (Clase clase : clases){
            if(clase.getEjercicio().getNombre().equals(claseElegida) && clase.getHorarioInicioFecha() == fechaHorario){
                sedeClase.agregarAlumno(clase.getClaseID(), this.usuario);
            }
        }
    }
    /* =======================================================
     *                    GETTERS / SETTERS
     * =======================================================
     */

    public HashMap<LocalDate, ArrayList<LocalTime>> getClasesAsignadas(String ubicacionSede) {
        return gym.getHorariosClasesAsignadasClientes(ubicacionSede, this.usuario);
    }

}
