package Navegador_Datos;

import javax.swing.*;
import java.io.*;

public class GestorDatos {
    private static final String CARPETA_EXPERIMENTOS = "Experimentos/";
    public static void guardarExperimento(String nombreArchivo, String contenido) {
        // Crear la carpeta Experimentos si no existe
        File carpetaExperimentos = new File(CARPETA_EXPERIMENTOS);
        if (!carpetaExperimentos.exists()) {
            carpetaExperimentos.mkdirs();
        }

        // Obtener la ruta del archivo donde se guardará el experimento
        File archivo = new File(CARPETA_EXPERIMENTOS + nombreArchivo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(contenido);
            JOptionPane.showMessageDialog(null, "Experimento guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el experimento.");
        }
    }

    public static void guardarExperimentoComo(String contenido) {
        // Crear la carpeta Experimentos si no existe
        File carpetaExperimentos = new File(CARPETA_EXPERIMENTOS);
        if (!carpetaExperimentos.exists()) {
            carpetaExperimentos.mkdirs();
        }

        // Obtener la ruta y el nombre del archivo donde se guardará el experimento
        JFileChooser fileChooser = new JFileChooser(CARPETA_EXPERIMENTOS);
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                writer.write(contenido);
                JOptionPane.showMessageDialog(null, "Experimento guardado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar el experimento.");
            }
        }
    }

    public static String cargarExperimento() {
        JFileChooser fileChooser = new JFileChooser(CARPETA_EXPERIMENTOS);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Experimento cargado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar el experimento.");
            }
            return contenido.toString();
        }
        return null;
    }

}
