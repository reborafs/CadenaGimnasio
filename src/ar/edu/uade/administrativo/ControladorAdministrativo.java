package ar.edu.uade.administrativo;

import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.login.Login;

import javax.swing.*;

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

    public void abrirVistaPrincipal(Administrativo usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaAdministrativo vistaAdmin = new VistaAdministrativo();;
            vistaAdmin.setVisible(true);
        });
    }
}
