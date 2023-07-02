package ar.edu.uade.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame{
	
    JTextField campoUsuario, campoContrasenia;
    private ControladorLogin controladorLogin;


    public Login() {
        super("Login");
        this.controladorLogin = ControladorLogin.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Agregar el título
        JLabel labelTitulo = new JLabel("GIMNASIO");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(labelTitulo, gbc);

        gbc.gridwidth = 1; // Restablecer el gridwidth a 1

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelUsuario = new JLabel("Usuario");
        panel.add(labelUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(150, 30));
        panel.add(campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelContrasenia = new JLabel("Contraseña");
        panel.add(labelContrasenia, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        campoContrasenia = new JPasswordField();
        campoContrasenia.setPreferredSize(new Dimension(150, 30));
        panel.add(campoContrasenia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnConectar = new JButton("Conectar");
        panel.add(btnConectar, gbc);

        this.add(panel, BorderLayout.CENTER);

		/*ASIGNACION DEL MANEJADOR AL BOTON*/
		btnConectar.addActionListener(e -> {
            try {
                controladorLogin.loginUsuario(campoUsuario.getText(), campoContrasenia.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        );
    }
}
