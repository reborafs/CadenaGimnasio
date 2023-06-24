package ar.edu.uade.profesor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VistaProfesorSueldo extends JFrame {
	
	public VistaProfesorSueldo() {
		super("Profesor: Sueldo");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
        /* Clases Asignadas */
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnEjerciciosSedes = new JButton("Clases Asignadas");
		panelMenu.add(btnEjerciciosSedes, gbc);
		
		/* Sueldo */
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnMembresia = new JButton("Sueldo");
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
		
		int valor = 200000;
		
		JLabel labelMembresia1 = new JLabel("Su sueldo es de: " + valor);
        labelMembresia1.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelMembresia1, gbc);

	}
	
    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
        	VistaProfesorSueldo vistaProfesorSueldo = new VistaProfesorSueldo();
        	vistaProfesorSueldo.setVisible(true);
        });
    }
}
