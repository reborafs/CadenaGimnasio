package ar.edu.uade.administrativo;

import ar.edu.uade.gym.Emplazamiento;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.profesor.ControladorProfesor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaPrincipalGestionClases extends JFrame{
	private ControladorAdministrativo controller;

	public VistaPrincipalGestionClases() {
		super("Administrativo: Gestion de clases");
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
		
		/* Agendar Clases */
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnAgendarClases = new JButton("Agendar Clases");
        panelMenu.add(btnAgendarClases, gbc);

        /* Cambiar Estado Clase */
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnCambiarEstadoClase = new JButton("Cambiar Estado Clase");
        panelMenu.add(btnCambiarEstadoClase, gbc);

		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

		btnCliente.addActionListener(actionEvent -> abrirVistaClientes());
		btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());
		btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());
		btnAgendarClases.addActionListener(actionEvent -> abrirVistaAgendarClases());
		btnCambiarEstadoClase.addActionListener(actionEvent -> cambiarEstadoClase());

		mostrarTablaClases();
	}

	private void abrirVistaArticulos() {
		this.dispose();
		controller.abrirVistaArticulos();
	}

	private void abrirVistaProfesores() {
		this.dispose();
		controller.abrirVistaProfesores();
	}

	private void abrirVistaClientes() {
		this.dispose();
		controller.abrirVistaClientes();
	}

	private void abrirVistaAgendarClases() {

		JDialog dialogo = new JDialog(this, "Agendar Clases", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 2));

		JLabel lblSede = new JLabel("Ingrese la sede de la clase:");
		JComboBox<String> txtSede = new JComboBox<>();
		for (String sede : controller.getListaSedes()) {
			txtSede.addItem(sede);
		}

		JLabel lblEjercicio = new JLabel("Ingrese el tipo de ejercicio:");
		JComboBox<String> txtEjercicio = new JComboBox<>();
		ArrayList<String> listaEjercicios = controller.getListaEjercicios(txtSede.getSelectedItem().toString());
		for (String ejercicio : listaEjercicios) {
			if (!estaEnLista(txtEjercicio, ejercicio)){
				txtEjercicio.addItem(ejercicio);
			}
		}

		JLabel lblIdProfesor = new JLabel("Ingrese el id del profesor:");
		JTextField txtIdProfesor = new JTextField();

		JLabel lblFecha = new JLabel("Ingrese la fecha (dd-MM-yyyy):");
		JTextField txtFecha = new JTextField();

		JLabel lblEmplazamiento = new JLabel("Ingrese el Emplazamiento:");
		JComboBox<String> txtEmplazamiento = new JComboBox<>();
		ArrayList<Emplazamiento> listaEmplazamiento = controller.getEmplazamiento(txtSede.getSelectedItem().toString());
		for (Emplazamiento tipoEmplazamiento : listaEmplazamiento)
			if (!estaEnLista(txtEmplazamiento, tipoEmplazamiento.toString())){
				txtEmplazamiento.addItem(tipoEmplazamiento.toString());
			}

		JLabel lblHorarioInicio = new JLabel("Horario de Inicio (HH):");
		JTextField txtHorarioInicio = new JFormattedTextField(NumberFormat.getNumberInstance());

		JRadioButton btnPresencial = new JRadioButton("Presencial");
		JRadioButton btnVirtual = new JRadioButton("Virtual");
		ButtonGroup buttonGroup = new ButtonGroup();
		btnPresencial.setSelected(true);
		buttonGroup.add(btnPresencial);
		buttonGroup.add(btnVirtual);

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);


		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				String sede = txtSede.getItemAt(txtSede.getSelectedIndex());
				String ejercicio = txtEjercicio.getItemAt(txtEjercicio.getSelectedIndex());
				String idProfesor = txtIdProfesor.getText();
				String fecha = txtFecha.getText();
				String emplazamiento = txtEmplazamiento.getItemAt(txtEmplazamiento.getSelectedIndex());
				ArrayList<Articulo> listaArticulos = null;// txtListaArticulos.getText();
				String horarioInicio = txtHorarioInicio.getText();
				boolean flagVirtual = btnVirtual.isSelected();

				if (Integer.parseInt(horarioInicio) < 8 || Integer.parseInt(horarioInicio) > 23) {
					throw new GymException("El horario debe ser un número entre 8 y 23.");
				}

				asignarArticulos(sede, idProfesor, ejercicio, fecha, horarioInicio,
						emplazamiento, flagVirtual);

				// Cerrar el diálogo
				lblError.setVisible(false);
				lblErrorMessage.setVisible(false);
				JOptionPane.showMessageDialog(panel, "Clase creada exitosamente");
				dialogo.dispose();
			} catch (NumberFormatException ex) {
				lblErrorMessage.setText("Se debe ingresar un numero entero.");
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

		// Agrego updates a txtSede
		txtSede.addActionListener(e -> {
			String selectedSede = txtSede.getItemAt(txtSede.getSelectedIndex());
			txtEjercicio.removeAllItems();
			txtEmplazamiento.removeAllItems();

			if (selectedSede != null && !selectedSede.equals("---")) {
				ArrayList<String> ejercicios = controller.getListaEjercicios(selectedSede);
				ArrayList<Emplazamiento> emplazamientos = controller.getEmplazamiento(selectedSede);

				for (String ejercicio : ejercicios) {
					txtEjercicio.addItem(ejercicio);
				}

				for (Emplazamiento tipoEmplazamiento : emplazamientos) {
					txtEmplazamiento.addItem(tipoEmplazamiento.toString());
				}
			}
		});


		panel.add(lblSede);
		panel.add(txtSede);
		panel.add(lblEjercicio);
		panel.add(txtEjercicio);
		panel.add(lblIdProfesor);
		panel.add(txtIdProfesor);
		panel.add(lblFecha);
		panel.add(txtFecha);
		panel.add(lblEmplazamiento);
		panel.add(txtEmplazamiento);
		panel.add(lblHorarioInicio);
		panel.add(txtHorarioInicio);
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

	private void asignarArticulos(String sede, String idProfesor, String ejercicio, String fecha, String horarioInicio,
								  String emplazamiento, boolean flagVirtual) {

		JDialog dialogo = new JDialog(this, "Asignar Articulos", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		ArrayList<String[]> listaArticulos= controller.getListaArticulos().get(sede);
		panel.setLayout(new GridLayout(2+listaArticulos.size(), 2));

		JLabel lblArtTitulo = new JLabel("Articulos en Stock:");
		JLabel lblEspacio = new JLabel(" ");
		panel.add(lblArtTitulo);
		panel.add(lblEspacio);

		ArrayList<JCheckBox> checkBoxesArticulos = new ArrayList<>();

		for (String[] articulo : listaArticulos){
			JCheckBox checkBox = new JCheckBox(articulo[0] + ":" + articulo[1]);
			panel.add(checkBox);
			checkBoxesArticulos.add(checkBox);
		}

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				ArrayList<String> articulosSeleccionados = new ArrayList<>();
				for (JCheckBox check : checkBoxesArticulos)
					if (check.isSelected())
						articulosSeleccionados.add(check.getText());


				controller.agregarClase(sede, idProfesor, ejercicio, null, fecha, horarioInicio,
						emplazamiento, articulosSeleccionados, flagVirtual);
				lblError.setVisible(false);
				lblErrorMessage.setVisible(false);
				dialogo.dispose();
				JOptionPane.showMessageDialog(panel, "Clase creada exitosamente");
			} catch ( Exception ex ) {
				lblErrorMessage.setText(ex.getMessage());
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());

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


	private void cambiarEstadoClase() {
		JTextField txtClaseId = new JTextField();

		String[] opciones = {"Finalizada", "Agendada", "Confirmada"};
		JComboBox<String> txtEstado = new JComboBox<>(opciones);

		JPanel panel = new JPanel(new GridLayout(2, 2));
		panel.add(new JLabel("Seleccione el ID de la clase:"));
		panel.add(txtClaseId);
		panel.add(new JLabel("Seleccione el nuevo estado:"));
		panel.add(txtEstado);

		int result = JOptionPane.showConfirmDialog(null, panel, "Cambiar Estado Clase",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		try {
			if (result == JOptionPane.OK_OPTION) {
				String claseId = txtClaseId.getText();
				String estado = txtEstado.getItemAt(txtEstado.getSelectedIndex());
				ArrayList<String[]> listaClasesPorSede = controller.getListaClasesPorSede();
				boolean flagFound = false;
				for (String[] clase : listaClasesPorSede)
					if (clase[1].equals(claseId)){
						flagFound = true;
						controller.cambiarEstadoClase(clase[0],claseId,estado);
					}

				if (!flagFound) {throw new GymException("No existe la clase de ID " + claseId + ".");}
				JOptionPane.showMessageDialog(null, "Estado de la clase cambiado a: " + estado);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}

    }

	private void mostrarTablaClases() {
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();

		ArrayList<String[]> listaClasesPorSede = controller.getListaClasesPorSede();

		// Definicion de columnas
		String[] columnas = {"Sede", "ID Clase", "ID Profesor", "Profesor","Fecha","Horario","Ejercicio","Estado",
				"Cantidad de Alumnos","Emplazamiento","Cant. Articulos","Es virtual?"};
		int cantColumnas = columnas.length;

		modelo.setColumnIdentifiers(columnas);
		for (String[] infoClasePorSede : listaClasesPorSede) {
			String[] fila = new String[cantColumnas+1];
			fila[0] = infoClasePorSede[0];
			fila[1] = infoClasePorSede[1];
			fila[2] = infoClasePorSede[2];
			fila[3] = infoClasePorSede[3];
			fila[4] = infoClasePorSede[4];
			fila[5] = infoClasePorSede[5];
			fila[6] = infoClasePorSede[6];
			fila[7] = infoClasePorSede[7];
			fila[8] = infoClasePorSede[8];
			fila[9] = infoClasePorSede[9];
			fila[10] = infoClasePorSede[10];
			fila[11] = infoClasePorSede[11];
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


	private boolean estaEnLista(JComboBox<String>  lista, String valor){
		for (int i = 0; i < lista.getItemCount(); i++) {
			String elemento = lista.getItemAt(i);
			if (valor.equals(elemento)) {
				return true;
			}
		}
		return false;
	}



}
