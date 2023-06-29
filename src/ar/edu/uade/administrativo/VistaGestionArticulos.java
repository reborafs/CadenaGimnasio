package ar.edu.uade.administrativo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

import ar.edu.uade.gym.GymException;
import ar.edu.uade.gym.articulos.Articulo;

public class VistaGestionArticulos extends JFrame {

	private ControladorAdministrativo controller;

	public VistaGestionArticulos() {
		super("Administrativo: Gestion de articulos");
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

		/* Monitorear Articulos */
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JButton btnMonitorear = new JButton("Monitorear Articulos");
		panelMenu.add(btnMonitorear, gbc);

		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);


		class HandlerBtnIncorporar implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDialogoIncorporarArticulos();
			}
		}

		class HandlerBtnMonitorear implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarListaArticulos();
			}
		}

		class HandlerBtnClientes implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.abrirVistaClientes();
			}
		}

		class HandlerBtnClases implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.abrirVistaClases();
			}
		}

		class HandlerBtnProfesor implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.abrirVistaProfesores();
			}
		}


		/* INSTANCIACION DE LOS MANEJADORES */
		HandlerBtnIncorporar handlerBtnIncorporar = new HandlerBtnIncorporar();
		HandlerBtnMonitorear handlerBtnMonitorear = new HandlerBtnMonitorear();
		HandlerBtnClientes handlerBtnClientes = new HandlerBtnClientes();
		HandlerBtnClases handlerBtnClases = new HandlerBtnClases();
		HandlerBtnProfesor handlerBtnProfesor = new HandlerBtnProfesor();

		/* ASIGNACION DE LOS MANEJADORES A LOS BOTONES */
		btnIncorporar.addActionListener(handlerBtnIncorporar);
		btnMonitorear.addActionListener(handlerBtnMonitorear);
		btnCliente.addActionListener(handlerBtnClientes);
		btnClases.addActionListener(handlerBtnClases);
		btnProfesor.addActionListener(handlerBtnProfesor);

	}
	
	private void mostrarDialogoIncorporarArticulos() {
        JDialog dialogo = new JDialog(this, "Incorporar Articulos", true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

		JLabel lblCategoriaArticulo = new JLabel("Categoria:");
		JTextField txtCategoriaArticulo = new JTextField();

		JLabel lblMarca = new JLabel("Marca:");
		JTextField txtMarca = new JTextField();

		JLabel lblDescripcion = new JLabel("Descripcion:");
		JTextField txtDescripcion = new JTextField();

		JLabel lblUsosAmortizacion = new JLabel("Cantidad de Usos hasta renovar:");
		JTextField txtUsosAmortizacion = new JFormattedTextField(NumberFormat.getIntegerInstance());

		JLabel lblError = new JLabel("ERROR");
		JLabel lblErrorMessage = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblErrorMessage.setForeground(Color.RED);

		JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					String categoriaArticulo = txtCategoriaArticulo.getText();
					String marca = txtMarca.getText();
					String descripcion = txtDescripcion.getText();
					int usosAmortizacion = Integer.parseInt(txtUsosAmortizacion.getText());
					boolean flagFecha = true;
					// Lógica para procesar la información capturada
					controller.agregarTipoArticulo(null, categoriaArticulo, marca, descripcion, usosAmortizacion, flagFecha);

					// Cerrar el diálogo
					lblError.setVisible(false);
					lblErrorMessage.setVisible(false);
					dialogo.dispose();
				} catch (NumberFormatException ex) {
					lblErrorMessage.setText("Se debe ingresar un numero entero.");
					lblError.setVisible(true);
					lblErrorMessage.setVisible(true);
					return; // Exit the method without processing the information
				}
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


		panel.add(lblCategoriaArticulo);
		panel.add(txtCategoriaArticulo);
		panel.add(lblMarca);
		panel.add(txtMarca);
		panel.add(lblDescripcion);
		panel.add(txtDescripcion);
		panel.add(lblUsosAmortizacion);
		panel.add(txtUsosAmortizacion);
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
	
	private void mostrarListaArticulos() {
        // Lógica para obtener la lista de artículos
        // Supongamos que tienes un ArrayList llamado "listaArticulos" con los artículos
        
        StringBuilder sb = new StringBuilder();
		String[] listaArticulos = controller.getListaTiposArticulos();

		for (String articulo : listaArticulos ) {
            sb.append(articulo).append("\n");
        }
        
        String lista = sb.toString();

        // Mostrar el cuadro de diálogo con la lista de artículos
        JOptionPane.showMessageDialog(this, lista, "Lista de Articulos", JOptionPane.INFORMATION_MESSAGE);
    }

}
