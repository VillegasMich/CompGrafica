package RotacionesTeclado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) throws IOException{
    FileReader fileReader = new FileReader("./RotacionesTeclado/casita.txt");
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    // Read the lines of the file
    List<String> lines = new ArrayList<>();
    String line = bufferedReader.readLine();
    while (line != null) {
        lines.add(line);
        line = bufferedReader.readLine();
    }
    // Close the file
    bufferedReader.close();
    // Convert each line into an array of two numbers
    Double[][] numbers = new Double[lines.size()][lines.size()];
    for (int i = 0; i < lines.size(); i++) {
        String[] parts = lines.get(i).split(" ");
        if(parts.length >= 2){
            Double[] x = new Double[]{Double.parseDouble(parts[0]), Double.parseDouble(parts[1])};
            numbers[i] = x;
        }else{
            Double[] x = new Double[]{Double.parseDouble(parts[0])};
            numbers[i] = x;
        }
    }
    

    // Crear un nuevo Frame
    JFrame frame = new JFrame("Figura");
    // Al cerrar el frame, termina la ejecución de este programa
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Agregar un JPanel que se llama Points (esta clase)
    PlanoTeclado plano = new PlanoTeclado(numbers);
    frame.add(plano);
    // Asignarle tamaño
    frame.setSize(plano.width, plano.height);
    // Poner el frame en el centro de la pantalla
    frame.setLocationRelativeTo(null);
    // Mostrar el frame
    frame.setVisible(true);
    
  }
}
