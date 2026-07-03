package src;

import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();

        boolean salir = false;

        while (!salir) {
            mostrarMenu();

            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    agregarContacto(scanner, agenda);
                    break;

                case "2":
                    buscarContacto(scanner, agenda);
                    break;

                case "3":
                    eliminarContacto(scanner, agenda);
                    break;

                case "4":
                    agenda.listarContactos();
                    break;

                case "5":
                    agregarGrupo(scanner, agenda);
                    break;

                case "6":
                    agregarContactoAGrupo(scanner, agenda);
                    break;

                case "7":
                    agenda.listarGrupos();
                    break;

                case "8":
                    mostrarContactosPorGrupo(scanner, agenda);
                    break;

                case "9":
                    salir = true;
                    System.out.println("Saliendo de la agenda...");
                    break;

                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== AGENDA DE CONTACTOS ===");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Listar todos los contactos");
        System.out.println("5. Agregar grupo");
        System.out.println("6. Agregar contacto a grupo");
        System.out.println("7. Listar grupos");
        System.out.println("8. Mostrar contactos por grupo");
        System.out.println("9. Salir");
        System.out.println("===========================");
    }

    private static void agregarContacto(Scanner scanner, Agenda agenda) {
        System.out.println("--- AGREGAR CONTACTO ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Contacto contacto = new Contacto(nombre, telefono, email);
        agenda.agregarContacto(contacto);
    }

    private static void buscarContacto(Scanner scanner, Agenda agenda) {
        System.out.println("--- BUSCAR CONTACTO ---");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por teléfono");
        System.out.print("Elige una opción: ");

        String opcionBusqueda = scanner.nextLine();

        Contacto contacto = null;

        if (opcionBusqueda.equals("1")) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            contacto = agenda.buscarContacto(nombre);
        } else if (opcionBusqueda.equals("2")) {
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            contacto = agenda.buscarContactoPorTelefono(telefono);
        } else {
            System.out.println("Opción de búsqueda inválida.");
            return;
        }

        if (contacto == null) {
            System.out.println("Contacto no encontrado.");
        } else {
            System.out.println("Contacto encontrado:");
            System.out.println(contacto);
        }
    }

    private static void eliminarContacto(Scanner scanner, Agenda agenda) {
        System.out.println("--- ELIMINAR CONTACTO ---");

        System.out.print("Nombre del contacto: ");
        String nombre = scanner.nextLine();

        boolean eliminado = agenda.eliminarContacto(nombre);

        if (eliminado) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("No existe un contacto con ese nombre.");
        }
    }

    private static void agregarGrupo(Scanner scanner, Agenda agenda) {
        System.out.println("--- AGREGAR GRUPO ---");

        System.out.print("Nombre del grupo: ");
        String nombreGrupo = scanner.nextLine();

        agenda.agregarGrupo(nombreGrupo);
    }

    private static void agregarContactoAGrupo(Scanner scanner, Agenda agenda) {
        System.out.println("--- AGREGAR CONTACTO A GRUPO ---");

        System.out.print("Nombre del contacto: ");
        String nombreContacto = scanner.nextLine();

        System.out.print("Nombre del grupo: ");
        String nombreGrupo = scanner.nextLine();

        agenda.agregarContactoAGrupo(nombreContacto, nombreGrupo);
    }

    private static void mostrarContactosPorGrupo(Scanner scanner, Agenda agenda) {
        System.out.println("--- CONTACTOS POR GRUPO ---");

        System.out.print("Nombre del grupo: ");
        String nombreGrupo = scanner.nextLine();

        agenda.mostrarContactosPorGrupo(nombreGrupo);
    }
}
