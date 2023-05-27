import java.util.Objects;

//Comparable define un orden natural entre nodos
public class Nodo implements Comparable<Nodo> {
    String id;
    float distancia;
    Nodo procedencia;//referencia del nodo precedente

    Nodo(String id) {
        this.id = id;
        this.distancia = Integer.MAX_VALUE;
        this.procedencia = null;
    }

    Nodo(String id, float distancia, Nodo procedencia) {
        this.id = id;
        this.distancia = distancia;
        this.procedencia = procedencia;
    }

    //compareTo es una implementación de la interfaz Comparable y compara las distacia entre dos
    //nodos y devuelve el resultado
    @Override
    public int compareTo(Nodo otro) {
        return Float.compare(distancia, otro.distancia);
    }

    //compara los id de los nodos para ver si son iguales
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Nodo otro = (Nodo) obj;
        return id == otro.id;
    }

    //genera un valor hash para el nodo basandose en si id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}