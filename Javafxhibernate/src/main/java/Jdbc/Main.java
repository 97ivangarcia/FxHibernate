package Jdbc;

import DAO.*;
import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.Date;  // Importar la clase Date

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AutoresDAO autoresDAO = new AutoresDAOImpl();
        LibrosDAO librosDAO = new LibrosDAOImpl();
        SociosDAO sociosDAO = new SociosDAOImpl();
        PrestamosDAO prestamosDAO = new PrestamosDAOImpl();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Agregar Autor");
            System.out.println("2. Modificar Autor");
            System.out.println("3. Eliminar Autor");
            System.out.println("4. Buscar Autor por Nombre");
            System.out.println("5. Agregar Libro");
            System.out.println("6. Modificar Libro");
            System.out.println("7. Eliminar Libro");
            System.out.println("8. Buscar Libro por Autor");
            System.out.println("9. Buscar Libro por Titulo");
            System.out.println("10. Agregar Socio");
            System.out.println("11. Modificar Socio");
            System.out.println("12. Eliminar Socio");
            System.out.println("13. Buscar Socio por Nombre");
            System.out.println("14. Realizar Préstamo");
            System.out.println("15. Buscar Préstamos Actuales");
            System.out.println("16. Historial de Préstamos por Socio");
            System.out.println("17. Listar Libros Disponibles");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número

            switch (opcion) {
                case 1: // Agregar Autor
                    System.out.print("Ingrese nombre del autor: ");
                    String nombreAutor = scanner.nextLine();
                    System.out.print("Ingrese nacionalidad del autor: ");
                    String nacionalidad = scanner.nextLine();
                    Autores nuevoAutor = new Autores(null, nombreAutor, nacionalidad);
                    autoresDAO.create(nuevoAutor);
                    System.out.println("Autor agregado exitosamente.");
                    break;

                case 2: // Modificar Autor
                    System.out.print("Ingrese ID del autor a modificar: ");
                    int idAutor = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese nuevo nombre del autor: ");
                    String nuevoNombreAutor = scanner.nextLine();
                    System.out.print("Ingrese nueva nacionalidad del autor: ");
                    String nuevaNacionalidad = scanner.nextLine();
                    Autores autorModificar = new Autores(idAutor, nuevoNombreAutor, nuevaNacionalidad);
                    autoresDAO.update(autorModificar);
                    System.out.println("Autor modificado exitosamente.");
                    break;

                case 3: // Eliminar Autor
                    System.out.print("Ingrese ID del autor a eliminar: ");
                    int eliminarIdAutor = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    boolean autorEliminado = autoresDAO.deleteById(eliminarIdAutor);
                    if (autorEliminado) {
                        System.out.println("Autor eliminado exitosamente.");
                    } else {
                        System.out.println("No se encontró el autor.");
                    }
                    break;

                case 4: // Buscar Autor por Nombre
                    System.out.print("Ingrese nombre del autor a buscar: ");
                    String buscarAutorNombre = scanner.nextLine();
                    List<Autores> autoresEncontrados = autoresDAO.findByName(buscarAutorNombre);
                    if (autoresEncontrados.isEmpty()) {
                        System.out.println("No se encontraron autores con ese nombre.");
                    } else {
                        System.out.println("Autores encontrados:");
                        for (Autores a : autoresEncontrados) {
                            System.out.println("ID: " + a.getId() + ", Nombre: " + a.getNombre() + ", Nacionalidad: " + a.getNacionalidad());
                        }
                    }
                    break;

                case 5: // Agregar Libro
                    System.out.print("Ingrese título del libro: ");
                    String tituloLibro = scanner.nextLine();
                    System.out.print("Ingrese ISBN del libro: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ingrese autor del libro: ");
                    String autorLibro = scanner.nextLine();
                    System.out.print("Ingrese editorial del libro: ");
                    String editorial = scanner.nextLine();
                    System.out.print("Ingrese año de publicación del libro: ");
                    int anioPublicacion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    Libros nuevoLibro = new Libros(null, tituloLibro, isbn, autorLibro, editorial, anioPublicacion);
                    librosDAO.update(nuevoLibro);
                    System.out.println("Libro agregado exitosamente.");
                    break;

                case 6: // Modificar Libro
                    System.out.print("Ingrese ID del libro a modificar: ");
                    int idLibro = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese nuevo título del libro: ");
                    String nuevoTituloLibro = scanner.nextLine();
                    System.out.print("Ingrese nuevo ISBN del libro: ");
                    String nuevoIsbn = scanner.nextLine();
                    System.out.print("Ingrese nuevo autor del libro: ");
                    String nuevoAutorLibro = scanner.nextLine();
                    System.out.print("Ingrese nueva editorial del libro: ");
                    String nuevaEditorial = scanner.nextLine();
                    System.out.print("Ingrese nuevo año de publicación del libro: ");
                    int nuevoAnioPublicacion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    Libros libroModificar = new Libros(idLibro, nuevoTituloLibro, nuevoIsbn, nuevoAutorLibro, nuevaEditorial, nuevoAnioPublicacion);
                    librosDAO.update(libroModificar);
                    System.out.println("Libro modificado exitosamente.");
                    break;

                case 7: // Eliminar Libro
                    System.out.print("Ingrese ID del libro a eliminar: ");
                    int idEliminarLibro = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    boolean libroEliminado = librosDAO.deleteById(idEliminarLibro);
                    if (libroEliminado) {
                        System.out.println("Libro eliminado exitosamente.");
                    } else {
                        System.out.println("No se encontró el libro.");
                    }
                    break;

                case 8: // Buscar Libro por Autor
                    System.out.print("Ingrese nombre del autor para buscar sus libros: ");
                    String nombreAutorBuscar = scanner.nextLine();
                    List<Libros> librosPorAutor = librosDAO.findByAutor(nombreAutorBuscar);
                    if (librosPorAutor.isEmpty()) {
                        System.out.println("No se encontraron libros de ese autor.");
                    } else {
                        System.out.println("Libros encontrados:");
                        for (Libros l : librosPorAutor) {
                            System.out.println("ID: " + l.getId() + ", Título: " + l.getTitulo() + ", ISBN: " + l.getIsbn());
                        }
                    }
                    break;

                case 9: // Buscar Libro por Título
                    System.out.print("Ingrese título del libro a buscar: ");
                    String tituloLibroBuscar = scanner.nextLine();
                    List<Libros> librosPorTitulo = librosDAO.findByTitulo(tituloLibroBuscar);
                    if (librosPorTitulo.isEmpty()) {
                        System.out.println("No se encontraron libros con ese título.");
                    } else {
                        System.out.println("Libros encontrados:");
                        for (Libros l : librosPorTitulo) {
                            System.out.println("ID: " + l.getId() + ", Título: " + l.getTitulo() + ", ISBN: " + l.getIsbn());
                        }
                    }
                    break;

                case 10: // Agregar Socio
                    System.out.print("Ingrese nombre del socio: ");
                    String nombreSocio = scanner.nextLine();
                    System.out.print("Ingrese dirección del socio: ");
                    String direccionSocio = scanner.nextLine();
                    System.out.print("Ingrese teléfono del socio: ");
                    String telefonoSocio = scanner.nextLine();
                    Socios nuevoSocio = new Socios(null, nombreSocio, direccionSocio, telefonoSocio);
                    sociosDAO.create(nuevoSocio);
                    System.out.println("Socio agregado exitosamente.");
                    break;

                case 11: // Modificar Socio
                    System.out.print("Ingrese ID del socio a modificar: ");
                    int idSocio = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese nuevo nombre del socio: ");
                    String nuevoNombreSocio = scanner.nextLine();
                    System.out.print("Ingrese nueva dirección del socio: ");
                    String nuevaDireccionSocio = scanner.nextLine();
                    System.out.print("Ingrese nuevo teléfono del socio: ");
                    String nuevoTelefonoSocio = scanner.nextLine();
                    Socios socioModificar = new Socios(idSocio, nuevoNombreSocio, nuevaDireccionSocio, nuevoTelefonoSocio);
                    sociosDAO.update(socioModificar);
                    System.out.println("Socio modificado exitosamente.");
                    break;

                case 12: // Eliminar Socio
                    System.out.print("Ingrese ID del socio a eliminar: ");
                    int idEliminarSocio = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    boolean socioEliminado = sociosDAO.deleteById(idEliminarSocio);
                    if (socioEliminado) {
                        System.out.println("Socio eliminado exitosamente.");
                    } else {
                        System.out.println("No se encontró el socio.");
                    }
                    break;

                case 13: // Buscar Socio por Nombre
                    System.out.print("Ingrese nombre del socio a buscar: ");
                    String buscarSocioNombre = scanner.nextLine();
                    List<Socios> sociosEncontrados = sociosDAO.findByName(buscarSocioNombre);
                    if (sociosEncontrados.isEmpty()) {
                        System.out.println("No se encontraron socios con ese nombre.");
                    } else {
                        System.out.println("Socios encontrados:");
                        for (Socios s : sociosEncontrados) {
                            System.out.println("ID: " + s.getId() + ", Nombre: " + s.getNombre() + ", Teléfono: " + s.getTelefono());
                        }
                    }
                    break;

               /* case 14: // Realizar Préstamo
                    System.out.print("Ingrese ID del libro a prestar: ");
                    int libroIdPrestamo = scanner.nextInt();
                System.out.print("Ingrese ID del socio: ");
                int socioIdPrestamo = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                Libros libroParaPrestamo = librosDAO.findById(libroIdPrestamo);
                Socios socioParaPrestamo = sociosDAO.findById(socioIdPrestamo);
                if (libroParaPrestamo != null && socioParaPrestamo != null) {
                    Prestamos nuevoPrestamo = new Prestamos(null, libroParaPrestamo, socioParaPrestamo, new Date(), null);
                    prestamosDAO.create(nuevoPrestamo);
                    System.out.println("Préstamo realizado exitosamente.");
                } else {
                    System.out.println("Libro o socio no encontrado.");
                }
                break;*/

                case 14: // Buscar Préstamos Actuales
                    List<Prestamos> prestamosActivos = prestamosDAO.findCurrentlyLoanedBooks();
                    if (prestamosActivos.isEmpty()) {
                        System.out.println("No hay libros prestados actualmente.");
                    } else {
                        System.out.println("Libros prestados:");
                        for (Prestamos p : prestamosActivos) {
                            System.out.println("Libro: " + p.getLibro().getTitulo() + ", Socio: " + p.getSocio().getNombre() + ", Fecha Préstamo: " + p.getFechaPrestamo());
                        }
                    }
                    break;

                case 16: // Historial de Préstamos por Socio
                    System.out.print("Ingrese ID del socio para ver su historial de préstamos: ");
                    int socioIdHistorial = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    List<Prestamos> historialPrestamos = prestamosDAO.findLoanHistoryBySocio(socioIdHistorial);
                    if (historialPrestamos.isEmpty()) {
                        System.out.println("No se encontraron préstamos para este socio.");
                    } else {
                        System.out.println("Historial de préstamos:");
                        for (Prestamos p : historialPrestamos) {
                            System.out.println("Libro: " + p.getLibro().getTitulo() + ", Fecha de Préstamo: " + p.getFechaPrestamo());
                        }
                    }
                    break;

                case 17: // Listar Libros Disponibles
                    List<Libros> librosDisponibles = librosDAO.findAvailableBooks();
                    System.out.println("Libros disponibles:");
                    for (Libros l : librosDisponibles) {
                        System.out.println("ID: " + l.getId() + ", Título: " + l.getTitulo());
                    }
                    break;

                case 0: // Salir
                    System.out.println("Gracias por usar el sistema.");
                    return;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
