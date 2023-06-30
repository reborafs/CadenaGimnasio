package ar.edu.uade.soporteTecnico;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ar.edu.uade.usuarios.SoporteTecnico;

public class VistaGestionSedes extends JFrame{
	private ControladorSoporteTecnico controller;
	private SoporteTecnico usuario;

	public VistaGestionSedes() {
		super("Soporte Tecnico: Gestion Sedes");
		this.setLayout(new BorderLayout());
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		/* Gestion de Sedes*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnSedes = new JButton("Gestion de Sedes");
		panelMenu.add(btnSedes, gbc);
		
		this.add(panelMenu, BorderLayout.NORTH);
		
		/* Gestion de Usuarios */
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnUsuarios = new JButton("Gestion de Usuarios");
		panelMenu.add(btnUsuarios, gbc);
		
		/* Gestion de Ejercicios */
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnEjercicios = new JButton("Gestion de Ejercicios");
		panelMenu.add(btnEjercicios, gbc);
		
		/* Gestion de Articulos */
		gbc.gridx = 7;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnArticulos = new JButton("Gestion de Articulos");
		panelMenu.add(btnArticulos, gbc);
		
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        class HandlerBtnSedes implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		controller.abrirVistaSedes(controller.getUsuario());
        	}
        }

        class HandlerBtnUsuarios implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		controller.abrirVistaUsuarios(controller.getUsuario());
        	}
        }

        class HandlerBtnEjercicios implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		controller.abrirVistaEjercicios(controller.getUsuario());
        	}
        }

    	class HandlerBtnArticulos implements ActionListener {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			controller.abrirVistaArticulos(controller.getUsuario());
    		}
    	}

    	/* INSTANCIACION DE LOS MANEJADORES */
    	HandlerBtnSedes handlerBtnSedes = new HandlerBtnSedes();
    	HandlerBtnUsuarios handlerBtnUsuarios = new HandlerBtnUsuarios();
    	HandlerBtnEjercicios handlerBtnEjercicios = new HandlerBtnEjercicios();
    	HandlerBtnArticulos handlerBtnArticulos = new HandlerBtnArticulos();

    	/* ASIGNACION DE LOS MANEJADORES A LOS BOTONES */
    	btnSedes.addActionListener(handlerBtnSedes);
    	btnUsuarios.addActionListener(handlerBtnUsuarios);
    	btnEjercicios.addActionListener(handlerBtnEjercicios);
    	btnArticulos.addActionListener(handlerBtnArticulos);
	}
	
    public static void main(String[] args) {
    	VistaSoporteTecnico VistaSoporteTecnico = new VistaSoporteTecnico();
    }
}
