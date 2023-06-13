package ar.edu.uade.vistas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import ar.edu.uade.usuarios.Administrativo;
//import ar.edu.uade.usuarios.Cliente;
//import ar.edu.uade.usuarios.Profesor;
//import ar.edu.uade.usuarios.SoporteTecnico;
//import ar.edu.uade.usuarios.Usuario;
import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.usuarios.Usuario;

public class Login  extends JFrame{
	
    JTextField campoUsuario, campoContrasenia;

    public Login() {
        super("Login");
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Agregar el título
        JLabel labelTitulo = new JLabel("GYMNASIO");
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
        campoContrasenia = new JTextField();
        campoContrasenia.setPreferredSize(new Dimension(150, 30));
        panel.add(campoContrasenia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnConectar = new JButton("Conectar");
        panel.add(btnConectar, gbc);

        this.add(panel, BorderLayout.CENTER);
        
       class HandlerBtnConectar implements ActionListener{

        	@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(campoUsuario.getText()+" "+campoContrasenia.getText());
			    String nombreUsuarioConectar = campoUsuario.getText();  
			    String contraseniaConectar = campoContrasenia.getText();
			    
			    ArrayList<Usuario> listaUsuarios = CadenaGimnasio.getListaUsuarios();
			    
			    if()
			    
			    
			    String tipoUsuario;
			    
			    
			}
		}
		
		/*INSTANCIACION DEL MANEJADOR*/
		HandlerBtnConectar handlerBtnConectar=new HandlerBtnConectar();
		
		/*ASIGNACION DEL MANEJADOR AL BOTON*/
		btnConectar.addActionListener(handlerBtnConectar);
    }
}
