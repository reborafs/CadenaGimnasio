package ar.edu.uade.vistas;

import ar.edu.uade.administrativo.VistaAdministrativo;
import ar.edu.uade.cliente.PrincipalVistaClienteClaseAsignada;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.profesor.VistaProfesorClaseAsignada;
import ar.edu.uade.soporteTecnico.VistaSoporteTecnico;
import ar.edu.uade.usuarios.Usuario;
import ar.edu.uade.gym.CadenaGimnasio;

import javax.swing.*;

public class ControladorLogin {
    private CadenaGimnasio gym;

    public ControladorLogin() {
        this.gym = CadenaGimnasio.getInstance();
    }

    public void validarUsuarioExistente(String nombreUsuario, String contraseniaUsuario) {
        try {
            Usuario user = gym.getUsuario(nombreUsuario, contraseniaUsuario);
            abrirVistaUsuario(user);
        } catch (GymException e)  {
            e.printStackTrace();
        }
    }

    private void abrirVistaUsuario(Usuario user) {
        switch (user.getStringTipoUsuario()) {
            case "Administrativo":
                abrirVistaAdministrativo();
                break;
            case "Cliente":
                abrirVistaCliente();
                break;
            case "SoporteTecnico":
                abrirVistaSoporteTecnico();
                break;
            case "Profesor":
                abrirVistaProfesor();
                break;
        };
    }

    private void abrirVistaAdministrativo() {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            PrincipalVistaClienteClaseAsignada vistaClienteClaseAsignada = new PrincipalVistaClienteClaseAsignada();
            vistaClienteClaseAsignada.setVisible(true);
        });
    }

    private void abrirVistaCliente() {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            PrincipalVistaClienteClaseAsignada vistaClienteClaseAsignada = new PrincipalVistaClienteClaseAsignada();
            vistaClienteClaseAsignada.setVisible(true);
        });
    }

    private void abrirVistaSoporteTecnico() {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            PrincipalVistaClienteClaseAsignada vistaClienteClaseAsignada = new PrincipalVistaClienteClaseAsignada();
            vistaClienteClaseAsignada.setVisible(true);
        });
    }

    private void abrirVistaProfesor() {
        // Crear y mostrar la ventana Profesor
        SwingUtilities.invokeLater(() -> {
            VistaProfesorClaseAsignada vistaProfesorClaseAsignada = new VistaProfesorClaseAsignada();
            vistaProfesorClaseAsignada.setVisible(true);
        });
    }
}