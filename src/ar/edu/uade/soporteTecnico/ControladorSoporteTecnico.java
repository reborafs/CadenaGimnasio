package ar.edu.uade.soporteTecnico;

import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.usuarios.Administrativo;
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
    
    public SoporteTecnico getUsuario() {
        return this.usuario;
    }

    public void abrirVistaPrincipal(SoporteTecnico usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaSoporteTecnico vistaSoporteTecnico = new VistaSoporteTecnico();;
            vistaSoporteTecnico.setVisible(true);
        });
    }
    
    public void abrirVistaSedes(SoporteTecnico usuario) {
		setUsuario(usuario);
		SwingUtilities.invokeLater(() -> {
			VistaGestionSedes vistaSedes = new VistaGestionSedes();;
			vistaSedes.setVisible(true);
		});
    }

    public void abrirVistaUsuarios(SoporteTecnico usuario) {
		setUsuario(usuario);
		SwingUtilities.invokeLater(() -> {
			VistaGestionUsuarios vistaUsuarios = new VistaGestionUsuarios();;
			vistaUsuarios.setVisible(true);
		});
    }

    public void abrirVistaEjercicios(SoporteTecnico usuario) {
		setUsuario(usuario);
		SwingUtilities.invokeLater(() -> {
			VistaGestionEjercicios vistaEjercicios = new VistaGestionEjercicios();;
			vistaEjercicios.setVisible(true);
		});
    }

    public void abrirVistaArticulos(SoporteTecnico usuario) {
		setUsuario(usuario);
		SwingUtilities.invokeLater(() -> {
			VistaGestionArticulosST vistaArticulosST = new VistaGestionArticulosST();;
			vistaArticulosST.setVisible(true);
		});
    }
}

