import Grafo.Element;

public class Personaje implements Element{
    String alias,nombre;
    public Personaje(String alias, String nombre) {
        this.alias = alias;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    @Override
    public String toString() {
        return "El personaje con alias "+alias+" tiene nombre "+nombre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Personaje)) return false;
        Personaje that = (Personaje) o;
        return alias.equals(that.alias);
    }
    @Override
    public String getID() {
        return alias;
    }
    
}
