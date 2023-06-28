package ar.edu.uade.cliente;

import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.usuarios.Cliente;

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
}
