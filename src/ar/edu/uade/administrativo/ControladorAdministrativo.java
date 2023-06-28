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
    
    public Administrativo getUsuario() {
        return this.usuario;
    }

    public void abrirVistaPrincipal(Administrativo usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaAdministrativo vistaAdmin = new VistaAdministrativo();;
            vistaAdmin.setVisible(true);
        });
    }
    
    public void abrirVistaArticulos(Administrativo usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaGestionArticulos vistaArticulos = new VistaGestionArticulos();;
            vistaArticulos.setVisible(true);
        });
    }
    
    public void abrirVistaClases(Administrativo usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
            VistaGestionClases vistaClases = new VistaGestionClases();;
            vistaClases.setVisible(true);
        });
    }
    
    public void abrirVistaClientes(Administrativo usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
        	VistaGestionClientes vistaClientes = new VistaGestionClientes();;
        	vistaClientes.setVisible(true);
        });
    }
    
    public void abrirVistaProfesores(Administrativo usuario) {
        setUsuario(usuario);
        SwingUtilities.invokeLater(() -> {
        	VistaGestionProfesores vistaProfesores = new VistaGestionProfesores();;
        	vistaProfesores.setVisible(true);
        });
    }
}
