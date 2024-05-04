package Logica_de_Negocio;

import java.util.ArrayList;
import java.util.List;

public class Experimento {
    private String nombre;
    private final List<Poblacion> poblaciones;

    public Experimento() {
        this.poblaciones = new ArrayList<>();
    }

    // Getters and setters for all variables
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Poblacion> getPoblaciones() {
        return poblaciones;
    }
}