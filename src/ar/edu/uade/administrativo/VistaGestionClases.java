package ar.edu.uade.administrativo;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VistaGestionClases extends JFrame{
	public VistaGestionClases() {
		super("Administrativo: Gestion de clases");
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
		
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void abrirVistaAgendarClases() {
        String profesor = JOptionPane.showInputDialog(this, "Ingrese el nombre del profesor:");
        String horario = JOptionPane.showInputDialog(this, "Ingrese el horario de la clase:");
        String sede = JOptionPane.showInputDialog(this, "Ingrese la sede de la clase:");

        // Aquí puedes utilizar los datos ingresados para crear la clase
        Clase nuevaClase = new Clase(profesor, horario, sede);

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
	
	
	
    public static void main(String[] args) {
    	VistaAdministrativo vistaAdministrativo = new VistaAdministrativo();
    }
}