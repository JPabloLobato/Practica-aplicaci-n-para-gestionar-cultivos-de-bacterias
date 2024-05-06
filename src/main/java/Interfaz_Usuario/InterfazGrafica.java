package Interfaz_Usuario;

import Logica_de_Negocio.*;
import Navegador_Archivos.GestorDatos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private JButton btnAbrirArchivo;
    private JButton btnNuevoExperimento;
    private JButton btnAgregarPoblacion;
    private JButton btnVisualizarPoblaciones;
    private JButton btnBorrarPoblacion;
    private JButton btnVerDetalle;
    private Experimento experimento;

    private Poblacion recogerDatosPoblacion() {
        Poblacion poblacion = new Poblacion();
        String nombrePoblacion = JOptionPane.showInputDialog("Introduce el nombre de la población:");
        String fechaInicio = JOptionPane.showInputDialog("Introduce la fecha de inicio (DD/MM/AAAA):");
        String fechaFin = JOptionPane.showInputDialog("Introduce la fecha de fin, 30 días más al del inicio (DD/MM/AAAA):");
        String numBacterias = JOptionPane.showInputDialog("Introduce el número de bacterias iniciales:");
        String temperatura = JOptionPane.showInputDialog("Introduce la temperatura a la que se van a someter las bacterias:");
        String luminosidad = JOptionPane.showInputDialog("Introduce las condiciones de luminosidad (Alta, Media, Baja):");
        String comidaInicial = JOptionPane.showInputDialog("Introduce la cantidad inicial de comida, la comida debe ser menor a 300:");
        String diaIncrementoComida = JOptionPane.showInputDialog("Introduce el día hasta el cual se debe incrementar la cantidad de comida:");

        String comidaDiaIncrementoStr;
        int comidaDiaIncremento;
        do {
            comidaDiaIncrementoStr = JOptionPane.showInputDialog("Introduce el incremento de comida diario:");
            try {
                comidaDiaIncremento = Integer.parseInt(comidaDiaIncrementoStr);
                if (comidaDiaIncremento > 300) {
                    JOptionPane.showMessageDialog(null, "El incremento de comida diario no debe ser mayor a 300. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un número entero válido. Inténtalo de nuevo.");
                comidaDiaIncremento = 301; // Set to a value greater than 300 to continue the loop
            }
        } while (comidaDiaIncremento > 300);

        String comidaFinal = JOptionPane.showInputDialog("Introduce la cantidad final de comida en el día 30:");

        poblacion.setNombre(nombrePoblacion);
        poblacion.setFechaInicio(fechaInicio);
        poblacion.setFechaFin(fechaFin);
        poblacion.setNumBacteriasIniciales(Integer.parseInt(numBacterias));
        poblacion.setTemperatura(Double.parseDouble(temperatura));
        poblacion.setLuminosidad(luminosidad);
        poblacion.setComidaInicial(Integer.parseInt(comidaInicial));
        poblacion.setDiaIncrementoComida(Integer.parseInt(diaIncrementoComida));
        poblacion.setComidaDiaIncremento(comidaDiaIncremento);
        poblacion.setComidaFinal(Integer.parseInt(comidaFinal));

        return poblacion;
    }

    public InterfazGrafica() {
        initComponents();
        setLayout(null);
        setSize(200,300);
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

        btnAbrirArchivo.setBounds(20, 20, 150, 35);
        btnNuevoExperimento.setBounds(20, 60, 150, 30);
        btnAgregarPoblacion.setBounds(20, 100, 150, 30);
        btnVisualizarPoblaciones.setBounds(20, 140, 150, 35);
        btnBorrarPoblacion.setBounds(20, 180, 150, 30);
        btnVerDetalle.setBounds(20, 220, 150, 35);

        add(btnAbrirArchivo);
        add(btnNuevoExperimento);
        add(btnAgregarPoblacion);
        add(btnVisualizarPoblaciones);
        add(btnBorrarPoblacion);
        add(btnVerDetalle);

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

        /* Como el método recogerDatosPoblacion() es muy largo, y iba a repetirlo en btnNuevoExperimento y
        btnAgregarPoblacion, se puede extraer a un método privado y reutilizarlo en ambos botones. */
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
                                Poblacion poblacion = recogerDatosPoblacion(); // Cada llamada a recogerDatosPoblacion() crea una nueva instancia de Poblacion
                                experimento.getPoblaciones().add(poblacion);
                            }
                            JOptionPane.showMessageDialog(null, "Nuevo experimento creado.");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: se esperaba un número.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: se esperaba un número.");
                    }
                }
                String nombreArchivo = experimento.getNombre() + ".txt";
                GestorDatos.guardarExperimento(nombreArchivo, experimento);
                JOptionPane.showMessageDialog(null, "Experimento guardado.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: se esperaba un nombre de experimento.");
            }
        });

        btnAgregarPoblacion.addActionListener(e -> {
            String[] nombresExperimentos = GestorDatos.obtenerNombresExperimentos();
            String nombreArchivo = (String) JOptionPane.showInputDialog(null, "Selecciona un experimento:", "Abrir Experimento", JOptionPane.QUESTION_MESSAGE, null, nombresExperimentos, nombresExperimentos[0]);
            if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
                experimento = GestorDatos.cargarExperimento(nombreArchivo);
                if (experimento != null) {
                    Poblacion poblacion = recogerDatosPoblacion();
                    experimento.getPoblaciones().add(poblacion);
                    GestorDatos.guardarExperimento(nombreArchivo, experimento);
                    JOptionPane.showMessageDialog(null, "Población agregada al experimento.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar el experimento.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: se esperaba un nombre de archivo.");
            }
        });

        btnVisualizarPoblaciones.addActionListener(e -> {
            String[] nombresExperimentos = GestorDatos.obtenerNombresExperimentos();
            String nombreArchivo = (String) JOptionPane.showInputDialog(null, "Selecciona un experimento:", "Abrir Experimento", JOptionPane.QUESTION_MESSAGE, null, nombresExperimentos, nombresExperimentos[0]);
            if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
                experimento = GestorDatos.cargarExperimento(nombreArchivo);
                if (experimento != null) {
                    if (!experimento.getPoblaciones().isEmpty()) {
                        StringBuilder nombresPoblaciones = new StringBuilder("Nombres de las poblaciones:\n");
                        for (Poblacion poblacion : experimento.getPoblaciones()) {
                            nombresPoblaciones.append(poblacion.getNombre()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, nombresPoblaciones.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay poblaciones para visualizar.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar el experimento.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: se esperaba un nombre de archivo.");
            }
        });

        btnBorrarPoblacion.addActionListener(e -> {
            String[] nombresExperimentos = GestorDatos.obtenerNombresExperimentos();
            String nombreArchivo = (String) JOptionPane.showInputDialog(null, "Selecciona un experimento:", "Abrir Experimento", JOptionPane.QUESTION_MESSAGE, null, nombresExperimentos, nombresExperimentos[0]);
            if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
                experimento = GestorDatos.cargarExperimento(nombreArchivo);
                if (experimento != null) {
                    if (!experimento.getPoblaciones().isEmpty()) {
                        String[] nombresPoblaciones = experimento.getPoblaciones().stream().map(Poblacion::getNombre).toArray(String[]::new);
                        String nombrePoblacion = (String) JOptionPane.showInputDialog(null, "Selecciona una población para eliminar:", "Eliminar Población", JOptionPane.QUESTION_MESSAGE, null, nombresPoblaciones, nombresPoblaciones[0]);
                        if (nombrePoblacion != null && !nombrePoblacion.trim().isEmpty()) {
                            experimento.getPoblaciones().removeIf(poblacion -> poblacion.getNombre().equals(nombrePoblacion));
                            GestorDatos.guardarExperimento(nombreArchivo, experimento);
                            JOptionPane.showMessageDialog(null, "Población eliminada del experimento.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: se esperaba un nombre de población.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay poblaciones para eliminar.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar el experimento.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: se esperaba un nombre de archivo.");
            }
        });

        btnVerDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] nombresExperimentos = GestorDatos.obtenerNombresExperimentos();
                String nombreExperimento = (String) JOptionPane.showInputDialog(null, "Selecciona un experimento:", "Abrir Experimento", JOptionPane.QUESTION_MESSAGE, null, nombresExperimentos, nombresExperimentos[0]);
                if (nombreExperimento != null) {
                    Experimento experimento = GestorDatos.cargarExperimento(nombreExperimento);
                    if (experimento != null) {
                        String[] nombresPoblaciones = experimento.getPoblaciones().stream().map(Poblacion::getNombre).toArray(String[]::new);
                        String nombrePoblacion = (String) JOptionPane.showInputDialog(null, "Selecciona una población:", "Seleccionar Población", JOptionPane.QUESTION_MESSAGE, null, nombresPoblaciones, nombresPoblaciones[0]);
                        if (nombrePoblacion != null) {
                            Poblacion poblacion = experimento.getPoblaciones().stream()
                                    .filter(p -> p.getNombre().equals(nombrePoblacion))
                                    .findFirst()
                                    .orElse(null);
                            if (poblacion != null) {
                                new PoblacionDialogo(poblacion).setVisible(true);
                                new ComidaDialogo(poblacion).setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró la población", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el experimento", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
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