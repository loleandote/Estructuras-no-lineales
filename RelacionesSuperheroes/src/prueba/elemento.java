package prueba;

import Grafo.Element;

public class elemento implements Element {
    private int numero;
    private String nombre;
    public elemento(int numero, String n){
        this.numero = numero;
        this.nombre = n;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String getID() {
        
        return nombre;
    }
    @Override
    public String toString(){
        return numero + " " + nombre;
    }
}
