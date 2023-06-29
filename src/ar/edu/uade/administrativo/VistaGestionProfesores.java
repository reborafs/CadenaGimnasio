package ar.edu.uade.administrativo;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

		class HandlerBtnClientes implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.abrirVistaClientes();
			}
		}

		class HandlerBtnClases implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.abrirVistaClases();
			}
		}

		class HandlerBtnArticulos implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.abrirVistaArticulos();
			}
		}

		/* INSTANCIACION DE LOS MANEJADORES */
		HandlerBtnClientes handlerBtnClientes = new HandlerBtnClientes();
		HandlerBtnClases handlerBtnClases = new HandlerBtnClases();
		HandlerBtnArticulos handlerBtnArticulos = new HandlerBtnArticulos();

		/* ASIGNACION DE LOS MANEJADORES A LOS BOTONES */
		btnCliente.addActionListener(handlerBtnClientes);
		btnClases.addActionListener(handlerBtnClases);
		btnArticulos.addActionListener(handlerBtnArticulos);
	}
	
}
