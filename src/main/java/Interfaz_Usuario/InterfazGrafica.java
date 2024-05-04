package Interfaz_Usuario;

import Logica_de_Negocio.Experimento;
import Logica_de_Negocio.Poblacion;
import Navegador_Archivos.GestorDatos;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InterfazGrafica extends JFrame {
    private JButton btnAbrirArchivo;
    private JButton btnNuevoExperimento;
    private JButton btnAgregarPoblacion;
    private JButton btnVisualizarPoblaciones;
    private JButton btnBorrarPoblacion;
    private JButton btnVerDetalle;
    private JButton btnGuardar;
    private JButton btnGuardarComo;
    private Experimento experimento;

    public InterfazGrafica() {
        initComponents();
        setLayout(null);
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestión de cultivos de bacterias.");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        btnAbrirArchivo = new JButton("<html>Abrir archivo<br>de un experimento</html>");
        btnNuevoExperimento = new JButton("Nuevo experimento");
        btnAgregarPoblacion = new JButton("Agregar poblacion");
        btnVisualizarPoblaciones = new JButton("<html>Visualizar nombres<br>poblaciones</html>");
        btnBorrarPoblacion = new JButton("Borrar poblacion");
        btnVerDetalle = new JButton("<html>Informción detallada<br>de una población</html>");
        btnGuardar = new JButton("Guardar");
        btnGuardarComo = new JButton("Guardar Como");

        btnAbrirArchivo.setBounds(20, 20, 150, 35);
        btnNuevoExperimento.setBounds(20, 60, 150, 30);
        btnAgregarPoblacion.setBounds(20, 100, 150, 30);
        btnVisualizarPoblaciones.setBounds(20, 140, 150, 35);
        btnBorrarPoblacion.setBounds(20, 180, 150, 30);
        btnVerDetalle.setBounds(20, 220, 150, 35);
        btnGuardar.setBounds(200, 100, 150, 30);
        btnGuardarComo.setBounds(200, 140, 150, 30);

        add(btnAbrirArchivo);
        add(btnNuevoExperimento);
        add(btnAgregarPoblacion);
        add(btnVisualizarPoblaciones);
        add(btnBorrarPoblacion);
        add(btnVerDetalle);
        add(btnGuardar);
        add(btnGuardarComo);

        btnAbrirArchivo.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser("Experimentos/");
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                    StringBuilder contenido = new StringBuilder();
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                    JTextArea textArea = new JTextArea(contenido.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    scrollPane.setPreferredSize(new Dimension(500, 500));
                    JOptionPane.showMessageDialog(null, scrollPane, "Contenido del archivo", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
                }
            }
        });

        btnNuevoExperimento.addActionListener(e -> {
            String nombreExperimento = JOptionPane.showInputDialog("Introduce el nombre del experimento:");
            if (nombreExperimento != null && !nombreExperimento.trim().isEmpty()) {
                experimento = new Experimento();
                experimento.setNombre(nombreExperimento);
                String numPoblacionesStr = JOptionPane.showInputDialog("¿Cuántas poblaciones quieres agregar?");
                if (numPoblacionesStr != null && !numPoblacionesStr.trim().isEmpty()) {
                    try {
                        int numPoblaciones = Integer.parseInt(numPoblacionesStr);
                        for (int i = 0; i < numPoblaciones; i++) {
                            Poblacion poblacion = new Poblacion();
                            // Aquí se recogen los datos de la población y se añaden a la lista de poblaciones del experimento
                            experimento.getPoblaciones().add(poblacion);
                        }
                        JOptionPane.showMessageDialog(null, "Nuevo experimento creado");
                        String nombreArchivo = experimento.getNombre() + ".txt";
                        GestorDatos.guardarExperimento(nombreArchivo, experimento);
                        JOptionPane.showMessageDialog(null, "Experimento guardado");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error: se esperaba un número");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: se esperaba un número");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: se esperaba un nombre de experimento");
            }
        });

        btnAgregarPoblacion.addActionListener(e -> {
            if (experimento != null) {
                Poblacion poblacion = new Poblacion();
                experimento.getPoblaciones().add(poblacion);
                JOptionPane.showMessageDialog(null, "Población agregada al experimento");
            } else {
                JOptionPane.showMessageDialog(null, "Primero debes crear un experimento");
            }
        });

        btnBorrarPoblacion.addActionListener(e -> {
            if (experimento != null && !experimento.getPoblaciones().isEmpty()) {
                // Aquí puedes agregar lógica para seleccionar y eliminar una población
                JOptionPane.showMessageDialog(null, "Población eliminada del experimento");
            } else {
                JOptionPane.showMessageDialog(null, "No hay poblaciones para eliminar");
            }
        });

        btnVerDetalle.addActionListener(e -> {
            if (experimento != null && !experimento.getPoblaciones().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Mostrando detalles de la población");
            } else {
                JOptionPane.showMessageDialog(null, "No hay poblaciones para mostrar detalles");
            }
        });

        btnGuardar.addActionListener(e -> {
            if (experimento != null) {
                String nombreArchivo = experimento.getNombre() + ".txt";
                GestorDatos.guardarExperimento(nombreArchivo, experimento);
                JOptionPane.showMessageDialog(null, "Experimento guardado");
            } else {
                JOptionPane.showMessageDialog(null, "No hay experimento para guardar");
            }
        });

        btnGuardarComo.addActionListener(e -> {
            if (experimento != null) {
                JOptionPane.showMessageDialog(null, "Experimento guardado con un nuevo nombre");
            } else {
                JOptionPane.showMessageDialog(null, "No hay experimento para guardar");
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica();
            }
        });
    }
}