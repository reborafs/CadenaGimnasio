package ar.edu.uade.main;

import ar.edu.uade.cliente.PrincipalVistaClienteClaseAsignada;
import ar.edu.uade.gym.CadenaGimnasio;
import ar.edu.uade.vistas.Login;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        CadenaGimnasio gym = CadenaGimnasio.getInstance();
        gym.llenarGym();

        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            Login vistaLogin = new Login();;
            vistaLogin.setVisible(true);
        });
    }
}
