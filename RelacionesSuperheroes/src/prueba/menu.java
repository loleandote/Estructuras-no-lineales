package prueba;

import java.util.*;
import Grafo.Edge;
import Grafo.Graph;
import Grafo.TreeMapGraph;
import Grafo.Vertex;

public class menu {
    public static void main(String[] args) {
        Graph<elemento,Integer> g = new TreeMapGraph<>();
        elemento e= new elemento(0, "jpña");
        elemento e2= new elemento(1, "jpña2");
        elemento e3= new elemento(2, "jpña3");
        elemento e4= new elemento(3, "jpña5");
        g.insertEdge(e, e2,1);
        g.insertEdge(e, e3,2);
        g.insertEdge(e2, e4,3);
        mostarMayor(g);
        Iterator<Edge<Integer>> ga= g.getEdges();
        while (ga.hasNext()) {
            Edge<Integer> ele= ga.next();
            System.out.println(ele.getElement());
        }
    }

    @SuppressWarnings("unchecked")
    private static void mostarMayor(Graph<elemento,Integer> g) {
        List<Vertex<elemento>> actualList = iterableToList(g.getVertices());
        try {
            actualList.sort(((a,b)-> (iterableToList2(g.incidentEdges(b)).size()-iterableToList2(g.incidentEdges(a)).size())));
           for (int i=0;i< actualList.size()&&(iterableToList2(g.incidentEdges(actualList.get((0)))).size())==(iterableToList2(g.incidentEdges(actualList.get((i)))).size());)
                System.out.println(actualList.get(i++).getElement().toString());
        } catch (NoSuchElementException  e) {
           System.out.println("NO hay elementos en el grafo");
        }   
    }

    private static List<Vertex<elemento>>iterableToList(Iterator<Vertex<elemento>> iterator){
        List<Vertex<elemento>>list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }
    private static List<Edge<Integer>>iterableToList2(Iterator<Edge<Integer>> iterator){
        List<Edge<Integer>>list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }
}
