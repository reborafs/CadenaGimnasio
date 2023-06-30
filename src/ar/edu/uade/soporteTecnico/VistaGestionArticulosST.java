package ar.edu.uade.soporteTecnico;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.usuarios.SoporteTecnico;

public class VistaGestionArticulosST extends JFrame{
	private ControladorSoporteTecnico controller;
	private SoporteTecnico usuario;

	public VistaGestionArticulosST() {
		super("Soporte Tecnico: Gestion Articulos");
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
		
		/* Incorporar Articulos */
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnIncorporar = new JButton("Incorporar Articulos");
		panelMenu.add(btnIncorporar, gbc);

		/* Monitorear Articulos */
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnMonitorear = new JButton("Monitorear Articulos");
		panelMenu.add(btnMonitorear, gbc);
		
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
    	
    	// Acción del botón Incorporar Articulos
        btnIncorporar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoIncorporarArticulos();
            }
        });

		// Acción del botón Monitorear Articulos
        btnMonitorear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarListaArticulos();
            }
        });
	}
	
	private void mostrarDialogoIncorporarArticulos() {
        JDialog dialogo = new JDialog(this, "Incorporar Articulos", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel lblTipoArticulo = new JLabel("Tipo de Articulo:");
        JTextField txtTipoArticulo = new JTextField();

        JLabel lblSerie = new JLabel("Sede:");
        JTextField txtSerie = new JTextField();

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoArticulo = txtTipoArticulo.getText();
                String sede = txtSerie.getText();
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

        panel.add(lblTipoArticulo);
        panel.add(txtTipoArticulo);
        panel.add(lblSerie);
        panel.add(txtSerie);
        panel.add(btnAceptar);
        panel.add(btnCancelar);

        dialogo.add(panel);
        dialogo.pack();
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }
	
	private void mostrarListaArticulos() {
        // Lógica para obtener la lista de artículos
        // Supongamos que tienes un ArrayList llamado "listaArticulos" con los artículos
        
        StringBuilder sb = new StringBuilder();
        Articulo[] listaArticulos;
		for (Articulo articulo : listaArticulos ) {
            sb.append(articulo.toString()).append("\n");
        }
        
        String lista = sb.toString();

        // Mostrar el cuadro de diálogo con la lista de artículos
        JOptionPane.showMessageDialog(this, lista, "Lista de Articulos", JOptionPane.INFORMATION_MESSAGE);
    }
	
    public static void main(String[] args) {
    	VistaSoporteTecnico VistaSoporteTecnico = new VistaSoporteTecnico();
    }
}
