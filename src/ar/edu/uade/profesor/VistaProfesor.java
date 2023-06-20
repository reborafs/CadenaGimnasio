package ar.edu.uade.profesor;
import ar.edu.uade.profesor.ControladorProfesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class VistaProfesor extends JFrame {
	
	public VistaProfesor() {
		super("Profesor");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Tabla de clases asignadas
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();
		
		
		// Definicion de columnas
		ControladorProfesor test = new ControladorProfesor();
		String[] columnas = test.getSedes();
		modelo.setColumnIdentifiers(columnas);

		
		// Definición de filas
		String[] fila = test.getTipoEjercicio();
		int cantColumnas = columnas.length;
		
		
        for (String tipoEjercicio : fila) {
            String[] ejercicioDisponible = new String[cantColumnas+1];
            ejercicioDisponible[0] = tipoEjercicio;
            for (int j = 1; j <= 5; j++) {
            	ejercicioDisponible[j] = String.valueOf(j);
            }
            modelo.addRow(ejercicioDisponible);
        }
		
		tabla.setModel(modelo);
		
        for (int i = 0; i < 4; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        // Agregar la tabla a un JScrollPane y añadirlo a la ventana
        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
	
    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
        	VistaProfesor vistaProfesor = new VistaProfesor();
        	vistaProfesor.setVisible(true);
        });
    }
}
