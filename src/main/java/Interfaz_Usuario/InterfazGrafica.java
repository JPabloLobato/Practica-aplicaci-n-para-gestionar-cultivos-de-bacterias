package Interfaz_Usuario;

import Navegador_Datos.GestorDatos;
import Navegador_Datos.NavegadorDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private JButton btnAbrirArchivo;
    private JButton btnNuevoExperimento;
    private JButton btnAgregarPoblacion;
    private JButton btnVisualizarPoblaciones;
    private JButton btnBorrarPoblacion;
    private JButton btnVerDetalle;
    private JButton btnGuardar;
    private JButton btnGuardarComo;

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

        btnAbrirArchivo.setBounds(20, 20, 150, 30);
        btnNuevoExperimento.setBounds(20, 60, 150, 30);
        btnAgregarPoblacion.setBounds(20, 100, 150, 30);
        btnVisualizarPoblaciones.setBounds(20, 140, 150, 30);
        btnBorrarPoblacion.setBounds(20, 180, 150, 30);
        btnVerDetalle.setBounds(20, 220, 150, 30);
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
            String contenidoExperimento = GestorDatos.cargarExperimento();
            JOptionPane.showMessageDialog(null, "Experimento cargado correctamente: \n" +contenidoExperimento);
        });

        btnVisualizarPoblaciones.addActionListener(e -> {
            String nombresPoblaciones = NavegadorDatos.visualizarNombresPoblaciones();
            JOptionPane.showMessageDialog(null, nombresPoblaciones);
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