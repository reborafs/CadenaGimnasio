package ar.edu.uade.soporteTecnico;

import ar.edu.uade.gym.GymException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class VistaGestionTipoArticulos extends JFrame {

	private ControladorSoporteTecnico controller;

	public VistaGestionTipoArticulos() {
		super("Soporte Tecnico: Gestion de tipos de articulos");
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
		JButton btnArticulos = new JButton("Gestion de Tipo de Articulos");
		panelMenu.add(btnArticulos, gbc);

		/* Agregar Tipo de Articulos */
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnAgregar = new JButton("Agregar Tipo de Articulo");
		panelMenu.add(btnAgregar, gbc);

		/* Eliminar Tipo de Articulos */
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnEliminar = new JButton("Eliminar Tipo de Articulo");
		panelMenu.add(btnEliminar, gbc);

		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);


		/* ASIGNACION DE LOS BOTONES */
		btnAgregar.addActionListener(actionEvent -> mostrarDialogoAgregarArticulos());
		btnEliminar.addActionListener(actionEvent -> mostrarDialogoEliminarArticulos());
		btnUsuarios.addActionListener(actionEvent -> abrirVistaUsuarios());
		btnSedes.addActionListener(actionEvent -> abrirVistaClases());
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

	private void abrirVistaUsuarios() {
		this.dispose();
		controller.abrirVistaUsuarios();
	}

	private void mostrarDialogoAgregarArticulos() {
        JDialog dialogo = new JDialog(this, "Agregar Tipo de Articulos", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
		JLabel lblNombre = new JLabel("Nombre:");
		JTextField txtNombre = new JTextField();

		JLabel lblCategoriaArticulo = new JLabel("Categoria:");
		JComboBox<String> txtCategoriaArticulo = new JComboBox<>();
		for (String sede : controller.getCategoriasArticulos())
			txtCategoriaArticulo.addItem(sede);

		JLabel lblMarca = new JLabel("Marca:");
		JTextField txtMarca = new JTextField();

		JLabel lblDescripcion = new JLabel("Descripcion:");
		JTextField txtDescripcion = new JTextField();

		JLabel lblUsosAmortizacion = new JLabel("Cantidad de Usos/Dias hasta renovar:");
		JTextField txtUsosAmortizacion = new JTextField();

		JRadioButton btnPorUsos = new JRadioButton("Amortizacion por Usos");
		JRadioButton btnPorFecha = new JRadioButton("Amortizacion por Fecha");

		ButtonGroup buttonGroup = new ButtonGroup();
		btnPorUsos.setSelected(true);
		buttonGroup.add(btnPorUsos);
		buttonGroup.add(btnPorFecha);

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
			try {
				String nombre = txtNombre.getText();
				String categoriaArticulo = txtCategoriaArticulo.getItemAt(txtCategoriaArticulo.getSelectedIndex());
				String marca = txtMarca.getText();
				String descripcion = txtDescripcion.getText();
				int usosAmortizacion = Integer.parseInt(txtUsosAmortizacion.getText());
				boolean flagFecha = btnPorFecha.isSelected();

				if(nombre.isEmpty() || marca.isEmpty() || descripcion.isEmpty() || usosAmortizacion == 0){
					String error = "Debe completar todos los campos";
					throw new IllegalArgumentException(error);
				}

				// Lógica para procesar la información capturada
				controller.agregarTipoArticulo(nombre, categoriaArticulo, marca, descripcion, usosAmortizacion, btnPorUsos.isSelected());

				// Cerrar el diálogo
				lblError.setVisible(false);
				lblErrorMessage.setVisible(false);
				dialogo.dispose();
			} catch (NumberFormatException ex) {
				lblErrorMessage.setText("Se debe ingresar un número entero en el campo \"Cantidad de Usos/Dias hasta renovar\".");
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
				return; // Salir del método sin procesar la información
			} catch (IllegalArgumentException ex) {
				lblErrorMessage.setText(ex.getMessage());
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
				throw new RuntimeException(ex);
			}
		});

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());


		panel.add(lblNombre);
		panel.add(txtNombre);
		panel.add(lblCategoriaArticulo);
		panel.add(txtCategoriaArticulo);
		panel.add(lblMarca);
		panel.add(txtMarca);
		panel.add(lblDescripcion);
		panel.add(txtDescripcion);
		panel.add(lblUsosAmortizacion);
		panel.add(txtUsosAmortizacion);
		panel.add(btnPorUsos);
		panel.add(btnPorFecha);
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

	private void mostrarDialogoEliminarArticulos() {
		JDialog dialogo = new JDialog(this, "Eliminar Tipo de Articulos", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		JLabel lblIdArticulo = new JLabel("ID del articulo:");
		JTextField txtIdArticulo = new JTextField();

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				int idArticulo = Integer.parseInt(txtIdArticulo.getText());
				controller.eliminarTipoArticulo(idArticulo);
				dialogo.dispose();
			} catch (Exception ex) {
				lblErrorMessage.setText(ex.getMessage());
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());


		panel.add(lblIdArticulo);
		panel.add(txtIdArticulo);
		panel.add(lblError);
		panel.add(lblErrorMessage);
		panel.add(btnAceptar);
		panel.add(btnCancelar);
		lblError.setVisible(false);
		lblErrorMessage.setVisible(false);

		dialogo.add(panel);
		//dialogo.setSize(800, 300);
		dialogo.pack();
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);

	}

	private void mostrarTablaTiposArticulos() {
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();

		ArrayList<String[]> listaTiposArticulos = controller.getListaTiposArticulos();

		// Definicion de columnas
		String[] columnas = {"ID","Nombre","Categoria","Marca","Descripcion","Forma Amortizacion","Dias / Usos"};
		int cantColumnas = columnas.length;

		modelo.setColumnIdentifiers(columnas);

		for (String[] infoTipoArticulo : listaTiposArticulos) {
			String[] fila = new String[cantColumnas+1];
			fila[0] = infoTipoArticulo[0];
			fila[1] = infoTipoArticulo[1];
			fila[2] = infoTipoArticulo[2];
			fila[3] = infoTipoArticulo[3];
			fila[4] = infoTipoArticulo[4];
			fila[5] = infoTipoArticulo[5];
			fila[6] = infoTipoArticulo[6];
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
