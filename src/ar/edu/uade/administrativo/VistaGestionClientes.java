package ar.edu.uade.administrativo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

public class VistaGestionClientes extends JFrame {
    private ControladorAdministrativo controller;

    public VistaGestionClientes() {
		super("Administrativo: Gestion de clientes");
        this.controller = ControladorAdministrativo.getInstance();
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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        btnCrearCliente.addActionListener(actionEvent -> crearCliente());
        btnEliminarCliente.addActionListener(actionEvent -> eliminarCliente());
        btnModificarCliente.addActionListener(actionEvent -> modificarCliente());

        btnClases.addActionListener(actionEvent -> abrirVistaClases());
        btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());
        btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());

    }

    private void abrirVistaProfesores() {
        this.dispose();
        controller.abrirVistaProfesores();
    }

    private void abrirVistaArticulos() {
        this.dispose();
        controller.abrirVistaArticulos();
    }

    private void abrirVistaClases() {
        this.dispose();
        controller.abrirVistaClases();
    }

    private void crearCliente() {
        // Implementación de la funcionalidad de creación de cliente
        JDialog dialogo = new JDialog(this, "Crear Cliente", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JTextField txtContrasena = new JTextField();

        JLabel lblNivel = new JLabel("Nivel:");
        JTextField txtNivel = new JTextField();

        JLabel lblError = new JLabel("ERROR");
        JLabel lblErrorMessage = new JLabel("ERROR");
        lblError.setForeground(Color.RED);
        lblErrorMessage.setForeground(Color.RED);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombre.getText();
                    String contrasena = txtContrasena.getText();
                    String nivel = txtNivel.getText();

                    controller.agregarCliente(nombre, contrasena, nivel);

                    // Cerrar el diálogo
                    lblError.setVisible(false);
                    lblErrorMessage.setVisible(false);
                    dialogo.dispose();
                } catch (Exception ex) {
                    lblErrorMessage.setText("Error.");
                    lblError.setVisible(true);
                    lblErrorMessage.setVisible(true);
                    return; // Exit the method without processing the information
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el diálogo sin procesar la información
                dialogo.dispose();
            }
        });


        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(lblNivel);
        panel.add(txtNivel );

        panel.add(lblError);
        panel.add(lblErrorMessage);
        panel.add(btnAceptar);
        panel.add(btnCancelar);
        lblError.setVisible(false);
        lblErrorMessage.setVisible(false);

        dialogo.add(panel);
        dialogo.pack();
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void eliminarCliente() {
        // Implementación de la funcionalidad de eliminación de cliente
        // ...
    }

    private void modificarCliente() {
        // Implementación de la funcionalidad de modificación de cliente
        // ...
    }
	
}
