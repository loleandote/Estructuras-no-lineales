package Pruebas;

import Grafo.Element;

public class PersonajePrueba implements Element{
    private String nombre;
    public PersonajePrueba (String nombre) {
        this.nombre=nombre;
    }
    @Override
    public String getID() {
        // TODO Auto-generated method stub
        return nombre;
    }
}
