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

public class VistaGestionEjercicios extends JFrame{
	private ControladorSoporteTecnico controller;
	private SoporteTecnico usuario;

	public VistaGestionEjercicios() {
		super("Soporte Tecnico: Gestion Ejercicios");
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
		
		/* Crear Ejercicio */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnCrearEjercicio = new JButton("Crear Ejercicio");
        panelMenu.add(btnCrearEjercicio, gbc);

        /* Modificar Ejercicio */
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnModificarEjercicio = new JButton("Modificar Ejercicio");
        panelMenu.add(btnModificarEjercicio, gbc);

        /* Eliminar Ejercicio */
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnEliminarEjercicio = new JButton("Eliminar Ejercicio");
        panelMenu.add(btnEliminarEjercicio, gbc);
		
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
    	
    	btnCrearEjercicio.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        mostrarDialogoCrearEjercicio();
    	    }
    	});
	}
	
	private void mostrarDialogoCrearEjercicio() {
	    JDialog dialogo = new JDialog(this, "Crear Ejercicio", true);
	    dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4, 2));

	    JLabel lblTipoEjercicio = new JLabel("Tipo de Ejercicio:");
	    JTextField txtTipoEjercicio = new JTextField();

	    JLabel lblRequerimientos = new JLabel("Requerimientos de Emplazamiento:");
	    JTextField txtRequerimientos = new JTextField();

	    JLabel lblTiposArticulos = new JLabel("Tipos de Artículos:");
	    JTextField txtTiposArticulos = new JTextField();

	    JButton btnAceptar = new JButton("Aceptar");
	    btnAceptar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String tipoEjercicio = txtTipoEjercicio.getText();
	            String requerimientos = txtRequerimientos.getText();
	            String tiposArticulos = txtTiposArticulos.getText();
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

	    panel.add(lblTipoEjercicio);
	    panel.add(txtTipoEjercicio);
	    panel.add(lblRequerimientos);
	    panel.add(txtRequerimientos);
	    panel.add(lblTiposArticulos);
	    panel.add(txtTiposArticulos);
	    panel.add(btnAceptar);
	    panel.add(btnCancelar);

	    dialogo.add(panel);
	    dialogo.pack();
	    dialogo.setLocationRelativeTo(this);
	    dialogo.setVisible(true);
	}
	
	private void crearEjercicio() {
        // Implementación de la funcionalidad de creación de ejercicio
        // ...
    }

    private void modificarEjercicio() {
        // Implementación de la funcionalidad de modificación de ejercicio
        // ...
    }

    private void eliminarEjercicio() {
        // Implementación de la funcionalidad de eliminación de ejercicio
        // ...
    }
	
    public static void main(String[] args) {
    	VistaSoporteTecnico VistaSoporteTecnico = new VistaSoporteTecnico();
    }
}
