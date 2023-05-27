import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Grafo {
    private final String[] nodos; //nombres para los nodos
    private final float[][] grafo; //distancia entre municipios
    private final PriorityQueue<Nodo> cola = new PriorityQueue<>();//para que los nodos se ordenen en funcion a sus distancias
    private List<Nodo> listos; //para almacenar los nodos visitados
    public Grafo(String[] nodos) {
        this.nodos = nodos;
        grafo = new float[nodos.length][nodos.length];
    }

    //Como su nombre lo indica, hacemos las conexiones entre municipios
    public void agregarConexion(String nodoA, String nodoB, float distancia) {
        int A = buscarIndice(nodoA);
        int B = buscarIndice(nodoB);
        //verificamos si existen los nodos
        if (A != -1 && B != -1) {
            grafo[A][B] = distancia;
            grafo[B][A] = distancia;
        } else {
            System.out.println("Error: Uno o ambos nodos no existen en el grafo.");
        }
    }

    //buscamos el indice en el arreglo nodos
    private int buscarIndice(String nodo) {
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i].equals(nodo)) {
                return i;
            }
        }
        return -1;
    }

    //encontramos la ruta más corta
    public String encontrarRutaMasCortaDijkstra(String inicio, String fin) {
        //invocamos al metodo privado
        encontrarRutaMasCortaDijkstra(inicio);
        Nodo tmp = new Nodo(fin);
        //verificamos si el nodo no está
        if (!listos.contains(tmp)) {
            return "Error: Nodo no alcanzable.";
        }
        //almacena el nodo destino, indexOf devuelve la posicion
        tmp = listos.get(listos.indexOf(tmp));
        //almacenamos la distancia del nodo destino
        float distancia = tmp.distancia;
        //Este pila almacena los nodos de la ruta mas corta
        Stack<Nodo> pila = new Stack<>();
        //creamos la ruta desde el nodo destino hacia el nodo inicio
        while (tmp != null) {
            pila.add(tmp);
            tmp = tmp.procedencia;
        }
        //para manipular cadena de una manera mas eficiente
        StringBuilder ruta = new StringBuilder();
        //concatenamos los id de los nodos que estan en la pila
        while (!pila.isEmpty()) {
            //agregamos el id del nodo actual y separamos con espacio
            ruta.append(pila.pop().id).append(" ");
        }
        return distancia + " KM Recorridos | La Mejor Ruta es: " + ruta;
    }

    //calcula la ruta mas corta desde el nodo inicio a los demas nodos
    private void encontrarRutaMasCortaDijkstra(String inicio) {
        listos = new ArrayList<>();
        cola.clear();
        Nodo nodoInicial = new Nodo(inicio, 0, null);
        cola.add(nodoInicial);

        while (!cola.isEmpty()) {
            //se almacena el nodo de menor distancia
            Nodo tmp = cola.poll();//poll devuelve y elimina el elemento de mayor prioridad
            //se verifica si el nodo acutal no se encuentrá en la lista
            if (!listos.contains(tmp)) {
                listos.add(tmp);
                int p = buscarIndice(tmp.id);
                for (int j = 0; j < nodos.length; j++) {
                    //se verifica si hay una conexión entre los nodos y el nodo no visitado
                    if (grafo[p][j] != 0 && !estaTerminado(nodos[j])) {
                        //se calcula la distancia entre el nodo inicio y el actual
                        float distancia = tmp.distancia + grafo[p][j];
                        Nodo nod = new Nodo(nodos[j], distancia, tmp);
                        //se verifica si el nodo actual ya existe en la cola
                        if (existeEnCola(nod)) {
                            actualizarDistanciaEnCola(nod);
                        } else {
                            //si el nodo no existe en la cola, se agrega
                            cola.add(nod);
                        }
                    }
                }
            }
        }
    }

    //verifica si un nodo está en la lista de nodos visitados
    private boolean estaTerminado(String nodo) {
        for (Nodo n : listos) {
            if (n.id.equals(nodo)) {
                return true;
            }
        }
        return false;
    }

    //verifica si el nodo está en la cola
    private boolean existeEnCola(Nodo nodo) {
        for (Nodo n : cola) {
            if (n.id.equals(nodo.id)) {
                return true;
            }
        }
        return false;
    }

    //
    private void actualizarDistanciaEnCola(Nodo nodo) {
        for (Nodo n : cola) {
            //comparamos el id y distancia del nodo acutal con el nodo actualizado
            if (n.id.equals(nodo.id) && n.distancia > nodo.distancia) {
                //eliminamos el nodo actual
                cola.remove(n);
                //agregamos el nodo actualizado
                cola.add(nodo);
                //terminamos el ciclo for
                break;
            }
        }
    }
}

