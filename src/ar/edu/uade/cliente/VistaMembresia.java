package ar.edu.uade.cliente;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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


        HashMap<String, String>  membresias = controller.getMembresias();

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 0;

        for (String membresia : membresias.keySet()) {

            JLabel labelMembresia1 = new JLabel(membresia);
            labelMembresia1.setFont(new Font("Arial", Font.BOLD, 20));
            gbc.anchor = GridBagConstraints.NORTHWEST;
            panelMenu.add(labelMembresia1, gbc);
            gbc.gridy++;

            System.out.print(membresia.toString());

            JLabel labelMembresia1Desc = new JLabel(membresias.get(membresia));
            gbc.anchor = GridBagConstraints.NORTHWEST;
            panelMenu.add(labelMembresia1Desc, gbc);
            gbc.gridy++;
        }

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