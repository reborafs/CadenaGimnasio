package ar.edu.uade.administrativo;

import ar.edu.uade.profesor.ControladorProfesor;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VistaPrincipalGestionClases extends JFrame{
	private ControladorAdministrativo controller;

	public VistaPrincipalGestionClases() {
		super("Administrativo: Gestion de clases");
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
		
		/* Agendar Clases */
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnAgendarClases = new JButton("Agendar Clases");
        panelMenu.add(btnAgendarClases, gbc);

        /* Cambiar Estado Clase */
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnCambiarEstadoClase = new JButton("Cambiar Estado Clase");
        panelMenu.add(btnCambiarEstadoClase, gbc);

		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

		btnCliente.addActionListener(actionEvent -> abrirVistaClientes());
		btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());
		btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());
	}

	private void abrirVistaArticulos() {
		this.dispose();
		controller.abrirVistaArticulos();
	}

	private void abrirVistaProfesores() {
		this.dispose();
		controller.abrirVistaProfesores();
	}

	private void abrirVistaClientes() {
		this.dispose();
		controller.abrirVistaClientes();
	}

	private void abrirVistaAgendarClases() {
        String profesor = JOptionPane.showInputDialog(this, "Ingrese el nombre del profesor:");
        String horario = JOptionPane.showInputDialog(this, "Ingrese el horario de la clase:");
        String sede = JOptionPane.showInputDialog(this, "Ingrese la sede de la clase:");

        // Aquí puedes utilizar los datos ingresados para crear la clase
        // Clase nuevaClase = new Clase(profesor, horario, sede);

        // Realizar las acciones necesarias con la nueva clase (almacenarla, mostrarla, etc.)
        // ...

        JOptionPane.showMessageDialog(this, "Clase creada exitosamente");
    }

    private void cambiarEstadoClase() {
    	// Aquí puedes implementar la lógica para cambiar el estado de una clase
        String[] opciones = {"Finalizada", "Agendada", "Confirmada"};
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el nuevo estado de la clase:",
                "Cambiar Estado Clase",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcionSeleccionada != null) {
            // Realizar la acción correspondiente al nuevo estado de la clase
            JOptionPane.showMessageDialog(this, "Estado de la clase cambiado a: " + opcionSeleccionada);
        }
    }
	

}
