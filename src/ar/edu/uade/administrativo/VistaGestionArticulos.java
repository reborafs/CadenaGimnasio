package ar.edu.uade.administrativo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.Sede;
import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.gym.articulos.TipoArticulo;

public class VistaGestionArticulos extends JFrame {

	private ControladorAdministrativo controller;

	public VistaGestionArticulos() {
		super("Administrativo: Gestion de stock");
		this.controller = ControladorAdministrativo.getInstance();
		this.setLayout(new BorderLayout());

		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		/* Gestion de Cliente */
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
		
		/* Incorporar Articulos */
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnIncorporar = new JButton("Incorporar Articulos");
		panelMenu.add(btnIncorporar, gbc);

		/* Desincorporar Articulos */
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnDesincorporar = new JButton("Desincorporar Articulos");
		panelMenu.add(btnDesincorporar, gbc);

		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);


		/* ASIGNACION DE LOS BOTONES */
		btnIncorporar.addActionListener(actionEvent -> mostrarDialogoIncorporarArticulos());
		btnDesincorporar.addActionListener(actionEvent -> eliminarArticulos());
		btnCliente.addActionListener(actionEvent -> abrirVistaClientes());
		btnClases.addActionListener(actionEvent -> abrirVistaClases());
		btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());

		mostrarTablaTiposArticulos();
	}

	private void abrirVistaProfesores() {
		this.dispose();
		controller.abrirVistaProfesores();
	}

	private void abrirVistaClases() {
		this.dispose();
		controller.abrirVistaClases();
	}

	private void abrirVistaClientes() {
		this.dispose();
		controller.abrirVistaClientes();
	}

	private void mostrarDialogoIncorporarArticulos() {
        JDialog dialogo = new JDialog(this, "Incorporar Articulos", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2));

		JLabel lblNombreSede = new JLabel("Sede:");
		JComboBox<String> txtNombreSede = new JComboBox<>();
		for (String sede : controller.getListaSedes())
			txtNombreSede.addItem(sede);

		JLabel lblCantidad = new JLabel("Cantidad:");
		JTextField txtCantidad = new JTextField();

		JLabel lblTipoArticulo = new JLabel("TipoArticulo:");
		JComboBox<String> txtTipoArticulo = new JComboBox<>();
		for (String[] tipoArticulo : controller.getListaTiposArticulos())
			txtTipoArticulo.addItem(tipoArticulo[1]);

		JLabel lblPrecio = new JLabel("Precio:");
		JTextField txtPrecio = new JTextField();

		JLabel lblFechaCompra = new JLabel("Fecha de la compra (dd-MM-yyyy):");
		JTextField txtFechaCompra = new JTextField();

		JLabel lblFechaFabricacion = new JLabel("Fecha de fabricación (dd-MM-yyyy):");
		JTextField txtFechaFabricacion = new JTextField();

	//	ButtonGroup buttonGroup = new ButtonGroup();
	//	btnPorUsos.setSelected(true);
	//	buttonGroup.add(btnPorUsos);
	//	buttonGroup.add(btnPorFecha);

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {

				int cantidad = Integer.valueOf(txtCantidad.getText());
				String nombre = txtNombreSede.getSelectedItem().toString();
				String tipoArticulo = txtTipoArticulo.getSelectedItem().toString();
				Double precio = Double.parseDouble(txtPrecio.getText());
				String fechaCompra = txtFechaCompra.getText();
				String fechaFabricacion = txtFechaFabricacion.getText();
				controller.agregarArticulo(cantidad, nombre, tipoArticulo, precio, fechaCompra, fechaFabricacion);

				// Cerrar el diálogo
				lblError.setVisible(false);
				lblErrorMessage.setVisible(false);
				dialogo.dispose();
		});

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


		panel.add(lblNombreSede);
		panel.add(txtNombreSede);
		panel.add(lblCantidad);
		panel.add(txtCantidad);
		panel.add(lblTipoArticulo);
		panel.add(txtTipoArticulo);
		panel.add(lblPrecio);
		panel.add(txtPrecio);
		panel.add(lblFechaFabricacion);
		panel.add(txtFechaFabricacion);
		panel.add(lblFechaCompra);
		panel.add(txtFechaCompra);
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

	private void mostrarTablaTiposArticulos() {
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();

		HashMap<String,ArrayList<String[]>> listaArticulosSede = controller.getListaArticulos();

		// Definicion de columnas
		String[] columnas = {"ID", "Sede","tipoArticulo","Fecha Compra","Fecha Fabricacion","Usos"};
		int cantColumnas = columnas.length;

		modelo.setColumnIdentifiers(columnas);

		for(String sede : listaArticulosSede.keySet()) {
			for(String[] infoTipoArticulo: listaArticulosSede.get(sede)) {
				if(infoTipoArticulo[5].equals("false")) {
					String[] fila = new String[cantColumnas + 1];
					fila[0] = infoTipoArticulo[0];
					fila[1] = sede;
					fila[2] = infoTipoArticulo[1];
					fila[3] = infoTipoArticulo[2];
					fila[4] = infoTipoArticulo[3];
					fila[5] = infoTipoArticulo[4];
					modelo.addRow(fila);
				}
			}
		}

		tabla.setModel(modelo);

		for (int i = 0; i < cantColumnas; i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
		}

		// Agregar la tabla a un JScrollPane y añadirlo a la ventana
		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	private void eliminarArticulos() {
		// Implementación de la funcionalidad de creación de cliente
		JDialog dialogo = new JDialog(this, "Eliminar Articulo", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		JLabel lblID = new JLabel("ID del articulo:");
		JTextField txtID = new JFormattedTextField(NumberFormat.getIntegerInstance());

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				int id = Integer.parseInt(txtID.getText());
				controller.eliminarArticulo(id);
				dialogo.dispose(); // Cerrar el diálogo
			} catch (Exception ex) {
				lblErrorMessage.setText(ex.getMessage());
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());

		panel.add(lblID);
		panel.add(txtID);
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
	/*
	private void mostrarListaArticulos() {

        StringBuilder sb = new StringBuilder();
		String[] listaArticulos = controller.getListaTiposArticulos();

		for (String articulo : listaArticulos ) {
            sb.append(articulo).append("\n");
        }
        
        String lista = sb.toString();

        // Mostrar el cuadro de diálogo con la lista de artículos
        JOptionPane.showMessageDialog(this, lista, "Lista de Articulos", JOptionPane.INFORMATION_MESSAGE);
    }
*/
}
