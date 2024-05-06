package Navegador_Archivos;

import com.google.gson.Gson;
import Logica_de_Negocio.Experimento;
import java.io.*;

public class GestorDatos {
    private static final String RUTA_CARPETA = "Experimentos/";

    public static void guardarExperimento(String nombreArchivo, Experimento experimento) {
        File file = new File(RUTA_CARPETA + nombreArchivo);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(new Gson().toJson(experimento));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Experimento cargarExperimento(String nombreArchivo) {
        try {
            File file = new File(RUTA_CARPETA + nombreArchivo);
            FileReader fileReader = new FileReader(file);
            return new Gson().fromJson(fileReader, Experimento.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] obtenerNombresExperimentos() {
        File folder = new File(RUTA_CARPETA);
        return folder.list((dir, name) -> name.endsWith(".txt"));
    }
}