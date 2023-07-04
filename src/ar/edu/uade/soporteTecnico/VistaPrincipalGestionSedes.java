package ar.edu.uade.soporteTecnico;

import ar.edu.uade.gym.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class VistaPrincipalGestionSedes extends JFrame{
	private ControladorSoporteTecnico controller;

	public VistaPrincipalGestionSedes() {
		super("Soporte Tecnico: Gestion de sedes");
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

		/* Agendar Sedes */
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnAgregarSedes = new JButton("Agregar Sedes");
		panelMenu.add(btnAgregarSedes, gbc);

		/* Agendar Sedes */
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnEliminarSedes = new JButton("Eliminar Sedes");
		panelMenu.add(btnEliminarSedes, gbc);


		this.setSize(800, 600);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

		btnUsuarios.addActionListener(actionEvent -> abrirVistaUsuarios());
		btnProfesor.addActionListener(actionEvent -> abrirVistaProfesores());
		btnArticulos.addActionListener(actionEvent -> abrirVistaArticulos());
		btnAgregarSedes.addActionListener(actionEvent -> abrirAgregarSedes());
		btnEliminarSedes.addActionListener(actionEvent -> abrirEliminarSedes());

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

		ArrayList<String> listaEjercicios = controller.getListaNombresEjercicios();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9+listaEjercicios.size()/2, 2));

		JLabel lblUbicacion = new JLabel("Ubicacion:");
		JTextField txtUbicacion = new JTextField();

		JLabel lblTipoNivel = new JLabel("Nivel:");
		JComboBox<String> txtTipoNivel = new JComboBox<>();
		for (String sede : controller.getTiposMembresias())
			txtTipoNivel.addItem(sede);

		JLabel lblCantEmplazamientos = new JLabel("Cantidad de Emplazamientos:");
		JTextField txtCantEmplazamientos = new JFormattedTextField(NumberFormat.getNumberInstance());

		JLabel lblEjerciciosDisponibles = new JLabel("Ejercicios Disponibles");

		JLabel lblEspacioVacio = new JLabel("   ");
		JLabel lblEspacioVacio2 = new JLabel("   ");

		panel.add(lblUbicacion);
		panel.add(txtUbicacion);
		panel.add(lblTipoNivel);
		panel.add(txtTipoNivel);
		panel.add(lblCantEmplazamientos);
		panel.add(txtCantEmplazamientos);
		panel.add(lblEjerciciosDisponibles);
		panel.add(lblEspacioVacio);

		ArrayList<JCheckBox> checkBoxesEjercicios = new ArrayList<>();

		for (String sede : listaEjercicios) {
			JCheckBox checkBox = new JCheckBox(sede);
			panel.add(checkBox);
			checkBoxesEjercicios.add(checkBox);
		}

		if(checkBoxesEjercicios.size() % 2 == 1)
			panel.add(lblEspacioVacio2);

		JLabel lblAlquilerSede = new JLabel("Alquiler Sede");
		JTextField txtAlquilerSede = new JTextField();

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);



		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				String ubicacion = txtUbicacion.getText();
				String tipoNivel = txtTipoNivel.getItemAt(txtTipoNivel.getSelectedIndex());
				int cantEmplazamientos = Integer.parseInt(txtCantEmplazamientos.getText());
				ArrayList<String> ejerciciosDisponibles = new ArrayList<>();
				double alquilerSede = Double.parseDouble(txtAlquilerSede.getText());

				boolean flag = false;
				for (JCheckBox check : checkBoxesEjercicios){

					if (check.isSelected()) {
						ejerciciosDisponibles.add(check.getText());
						flag = true;
					}
				}

				if(!flag){
					String error = "Debe completar todos los campos";
					throw new Exception(error);
				}

				crearEmplazamientos(ubicacion, tipoNivel, cantEmplazamientos, ejerciciosDisponibles, alquilerSede);
				lblError.setVisible(false);
				lblErrorMessage.setVisible(false);
				dialogo.dispose();
			} catch (Exception ex) {
				lblErrorMessage.setText("Debe completar todos los campos");
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());


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

	private void crearEmplazamientos(String ubicacion, String tipoNivel, int cantEmplazamientos,
													ArrayList<String> ejerciciosDisponibles, double alquilerSede) {
		JDialog dialogo = new JDialog(this, "Crear Emplazamientos", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5*cantEmplazamientos, 2));
		ArrayList<String[]> emplazamientos = new ArrayList<>();

		for (int i = 0; i < cantEmplazamientos; i++){
			JLabel lblTitulo = new JLabel("Emplazamiento NUMERO:");
			JLabel lblNumero = new JLabel(String.valueOf(i+1));
			JLabel lblTipoEmplazamiento = new JLabel("Tipo Emplazamiento:");
			JComboBox<String> txtTipoEmplazamiento = new JComboBox<>();
			for (String tipoEmplazamiento : controller.getListaTiposEmplazamientos())
				txtTipoEmplazamiento.addItem(tipoEmplazamiento);
			JLabel lblCapacidad = new JLabel("Capacidad:");
			JTextField txtCapacidad = new JFormattedTextField(NumberFormat.getNumberInstance());
			JLabel lblMetros = new JLabel("Metros Cuadrados:");
			JTextField txtMetros = new JFormattedTextField(NumberFormat.getNumberInstance());

			panel.add(lblTitulo);
			panel.add(lblNumero);
			panel.add(lblTipoEmplazamiento);
			panel.add(txtTipoEmplazamiento);
			panel.add(lblCapacidad);
			panel.add(txtCapacidad);
			panel.add(lblMetros);
			panel.add(txtMetros);
		}

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				for (int i = 0; i < cantEmplazamientos; i++) {

					Component componentTipo = panel.getComponent(i * 8 + 3);
					Component componentCapacidad = panel.getComponent(i * 8 +5);
					Component componentMetros = panel.getComponent(i * 8 + 7);

					JComboBox<String> txtTipo = (JComboBox<String>) componentTipo;
					JTextField txtCapacidad = (JTextField) componentCapacidad;
					JTextField txtMetros = (JTextField) componentMetros;
/*
					[javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Emplazamiento NUMERO:,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=1,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Tipo Emplazamiento:,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JComboBox[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=0.0,alignmentY=0.0,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder=PILETA],
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Capacidad:,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JFormattedTextField[,0,0,0x0,invalid,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=0.0,alignmentY=0.0,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@acc72cc,flags=296,maximumSize=,minimumSize=,preferredSize=,caretColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],disabledTextColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],selectionColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],columns=0,columnWidth=0,command=,horizontalAlignment=LEADING],
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Metros Cuadrados:,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JFormattedTextField[,0,0,0x0,invalid,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=0.0,alignmentY=0.0,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@acc72cc,flags=296,maximumSize=,minimumSize=,preferredSize=,caretColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],disabledTextColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],selectionColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],columns=0,columnWidth=0,command=,horizontalAlignment=LEADING],
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Emplazamiento NUMERO:,verticalAlignment=CENTER,verticalTextPosition=CENTER
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=2,verticalAlignment=CENTER,verticalTextPosition=CENTER
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Tipo Emplazamiento:,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JComboBox[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=0.0,alignmentY=0.0,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder=PILETA
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Capacidad:,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JFormattedTextField[,0,0,0x0,invalid,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=0.0,alignmentY=0.0,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@acc72cc,flags=296,maximumSize=,minimumSize=,preferredSize=,caretColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],disabledTextColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],selectionColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],columns=0,columnWidth=0,command=,horizontalAlignment=LEADING],
					javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=Metros Cuadrados:,verticalAlignment=CENTER,verticalTextPosition=CENTER],
					javax.swing.JFormattedTextField[,0,0,0x0,invalid,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=0.0,alignmentY=0.0,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@acc72cc,flags=296,maximumSize=,minimumSize=,preferredSize=,caretColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],disabledTextColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=sun.swing.PrintColorUIResource[r=51,g=51,b=51],selectionColor=javax.swing.plaf.ColorUIResource[r=184,g=207,b=229],columns=0,columnWidth=0,command=,horizontalAlignment=LEADING]]
*/
					String tipo = txtTipo.getItemAt(txtTipo.getSelectedIndex());
					String capacidad = txtCapacidad.getText();
					String metros = txtMetros.getText();

					if (capacidad == null || metros == null) {
						throw new GymException("Campos vacios.");
					}

					emplazamientos.add(new String[]{tipo, capacidad, metros});
				}
				controller.agregarSede(ubicacion, tipoNivel, emplazamientos, ejerciciosDisponibles, alquilerSede);
				lblError.setVisible(false);
				lblErrorMessage.setVisible(false);
				dialogo.dispose();
				JOptionPane.showMessageDialog(panel, "Sede creada exitosamente");
			} catch ( Exception ex ) {
				lblErrorMessage.setText(ex.getMessage());
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());

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

	private void abrirEliminarSedes() {
		JDialog dialogo = new JDialog(this, "Eliminar Sede", true);
		dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		JLabel lblUbicacion = new JLabel("Ubicacion:");
		JTextField txtUbicacion = new JTextField();

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
			try {
				String ubicacion = txtUbicacion.getText();
				controller.eliminarSede(ubicacion);
				lblError.setVisible(false);
				lblErrorMessage.setVisible(false);
				dialogo.dispose();
				JOptionPane.showMessageDialog(panel, "Sede Eliminada exitosamente");
			} catch ( Exception ex ) {
				lblErrorMessage.setText(ex.getMessage());
				lblError.setVisible(true);
				lblErrorMessage.setVisible(true);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dialogo.dispose());

		panel.add(lblUbicacion);
		panel.add(txtUbicacion);
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
