package Logica_de_Negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NavegadorDatos {
    public static final String CARPETA_EXPERIMENTOS = "Experimentos/";

    public static String visualizarNombresPoblaciones() {
        File carpetaExperimentos = new File(CARPETA_EXPERIMENTOS);
        File[] listaArchivos = carpetaExperimentos.listFiles();

        if (listaArchivos == null || listaArchivos.length == 0) {
            return "No hay ningún experimento en la carpeta. ";
        }

        StringBuilder nombresPoblaciones = new StringBuilder("Nombres de las poblaciones:\n");

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

        return nombresPoblaciones.toString();
    }
}