import java.util.InputMismatchException;
import java.util.Scanner;

import Grafo.Graph;
import Grafo.TreeMapGraph;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Menu(sc,null);
        sc.close();
    }

    /**
     * La función Menu() es una función nula que toma un objeto Scanner como
     * parámetro. Imprime un menú
     * con tres opciones y le pide al usuario que elija una de ellas. Si el usuario
     * ingresa una opción
     * válida, la función llama a la función Cargar() y luego se vuelve a llamar a
     * sí misma. Si el
     * usuario ingresa una opción no válida, la función imprime un mensaje de error
     * y se vuelve a
     * llamar a sí misma
     * 
     * @param sc Objeto de escáner
     */
    private static void Menu(Scanner sc,Graph g) {
        System.out.println("1. Cargar datos\n2. Conexión entre dos personajes\n3. Formar equipo entre dos personajes\n4. Salir");
        System.out.println("Introduce una opcion:");
        try {
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println(opcion);
                    Graph grafo=Cargar();
                    Menu(sc,grafo);
                    break;
                case 2:
                    System.out.println(opcion);
                    
                    Menu(sc,g);
                    break;
                case 3:
                    System.out.println(opcion);
                    
                    Menu(sc,g);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion no valida: " + opcion);
                    Menu(sc,g);
            }
        } catch (InputMismatchException e) {
            System.err.println(e);
            sc.next();
            Menu(sc,g);
        }
    }

    private static Graph Cargar() {
        Graph g = new TreeMapGraph<>();
        mostarMayor(g);
        mostarMenor(g);
        return g;
    }

    /**
     * > Esta función toma un gráfico como entrada e imprime el nombre del nodo con
     * más conexiones
     * 
     * @param g El gráfo a analizar.
     */
    private static void mostarMayor(Graph g) {

    }

    /**
     * > Esta función toma un gráfico e imprime el nombre del vértice con el menor
     * número de conexiones
     * 
     * @param g El grafo en el que se busca.
     */
    private static void mostarMenor(Graph g) {

    }

    /**
     * Muestra el camnio mas corto entre dos vertices(personajes introducidos por el
     * usuario) del grafo
     * 
     * @param grafo
     * @param sc
     */
    private static void Camino(Graph grafo, Scanner sc) {

    }

    /**
     * El usuario introduce dos personajes por teclado.
     * Devuelve la lista de personajes que relacionan al primero con el segundo con
     * un menor peso en cada relacion
     * El peso de cada relacion debe ser menor que 10 en caso contrario se finaliza
     * el proceso
     * mostrando al usuario la imposiblidad de formar equipo
     * 
     * @param grafo
     * @param sc
     */
    private static void diseñarEquipo(Graph grafo, Scanner sc) {

    }

    /**
     * Toma un objeto Scanner y un objeto String como parámetros, y devuelve un
     * objeto String
     * 
     * @param sc      Objeto de escáner
     * @param mensaje El mensaje que se mostrará al usuario.
     * @return El método está devolviendo el valor de la variable "mensaje"
     */
    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }
}
