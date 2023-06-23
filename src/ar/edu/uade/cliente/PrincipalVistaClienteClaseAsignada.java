package ar.edu.uade.cliente;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PrincipalVistaClienteClaseAsignada extends JFrame {
	public PrincipalVistaClienteClaseAsignada() {
		super("Cliente: Clases Asignadas");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		/* Ejercicios Disponibles por Sedes*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		JButton btnClasesSedes = new JButton("Clases Asignadas");
		panelMenu.add(btnClasesSedes, gbc);
		
		/* Ejercicios Disponibles por Sedes*/
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnEjerciciosSedes = new JButton("Ejercicios Disponibles por Sedes");
		panelMenu.add(btnEjerciciosSedes, gbc);
		
		/* Membresia */
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnMembresia = new JButton("Membresia");
		panelMenu.add(btnMembresia, gbc);
		
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
		
		// Tabla de clases asignadas
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();
		
		
		
		// Definicion de columnas
		//ControladorProfesor test = new ControladorProfesor();
		String[] columnas = {"Horario", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
		modelo.setColumnIdentifiers(columnas);

		// Definición de filas
		String[] fila = {"7:00 - 8:00", "8:00 - 9:00", "9:00 - 10:00", "11:00 - 12:00", "13:00 - 14:00", "15:00 - 16:00", "18:00 - 19:00", "20:00 - 21:00"};
		int cantColumnas = columnas.length;
		
		String[] horario1 = {"8:00 - 9:00"};
		String[] horario2 = {"11:00 - 12:00"};
		String[] horario3 = {"15:00 - 16:00"};
		String[] horario4 = {"7:00 - 8:00"};
		String[] horario5 = {};
		
		HashMap<String, String[]> calendario = new HashMap<String, String[]>();
		calendario.put("Lunes", horario1);
		calendario.put("Martes", horario2);
		calendario.put("Miercoles", horario3);
		calendario.put("Viernes", horario4);
		calendario.put("Sabado", horario5);
		
        for (String horario : fila) {
            String[] horarioDisponible = new String[cantColumnas+1];
            horarioDisponible[0] = horario;
            for (int j = 1; j <= cantColumnas-1; j++) {
            	String dia = columnas[j];
                String[] horarioClase = calendario.get(dia);
                if (horarioClase != null && contieneEjercicio(horarioClase, horario)) {
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
	
    private boolean contieneEjercicio(String[] ejercicios, String ejercicio) {
        for (String e : ejercicios) {
            if (e.equals(ejercicio)) {
                return true;
            }
        }
        return false;
    }
	
    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
        	PrincipalVistaClienteClaseAsignada vistaClienteClaseAsignada = new PrincipalVistaClienteClaseAsignada();
        	vistaClienteClaseAsignada.setVisible(true);
        });
    }
}
