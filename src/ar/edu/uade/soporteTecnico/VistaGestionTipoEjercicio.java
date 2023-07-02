package ar.edu.uade.soporteTecnico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class VistaGestionTipoEjercicio extends JFrame{
	private ControladorSoporteTecnico controller;

	public VistaGestionTipoEjercicio() {
		super("Soporte Tecnico: Gestion de Ejercicios");
		this.controller = ControladorSoporteTecnico.getInstance();

		this.setLayout(new BorderLayout());
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);


		/* Gestion de Usuarios*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnUsuarios = new JButton("Gestion de Usuarios");
		panelMenu.add(btnUsuarios, gbc);

		/* Gestion de Sedes */
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnSedes = new JButton("Gestion de Sedes");
		panelMenu.add(btnSedes, gbc);

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

		/* Crear Profesor */
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnCrearEjercicio = new JButton("Crear Ejercicio");
		panelMenu.add(btnCrearEjercicio, gbc);

		/* Eliminar Profesor */
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnEliminarEjercicio = new JButton("Eliminar Ejercicio");
		panelMenu.add(btnEliminarEjercicio, gbc);


		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);


		btnUsuarios.addActionListener(actionEvent -> abrirVistaUsuarios());
		btnSedes.addActionListener(actionEvent -> abrirVistaClases());
		btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());

		btnCrearEjercicio.addActionListener(actionEvent -> crearEjercicio());
		btnEliminarEjercicio.addActionListener(actionEvent -> eliminarEjercicio());

		mostrarTablaEjercicio();
	}

	private void abrirVistaArticulos() {
		this.dispose();
		controller.abrirVistaArticulos();
	}

	private void abrirVistaClases() {
		this.dispose();
		controller.abrirVistaClases();
	}

	private void abrirVistaUsuarios() {
		this.dispose();
		controller.abrirVistaUsuarios();
	}

	private void crearEjercicio() {
		JDialog dialogo = new JDialog(this, "Crear Ejercicio", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ArrayList<String> ejercicios = controller.getListaNombresEjercicios();
		ArrayList<String[]> tiposArticulos = controller.getListaTiposArticulos();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		JLabel lblNombre = new JLabel("Nombre:");
		JTextField txtNombre = new JTextField();

		JLabel lblNumVirtuales = new JLabel("Clases Virtuales a Guardar:");
		JTextField txtNumVirtuales = new JFormattedTextField(NumberFormat.getNumberInstance());

		JRadioButton btnPresencial = new JRadioButton("Presencial");
		JRadioButton btnVirtual = new JRadioButton("Virtual");
		ButtonGroup buttonGroup = new ButtonGroup();
		btnPresencial.setSelected(true);
		buttonGroup.add(btnPresencial);
		buttonGroup.add(btnVirtual);

		JLabel lblArticulos = new JLabel("Articulos Necesarios");
		JTextField txtArticulos = new JTextField();

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				String nombre = txtNombre.getText();
				int numVirtuales = Integer.parseInt(txtNumVirtuales.getText());
				String articulos = txtArticulos.getText();
				boolean flagVirtual = btnVirtual.isSelected();
				controller.agregarEjercicio(nombre, numVirtuales, articulos, flagVirtual);

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
		panel.add(lblNumVirtuales);
		panel.add(txtNumVirtuales);
		panel.add(lblArticulos);
		panel.add(txtArticulos);
		panel.add(btnPresencial);
		panel.add(btnVirtual);

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

	private void eliminarEjercicio() {
		JDialog dialogo = new JDialog(this, "Eliminar Ejercicio", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		JLabel lblEjercicio = new JLabel("Nombre de Ejercicio:");
		JTextField txtEjercicio = new JTextField();

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				String nombre = txtEjercicio.getText();
				controller.eliminarEjercicio(nombre);
				dialogo.dispose(); // Cerrar el diálogo
			} catch (Exception ex) {
				lblErrorMessage.setText(ex.getMessage());
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());

		panel.add(lblEjercicio);
		panel.add(txtEjercicio);
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
				String sueldo = (txtSueldo.getText());
				controller.modificarCliente(id,nombre,contrasena,sueldo);
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

	private void mostrarTablaEjercicio() {
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();

		ArrayList<String[]> listaEjercicios = controller.getListaInfoEjercicios();

		String[] columnas = {"ID","Nombre","Es Virtual?","Max clases guardadas", "Articulos necesarios"};
		int cantColumnas = columnas.length;

		modelo.setColumnIdentifiers(columnas);

		for (String[] infoEjercicio : listaEjercicios) {
			String[] fila = new String[cantColumnas+1];
			fila[0] = infoEjercicio[0];
			fila[1] = infoEjercicio[1];
			fila[2] = infoEjercicio[2];
			fila[3] = infoEjercicio[3];
			fila[4] = infoEjercicio[4];
			modelo.addRow(fila);
		}

		tabla.setModel(modelo);

		for (int i = 0; i < cantColumnas; i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
		}

		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
