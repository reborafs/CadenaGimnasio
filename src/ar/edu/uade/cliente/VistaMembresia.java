package ar.edu.uade.cliente;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaMembresia extends JFrame {

    private ControladorCliente controller;

    public VistaMembresia() {
        super("Cliente: Membresia");
        this.controller = ControladorCliente.getInstance();
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        /* Ejercicios Disponibles por Sedes */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        JButton btnClaseAsignada = new JButton("Clases Asignadas");
        panelMenu.add(btnClaseAsignada, gbc);

        /* Ejercicios Disponibles por Sedes */
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnEjerciciosSedes = new JButton("Ejercicios Disponibles por Sedes");
        panelMenu.add(btnEjerciciosSedes, gbc);

        /* Membresia */
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JButton btnMembresia = new JButton("Membresia");
        panelMenu.add(btnMembresia, gbc);

        // Titulo
        JPanel panel = new JPanel();
        JLabel labelTitulo = new JLabel("Membresias");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        panelMenu.add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        this.add(panelMenu, BorderLayout.NORTH);

        JLabel labelMembresia1 = new JLabel("Black");
        labelMembresia1.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelMembresia1, gbc);

        JLabel labelMembresia1Desc = new JLabel("Descripción");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelMembresia1Desc, gbc);
        
        JLabel labelMembresia2 = new JLabel("Gold");
        labelMembresia2.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelMembresia2, gbc);

        JLabel labelMembresia2Desc = new JLabel("Descripción");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelMembresia2Desc, gbc);
        
        JLabel labelMembresia3 = new JLabel("Platinum");
        labelMembresia3.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelMembresia3, gbc);

        JLabel labelMembresia3Desc = new JLabel("Descripción");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelMenu.add(labelMembresia3Desc, gbc);


        class HandlerBtnClaseAsignada implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.abrirVistaClaseAsignada();
                //controladorLogin.validarUsuarioExistente(campoUsuario.getText(), campoContrasenia.getText());
            }
        }

        /*INSTANCIACION DEL MANEJADOR*/
        HandlerBtnClaseAsignada handlerBtnClaseAsignada =new HandlerBtnClaseAsignada();

        /*ASIGNACION DEL MANEJADOR AL BOTON*/
        btnClaseAsignada.addActionListener(handlerBtnClaseAsignada);

        class HandlerBtnEjerciciosSedes implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.abrirVistaEjercicioPorSede();
                //controladorLogin.validarUsuarioExistente(campoUsuario.getText(), campoContrasenia.getText());
            }
        }

        /*INSTANCIACION DEL MANEJADOR*/
        HandlerBtnEjerciciosSedes handlerBtnEjerciciosSedes=new HandlerBtnEjerciciosSedes();

        /*ASIGNACION DEL MANEJADOR AL BOTON*/
        btnEjerciciosSedes.addActionListener(handlerBtnEjerciciosSedes);

    }

}