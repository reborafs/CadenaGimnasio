package ar.edu.uade.profesor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaSueldo extends JFrame {

	private ControladorProfesor controller;
	
	public VistaSueldo() {
		super("Profesor: Sueldo");
		this.controller = ControladorProfesor.getInstance();

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
		JLabel labelTitulo = new JLabel("Sueldo Mensual");
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		panelMenu.add(labelTitulo, gbc);
		
		gbc.gridwidth = 1;

		this.add(panelMenu, BorderLayout.NORTH);
		
		JLabel labelSueldo = new JLabel("Su sueldo es de: $" + controller.getSueldo());
		labelSueldo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelSueldo, gbc);

		/*ASIGNACION DEL MANEJADOR AL BOTON*/
		btnClaseAsignada.addActionListener(e ->	abrirVistaClaseAsignada());

		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
/*
    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
        	VistaProfesorSueldo vistaProfesorSueldo = new VistaProfesorSueldo();
        	vistaProfesorSueldo.setVisible(true);
        });
    }
*/

	private void abrirVistaClaseAsignada() {
		this.dispose();
		controller.abrirVistaClaseAsignada();
	}

}
