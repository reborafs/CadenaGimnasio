package ar.edu.uade.administrativo;

import ar.edu.uade.gym.GymException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        btnCrearCliente.addActionListener(actionEvent -> crearCliente());
        btnEliminarCliente.addActionListener(actionEvent -> eliminarCliente());
        btnModificarCliente.addActionListener(actionEvent -> modificarCliente());

        btnClases.addActionListener(actionEvent -> abrirVistaClases());
        btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());
        btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());

        mostrarTablaClientes();
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
        JComboBox<String> txtTipoUsuario = new JComboBox<>();
        for (String tipoUser : controller.getListaNiveles())
            txtTipoUsuario.addItem(tipoUser);

        JLabel lblError = new JLabel("ERROR");
        JLabel lblErrorMessage = new JLabel("ERROR");
        lblError.setForeground(Color.RED);
        lblErrorMessage.setForeground(Color.RED);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String nivel = txtTipoUsuario.getSelectedItem().toString();
                controller.agregarCliente(nombre, contrasena, nivel);
                lblError.setVisible(false);
                lblErrorMessage.setVisible(false);
                dialogo.dispose();
            } catch (Exception ex) {
                lblErrorMessage.setText(ex.getMessage());
                lblError.setVisible(true);
                lblErrorMessage.setVisible(true);
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(lblNivel);
        panel.add(txtTipoUsuario );

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
        // Implementación de la funcionalidad de creación de cliente
        JDialog dialogo = new JDialog(this, "Eliminar Cliente", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel lblID = new JLabel("ID de Cliente a Eliminar:");
        JTextField txtID = new JFormattedTextField(NumberFormat.getIntegerInstance());

        JLabel lblError = new JLabel("ERROR");
        JLabel lblErrorMessage = new JLabel("ERROR");
        lblError.setForeground(Color.RED);
        lblErrorMessage.setForeground(Color.RED);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                controller.eliminarCliente(id);
                dialogo.dispose();
            } catch (NumberFormatException ex) {
                lblErrorMessage.setText("El numero no es valido.");
                lblError.setVisible(true);
                lblErrorMessage.setVisible(true);
            } catch (GymException ex) {
                lblErrorMessage.setText(ex.getMessage());
                lblError.setVisible(true);
                lblErrorMessage.setVisible(true);
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(lblID);
        panel.add(txtID);
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

    private void modificarCliente() {
        JDialog dialogo = new JDialog(this, "Modificar Cliente", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel lblID = new JLabel("ID de Cliente:");
        JTextField txtID = new JFormattedTextField(NumberFormat.getIntegerInstance());

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JTextField txtContrasena = new JTextField();

        JLabel lblNivel = new JLabel("Nivel:");
        JComboBox<String> txtNivel = new JComboBox<>();
        for (String nivel : controller.getListaNiveles()) {
            txtNivel.addItem(nivel);
        }

        JLabel lblError = new JLabel("ERROR");
        JLabel lblErrorMessage = new JLabel("ERROR");
        lblError.setForeground(Color.RED);
        lblErrorMessage.setForeground(Color.RED);

        JButton btnAceptar = new JButton("Modificar");
        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String nivel  = txtNivel.getItemAt(txtNivel.getSelectedIndex());
                controller.modificarCliente(id,nombre,contrasena,nivel);
                dialogo.dispose(); // Cerrar el diálogo
            } catch (Exception ex) {
                lblErrorMessage.setText(ex.getMessage());
                lblError.setVisible(true);
                lblErrorMessage.setVisible(true);
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());

        panel.add(lblID);
        panel.add(txtID);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(lblNivel);
        panel.add(txtNivel);
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

    private void mostrarTablaClientes() {
        // Tabla de clases asignadas
        JTable tabla = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();

        ArrayList<String[]> listaClientes = controller.getListaClientes();

        // Definicion de columnas
        String[] columnas = {"ID","Nombre","Nivel"};
        int cantColumnas = columnas.length;

        modelo.setColumnIdentifiers(columnas);

        for (String[] infoCliente : listaClientes) {
            String[] fila = new String[cantColumnas+1];
            fila[0] = infoCliente[0];
            fila[1] = infoCliente[1];
            fila[2] = infoCliente[2];
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);

        for (int i = 0; i < cantColumnas; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        // Agregar la tabla a un JScrollPane y añadirlo a la ventana
        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

}
