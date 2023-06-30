package ar.edu.uade.soporteTecnico;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ar.edu.uade.usuarios.SoporteTecnico;

public class VistaGestionUsuarios extends JFrame{
	private ControladorSoporteTecnico controller;
	private SoporteTecnico usuario;

	public VistaGestionUsuarios() {
		super("Soporte Tecnico: Gestion Usuarios");
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
        
        /* Crear Usuario */
        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        panelMenu.add(btnCrearUsuario, gbc);
        
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
    	
    	btnCrearUsuario.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        mostrarDialogoCrearUsuario();
    	    }
    	});
	}
	
	private void mostrarDialogoCrearUsuario() {
	    JDialog dialogo = new JDialog(this, "Crear Usuario", true);
	    dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(3, 2));

	    JLabel lblUsername = new JLabel("Username:");
	    JTextField txtUsername = new JTextField();

	    JLabel lblRol = new JLabel("Rol:");
	    JTextField txtRol = new JTextField();

	    JButton btnAceptar = new JButton("Aceptar");
	    btnAceptar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String username = txtUsername.getText();
	            String rol = txtRol.getText();
	            // Lógica para procesar la información capturada

	            // Cerrar el diálogo
	            dialogo.dispose();
	        }
	    });

	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Cerrar el diálogo sin procesar la información
	            dialogo.dispose();
	        }
	    });

	    panel.add(lblUsername);
	    panel.add(txtUsername);
	    panel.add(lblRol);
	    panel.add(txtRol);
	    panel.add(btnAceptar);
	    panel.add(btnCancelar);

	    dialogo.add(panel);
	    dialogo.pack();
	    dialogo.setLocationRelativeTo(this);
	    dialogo.setVisible(true);
	}
	
    public static void main(String[] args) {
    	VistaSoporteTecnico VistaSoporteTecnico = new VistaSoporteTecnico();
    }
}
