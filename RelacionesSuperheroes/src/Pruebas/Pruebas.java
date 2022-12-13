package Pruebas;

import Grafo.Element;
import Grafo.Graph;
import Grafo.TreeMapGraph;
import Grafo.Vertex;

public class Pruebas{

    public static Graph<PersonajePrueba,Integer> cargarDatosPruebas (){
        Graph<PersonajePrueba,Integer> grafo =new TreeMapGraph<PersonajePrueba,Integer>();
        PersonajePrueba[]personajes= new PersonajePrueba[10];
        for(int i=0;i<10;i++)
            personajes[i]=new PersonajePrueba(""+(char)('a'+i));
        grafo.insertEdge(personajes[0],personajes[1], 2);
        grafo.insertEdge(personajes[0],personajes[2], 8);
        grafo.insertEdge(personajes[0],personajes[3], 24);
        grafo.insertEdge(personajes[0],personajes[4], 12);
        grafo.insertEdge(personajes[2],personajes[5], 6);
        grafo.insertEdge(personajes[4],personajes[5], 4);
        grafo.insertEdge(personajes[5],personajes[6], 3);
        grafo.insertEdge(personajes[6],personajes[7], 5);
        grafo.insertEdge(personajes[6],personajes[8], 35);
        grafo.insertEdge(personajes[8],personajes[9], 7);
        return grafo;
    }
    public static void main(String[] args) {
        Graph<PersonajePrueba,Integer> grafo =new TreeMapGraph<PersonajePrueba,Integer>();
        PersonajePrueba[]personajes= new PersonajePrueba[10];
        //char be= 'a'+1;
        for(int i=0;i<10;i++)
            personajes[i]=new PersonajePrueba(""+(char)('a'+i));
        grafo.insertEdge(personajes[0],personajes[1], 2);
        grafo.insertEdge(personajes[0],personajes[2], 8);
        grafo.insertEdge(personajes[0],personajes[3], 24);
        grafo.insertEdge(personajes[0],personajes[4], 12);
        grafo.insertEdge(personajes[2],personajes[5], 6);
        grafo.insertEdge(personajes[4],personajes[5], 4);
        grafo.insertEdge(personajes[5],personajes[6], 3);
        grafo.insertEdge(personajes[6],personajes[7], 5);
        grafo.insertEdge(personajes[6],personajes[8], 35);
        grafo.insertEdge(personajes[8],personajes[9], 7);
        grafo.getVertices().forEachRemaining(p->System.out.println(((PersonajePrueba)(((Vertex<PersonajePrueba>) p).getElement())).getID()));
    }

}