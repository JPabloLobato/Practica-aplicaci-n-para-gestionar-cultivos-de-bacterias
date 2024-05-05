package Navegador_Archivos;

import com.google.gson.Gson;
import Logica_de_Negocio.Experimento;
import java.io.*;

public class GestorDatos {
    private static final String CARPETA_EXPERIMENTOS = "experimento/";
    private static final Gson GSON = new Gson();

    public static void guardarExperimento(String nombreArchivo, Experimento experimento) {
        try {
            FileWriter fileWriter = new FileWriter("Experimentos/" + nombreArchivo);
            GSON.toJson(experimento, fileWriter);
            fileWriter.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Experimento cargarExperimento(String nombreArchivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Experimentos/" + nombreArchivo));
            Experimento experimento = GSON.fromJson(reader, Experimento.class);
            reader.close();
            return experimento;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    public static String[] obtenerNombresExperimentos() {
        File folder = new File("Experimentos/");
        return folder.list((dir, name) -> name.endsWith(".txt"));
    }
}