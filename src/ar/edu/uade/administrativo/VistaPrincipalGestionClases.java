package ar.edu.uade.administrativo;

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

		JLabel lblSede = new JLabel("Ingrese la sede de la clase::");
		JComboBox<String> txtSede = new JComboBox<>();
		for (String sede : controller.getListaSedes()) {
			txtSede.addItem(sede);
		}


		JLabel lblEjercicio = new JLabel("Ingrese el tipo de ejercicio:");
		JComboBox<String> txtEjercicio = new JComboBox<>();
		txtEjercicio.addItem("---");


		JLabel lblProfesor = new JLabel("Ingrese el nombre del profesor:");
		JTextField txtProfesor = new JTextField();

		JLabel lblFecha = new JLabel("Ingrese la fecha (dd-MM-yyyy):");
		JTextField txtFecha = new JTextField();

		JLabel lblEmplazamiento = new JLabel("Ingrese el Emplazamiento:");
		JTextField txtEmplazamiento = new JTextField();

		JLabel lblHorarioInicio = new JLabel("Horario de Inicio:");
		JTextField txtHorarioInicio = new JTextField();

		JLabel lblEsVirtual = new JLabel("Cantidad de Usos hasta renovar:");
		JTextField txtEsVirtual = new JTextField();

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sede = txtSede.getItemAt(txtSede.getSelectedIndex());
					String ejercicio = txtEjercicio.getItemAt(txtSede.getSelectedIndex());
					String profesor = txtProfesor.getText();
					String fecha = txtFecha.getText();
					String emplazamiento = txtEmplazamiento.getText();
					String listaArticulos = null;// txtListaArticulos.getText();
					String horarioInicio = txtHorarioInicio.getText();
					boolean esVirtual = false;
					// Lógica para procesar la información capturada

					controller.agregarClase(sede, profesor, ejercicio, null, fecha, horarioInicio,
							emplazamiento, listaArticulos, esVirtual);

					// Cerrar el diálogo
					lblError.setVisible(false);
					lblErrorMessage.setVisible(false);
					dialogo.dispose();
				} catch (NumberFormatException ex) {
					lblErrorMessage.setText("Se debe ingresar un numero entero.");
					lblError.setVisible(true);
					lblErrorMessage.setVisible(true);
					return; // Exit the method without processing the information
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());

		// Agrego updates a txtSede
		txtSede.addActionListener(e -> {
			String selectedSede = (String) txtSede.getSelectedItem();
			txtEjercicio.removeAllItems();

			if (selectedSede != null && !selectedSede.equals("---")) {
				ArrayList<String> ejercicios = controller.getListaEjercicios(selectedSede);
				for (String ejercicio : ejercicios) {
					System.out.println(ejercicio);
					txtEjercicio.addItem(ejercicio);
				}
			}
			txtEjercicio.addItem("---");
		});


		panel.add(lblSede);
		panel.add(txtSede);
		panel.add(lblEjercicio);
		panel.add(txtEjercicio);
		panel.add(lblProfesor);
		panel.add(txtProfesor);
		panel.add(lblFecha);
		panel.add(txtFecha);
		panel.add(lblEmplazamiento);
		panel.add(txtEmplazamiento);
		panel.add(lblHorarioInicio);
		panel.add(txtHorarioInicio);
		panel.add(lblEsVirtual);
		panel.add(txtEsVirtual);
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



		JOptionPane.showMessageDialog(this, "Clase creada exitosamente");
    }


	private void cambiarEstadoClase() {
    	// Aquí puedes implementar la lógica para cambiar el estado de una clase
        String[] opciones = {"Finalizada", "Agendada", "Confirmada"};
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el nuevo estado de la clase:",
                "Cambiar Estado Clase",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcionSeleccionada != null) {
            // Realizar la acción correspondiente al nuevo estado de la clase
            JOptionPane.showMessageDialog(this, "Estado de la clase cambiado a: " + opcionSeleccionada);
        }
    }

	private void mostrarTablaClases() {
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();

		ArrayList<String[]> listaClasesPorSede = controller.getListaClasesPorSede();

		// Definicion de columnas
		String[] columnas = {"Sede","Profesor","Fecha","Horario","Ejercicio","Estado","Cantidad de Alumnos",
				"Emplazamiento","Cant. Articulos","Es virtual?"};
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
			modelo.addRow(fila);
		}

		tabla.setModel(modelo);

		for (int i = 0; i < cantColumnas; i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
		}

		// Agregar la tabla a un JScrollPane y añadirlo a la ventana
		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);


		/*
		// Tabla de clases asignadas
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();


		// Definicion de columnas
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String[] header = new String[8];
		ArrayList<LocalDate> semana = new ArrayList<LocalDate>();
		header[0] = "Horario";
		for (int day=1; day<=7; day++) {
			LocalDate fecha = LocalDate.now().plusDays(day-1);
			semana.add(fecha);
			header[day] = getDiaSemana(fecha.getDayOfWeek().getValue()) + " " + fecha.format(formatter);
		}

		modelo.setColumnIdentifiers(header);

		// Definición de filas
		LocalTime[] horas = {
				LocalTime.of(7,0),
				LocalTime.of(8,0),
				LocalTime.of(9,0),
				LocalTime.of(10,0),
				LocalTime.of(11,0),
				LocalTime.of(12,0),
				LocalTime.of(13,0),
				LocalTime.of(14,0),
				LocalTime.of(15,0),
				LocalTime.of(16,0),
				LocalTime.of(17,0),
				LocalTime.of(18,0),
				LocalTime.of(19,0),
				LocalTime.of(20,0),
				LocalTime.of(21,0)};

		int cantColumnas = header.length;
		int cantFilas = horas.length;

		HashMap<LocalDate, ArrayList<LocalTime>>
				horariosOcupados = controller.getClasesAsignadas();

		DateTimeFormatter horasFormatter = DateTimeFormatter.ofPattern("HH");

		for (int i = 1; i <= cantFilas-1; i++) {
			String[] horarioDisponible = new String[cantColumnas+1];
			horarioDisponible[0] = horas[i].format(horasFormatter) + "-" + horas[i].plusHours(1).format(horasFormatter);
			for (int j = 1; j <= cantColumnas-1; j++) {
				LocalDate dia = semana.get(j - 1);
				LocalTime horarioClase = horas[i];
				if (horarioClase != null && contieneEjercicio(horariosOcupados, dia, horarioClase)) {
					horarioDisponible[j] = "Ocupado";
				} else {
					horarioDisponible[j] = "Libre";
				}
			}
			modelo.addRow(horarioDisponible);
		}

		tabla.setModel(modelo);

		for (int i = 0; i < 4; i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
		}

		// Agregar la tabla a un JScrollPane y añadirlo a la ventana
		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		 */

	}
/*
	private String getDiaSemana(int dia) {
		return switch (dia) {
			case 1 -> "Lun";
			case 2 -> "Mar";
			case 3 -> "Mie";
			case 4 -> "Jue";
			case 5 -> "Vie";
			case 6 -> "Sab";
			case 7 -> "Dom";
			default -> "xxx";
		};
	}*/

	private boolean contieneEjercicio(HashMap<LocalDate, ArrayList<LocalTime>> horariosOcupados,LocalDate dia, LocalTime hora) {
		boolean flagOcupado = false;
		for (LocalTime horarioOcupado : horariosOcupados.get(dia)) {
			if (horarioOcupado.equals(hora)) {
				flagOcupado = true;
			}
		}
		return flagOcupado;
	}

}
