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

public class VistaClienteClaseSede extends JFrame {
	public VistaClienteClaseSede() {
		super("Cliente: Ejercicios por sede");
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
		JLabel labelTitulo = new JLabel("Ejercicios por Sede");
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
		String[] columnas = {"", "Caballito", "Palermo", "Almagro", "Recoleta", "Tigre"};
		columnas[0] = "Tipo de Ejercicio";
		modelo.setColumnIdentifiers(columnas);

		// Definición de filas
		String[] fila = {"Crossfit", "Yoga", "Boxing", "Bailoterapia"};
		int cantColumnas = columnas.length;
		
		String[] sede1 = {"Crossfit", "Boxing", "Bailoterapia"};
		String[] sede2 = {"Yoga", "Boxing"};
		String[] sede3 = {"Yoga"};
		String[] sede4 = {"Boxing", "Bailoterapia"};
		String[] sede5 = {};
		
		HashMap<String, String[]> sedeEjercicio = new HashMap<String, String[]>();
		sedeEjercicio.put("Caballito", sede1);
		sedeEjercicio.put("Palermo", sede2);
		sedeEjercicio.put("Almagro", sede3);
		sedeEjercicio.put("Recoleta", sede4);
		sedeEjercicio.put("Tigre", sede5);
		
        for (String ejercicio : fila) {
            String[] ejercicioDisponible = new String[cantColumnas+1];
            ejercicioDisponible[0] = ejercicio;
            for (int j = 1; j <= 5; j++) {
            	String sede = columnas[j];
                String[] ejerciciosSede = sedeEjercicio.get(sede);
                if (ejerciciosSede != null && contieneEjercicio(ejerciciosSede, ejercicio)) {
                	ejercicioDisponible[j] = "Disponible";
                } else {
                	ejercicioDisponible[j] = "No Disponible";
                }
            }
            modelo.addRow(ejercicioDisponible);
        }
		
		tabla.setModel(modelo);
		
        for (int i = 0; i < cantColumnas; i++) {
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
	
//    public static void main(String[] args) {
//        // Crear y mostrar la ventana
//        SwingUtilities.invokeLater(() -> {
//        	VistaClienteClaseSede vistaClienteClaseSede = new VistaClienteClaseSede();
//        	vistaClienteClaseSede.setVisible(true);
//        });
//    }
}
