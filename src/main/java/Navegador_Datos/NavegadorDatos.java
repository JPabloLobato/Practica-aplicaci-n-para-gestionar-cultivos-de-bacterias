package Navegador_Datos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NavegadorDatos {
    public static final String CARPETA_EXPERIMENTOS = "Experimentos/";

    public static void visualizarNombresPoblaciones() {
        File carpetaExperimentos = new File(CARPETA_EXPERIMENTOS);
        File[] listaArchivos = carpetaExperimentos.listFiles();
        StringBuilder nombresPoblaciones = new StringBuilder("Nombres de las poblaciones:\n");

        if (listaArchivos != null) {
            for (File archivo : listaArchivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".txt")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            if (linea.startsWith("Población:")) {
                                nombresPoblaciones.append(linea.substring(10)).append("\n");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, nombresPoblaciones.toString());
    }
}