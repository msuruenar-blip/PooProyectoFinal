package convocatoria;
public class Libro {

    private int id;
    private String nombre;
    private String genero;
    private int estanteria; // 1, 2 o 3

    // Estados posibles del libro
    public static final String DISPONIBLE = "Disponible";
    public static final String PRESTADO   = "Prestado";

    private String estado;

    public Libro(int id, String nombre, String genero, int estanteria) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.estanteria = estanteria;
        this.estado = DISPONIBLE; // Por defecto está disponible
    }

    // Getters
    public int getId()          { return id; }
    public String getNombre()   { return nombre; }
    public String getGenero()   { return genero; }
    public int getEstanteria()  { return estanteria; }
    public String getEstado()   { return estado; }

    public boolean estaDisponible() {
        return estado.equals(DISPONIBLE);
    }

    // Cambia el estado a Prestado
    public void prestar() {
        this.estado = PRESTADO;
    }

    // Cambia el estado a Disponible
    public void devolver() {
        this.estado = DISPONIBLE;
    }

    @Override
    public String toString() {
        return "Libro [ID: " + id
             + ", Nombre: \"" + nombre + "\""
             + ", Género: " + genero
             + ", Estantería: " + estanteria
             + ", Estado: " + estado + "]";
    }
}
