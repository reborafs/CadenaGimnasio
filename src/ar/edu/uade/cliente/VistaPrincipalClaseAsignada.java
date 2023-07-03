package ar.edu.uade.cliente;

import ar.edu.uade.gym.GymException;
import ar.edu.uade.soporteTecnico.ControladorSoporteTecnico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class VistaPrincipalClaseAsignada extends JFrame {

    private ControladorCliente controller;

	public VistaPrincipalClaseAsignada() {
		super("Cliente: Clases Asignadas");
        this.controller = ControladorCliente.getInstance();
        this.setLayout(new BorderLayout());

        JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		/* Ejercicios Disponibles por Sedes*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		JButton btnClaseAsignada = new JButton("Clases Asignadas");
		panelMenu.add(btnClaseAsignada, gbc);

		/* Ejercicios Disponibles por Sedes*/
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

        /* Sleccion de sede */
        JLabel lblSedePrincipal = new JLabel("Sede:");
        JComboBox<String> txtSedePrincipal = new JComboBox<>();
        for (String sede : controller.getSedeCliente()) {
            txtSedePrincipal.addItem(sede);
            if(txtSedePrincipal.getItemCount() == 1) {
                txtSedePrincipal.setSelectedItem(sede);
            }
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelMenu.add(lblSedePrincipal, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelMenu.add(txtSedePrincipal, gbc);

        JButton btnInscribirse = new JButton("Inscribirse a una clase");
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelMenu.add(btnInscribirse, gbc);

		//Titulo
        JPanel panel = new JPanel();
		JLabel labelTitulo = new JLabel("Clases Asignadas");
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		panelMenu.add(labelTitulo, gbc);


		gbc.gridwidth = 1;

		this.add(panelMenu, BorderLayout.NORTH);

		JPanel panelContenido = new JPanel(new BorderLayout());

		// Tabla de clases asignadas
        JTable tabla = new JTable();

        cargarContenido(tabla, txtSedePrincipal.getSelectedItem().toString());

        // Agregar la tabla a un JScrollPane y añadirlo a la ventana
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelContenido.add(scrollPane, BorderLayout.CENTER);
        this.add(panelContenido, BorderLayout.CENTER);

        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        btnInscribirse.addActionListener(e -> abrirAgregarClase(txtSedePrincipal.getSelectedItem().toString()));
		btnEjerciciosSedes.addActionListener(e -> abrirVistaEjercicioPorSede());
		btnMembresia.addActionListener(e -> abrirVistaMembresia());
        txtSedePrincipal.addActionListener(e -> {
            String sede = (String) txtSedePrincipal.getSelectedItem();
            actualizarTabla(tabla, sede);
        });
    }

    private void abrirVistaMembresia() {
        this.dispose();
        controller.abrirVistaMembresia();
    }

    private void abrirVistaEjercicioPorSede() {
        this.dispose();
        controller.abrirVistaEjercicioPorSede();
    }



    private void actualizarTabla(JTable tabla, String sede) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        cargarContenido(tabla, sede);
    }

    public void cargarContenido(JTable tabla, String sede){
        DefaultTableModel modelo = new DefaultTableModel();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] header = new String[8];
        ArrayList<LocalDate> semana = new ArrayList<LocalDate>();
        header[0] = "Horario";
        for (int day=1; day<=7; day++) {
            LocalDate fecha = LocalDate.now().plusDays(day-1);
            semana.add(fecha);
            header[day] = getDiaSemana(fecha.getDayOfWeek().getValue()) + " " + fecha.format(formatter);
        }

        modelo.setColumnIdentifiers(header);

        LocalTime[] horas = {
                LocalTime.of(7, 0),
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(18, 0),
                LocalTime.of(19, 0),
                LocalTime.of(20, 0),
                LocalTime.of(21, 0)};

        int cantColumnas = header.length;
        int cantFilas = horas.length;

        HashMap<LocalDate, ArrayList<LocalTime>> horariosOcupados = controller.getClasesAsignadas(sede);

        System.out.print("");

        DateTimeFormatter horasFormatter = DateTimeFormatter.ofPattern("HH");

        for (int i = 1; i <= cantFilas - 1; i++) {
            String[] horarioDisponible = new String[cantColumnas + 1];
            horarioDisponible[0] = horas[i].format(horasFormatter) + "-" + horas[i].plusHours(1).format(horasFormatter);
            for (int j = 1; j <= cantColumnas - 1; j++) {
                LocalDate dia = semana.get(j - 1);
                LocalTime horarioClase = horas[i];
                if (horarioClase != null && contieneEjercicio(horariosOcupados, dia, horarioClase)) {
                    horarioDisponible[j] = controller.getClase(sede,dia,horarioClase).toUpperCase();
                } else {

                    horarioDisponible[j] = "Libre";
                }
            }
            modelo.addRow(horarioDisponible);
        }

        tabla.setModel(modelo);

        for (int i = 0; i < 4; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
    }

    private void abrirAgregarClase(String sede) {

        HashMap<String, ArrayList<LocalDateTime>> clasesExistentes = controller.getClasesPorSede(sede);
        if(!clasesExistentes.isEmpty()) {
            if (controller.validarNivelCliente(sede)){
                JDialog dialogo = new JDialog(this, "Agendar Clase", true);
                dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(9, 2));


                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbcInscripcion = new GridBagConstraints();
                gbcInscripcion.insets = new Insets(5, 5, 5, 5);
                gbcInscripcion.anchor = GridBagConstraints.WEST;

                List<JComboBox<String>> comboBoxesHorarios = new ArrayList<>();
                int i = 0;
                for (String clase : clasesExistentes.keySet()) {
                    gbcInscripcion.gridx = 0;
                    gbcInscripcion.gridy = i;
                    JLabel labelClase = new JLabel(clase + ":");
                    panel.add(labelClase, gbcInscripcion);

                    gbcInscripcion.gridx = 1;
                    gbcInscripcion.gridy = i;
                    JComboBox<String> comboBoxHorarios = new JComboBox<>();
                    comboBoxHorarios.addItem("---");
                    ArrayList<LocalDateTime> horarios = clasesExistentes.get(clase);
                    for (LocalDateTime horario : horarios) {
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
                        String fechaHoraFormateada = horario.format(formato);
                        comboBoxHorarios.addItem(fechaHoraFormateada);
                    }
                    panel.add(comboBoxHorarios, gbcInscripcion);
                    comboBoxesHorarios.add(comboBoxHorarios);
                    i++;
                }

                //Boton Incripcion
                JButton btnInscribirse = new JButton("Inscribirse");
                btnInscribirse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < comboBoxesHorarios.size(); i++) {
                            String clase = clasesExistentes.keySet().toArray(new String[0])[i];
                            JComboBox<String> comboBoxHorarios = comboBoxesHorarios.get(i);
                            String horarioSeleccionado = (String) comboBoxHorarios.getSelectedItem();
                            if (!horarioSeleccionado.equals("---")) {
                                try {
                                    controller.inscibirAlumno(clase, horarioSeleccionado, sede);
                                } catch (GymException ex) {
                                    throw new RuntimeException(ex);
                                }
                                System.out.println("Clase: " + clase + ", Horario: " + horarioSeleccionado);
                            }
                        }
                        dialogo.dispose();
                    }
                });

                JButton btnCancelar = new JButton("Cancelar");
                btnCancelar.addActionListener(e -> dialogo.dispose());


                panel.add(btnInscribirse);
                panel.add(btnCancelar);
                dialogo.add(panel);
                dialogo.pack();
                dialogo.setLocationRelativeTo(this);
                dialogo.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No tiene nivel para acceder a esta sede.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existen clases en la sede seleccionada.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean contieneEjercicio(HashMap<LocalDate, ArrayList<LocalTime>> horariosOcupados,LocalDate dia, LocalTime hora) {
        boolean flagOcupado = false;
        for (LocalTime horarioOcupado : horariosOcupados.get(dia)) {
            if (horarioOcupado.equals(hora)) {
                flagOcupado = true;
            }
        }
        return flagOcupado;
    }

    private String nombreEjercicio(String[] ejercicios, String ejercicio) {
        for (String e : ejercicios) {
            if (e.equals(ejercicio)) {
                return ejercicios[1];
            }
        }
        return null;
    }

    private String getDiaSemana(int dia) {
        return switch (dia) {
            case 1 -> "Lun";
            case 2 -> "Mar";
            case 3 -> "Mie";
            case 4 -> "Jue";
            case 5 -> "Vie";
            case 6 -> "Sab";
            case 7 -> "Dom";
            default -> "xxx";
        };

    }


}
