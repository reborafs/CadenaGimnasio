package ar.edu.uade.administrativo;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaGestionProfesores extends JFrame{
	private ControladorAdministrativo controller;

	public VistaGestionProfesores() {
		super("Administrativo: Gestion de profesores");
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

		/* Crear Profesor */
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnCrearProfesor = new JButton("Crear Profesor");
		panelMenu.add(btnCrearProfesor, gbc);

		/* Eliminar Profesor */
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnEliminarProfesor = new JButton("Eliminar Profesor");
		panelMenu.add(btnEliminarProfesor, gbc);

		/* Modificar Profesor */
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnModificarProfesor = new JButton("Modificar Profesor");
		panelMenu.add(btnModificarProfesor, gbc);


		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);


		btnCliente.addActionListener(actionEvent -> abrirVistaClientes());
		btnClases.addActionListener(actionEvent -> abrirVistaClases());
		btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());

		btnCrearProfesor.addActionListener(actionEvent -> crearProfesor());
		btnEliminarProfesor.addActionListener(actionEvent -> eliminarProfesor());
		btnModificarProfesor.addActionListener(actionEvent -> modificarProfesor());

		mostrarTablaProfesor();
	}

	private void abrirVistaArticulos() {
		this.dispose();
		controller.abrirVistaArticulos();
	}

	private void abrirVistaClases() {
		this.dispose();
		controller.abrirVistaClases();
	}

	private void abrirVistaClientes() {
		this.dispose();
		controller.abrirVistaClientes();
	}

	private void crearProfesor() {
		JDialog dialogo = new JDialog(this, "Crear Profesor", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));

		JLabel lblNombre = new JLabel("Nombre:");
		JTextField txtNombre = new JTextField();

		JLabel lblContrasena = new JLabel("Contraseña:");
		JTextField txtContrasena = new JTextField();

		JLabel lblSueldo = new JLabel("Sueldo:");
		JTextField txtSueldo = new JFormattedTextField(NumberFormat.getNumberInstance());

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				String nombre = txtNombre.getText();
				String contrasena = txtContrasena.getText();
				double sueldo = Double.parseDouble(txtSueldo.getText());
				controller.agregarProfesor(nombre, contrasena, sueldo);

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
		panel.add(lblSueldo);
		panel.add(txtSueldo );

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

	private void eliminarProfesor() {
		JDialog dialogo = new JDialog(this, "Eliminar Profesor", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		JLabel lblID = new JLabel("ID de Profesor a Eliminar:");
		JTextField txtID = new JFormattedTextField(NumberFormat.getIntegerInstance());

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				int id = Integer.parseInt(txtID.getText());
				controller.eliminarProfesor(id);
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

	private void modificarProfesor() {
		JDialog dialogo = new JDialog(this, "Modificar Profesor", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		JLabel lblID = new JLabel("ID de Profesor:");
		JTextField txtID = new JFormattedTextField(NumberFormat.getIntegerInstance());

		JLabel lblNombre = new JLabel("Nombre:");
		JTextField txtNombre = new JTextField();

		JLabel lblContrasena = new JLabel("Contraseña:");
		JTextField txtContrasena = new JTextField();

		JLabel lblSueldo = new JLabel("Sueldo:");
		JTextField txtSueldo = new JFormattedTextField(NumberFormat.getNumberInstance());

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
				Integer sueldo = Integer.valueOf((txtSueldo.getText()));
				controller.modificarProfesor(id,nombre,contrasena,sueldo);
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
		panel.add(lblSueldo);
		panel.add(txtSueldo);
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

	private void mostrarTablaProfesor() {
		// Tabla de clases asignadas
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();

		ArrayList<String[]> listaProfesors = controller.getListaProfesores();

		// Definicion de columnas
		String[] columnas = {"ID","Nombre","Sueldo"};
		int cantColumnas = columnas.length;

		modelo.setColumnIdentifiers(columnas);

		for (String[] infoProfesor : listaProfesors) {
			String[] fila = new String[cantColumnas+1];
			fila[0] = infoProfesor[0];
			fila[1] = infoProfesor[1];
			fila[2] = infoProfesor[2];
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
