package ar.edu.uade.administrativo;

import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.articulos.CategoriaArticulo;
import ar.edu.uade.usuarios.Administrativo;

import javax.swing.*;
import java.util.ArrayList;

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

    public void agregarTipoArticulo(String nombre, String categoriaArticulo, String marca, String descripcion,
                                    int usosAmortizacion, boolean flagFecha) {
        try {
            if (flagFecha) {
                gym.agregarTipoArticuloPorFecha(nombre, categoriaArticulo, marca, descripcion, usosAmortizacion);
            } else {
                gym.agregarTipoArticuloPorUso(nombre, categoriaArticulo, marca, descripcion, usosAmortizacion);
            }
        } catch (GymException e) {
            e.printStackTrace();
        }

    }

    public String[] getListaTiposArticulos() {
        return gym.getStringCatalogoDeArticulos().toArray(new String[0]);
    }

    public void agregarCliente(String nombre, String contrasena, String nivel) {
        try {
            gym.agregarCliente(nombre,contrasena,nivel);
        } catch (GymException e) {
            e.printStackTrace();
        }
    }
}
