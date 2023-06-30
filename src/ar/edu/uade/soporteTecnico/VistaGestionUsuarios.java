package ar.edu.uade.soporteTecnico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class VistaGestionUsuarios extends JFrame {
    private ControladorSoporteTecnico controller;

    public VistaGestionUsuarios() {
		super("Administrativo: Gestion de Usuarios");
        this.controller = ControladorSoporteTecnico.getInstance();
        this.setLayout(new BorderLayout());
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

        /* Gestion de Sedes */
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnSedes = new JButton("Gestion de Sedes");
        panelMenu.add(btnSedes, gbc);

        /* Gestion de Usuarios*/
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnUsuarios = new JButton("Gestion de Usuarios");
        panelMenu.add(btnUsuarios, gbc);

        this.add(panelMenu, BorderLayout.NORTH);

        /* Gestion de Profesor */
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnProfesor = new JButton("Gestion de Ejercicios");
        panelMenu.add(btnProfesor, gbc);

        /* Gestion de Articulos */
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnArticulos = new JButton("Gestion de Articulos");
        panelMenu.add(btnArticulos, gbc);

		/* Crear Usuario */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        panelMenu.add(btnCrearUsuario, gbc);

        /* Eliminar Usuario */
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        panelMenu.add(btnEliminarUsuario, gbc);

        /* Modificar Usuario */
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnModificarUsuario = new JButton("Asignar Sede a Admin");
        panelMenu.add(btnModificarUsuario, gbc);

        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        btnCrearUsuario.addActionListener(actionEvent -> crearUsuario());
        btnEliminarUsuario.addActionListener(actionEvent -> eliminarUsuario());
        btnModificarUsuario.addActionListener(actionEvent -> modificarUsuario());

        btnSedes.addActionListener(actionEvent -> abrirVistaClases());
        btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());
        btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());

        mostrarTablaUsuarios();
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

    private void crearUsuario() {
        // Implementación de la funcionalidad de creación de cliente
        JDialog dialogo = new JDialog(this, "Crear Usuario", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel lblTipoUsuario = new JLabel("Elegir tipo Usuario:");
        JComboBox<String> txtTipoUsuario = new JComboBox<>();
        for (String tipoUser : controller.getListaTiposUsuarios())
            txtTipoUsuario.addItem(tipoUser);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            try {
                String tipoUsuario = txtTipoUsuario.getItemAt(txtTipoUsuario.getSelectedIndex());
                dialogo.dispose();
                switch (tipoUsuario) {
                    case "Administrativo" -> abrirCrearAdministrativo();
                    case "Cliente" -> abrirCrearCliente();
                    case "Profesor" -> abrirCrearProfesor();
                    case "Soporte Tecnico" -> abrirCrearSoporteTecnico();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


        panel.add(lblTipoUsuario);
        panel.add(txtTipoUsuario);
        panel.add(btnAceptar);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.pack();
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void abrirCrearSoporteTecnico() {
        // Implementación de la funcionalidad de creación de cliente
        JDialog dialogo = new JDialog(this, "Crear Soporte Tecnico", true);
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
        btnAceptar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String nivel = txtNivel.getText();
                controller.agregarUsuario(nombre, contrasena, nivel);

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
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


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

    private void abrirCrearProfesor() {
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
        btnAceptar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String nivel = txtNivel.getText();
                controller.agregarUsuario(nombre, contrasena, nivel);

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
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


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

    private void abrirCrearAdministrativo() {
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
        btnAceptar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String nivel = txtNivel.getText();
                controller.agregarUsuario(nombre, contrasena, nivel);

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
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


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

    private void abrirCrearCliente() {
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
        btnAceptar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String nivel = txtNivel.getText();
                controller.agregarUsuario(nombre, contrasena, nivel);

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
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


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


    private void eliminarUsuario() {
        // Implementación de la funcionalidad de creación de cliente
        JDialog dialogo = new JDialog(this, "Eliminar Usuario", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel lblID = new JLabel("ID de Usuario a Eliminar:");
        JTextField txtID = new JFormattedTextField(NumberFormat.getIntegerInstance());

        JLabel lblError = new JLabel("ERROR");
        JLabel lblErrorMessage = new JLabel("ERROR");
        lblError.setForeground(Color.RED);
        lblErrorMessage.setForeground(Color.RED);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                controller.eliminarUsuario(id);
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

    private void modificarUsuario() {
        JDialog dialogo = new JDialog(this, "Modificar Usuario", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel lblID = new JLabel("ID de Usuario:");
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
                controller.modificarUsuario(id,nombre,contrasena,nivel);
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

    private void mostrarTablaUsuarios() {
        // Tabla de clases asignadas
        JTable tabla = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();

        ArrayList<String[]> listaUsuarios = controller.getListaUsuarios();

        // Definicion de columnas
        String[] columnas = {"ID","Tipo de Usuario","Nombre"};
        int cantColumnas = columnas.length;

        modelo.setColumnIdentifiers(columnas);

        for (String[] infoUsuario : listaUsuarios) {
            String[] fila = new String[cantColumnas+1];
            fila[0] = infoUsuario[0];
            fila[1] = infoUsuario[1];
            fila[2] = infoUsuario[2];
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
