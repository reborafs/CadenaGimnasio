package ar.edu.uade.soporteTecnico;

import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.usuarios.SoporteTecnico;
import ar.edu.uade.login.Login;

import javax.swing.*;

public class ControladorSoporteTecnico {

    private static ControladorSoporteTecnico instancia;
    private final CadenaGimnasio gym;
    private SoporteTecnico usuario;

    private ControladorSoporteTecnico(){
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

    public void abrirVistaPrincipal(SoporteTecnico usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaSoporteTecnico vistaSoporteTecnico = new VistaSoporteTecnico();;
            vistaSoporteTecnico.setVisible(true);
        });
    }

}

