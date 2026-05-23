package convocatoria;

public class Ubicacion {

    private static final String[] PASILLOS = { "A", "B", "C" };

    public static String encontrarUbicacion(Libro libro) {
        int estanteria = libro.getEstanteria();
        String pasillo = PASILLOS[estanteria - 1]; // indice 0,1,2

        return "El libro \"" + libro.getNombre() + "\" se encuentra en:"
             + "  Pasillo " + pasillo
             + " en la Estantería " + estanteria;
    }

}
