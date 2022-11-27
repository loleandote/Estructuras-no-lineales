import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import Grafo.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Menu(sc, null);
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
    private static void Menu(Scanner sc, Graph<Personaje, Integer> g) {
        System.out.println(
                "1. Cargar datos\n2. Conexión entre dos personajes\n3. Formar equipo entre dos personajes\n4. Salir");
        System.out.println("Introduce una opcion:");
        try {
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Menu(sc, Cargar());
                    break;
                case 2:
                    System.out.println(opcion);
                    Menu(sc, g);
                    break;
                case 3:
                    System.out.println(opcion);

                    Menu(sc, g);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion no valida: " + opcion);
                    Menu(sc, g);
            }
        } catch (InputMismatchException e) {
            System.err.println(e);
            sc.next();
            Menu(sc, g);
        }
    }

    /**
     * Crea un nuevo gráfico, imprime el número de vértices y aristas, y luego devuelve el gráfico
     * 
     * @return Un gráfico con los vértices y las aristas del gráfico.
     */
    private static Graph<Personaje, Integer> Cargar() {
        Graph<Personaje, Integer> g = new TreeMapGraph<>();
        System.out.println("Nodos: "+iterableToList(g.getVertices()).size());
        System.out.println("Aristas: "+iterableToList2(g.getEdges()).size());
        mostarMayor(g);
        mostarMenor(g);
        return g;
    }

    /**
     * > Esta función toma un gráfico como entrada e imprime el nombre del Personaje con
     * más conexiones
     * 
     * @param g El gráfo a analizar.
     */
    @SuppressWarnings("unchecked")
    private static void mostarMayor(Graph<Personaje, Integer> g) {
        List<Vertex<Personaje>> actualList = iterableToList(g.getVertices());
        try {
            Vertex<Personaje> c = Collections.max(actualList, (((a,b) ->
                    (iterableToList2(g.incidentEdges(b)).size() - iterableToList2(g.incidentEdges(a)).size()))));
            System.out.println("El personaje mas socialble es:\n" + c.getElement().toString());
            actualList.forEach((a) -> {
                if (((Collection<Edge<Integer>>) g.incidentEdges(a)).size() ==
                    ((Collection<Edge<Integer>>) g.incidentEdges(c)).size())
                    System.out.println(a.getElement().toString());
                else
                    return;
            });
        } catch (NoSuchElementException e) {
            System.out.println("NO hay elementos en el grafo");
        }
    }

    /**
     * > Esta función toma un gráfico e imprime el nombre del Personaje con el menor
     * número de conexiones
     * 
     * @param g El grafo en el que se busca.
     */
    @SuppressWarnings("unchecked")
    private static void mostarMenor(Graph<Personaje, Integer> g) {
        Iterable<Vertex<Personaje>> iterable = () -> g.getVertices();
        List<Vertex<Personaje>> actualList = StreamSupport.stream((iterable).spliterator(), false)
                .collect(Collectors.toList());
        try {
            Vertex<Personaje> c = Collections.min(actualList, ((a,b) ->
                    (iterableToList2(g.incidentEdges(b)).size() - iterableToList2(g.incidentEdges(a)).size())));
            System.out.println("El personaje menos socialble es:\n" + c.getElement().toString());
            actualList.forEach((a) -> {
                if (((Collection<Edge<Integer>>) g.incidentEdges(a)).size() ==
                    ((Collection<Edge<Integer>>) g.incidentEdges(c)).size())
                    System.out.println(a.getElement().toString());
                else
                    return;
            });
        } catch (NoSuchElementException e) {
            System.out.println("NO hay elementos en el grafo");
        }
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

    // #region auxiliar

    private static List<Vertex<Personaje>> iterableToList(Iterator<Vertex<Personaje>> iterator) {
        List<Vertex<Personaje>> list = new ArrayList<>();
        while (iterator.hasNext())
            list.add(iterator.next());
        return list;
    }

    private static List<Edge<Integer>> iterableToList2(Iterator<Edge<Integer>> iterator) {
        List<Edge<Integer>> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
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

    // #endregion
}
