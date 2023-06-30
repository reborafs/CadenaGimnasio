package ar.edu.uade.profesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class VistaClaseAsignada extends JFrame {

	private ControladorProfesor controller;

	public VistaClaseAsignada() {
		super("Profesor: Clases Asignadas");
		this.controller = ControladorProfesor.getInstance();

        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
        /* Clases Asignadas */
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnClaseAsignada = new JButton("Clases Asignadas");
		panelMenu.add(btnClaseAsignada, gbc);
		
		/* Sueldo */
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnSueldo = new JButton("Sueldo");
		panelMenu.add(btnSueldo, gbc);
		
		//Titulo
        JPanel panel = new JPanel();
		JLabel labelTitulo = new JLabel("Clases Asignadas");
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		panelMenu.add(labelTitulo, gbc);
		
		gbc.gridwidth = 1;

		this.add(panelMenu, BorderLayout.NORTH);

		mostrarTablaClasesAsignadas();

		/*ASIGNACION DEL MANEJADOR AL BOTON*/
		btnSueldo.addActionListener( e -> abrirVistaSueldo());

		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
    private boolean contieneEjercicio(HashMap<LocalDate, ArrayList<LocalTime>> horariosOcupados,LocalDate dia, LocalTime hora) {
		boolean flagOcupado = false;
		for (LocalTime horarioOcupado : horariosOcupados.get(dia)) {
            if (horarioOcupado.equals(hora)) {
                flagOcupado = true;
            }
        }
        return flagOcupado;
    }

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
	}

	private void mostrarTablaClasesAsignadas() {
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
				horariosOcupados = controller.getClasesAsignadas("belgrano");

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

	}

	private void abrirVistaSueldo() {
		this.dispose();
		controller.abrirVistaSueldo();
	}
}
