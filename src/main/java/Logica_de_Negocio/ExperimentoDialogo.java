package Logica_de_Negocio;

import javax.swing.*;
import java.awt.*;

public class ExperimentoDialogo extends JDialog {
    private final Experimento experimento;
    private JTextField txtNombre;
    private JTextField txtNombrePoblacion;
    private JTextField txtFechaInicioPoblacion;
    private JTextField txtFechaFinPoblacion;
    private JTextField txtNumBacteriasInicialesPoblacion;
    private JTextField txtTemperaturaPoblacion;
    private JTextField txtLuminosidadPoblacion;
    private JTextField txtComidaInicialPoblacion;
    private JTextField txtDiaIncrementoComidaPoblacion;
    private JTextField txtComidaDiaIncrementoPoblacion;
    private JTextField txtComidaFinalPoblacion;

    public ExperimentoDialogo(Experimento experimento) {
        this.experimento = experimento;
        setTitle("Información del Experimento");
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));
        setLocationRelativeTo(null);
        setModal(true);
        DialogoExperimentoCompleto();
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            experimento.setNombre(txtNombre.getText());
            Poblacion poblacion = experimento.getPoblaciones().getFirst();
            poblacion.setNombre(txtNombrePoblacion.getText());
            poblacion.setFechaInicio(txtFechaInicioPoblacion.getText());
            poblacion.setFechaFin(txtFechaFinPoblacion.getText());
            poblacion.setNumBacteriasIniciales(Integer.parseInt(txtNumBacteriasInicialesPoblacion.getText()));
            poblacion.setTemperatura(Double.parseDouble(txtTemperaturaPoblacion.getText()));
            poblacion.setLuminosidad(txtLuminosidadPoblacion.getText());
            poblacion.setComidaInicial(Integer.parseInt(txtComidaInicialPoblacion.getText()));
            poblacion.setDiaIncrementoComida(Integer.parseInt(txtDiaIncrementoComidaPoblacion.getText()));
            poblacion.setComidaDiaIncremento(Integer.parseInt(txtComidaDiaIncrementoPoblacion.getText()));
            poblacion.setComidaFinal(Integer.parseInt(txtComidaFinalPoblacion.getText()));
            Navegador_Archivos.GestorDatos.guardarExperimento(experimento.getNombre() + ".txt", experimento);
            JOptionPane.showMessageDialog(null, "Experimento guardado.");
        });
        add(btnGuardar);
    }

    private void DialogoExperimentoCompleto() {
        add(new JLabel("Nombre del Experimento: "));
        txtNombre = new JTextField(experimento.getNombre());
        add(txtNombre);
        for (Poblacion poblacion : experimento.getPoblaciones()) {
            add(new JLabel("Nombre de la población: "));
            txtNombrePoblacion = new JTextField(poblacion.getNombre());
            add(txtNombrePoblacion);
            add(new JLabel("Fecha de inicio de la población: "));
            txtFechaInicioPoblacion = new JTextField(poblacion.getFechaInicio());
            add(txtFechaInicioPoblacion);
            add(new JLabel("Fecha de fin de la población: "));
            txtFechaFinPoblacion = new JTextField(poblacion.getFechaFin());
            add(txtFechaFinPoblacion);
            add(new JLabel("Número de bacterias iniciales de la población: "));
            txtNumBacteriasInicialesPoblacion = new JTextField(String.valueOf(poblacion.getNumBacteriasIniciales()));
            add(txtNumBacteriasInicialesPoblacion);
            add(new JLabel("Temperatura de la población: "));
            txtTemperaturaPoblacion = new JTextField(String.valueOf(poblacion.getTemperatura()));
            add(txtTemperaturaPoblacion);
            add(new JLabel("Luminosidad de la población: "));
            txtLuminosidadPoblacion = new JTextField(poblacion.getLuminosidad());
            add(txtLuminosidadPoblacion);
            add(new JLabel("Comida inicial de la población: "));
            txtComidaInicialPoblacion = new JTextField(String.valueOf(poblacion.getComidaInicial()));
            add(txtComidaInicialPoblacion);
            add(new JLabel("Día de incremento de comida de la población: "));
            txtDiaIncrementoComidaPoblacion = new JTextField(String.valueOf(poblacion.getDiaIncrementoComida()));
            add(txtDiaIncrementoComidaPoblacion);
            add(new JLabel("Comida en el día de incremento de la población: "));
            txtComidaDiaIncrementoPoblacion = new JTextField(String.valueOf(poblacion.getComidaDiaIncremento()));
            add(txtComidaDiaIncrementoPoblacion);
            add(new JLabel("Comida final de la población: "));
            txtComidaFinalPoblacion = new JTextField(String.valueOf(poblacion.getComidaFinal()));
            add(txtComidaFinalPoblacion);
        }
    }
}