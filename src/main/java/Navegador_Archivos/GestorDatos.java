package Navegador_Archivos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Logica_de_Negocio.Experimento;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;

public class GestorDatos {
    private static final String CARPETA_EXPERIMENTOS = "experimento/";
    private static final Gson GSON = new Gson();

    public static void guardarExperimento(String nombreArchivo, Experimento experimento) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Experimentos/" + nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(experimento);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void guardarExperimentoComo(Experimento experimento) {
        File carpetaExperimentos = new File(CARPETA_EXPERIMENTOS);
        if (!carpetaExperimentos.exists()) {
            carpetaExperimentos.mkdirs();
        }

        JFileChooser fileChooser = new JFileChooser(CARPETA_EXPERIMENTOS);
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                String contenido = GSON.toJson(experimento);
                writer.write(contenido);
                JOptionPane.showMessageDialog(null, "Experimento guardado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar el experimento.");
            }
        }
    }

    public static Experimento cargarExperimento() {
        JFileChooser fileChooser = new JFileChooser(CARPETA_EXPERIMENTOS);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                Type experimentoType = new TypeToken<Experimento>() {}.getType();
                Experimento experimento = GSON.fromJson(reader, experimentoType);
                JOptionPane.showMessageDialog(null, "Experimento cargado correctamente.");
                return experimento;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar el experimento.");
            }
        }
        return null;
    }
}