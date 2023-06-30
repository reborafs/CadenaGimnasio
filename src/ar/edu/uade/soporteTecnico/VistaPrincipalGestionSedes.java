package ar.edu.uade.soporteTecnico;

import ar.edu.uade.gym.Ejercicio;
import ar.edu.uade.gym.Emplazamiento;
import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.TipoNivel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaPrincipalGestionSedes extends JFrame{
	private ControladorSoporteTecnico controller;

	public VistaPrincipalGestionSedes() {
		super("Administrativo: Gestion de sedes");
		this.controller = ControladorSoporteTecnico.getInstance();
		this.setLayout(new BorderLayout());
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		/* Gestion de Sedes */
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnSedes = new JButton("Gestion de Sedes");
		panelMenu.add(btnSedes, gbc);

		/* Gestion de Usuarios*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnUsuarios = new JButton("Gestion de Usuarios");
		panelMenu.add(btnUsuarios, gbc);
		
		this.add(panelMenu, BorderLayout.NORTH);

		/* Gestion de Profesor */
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnProfesor = new JButton("Gestion de Ejercicios");
		panelMenu.add(btnProfesor, gbc);
		
		/* Gestion de Articulos */
		gbc.gridx = 7;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		JButton btnArticulos = new JButton("Gestion de Articulos");
		panelMenu.add(btnArticulos, gbc);
		
		/* Agendar Sedes */
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnAgregarSedes = new JButton("Agregar Sedes");
        panelMenu.add(btnAgregarSedes, gbc);


		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

		btnUsuarios.addActionListener(actionEvent -> abrirVistaUsuarios());
		btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());
		btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());
		btnAgregarSedes.addActionListener(actionEvent -> abrirAgregarSedes());

		mostrarTablaSedes();
	}

	private void abrirVistaArticulos() {
		this.dispose();
		controller.abrirVistaArticulos();
	}

	private void abrirVistaProfesores() {
		this.dispose();
		controller.abrirVistaProfesores();
	}

	private void abrirVistaUsuarios() {
		this.dispose();
		controller.abrirVistaUsuarios();
	}

	private void abrirAgregarSedes() {

		JDialog dialogo = new JDialog(this, "Agregar Sede", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 2));

		JLabel lblUbicacion = new JLabel("Ubicacion:");
		JTextField txtUbicacion = new JTextField();

		JLabel lblTipoNivel = new JLabel("Nivel:");
		JComboBox<String> txtTipoNivel = new JComboBox<>();
		for (String sede : controller.getTiposMembresias())
			txtTipoNivel.addItem(sede);

		JLabel lblEmplazamiento = new JLabel("Emplazamientos:");
		JTextField txtEmplazamiento = new JTextField();

		JLabel lblEjerciciosDisponibles = new JLabel("Ejercicios Disponibles");
		JTextField txtEjerciciosDisponibles = new JTextField();

		JLabel lblAlquilerSede = new JLabel("Alquiler Sede");
		JTextField txtAlquilerSede = new JTextField();

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String ubicacion = txtUbicacion.getText();
					String tipoNivel = txtTipoNivel.getItemAt(txtTipoNivel.getSelectedIndex());
					String emplazamientos = txtEmplazamiento.getText();
					String ejerciciosDisponibles = txtEjerciciosDisponibles.getText();
					String alquilerSede = txtAlquilerSede.getText();

					// Lógica para procesar la información capturada
					controller.agregarSede(ubicacion, tipoNivel, emplazamientos, ejerciciosDisponibles, alquilerSede);

					// Cerrar el diálogo
					lblError.setVisible(false);
					lblErrorMessage.setVisible(false);
					dialogo.dispose();
					JOptionPane.showMessageDialog(panel, "Sede creada exitosamente");
				} catch ( Exception ex ) {
					lblErrorMessage.setText(ex.getMessage());
					lblError.setVisible(true);
					lblErrorMessage.setVisible(true);
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());

		panel.add(lblUbicacion);
		panel.add(txtUbicacion);
		panel.add(lblTipoNivel);
		panel.add(txtTipoNivel);
		panel.add(lblEmplazamiento);
		panel.add(txtEmplazamiento);
		panel.add(lblEjerciciosDisponibles);
		panel.add(txtEjerciciosDisponibles);
		panel.add(lblAlquilerSede);
		panel.add(txtAlquilerSede);

		panel.add(lblError);
		panel.add(lblErrorMessage);
		panel.add(btnAceptar);
		panel.add(btnCancelar);
		lblError.setVisible(false);
		lblErrorMessage.setVisible(false);

		dialogo.add(panel);
		dialogo.pack();
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
    }

	private void mostrarTablaSedes() {
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();

		ArrayList<String[]> listaSedes = controller.getListaInfoSedes();

		// Definicion de columnas
		String[] columnas = {"Ubicacion", "tipoNivel", "stockArticulos", "emplazamientosDisponibles", "listaClases",
				"ejerciciosDisponibles", "alquilerSede"};


		int cantColumnas = columnas.length;

		modelo.setColumnIdentifiers(columnas);

		for (String[] infoSede : listaSedes) {
			String[] fila = new String[cantColumnas + 1];
			fila[0] = infoSede[0];
			fila[1] = infoSede[1];
			fila[2] = infoSede[2];
			fila[3] = infoSede[3];
			fila[4] = infoSede[4];
			fila[5] = infoSede[5];
			fila[6] = infoSede[6];
			modelo.addRow(fila);
		}

		tabla.setModel(modelo);

		for (int i = 0; i < cantColumnas; i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
		}

		// Agregar la tabla a un JScrollPane y añadirlo a la ventana
		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}


}
