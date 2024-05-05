package Interfaz_Usuario;

import Logica_de_Negocio.Experimento;
import Logica_de_Negocio.ExperimentoDialogo;
import Logica_de_Negocio.Poblacion;
import Navegador_Archivos.GestorDatos;

import javax.swing.*;

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
            String[] nombresExperimentos = GestorDatos.obtenerNombresExperimentos();
            String nombreArchivo = (String) JOptionPane.showInputDialog(null, "Selecciona un experimento:", "Abrir Experimento", JOptionPane.QUESTION_MESSAGE, null, nombresExperimentos, nombresExperimentos[0]);
            if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
                experimento = GestorDatos.cargarExperimento(nombreArchivo);
                if (experimento != null) {
                    JOptionPane.showMessageDialog(null, "Experimento cargado correctamente.");
                    new ExperimentoDialogo(experimento).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar el experimento.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: se esperaba un nombre de archivo.");
            }
        });

        btnNuevoExperimento.addActionListener(e -> {
            String nombreExperimento = JOptionPane.showInputDialog("Introduce el nombre del experimento:");
            if (nombreExperimento != null && !nombreExperimento.trim().isEmpty()) {
                experimento = new Experimento();
                experimento.setNombre(nombreExperimento);
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres añadir una población al experimento?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    String numPoblacionesStr = JOptionPane.showInputDialog("¿Cuántas poblaciones quieres agregar?");
                    if (numPoblacionesStr != null && !numPoblacionesStr.trim().isEmpty()) {
                        try {
                            int numPoblaciones = Integer.parseInt(numPoblacionesStr);
                            for (int i = 0; i < numPoblaciones; i++) {
                                Poblacion poblacion = new Poblacion();
                                // Aquí se recogen los datos de la población.
                                String nombrePoblacion = JOptionPane.showInputDialog("Introduce el nombre de la población:");
                                String fechaInicio = JOptionPane.showInputDialog("Introduce la fecha de inicio (DD/MM/AAAA):");
                                String fechaFin = JOptionPane.showInputDialog("Introduce la fecha de fin (DD/MM/AAAA):");
                                String numBacterias = JOptionPane.showInputDialog("Introduce el número de bacterias iniciales:");
                                String temperatura = JOptionPane.showInputDialog("Introduce la temperatura a la que se van a someter las bacterias:");
                                String luminosidad = JOptionPane.showInputDialog("Introduce las condiciones de luminosidad (Alta, Media, Baja):");
                                String dosisComida = JOptionPane.showInputDialog("Introduce la dosis de comida:");
                                // Aquí se asignan los datos a la población.
                                poblacion.setNombre(nombrePoblacion);
                                poblacion.setFechaInicio(fechaInicio);
                                poblacion.setFechaFin(fechaFin);
                                poblacion.setNumBacteriasIniciales(Integer.parseInt(numBacterias));
                                poblacion.setTemperatura(Double.parseDouble(temperatura));
                                poblacion.setLuminosidad(luminosidad);
                                poblacion.setDosisComida(Double.parseDouble(dosisComida));
                                // Aquí se añade la población a la lista de poblaciones del experimento.
                                experimento.getPoblaciones().add(poblacion);
                            }
                            JOptionPane.showMessageDialog(null, "Nuevo experimento creado");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: se esperaba un número");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: se esperaba un número");
                    }
                }
                String nombreArchivo = experimento.getNombre() + ".txt";
                GestorDatos.guardarExperimento(nombreArchivo, experimento);
                JOptionPane.showMessageDialog(null, "Experimento guardado");
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