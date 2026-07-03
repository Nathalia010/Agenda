package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Agenda {
    private Map<String, Contacto> contactosPorNombre;
    private Map<String, Contacto> contactosPorTelefono;
    private Set<String> grupos;
    private Map<String, Set<String>> contactosPorGrupo;
    private List<Contacto> ordenInsercion;

    public Agenda() {
        contactosPorNombre = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        contactosPorTelefono = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        grupos = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        contactosPorGrupo = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        ordenInsercion = new ArrayList<>();
    }

    public void agregarContacto(Contacto c) {
        if (contactosPorNombre.containsKey(c.getNombre())) {
            System.out.println("Error: Ya existe un contacto con el nombre '" + c.getNombre() + "'");
            return;
        }

        if (contactosPorTelefono.containsKey(c.getTelefono())) {
            System.out.println("Error: Ya existe un contacto con el teléfono '" + c.getTelefono() + "'");
            return;
        }

        contactosPorNombre.put(c.getNombre(), c);
        contactosPorTelefono.put(c.getTelefono(), c);
        ordenInsercion.add(c);

        System.out.println("Contacto agregado: " + c.getNombre());
    }

    public Contacto buscarContacto(String nombre) {
        return contactosPorNombre.get(nombre);
    }

    public Contacto buscarContactoPorTelefono(String telefono) {
        return contactosPorTelefono.get(telefono);
    }

    public boolean eliminarContacto(String nombre) {
        Contacto contacto = contactosPorNombre.remove(nombre);

        if (contacto == null) {
            return false;
        }

        contactosPorTelefono.remove(contacto.getTelefono());
        ordenInsercion.remove(contacto);

        for (Set<String> contactosDelGrupo : contactosPorGrupo.values()) {
            contactosDelGrupo.remove(contacto.getNombre());
        }

        return true;
    }

    public void listarContactos() {
        if (contactosPorNombre.isEmpty()) {
            System.out.println("No hay contactos registrados.");
            return;
        }

        System.out.println("--- LISTA DE CONTACTOS ---");

        for (Contacto contacto : contactosPorNombre.values()) {
            System.out.println(contacto);
        }
    }

    public void agregarGrupo(String nombreGrupo) {
        if (grupos.contains(nombreGrupo)) {
            System.out.println("El grupo '" + nombreGrupo + "' ya existe.");
            return;
        }

        grupos.add(nombreGrupo);
        contactosPorGrupo.put(nombreGrupo, new TreeSet<>(String.CASE_INSENSITIVE_ORDER));

        System.out.println("Grupo '" + nombreGrupo + "' creado.");
    }

    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        if (!contactosPorNombre.containsKey(nombreContacto)) {
            System.out.println("Error: No existe el contacto '" + nombreContacto + "'.");
            return;
        }

        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: No existe el grupo '" + nombreGrupo + "'.");
            return;
        }

        Set<String> contactosDelGrupo = contactosPorGrupo.get(nombreGrupo);

        if (contactosDelGrupo.contains(nombreContacto)) {
            System.out.println("El contacto ya está en el grupo '" + nombreGrupo + "'.");
            return;
        }

        Contacto contacto = contactosPorNombre.get(nombreContacto);
        contactosDelGrupo.add(contacto.getNombre());

        System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'.");
    }

    public void listarGrupos() {
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos registrados.");
            return;
        }

        System.out.println("--- GRUPOS DISPONIBLES ---");

        for (String grupo : grupos) {
            System.out.println("- " + grupo);
        }
    }

    public void mostrarContactosPorGrupo(String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: No existe el grupo '" + nombreGrupo + "'.");
            return;
        }

        Set<String> contactosDelGrupo = contactosPorGrupo.get(nombreGrupo);

        if (contactosDelGrupo.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos.");
            return;
        }

        System.out.println("Contactos en '" + nombreGrupo + "':");

        for (String nombreContacto : contactosDelGrupo) {
            Contacto contacto = contactosPorNombre.get(nombreContacto);
            if (contacto != null) {
                System.out.println("- " + contacto);
            }
        }
    }
}