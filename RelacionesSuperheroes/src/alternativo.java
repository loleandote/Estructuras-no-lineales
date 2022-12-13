import java.util.*;
import Grafo.*;
public class alternativo {
    private static void mostarMayorAlt(Graph<Personaje, Integer> g) {
        List<Vertex<Personaje>> actualList = iterableToList(g.getVertices());
        try {
            Collections.sort(actualList, (((a,b) ->
                    (iterableToList2(g.incidentEdges(b)).size() - iterableToList2(g.incidentEdges(a)).size()))));
            System.out.println("El personaje mas sociable es:");
            Vertex<Personaje>c= actualList.get(0);
            actualList.forEach((a) -> {
                if (iterableToList2(g.incidentEdges(a)).size() ==
                iterableToList2(g.incidentEdges(c)).size())
                    System.out.println(a.getElement().getID());
                else
                    return;
            });
        } catch (NoSuchElementException e) {
            System.out.println("NO hay elementos en el grafo");
        }
    }
    private static void mostarMenorAlt(Graph<Personaje, Integer> g) {
        List<Vertex<Personaje>> actualList = iterableToList(g.getVertices());
        try {
            Collections.sort(actualList, (((a,b) ->
                    (iterableToList2(g.incidentEdges(a)).size() - iterableToList2(g.incidentEdges(b)).size()))));
            System.out.println("El personaje mas sociable es:");
            Vertex<Personaje>c= actualList.get(0);
            actualList.forEach((a) -> {
                if (iterableToList2(g.incidentEdges(a)).size() ==
                iterableToList2(g.incidentEdges(c)).size())
                    System.out.println(a.getElement().getID());
                else
                    return;
            });
        } catch (NoSuchElementException e) {
            System.out.println("NO hay elementos en el grafo");
        }
    }
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
}
