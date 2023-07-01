package ar.edu.uade.login;

import ar.edu.uade.administrativo.ControladorAdministrativo;
import ar.edu.uade.cliente.ControladorCliente;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.profesor.ControladorProfesor;
import ar.edu.uade.soporteTecnico.ControladorSoporteTecnico;
import ar.edu.uade.usuarios.*;
import ar.edu.uade.gym.CadenaGimnasio;

public class ControladorLogin {
    private CadenaGimnasio gym;

    private static ControladorLogin instancia;


    public ControladorLogin() {
        this.gym = CadenaGimnasio.getInstance();
    }

    public static ControladorLogin getInstance() {
        if (instancia == null) {
            instancia = new ControladorLogin();
        }
        return instancia;
    }

    public void loginUsuario(String nombreUsuario, String contraseniaUsuario) throws GymException {
        Usuario user = gym.getUsuario(nombreUsuario, contraseniaUsuario);
        abrirVistaUsuario(user);
    }

    private void abrirVistaUsuario(Usuario user) {
        switch (user.getStringTipoUsuario()) {
            case "Administrativo" -> abrirVistaAdministrativo((Administrativo) user);
            case "Cliente" -> abrirVistaCliente((Cliente) user);
            case "SoporteTecnico" -> abrirVistaSoporteTecnico((SoporteTecnico) user);
            case "Profesor" -> abrirVistaProfesor((Profesor) user);
        }
    }

    private void abrirVistaAdministrativo(Administrativo user) {
        ControladorAdministrativo ctrl = ControladorAdministrativo.getInstance();
        ctrl.abrirVistaPrincipal(user);
    }

    private void abrirVistaCliente(Cliente user) {
        ControladorCliente ctrl = ControladorCliente.getInstance();
        ctrl.abrirVistaPrincipal(user);
    }

    private void abrirVistaSoporteTecnico(SoporteTecnico user) {
        ControladorSoporteTecnico ctrl = ControladorSoporteTecnico.getInstance();
        ctrl.abrirVistaPrincipal(user);    }

    private void abrirVistaProfesor(Profesor user) {
        // Crear y mostrar la ventana Profesor
        ControladorProfesor ctrl = ControladorProfesor.getInstance();
        ctrl.abrirVistaPrincipal(user);
    }
}