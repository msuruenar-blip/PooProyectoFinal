package convocatoria;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Cliente extends Usuario {

    private ArrayList<Libro>      librosPrestados;
    private ArrayList<LocalDate>  fechasPrestamo;

    // Dias maximos antes de generar multa
    private static final int DIAS_LIMITE = 7;
    // Multa por dia de retraso (en pesos colombianos, por ejemplo)
    private static final double MULTA_POR_DIA = 2000.0;

    public Cliente(int id, String nombre) {
        super(id, nombre);
        this.librosPrestados = new ArrayList<>();
        this.fechasPrestamo  = new ArrayList<>();
    }

    // Pide un libro: lo marca como prestado y registra la fecha de hoy
    public void pedirLibro(Libro libro) {
        if (!libro.estaDisponible()) {
            System.out.println("Lo sentimos, el libro " + libro.getNombre() + " no está disponible.");
            return;
        }
        libro.prestar();
        librosPrestados.add(libro);
        fechasPrestamo.add(LocalDate.now());
        System.out.println("Préstamo exitoso. Tienes " + DIAS_LIMITE + " días para devolver \""
                + libro.getNombre());
    }

    // Devuelve un libro por su id
    public void devolverLibro(int idLibro) {
        for (int i = 0; i < librosPrestados.size(); i++) {
            if (librosPrestados.get(i).getId() == idLibro) {
                Libro libro = librosPrestados.get(i);
                libro.devolver();
                librosPrestados.remove(i);
                fechasPrestamo.remove(i);
                System.out.println("Libro \"" + libro.getNombre() + "\" devuelto correctamente.");
                return;
            }
        }
        System.out.println("No tienes ese libro prestado.");
    }

    public void verificarRetraso() {
        if (librosPrestados.isEmpty()) {
            System.out.println("No tienes libros prestados actualmente.");
            return;
        }

        boolean hayRetraso = false;
        LocalDate hoy = LocalDate.now();

        System.out.println("=== Verificación de retrasos para: " + getNombre() + " ===");

        for (int i = 0; i < librosPrestados.size(); i++) {
            Libro libro     = librosPrestados.get(i);
            LocalDate fecha = fechasPrestamo.get(i);
            long diasTranscurridos = ChronoUnit.DAYS.between(fecha, hoy);
            long diasRetraso = diasTranscurridos - DIAS_LIMITE;

            System.out.println("\nLibro: \"" + libro.getNombre() + "\"");
            System.out.println("  Fecha de préstamo : " + fecha);
            System.out.println("  Días transcurridos: " + diasTranscurridos);

            if (diasRetraso > 0) {
                double multa = diasRetraso * MULTA_POR_DIA;
                System.out.println("  Días de retraso   : " + diasRetraso);
                System.out.printf("  Multa acumulada: , "+ multa + " pesos");
                hayRetraso = true;
            } else {
                System.out.println("  Sin retraso. Te quedan " + (diasRetraso) + " día(s).");
            }
        }

        if (!hayRetraso) {
            System.out.println("\nTodo en orden, no tienes multas.");
        }
    }

    public void mostrarLibrosPrestados() {
        if (librosPrestados.isEmpty()) {
            System.out.println("No tienes libros prestados.");
            return;
        }
        System.out.println("Libros prestados a " + getNombre() + ":");
        for (int i = 0; i < librosPrestados.size(); i++) {
            System.out.println("  - " + librosPrestados.get(i).getNombre()
                    + " (desde " + fechasPrestamo.get(i) + ")");
        }
    }

    public ArrayList<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    @Override
    public String toString() {
        return "Cliente [ID: " + getId() + ", Nombre: " + getNombre()
             + ", Libros prestados: " + librosPrestados.size() + "]";
    }
}


