package Test;

import org.example.DAO.PrestamosDAOImpl;
import org.example.DAO.LibrosDAOImpl;
import org.example.DAO.SociosDAOImpl;
import org.example.entities.Prestamos;
import org.example.entities.Socios;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PrestamosDAOImplTest {

    private static PrestamosDAOImpl prestamosDAO;
    private static LibrosDAOImpl librosDAO;
    private static SociosDAOImpl sociosDAO;
    private static Session session;

    @BeforeAll
    static void setUp() {
        prestamosDAO = new PrestamosDAOImpl();
        librosDAO = new LibrosDAOImpl();
        sociosDAO = new SociosDAOImpl();

        // Abrir sesión para interactuar con la base de datos
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @AfterAll
    static void tearDown() {
        // Cerrar la sesión
        if (session != null) {
            session.close();
        }
    }

   /* @Test
    void testRegistrarPrestamo() {
        // Crear un libro
        Libros libro = new Libros();
        libro.setTitulo("1984");
        libro.setIsbn("978-0-452-28423-1");
        libro.setAutor("George Orwell");
        libro.setEditorial("Harvill Secker");
        libro.setAnioPublicacion(1949);

        // Crear un socio
        Socios socio = new Socios();
        socio.setNombre("Juan Pérez");
        socio.setTelefono("123456789");
        socio.setDireccion("Calle Ficticia 123");

        // Insertar libro y socio en la base de datos
        Transaction transaction = session.beginTransaction();
        session.save(libro);
        session.save(socio);
        transaction.commit();

        // Crear un préstamo
        Prestamos prestamo = new Prestamos();
        prestamo.setLibro(libro);
        prestamo.setSocio(socio);
        prestamo.setFechaPrestamo(new Date());
        prestamo.setFechaDevolucion(null); // El libro aún no ha sido devuelto

        // Registrar el préstamo
        prestamo = prestamosDAO.create(prestamo);

        // Comprobar si el préstamo se registró correctamente
        assertNotNull(prestamo.getId(), "El préstamo no fue registrado correctamente.");
    }
*/
    @Test
    void testVerLibrosPrestados() {
        // Obtener la lista de libros prestados
        List<Prestamos> prestamos = prestamosDAO.findCurrentlyLoanedBooks();

        // Comprobar que la lista no está vacía
        assertFalse(prestamos.isEmpty(), "No se encontraron libros prestados.");
    }

    @Test
    void testHistorialPrestamosPorSocio() {
        // Obtener el primer socio registrado
        Socios socio = session.createQuery("FROM Socios s", Socios.class).setMaxResults(1).getSingleResult();

        // Obtener el historial de préstamos de este socio
        List<Prestamos> prestamos = prestamosDAO.findLoanHistoryBySocio(socio.getId());

        // Comprobar que la lista de préstamos no está vacía
        assertFalse(prestamos.isEmpty(), "No se encontró historial de préstamos para el socio.");
    }
}
