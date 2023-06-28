package ar.edu.uade.administrativo;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaGestionClientes extends JFrame{
	public VistaGestionClientes() {
		super("Administrativo: Gestion de clientes");
		this.setLayout(new BorderLayout());
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		/* Gestion de Cliente*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnCliente = new JButton("Gestion de Clientes");
		panelMenu.add(btnCliente, gbc);
		
		this.add(panelMenu, BorderLayout.NORTH);
		
		/* Gestion de Clases */
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnClases = new JButton("Gestion de Clases");
		panelMenu.add(btnClases, gbc);
		
		/* Gestion de Profesor */
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnProfesor = new JButton("Gestion de Profesor");
		panelMenu.add(btnProfesor, gbc);
		
		/* Gestion de Articulos */
		gbc.gridx = 7;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnArticulos = new JButton("Gestion de Articulos");
		panelMenu.add(btnArticulos, gbc);
		
		/* Crear Cliente */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnCrearCliente = new JButton("Crear Cliente");
        panelMenu.add(btnCrearCliente, gbc);

        /* Eliminar Cliente */
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        panelMenu.add(btnEliminarCliente, gbc);

        /* Modificar Cliente */
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        panelMenu.add(btnModificarCliente, gbc);

        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        btnCrearCliente.addActionListener(e -> {
            crearCliente();
        });

        btnEliminarCliente.addActionListener(e -> {
            eliminarCliente();
        });

        btnModificarCliente.addActionListener(e -> {
            modificarCliente();
        });
    }

    private void crearCliente() {
        // Implementación de la funcionalidad de creación de cliente
        // ...
    }

    private void eliminarCliente() {
        // Implementación de la funcionalidad de eliminación de cliente
        // ...
    }

    private void modificarCliente() {
        // Implementación de la funcionalidad de modificación de cliente
        // ...
    }
	
    public static void main(String[] args) {
    	VistaAdministrativo vistaAdministrativo = new VistaAdministrativo();
    }
}
