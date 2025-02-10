package Test;

import org.example.DAO.LibrosDAOImpl;
import org.example.entities.Libros;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class LibrosDAOImplTest {
    private static LibrosDAOImpl librosDAO;

    @BeforeAll
    static void setUp() {
        librosDAO = new LibrosDAOImpl();
    }

    @Test
    void testInsertAndFindBooks() {
        // Crear instancia de libro
        Libros libro1 = new Libros();
        libro1.setTitulo("El Quijote");
        libro1.setIsbn("978-3-16-148410-0");
        libro1.setAutor("Miguel de Cervantes");
        libro1.setEditorial("Editorial A");
        libro1.setAnioPublicacion(1605);

        Libros libro2 = new Libros();
        libro2.setTitulo("Cien años de soledad");
        libro2.setIsbn("978-84-376-0494-7");
        libro2.setAutor("Gabriel García Márquez");
        libro2.setEditorial("Editorial B");
        libro2.setAnioPublicacion(1967);

        // Insertar en la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(libro1);
            session.save(libro2);
            transaction.commit();
        } catch (Exception e) {
            fail("Error al insertar libros: " + e.getMessage());
        }

        // Verificar si los libros se insertaron correctamente
        List<Libros> librosEncontrados = librosDAO.findByTitulo("El Quijote");
        assertNotNull(librosEncontrados);
        assertFalse(librosEncontrados.isEmpty(), "No se encontraron libros");
    }
}
