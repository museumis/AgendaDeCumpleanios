package ejer29_t12_agendaclumpleaniosserializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ismael Martín Ramírez
 * [imartinr01@informatica.iesvalledeljerteplasencia.es]
 */
public class Ejer29_t12_AgendaClumpleaniosSerializable {

    public static void flujoEscritura() {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File fichero = new File("AgendaCumpleaños.ob");

        try {

            fos = new FileOutputStream(fichero);
            oos = new ObjectOutputStream(fos);
            escribirFichero(oos);

        } catch (FileNotFoundException ex) {
            System.out.println("Error en la creación del fichero en la parte incial.");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error en la creación del fichero en la parte final.");
            System.out.println(ex.getMessage());
        } finally {

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.out.println("Error cerrar el fichero en su parte final.");
                    System.out.println(ex.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero en su parte inicial.");
                    System.out.println(ex.getMessage());
                }
            }

        }

    }

    public static void escribirFichero(ObjectOutputStream oos) throws IOException {

        LocalDate fecha;
        String nombre;
        Agenda cumpleaños;

        do {

            nombre = pedirTexto("Nombre del cumpleañero: ");
            fecha = LocalDate.of(pedirEntero("Año de nacimiento: "), pedirEntero("Mes de nacimiento: "), pedirEntero("Día de nacimiento: "));
            cumpleaños = new Agenda(nombre, fecha);
            
            oos.writeObject(cumpleaños);

        } while (pedirTexto("¿Desea introducir mas nombre?").equalsIgnoreCase("Si"));
    }

    public static void flujoLectura() {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("AgendaCumpleaños.ob");
            ois = new ObjectInputStream(fis);
            leerFichero(ois);
        } catch (FileNotFoundException ex) {
            System.out.println("Error al leer el fichero");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Fin de lectura");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al leer el fichero");
            System.out.println(ex.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero");
                    System.out.println(ex.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero");
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

    public static void leerFichero(ObjectInputStream ois) throws IOException, ClassNotFoundException {

        Agenda cumpleaños;
        LocalDate hoy = LocalDate.now();
        LocalDate fecha;
        long edad;

        while (true) {

            cumpleaños = (Agenda) ois.readObject();
            fecha = cumpleaños.getFecha();
            edad = ChronoUnit.YEARS.between(fecha, hoy);
            System.out.println(cumpleaños.getNombre() + " cumple años el " + cumpleaños.getFecha().getDayOfMonth() + "/" + cumpleaños.getFecha().getMonth() + "/" + cumpleaños.getFecha().getYear()
                    + " por lo tanto cumplirá " + (edad + 1) + " años");
           

        }

    }

    public static String pedirTexto(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.print(texto);
        return entrada.nextLine();
    }

    public static int pedirEntero(String texto) {
        Scanner entrada = new Scanner(System.in);
        System.out.print(texto);
        return entrada.nextInt();
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int opcion;

        System.out.println("Agenda Cumpleaños");

        do {
            System.out.println("-------------------------");
            System.out.println("1.Añadir cumpleaños.");
            System.out.println("2.Ver cumpleaños.");
            System.out.println("3.Salir.");

            System.out.print("Opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1: {
                    flujoEscritura();
                    break;
                }
                case 2: {
                    flujoLectura();
                    break;
                }
                case 3: {
                    break;
                }

                default: {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (opcion != 3);
    }

}
