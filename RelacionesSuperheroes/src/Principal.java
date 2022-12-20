import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import Grafo.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Cargando los datos del archivo e imprimiendo el ID de cada vértice.
       // Scanner file = new Scanner(new File("marvel-unimodal-edges.csv"));
        //Graph<Personaje, Integer> ge = cargarDatosPruebas();
        Graph<Personaje, Integer> ge=Cargar();
        Menu(ge);
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
    private static void Menu(Graph<Personaje, Integer> g) {
        System.out.println(
                "1. Mostrar datos de los personajes\n2. Conexión entre dos personajes\n3. Formar equipo entre dos personajes\n4. Salir");
        System.out.println("Introduce una opcion:");
        Scanner sc = new Scanner(System.in);
        try {
            int opcion = sc.nextInt();
            List<Vertex<Personaje>> vertices = iterableToList(g.getVertices());
            switch (opcion) {
                case 1:
                    MostrarDatos(g);
                    Menu(g);
                    break;
                case 2:
                    vertices.forEach(a -> System.out.println(a.getID()));
                    sc = new Scanner(System.in);
                    Vertex<Personaje> inicio = obtenerPersonaje(vertices, sc);
                    Vertex<Personaje> fin = obtenerPersonaje(vertices, sc);
                    camino(g, inicio, fin);
                    Menu(g);
                    break;
                case 3:
                    vertices.forEach(a -> System.out.println(a.getID()));
                    sc = new Scanner(System.in);
                    inicio = obtenerPersonaje(vertices, sc);
                    fin = obtenerPersonaje(vertices, sc);
                    equipoVertices(g, inicio, fin);
                    if (fin.getElement().getParent() != null) {
                        recorreCamino(inicio.getElement(), fin.getElement());
                        System.out.println("");
                    } else
                        System.out.println("no se pudo hacer un equipo entre " + inicio.getID() + " y " + fin.getID());
                    Menu(g);
                    break;
                case 4:
                    System.out.println("Hasta la vista");
                    sc.close();
                    break;
                default:
                    System.out.println("Opcion no valida: " + opcion);
            }
        } catch (InputMismatchException e) {
            //System.err.println(e);
            System.out.println("Opcion no valida: ");
            Menu(g);

        }
    }

    private static Vertex<Personaje> obtenerPersonaje(List<Vertex<Personaje>> vertices, Scanner sc) {
        System.out.println("Elige un personaje:");
        String nombre = sc.nextLine();
        if (nombre.equals("") || vertices.stream().filter(a -> a.getID().equals(nombre)).count() == 0)
            return obtenerPersonaje(vertices, sc);
        else
            return vertices.stream().filter(a -> a.getID().equals(nombre)).findFirst().get();
    }

    /**
     * Crea un nuevo gráfico, imprime el número de vértices y aristas, y luego
     * devuelve el gráfico
     * 
     * @return Un gráfico con los vértices y las aristas del gráfico.
     */
    public static Graph<Personaje,Integer> Cargar() throws IOException { // Metodo lectura CSV
            /**
             * Primero, leeremos los registros por línea usando readLine() en BufferReader.
             * A continuación, dividiremos la linea en tokens según el delimitador coma.
             */

             Graph<Personaje, Integer> g = new TreeMapGraph<Personaje,Integer>();
            try {
               // FileReader ficheroCSV = new FileReader("marvel-unimodal-edges.csv"); // Ruta en el sistema del fichero de datos
               Scanner lector = new Scanner(new File("marvel-unimodal-edges.csv")); // Buffer de entrada
                String linea = lector.nextLine(); // Inicio de la lectura del fichero
                do {
                    linea = lector.nextLine();
                    String[] splitted = linea.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                    g.insertEdge(new Personaje(splitted[0]),new Personaje(splitted[1]),Integer.valueOf(splitted[2]));
                } while (lector.hasNextLine());
                lector.close();
                return g; // Devolvemos el array con todos los heroes encontrados y sus datos.
            } catch (IOException ex) {
                //FicheroNoEncontrado();
                System.exit(1);
                return null;
            } 
    }

    private static void MostrarDatos(Graph<Personaje, Integer> g) {
        System.out.println("Numero de personajes:" + g.getN());
        System.out.println("Numeros de relaciones:" + g.getM());
        mostarMayor(g);
        mostarMenor(g);
    }

    /**
     * > Esta función toma un gráfico como entrada e imprime el nombre del Personaje
     * con
     * más conexiones
     * 
     * @param g El gráfo a analizar.
     */
    private static void mostarMayor(Graph<Personaje, Integer> g) {
        List<Vertex<Personaje>> actualList = iterableToList(g.getVertices());
        try {
            Vertex<Personaje> c = Collections.max(actualList, (((a,
                    b) -> (iterableToList2(g.incidentEdges(a)).size() - iterableToList2(g.incidentEdges(b)).size()))));
            System.out.println("El personaje mas sociable es:");
            actualList.forEach((a) -> {
                if (iterableToList2(g.incidentEdges(a)).size() == iterableToList2(g.incidentEdges(c)).size())
                    System.out.println(a.getElement().getID());
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
    private static void mostarMenor(Graph<Personaje, Integer> g) {
        Iterable<Vertex<Personaje>> iterable = () -> g.getVertices();
        List<Vertex<Personaje>> actualList = StreamSupport.stream((iterable).spliterator(), false)
                .collect(Collectors.toList());
        try {
            Vertex<Personaje> c = Collections.min(actualList, ((a, b) -> iterableToList2(g.incidentEdges(a)).size()
                    - iterableToList2(g.incidentEdges(b)).size()));
            System.out.println("El personaje menos socialble es:");
            actualList.forEach((a) -> {
                if (iterableToList2(g.incidentEdges(a)).size() == iterableToList2(g.incidentEdges(c)).size())
                    System.out.println(a.getElement().getID());
                else
                    return;
            });
        } catch (NoSuchElementException e) {
            System.out.println("NO hay elementos en el grafo");
        }
    }

    /**
     * Toma un gráfico, un vértice inicial y un vértice final, e imprime la ruta más
     * corta desde el
     * vértice inicial hasta el vértice final.
     * 
     * @param graph  La gráfica
     * @param inicio El vértice inicial
     * @param fin    El vértice al que queremos llegar
     */
    private static void camino(Graph<Personaje, Integer> graph, Vertex<Personaje> inicio, Vertex<Personaje> fin) {
        LinkedList<Vertex<Personaje>> queue = new LinkedList<Vertex<Personaje>>();
        queue.add(inicio);
        boolean acabar = false;
        do {
            Vertex<Personaje> v = queue.poll();
            Iterator<Edge<Integer>> vertices = graph.incidentEdges(v);
            while (vertices.hasNext() && !acabar) {
                Vertex<Personaje> adVertex = graph.opposite(v, vertices.next());
                try {
                    acabar = adVertex.equals(fin);
                    if (adVertex.getElement().getParent() == null) {
                        queue.add(adVertex);
                        adVertex.getElement().setParent(v.getElement());
                    }
                } catch (NullPointerException e) {
                }
            }
        } while (fin.getElement().getParent() == null && !queue.isEmpty() && !acabar);
        if (acabar) {
            System.out.print(inicio.getID());
            recorreCamino(inicio.getElement(), fin.getElement());
            System.out.println(); 
        } else
            System.out.println("no se pudo encontrar un camino entre los dos personajes");
    }

    /**
     * Es una función recursiva que toma un gráfico, un vértice inicial y un vértice
     * final y devuelve
     * el vértice inicial
     * 
     * @param graph  El gráfico con el que estamos trabajando
     * @param inicio El vértice inicial
     * @param fin    El vértice al que queremos llegar
     * @return El método devuelve el vértice que es el punto inicial del gráfico.
     */
    private static Vertex<Personaje> equipoVertices(Graph<Personaje, Integer> graph, Vertex<Personaje> inicio,
            Vertex<Personaje> fin) {
        Edge<Integer> e = null;
        inicio.getElement().setVisited(true);
        Iterator<Edge<Integer>> it = graph.incidentEdges(inicio);
        Vertex<Personaje> nodoAux = null;
        while (it.hasNext() && !(inicio.equals(fin))) {
            e = it.next();
            nodoAux = graph.opposite(inicio, e);
            if (!nodoAux.getElement().isVisited() && e.getElement() < 10) {
                nodoAux.getElement().setParent(inicio.getElement()); // Declaramos a como el padre del nuevo nodo
                equipoVertices(graph, nodoAux, fin); // Pasamos el nuevo nodo a una llamada recursiva, para explorar a
                                                     // traves de este, ya que si no lo hacemos asi­, exploramos la
                                                     // siguiente arista de a y eso serÃa una busqueda en anchura
            }
            // Si esta visitado pero el coste con este camino es mejor (porque hemos llegado
            // desde un nuevo camino con un coste menor)
        }
        return inicio;
    }

    /**
     * Imprime la ruta desde el nodo inicial hasta el nodo final.
     * 
     * @param inicio El nodo inicial
     * @param fin    El nodo final
     */
    private static void recorreCamino(Personaje inicio, Personaje fin) {
        if (!fin.equals(inicio)){
            recorreCamino(inicio, fin.getParent());
        System.out.print(" -> "+fin.getID());
        }
    }
    // #region auxiliar

    /**
     * Toma un iterador y devuelve una lista.
     * 
     * @param iterator el iterador a convertir en una lista
     * @return Una lista de vértices.
     */
    private static List<Vertex<Personaje>> iterableToList(Iterator<Vertex<Personaje>> iterator) {
        List<Vertex<Personaje>> list = new ArrayList<>();
        while (iterator.hasNext())
            list.add(iterator.next());
        return list;
    }

    /**
     * Toma un iterador y devuelve una lista del mismo tipo
     * 
     * @param iterator el iterador para convertir a una lista
     * @return Una lista de bordes.
     */
    private static List<Edge<Integer>> iterableToList2(Iterator<Edge<Integer>> iterator) {
        List<Edge<Integer>> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    // #endregion

    // #region pruebas
    private static Graph<Personaje, Integer> cargarDatosPruebas() {
        Graph<Personaje, Integer> grafo = new TreeMapGraph<Personaje, Integer>();
        Personaje[] personajes = new Personaje[10];
        // Personaje pers = new Personaje("l");
        for (int i = 0; i < 10; i++)
            personajes[i] = new Personaje("" + (char) ('a' + i));
        grafo.insertEdge(personajes[0], personajes[1], 2);
        grafo.insertEdge(personajes[0], personajes[2], 8);
        grafo.insertEdge(personajes[0], personajes[3], 24);
        grafo.insertEdge(personajes[0], personajes[4], 12);
        grafo.insertEdge(personajes[2], personajes[5], 6);
        grafo.insertEdge(personajes[4], personajes[5], 4);
        grafo.insertEdge(personajes[5], personajes[6], 3);
        grafo.insertEdge(personajes[6], personajes[7], 5);
        grafo.insertEdge(personajes[6], personajes[8], 35);
        grafo.insertEdge(personajes[8], personajes[9], 7);
        return grafo;
    }
    // #endregion
}
