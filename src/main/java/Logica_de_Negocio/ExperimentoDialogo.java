package Logica_de_Negocio;

import javax.swing.*;
import java.awt.*;

public class ExperimentoDialogo extends JDialog {
    private Experimento experimento;

    public ExperimentoDialogo(Experimento experimento) {
        this.experimento = experimento;
        setTitle("Información del Experimento");
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));
        setLocationRelativeTo(null);
        setModal(true);
        initComponents();
    }

    private void initComponents() {
        add(new JLabel("Nombre del Experimento:"));
        add(new JLabel(experimento.getNombre()));
        for (Poblacion poblacion : experimento.getPoblaciones()) {
            add(new JLabel("Nombre de la Población:"));
            add(new JLabel(poblacion.getNombre()));
            add(new JLabel("Fecha de Inicio:"));
            add(new JLabel(poblacion.getFechaInicio()));
            add(new JLabel("Fecha de Fin:"));
            add(new JLabel(poblacion.getFechaFin()));
            add(new JLabel("Número de Bacterias Iniciales:"));
            add(new JLabel(String.valueOf(poblacion.getNumBacteriasIniciales())));
            add(new JLabel("Temperatura:"));
            add(new JLabel(String.valueOf(poblacion.getTemperatura()) + "°C"));
            add(new JLabel("Luminosidad:"));
            add(new JLabel(poblacion.getLuminosidad()));
            add(new JLabel("Dosis de Comida:"));
            add(new JLabel(String.valueOf(poblacion.getDosisComida())));
        }
    }
}