import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import Grafo.*;

public class Principal {
    public static void main(String[] args) throws Exception {

        // Cargando los datos del archivo e imprimiendo el ID de cada vértice.
        Graph<Personaje, Integer> ge = cargarDatosPruebas();
        // Graph<Personaje,Integer> g=Cargar();
        Scanner sc = new Scanner(System.in);
        Menu(sc, ge);
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
                "1. Mostrar datos(Modificar texto opcion)\n2. Conexión entre dos personajes\n3. Formar equipo entre dos personajes\n4. Salir");
        System.out.println("Introduce una opcion:");
        try {
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    MostrarDatos(g);
                    Menu(sc, g);
                    break;
                case 2:
                    // System.out.println(opcion);
                    List<Vertex<Personaje>> vertices = iterableToList(g.getVertices());
                    vertices.forEach(a -> System.out.println(a.getID()));
                    sc = new Scanner(System.in);
                    Vertex<Personaje> inicio = obtenerPersonaje(vertices, sc);
                    Vertex<Personaje> fin = obtenerPersonaje(vertices, sc);

                    camino(g, inicio, fin);
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

    private static Vertex<Personaje> obtenerPersonaje(List<Vertex<Personaje>> vertices, Scanner sc) {
        // sc.next();
        String nombre = leerTexto(sc, "Elige un personaje:");
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
    private static Graph<Personaje, Integer> Cargar() {
        Graph<Personaje, Integer> g = new TreeMapGraph<>();

        return g;
    }

    /**
     * Leer fichero. Este método lee el archivo y crea un gráfico con los nombres
     * como vértice y el peso.
     */
    /*
     * public static void leerFichero() {
     * String linea;
     * boolean seguir = true;
     * Scanner entrada = null;
     * Personaje p1;
     * Personaje p2;
     * Interaccion i1;
     * 
     * try {
     * entrada = new Scanner(new FileReader("marvel-unimodal-edges.csv"));
     * } catch(FileNotFoundException fne) {
     * System.out.println("No se ha podido leer el archivo");
     * seguir = false;
     * }
     * 
     * entrada.nextLine(); // Salta la primera línea
     * if(seguir) {
     * while(entrada.hasNextLine()) {
     * linea = entrada.nextLine();
     * StringTokenizer to = new StringTokenizer(linea, ",");
     * p1 = new Personaje<String>(to.nextToken());
     * p2 = new Personaje<String>(to.nextToken());
     * i1 = new Interaccion<Integer>(p1.getID()+"_"+p2.getID(),
     * Integer.parseInt(to.nextToken()));
     * DecoratedElement<Personaje<String>> d1 = new
     * DecoratedElement<Personaje<String>>(p1.getID(), p1);
     * DecoratedElement<Personaje<String>> d2 = new
     * DecoratedElement<Personaje<String>>(p2.getID(), p2);
     * 
     * t.insertEdge(d1, d2, i1);
     * 
     * if(to.hasMoreTokens())
     * to.nextToken();
     * 
     * }
     * entrada.close();
     * }
     * 
     * }
     */

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
        List<Vertex<Personaje>> verticesTotales = iterableToList(graph.getVertices());
        Personaje[] camino = new Personaje[graph.getN()];
        for (int i = 0; i < camino.length; i++)
            camino[i] = null;
        queue.add(inicio);
        boolean acabar = false;
        do {
            Vertex<Personaje> v = queue.poll();
            Iterator<Edge<Integer>> vertices = graph.incidentEdges(v);
            while (vertices.hasNext() && !acabar) {
                Vertex<Personaje> adVertex = graph.opposite(v, vertices.next());
                try {
                    acabar = adVertex.equals(fin);
                    if (camino[verticesTotales.indexOf(adVertex)] == null) {
                        queue.add(adVertex);
                        camino[verticesTotales.indexOf(adVertex)] = v.getElement();
                    }
                } catch (NullPointerException e) {
                }
            }
        } while (camino[verticesTotales.indexOf(fin)] == null && !queue.isEmpty() && !acabar);
        if (acabar) {
            recorreCamino(graph,1, camino, inicio.getElement(), fin.getElement());
            System.out.println(fin.getID());
        } else
            System.out.println("no se pudo encontrar un camino entre los dos personajes");
    }

    /**
     * Imprime recursivamente la ruta desde el primer vértice hasta el penúltimo
     * vértice.
     * 
     * @param graph   La gráfica
     * @param camino  La matriz de objetos Personaje que contiene la ruta.
     * @param primero El primer vértice del camino.
     * @param vertice El vértice al que queremos encontrar el camino más corto.
     */
    private static void recorreCamino(Graph<Personaje, Integer> graph,int distancia, Personaje[] camino, Personaje primero,
            Personaje fin) {

        Personaje vertice = camino[iterableToList(graph.getVertices())
                .indexOf(iterableToList(graph.getVertices()).stream()
                        .filter(e -> e.getID().equals(fin.getID())).findFirst().get())];
        if (!vertice.getID().equals(primero.getID()))
            recorreCamino(graph,distancia+1, camino, primero, vertice);
        else  System.out.printf("El camino más corto con una distacia de %d es:\n", distancia);
        System.out.print(vertice.getID() + " ");
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

    // #region pruebas
    private static Graph<Personaje, Integer> cargarDatosPruebas() {
        Graph<Personaje, Integer> grafo = new TreeMapGraph<Personaje, Integer>();
        Personaje[] personajes = new Personaje[10];
        //Personaje pers = new Personaje("l");
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
