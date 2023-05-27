public class Main {
    public static void main(String[] args) {

        String[] nodos = { "Cubulco", "Rabinal", "San Miguel Chicaj", "Salamá", "Purulhá", "San Jerónimo", "El Chol", "Granados"};
        Grafo grafo = new Grafo(nodos);

        grafo.agregarConexion("Cubulco", "Rabinal", 15f);
        grafo.agregarConexion("Cubulco", "Granados", 24f);

        grafo.agregarConexion("Rabinal", "Cubulco", 15f);
        grafo.agregarConexion("Rabinal", "San Miguel Chicaj", 11f);
        grafo.agregarConexion("Rabinal", "Salamá", 6f);
        grafo.agregarConexion("Rabinal", "El Chol", 14f);
        grafo.agregarConexion("Rabinal", "Granados", 19f);

        grafo.agregarConexion("San Miguel Chicaj", "Rabinal", 11f);
        grafo.agregarConexion("San Miguel Chicaj", "Salamá", 9f);

        grafo.agregarConexion("Salamá", "Rabinal", 19f);
        grafo.agregarConexion("Salamá", "San Miguel Chicaj", 9f);
        grafo.agregarConexion("Salamá", "Purulhá", 17f);
        grafo.agregarConexion("Salamá", "San Jerónimo", 9f);
        grafo.agregarConexion("Salamá", "El Chol", 25f);

        grafo.agregarConexion("Purulhá", "Salamá", 17f);

        grafo.agregarConexion("San Jerónimo", "Salamá", 9f);

        grafo.agregarConexion("El Chol", "Rabinal", 14f);
        grafo.agregarConexion("El Chol", "Salamá", 25f);
        grafo.agregarConexion("El Chol", "Granados", 6f);

        grafo.agregarConexion("Granados", "Cubulco", 24f);
        grafo.agregarConexion("Granados", "Rabinal", 19f);
        grafo.agregarConexion("Granados", "El Chol", 6f);


        String rutaMasCorta = grafo.encontrarRutaMasCortaDijkstra("San Jerónimo", "Cubulco");
        System.out.println(rutaMasCorta);
    }
}