package ar.edu.uade.cliente;

import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.gym.Ejercicio;
import ar.edu.uade.gym.Sede;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;

import java.time.LocalDate;
import java.time.LocalTime;
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
                if (sede.getEjerciciosDisponibles() != null) {
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
     *                    GETTERS / SETTERS
     * =======================================================
     */

    public HashMap<LocalDate, ArrayList<LocalTime>> getClasesAsignadas(String ubicacionSede) {
        return gym.getHorariosClasesAsignadasClientes(ubicacionSede, this.usuario);
    }

}
