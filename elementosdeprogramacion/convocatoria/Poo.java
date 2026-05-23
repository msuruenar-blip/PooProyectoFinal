package convocatoria;

import java.util.ArrayList;
import java.util.Scanner;

public class Poo {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Admin admin = new Admin(1, "Carlos");
        admin.agregarLibro(new Libro(101, "Cien años de soledad", "Novela",      1));
        admin.agregarLibro(new Libro(102, "El principito",        "Infantil",    2));
        admin.agregarLibro(new Libro(103, "1984",                 "Distopía",    3));
        admin.agregarLibro(new Libro(104, "Don Quijote",          "Clásico",     1));
        admin.agregarLibro(new Libro(105, "Harry Potter",         "Fantasía",    2));

        Cliente c1 = new Cliente(1, "Ana");
        Cliente c2 = new Cliente(2, "Luis");
        admin.agregarCliente(c1);
        admin.agregarCliente(c2);
        // ────────────────────────────────────────────────────────────────────

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║     SISTEMA DE BIBLIOTECA        ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean corriendo = true;
        while (corriendo) {
            System.out.println("\n¿Quién eres?");
            System.out.println("  1. Administrador");
            System.out.println("  2. Cliente");
            System.out.println("  0. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1" -> menuAdmin(admin);
                case "2" -> menuCliente(admin);
                case "0" -> {
                    System.out.println("¡Hasta luego!");
                    corriendo = false;
                }
                default  -> System.out.println("Opción no válida.");
            }
        }
    }

    static void menuAdmin(Admin admin) {
        System.out.print("\nIngresa el nombre del administrador: ");
        String nombre = sc.nextLine().trim();

        if (!nombre.equalsIgnoreCase(admin.getNombre())) {
            System.out.println("Administrador no encontrado.");
            return;
        }

        System.out.println("Bienvenido, " + admin.getNombre() + ".");

        boolean enMenu = true;
        while (enMenu) {
            System.out.println("\n── Menú Admin ──────────────────");
            System.out.println("  1. Ver todos los libros");
            System.out.println("  2. Agregar libro");
            System.out.println("  3. Eliminar libro");
            System.out.println("  4. Ver todos los clientes");
            System.out.println("  5. Agregar cliente");
            System.out.println("  6. Eliminar cliente");
            System.out.println("  0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1" -> admin.mostrarTodosLosLibros();
                case "2" -> agregarLibro(admin);
                case "3" -> eliminarLibro(admin);
                case "4" -> admin.mostrarTodosLosClientes();
                case "5" -> agregarCliente(admin);
                case "6" -> eliminarCliente(admin);
                case "0" -> enMenu = false;
                default  -> System.out.println("Opción no válida.");
            }
        }
    }

    static void agregarLibro(Admin admin) {
        System.out.println("\n── Agregar libro ──");
        System.out.print("ID del libro    : ");
        int id = leerEntero();
        sc.nextLine();

        System.out.print("Nombre          : ");
        String nombre = sc.nextLine().trim();

        System.out.print("Género          : ");
        String genero = sc.nextLine().trim();

        int estanteria = 0;
        while (estanteria < 1 || estanteria > 3) {
            System.out.print("Estantería (1-3): ");
            estanteria = leerEntero();
            sc.nextLine();
            if (estanteria < 1 || estanteria > 3)
                System.out.println("Debe ser 1, 2 o 3.");
        }

        admin.agregarLibro(new Libro(id, nombre, genero, estanteria));
    }

    static void eliminarLibro(Admin admin) {
        admin.mostrarTodosLosLibros();
        System.out.print("\nID del libro a eliminar: ");
        int id = leerEntero();
        sc.nextLine();
        admin.eliminarLibro(id);
    }

    static void agregarCliente(Admin admin) {
        System.out.println("\n── Agregar cliente ──");
        System.out.print("ID del cliente: ");
        int id = leerEntero();
        sc.nextLine();
        System.out.print("Nombre        : ");
        String nombre = sc.nextLine().trim();
        admin.agregarCliente(new Cliente(id, nombre));
    }

    static void eliminarCliente(Admin admin) {
        admin.mostrarTodosLosClientes();
        System.out.print("\nID del cliente a eliminar: ");
        int id = leerEntero();
        sc.nextLine();
        admin.eliminarCliente(id);
    }

    static void menuCliente(Admin admin) {
        System.out.print("\nIngresa tu nombre: ");
        String nombre = sc.nextLine().trim();

        Cliente cliente = admin.buscarClientePorNombre(nombre);
        if (cliente == null) {
            System.out.println("Cliente no encontrado. Pídele al administrador que te registre.");
            return;
        }

        System.out.println("Bienvenido/a, " + cliente.getNombre() + ".");

        boolean enMenu = true;
        while (enMenu) {
            System.out.println("\n── Menú Cliente ────────────────");
            System.out.println("  1. Ver catálogo de libros");
            System.out.println("  2. Buscar libro por nombre");
            System.out.println("  3. Ver ubicación de un libro");
            System.out.println("  4. Pedir un libro");
            System.out.println("  5. Devolver un libro");
            System.out.println("  6. Ver mis préstamos y multas");
            System.out.println("  0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1" -> admin.mostrarTodosLosLibros();
                case "2" -> buscarLibroPorNombre(admin);
                case "3" -> verUbicacion(admin);
                case "4" -> pedirLibro(admin, cliente);
                case "5" -> devolverLibro(cliente);
                case "6" -> cliente.verificarRetraso();
                case "0" -> enMenu = false;
                default  -> System.out.println("Opción no válida.");
            }
        }
    }

    static void buscarLibroPorNombre(Admin admin) {
        System.out.print("Nombre del libro: ");
        String nombre = sc.nextLine().trim();
        ArrayList<Libro> resultados = admin.buscarLibroPorNombre(nombre);
        if (resultados.isEmpty()) {
            System.out.println("No se encontró ningún libro con ese nombre.");
        } else {
            System.out.println("Resultados:");
            for (Libro libro : resultados) {
                System.out.println("  " + libro + " en " + Ubicacion.encontrarUbicacion(libro));
            }
        }
    }

    static void verUbicacion(Admin admin) {
        System.out.print("ID del libro: ");
        int id = leerEntero();
        sc.nextLine();
        Libro libro = admin.buscarLibroPorId(id);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
        } else {
            System.out.println(Ubicacion.encontrarUbicacion(libro));
        }
    }

    static void pedirLibro(Admin admin, Cliente cliente) {
        admin.mostrarTodosLosLibros();
        System.out.print("\nID del libro que deseas pedir: ");
        int id = leerEntero();
        sc.nextLine();
        Libro libro = admin.buscarLibroPorId(id);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
        } else {
            cliente.pedirLibro(libro);
        }
    }

    static void devolverLibro(Cliente cliente) {
        cliente.mostrarLibrosPrestados();
        if (cliente.getLibrosPrestados().isEmpty()) return;
        System.out.print("ID del libro a devolver: ");
        int id = leerEntero();
        sc.nextLine();
        cliente.devolverLibro(id);
    }


    static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un número válido: ");
            }
        }
    }
}
