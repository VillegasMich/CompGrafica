package BresenhamsLine;
import javax.swing.JFrame;

/**
 * main
 */
public class Main {
  public static void main(String[] args) {
  /*
    Para tener en cuenta:
      Fue necesario rebajar el pixelSize a 1 para que se mantengan las dimensiones.
      Se tenia problema con el eje Y inverso, resuelto invirtiendo el valor de la y en el algoritmo.  

  */

    // Crear un nuevo Frame
    JFrame frame = new JFrame("Eventos del Mouse");
    // Al cerrar el frame, termina la ejecución de este programa
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Agregar un JPanel que se llama Points (esta clase)
    EventoMouse ev = new EventoMouse();
    frame.add(ev);
    //frame.addMouseListener(ev);
    // Asignarle tamaño
    frame.setSize(ev.width, ev.height);
    // Poner el frame en el centro de la pantalla
    frame.setLocationRelativeTo(null);
    // Mostrar el frame
    frame.setVisible(true);
  }
  
}