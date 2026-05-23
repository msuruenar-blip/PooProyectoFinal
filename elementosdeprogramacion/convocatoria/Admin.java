package convocatoria;

import java.util.ArrayList;

public class Admin extends Usuario {

    private ArrayList<Libro>   catalogoLibros;
    private ArrayList<Cliente> listaClientes;

    public Admin(int id, String nombre) {
        super(id, nombre);
        this.catalogoLibros = new ArrayList<>();
        this.listaClientes  = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        catalogoLibros.add(libro);
        System.out.println("Libro agregado: " + libro.getNombre());
    }

    public void eliminarLibro(int idLibro) {
        for (int i = 0; i < catalogoLibros.size(); i++) {
            if (catalogoLibros.get(i).getId() == idLibro) {
                System.out.println("Libro eliminado: " + catalogoLibros.get(i).getNombre());
                catalogoLibros.remove(i);
                return;
            }
        }
        System.out.println("No se encontró un libro con ID " + idLibro + ".");
    }

    public Libro buscarLibroPorId(int idLibro) {
        for (Libro libro : catalogoLibros) {
            if (libro.getId() == idLibro) {
                return libro;
            }
        }
        return null;
    }

    public ArrayList<Libro> buscarLibroPorNombre(String nombre) {
        ArrayList<Libro> resultados = new ArrayList<>();
        for (Libro libro : catalogoLibros) {
            if (libro.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public void mostrarTodosLosLibros() {
        if (catalogoLibros.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("=== Catálogo de libros ===");
        for (Libro libro : catalogoLibros) {
            System.out.println("  " + libro + " | Ubicación: " + Ubicacion.encontrarUbicacion(libro));
        }
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        System.out.println("Cliente registrado: " + cliente.getNombre());
    }

    public void eliminarCliente(int idCliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == idCliente) {
                System.out.println("Cliente eliminado: " + listaClientes.get(i).getNombre());
                listaClientes.remove(i);
                return;
            }
        }
        System.out.println("No se encontró un cliente con ID " + idCliente + ".");
    }

    public Cliente buscarClientePorId(int idCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarClientePorNombre(String nombre) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    public void mostrarTodosLosClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("=== Lista de clientes ===");
        for (Cliente cliente : listaClientes) {
            System.out.println("  " + cliente);
        }
    }

    public ArrayList<Libro>   getCatalogoLibros() { return catalogoLibros; }
    public ArrayList<Cliente> getListaClientes()  { return listaClientes; }

    @Override
    public String toString() {
        return "Admin [ID: " + getId() + ", Nombre: " + getNombre() + "]";
    }
}