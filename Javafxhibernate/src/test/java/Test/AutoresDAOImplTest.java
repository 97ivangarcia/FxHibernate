package Test;

import org.example.DAO.AutoresDAOImpl;
import org.example.entities.Autores;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AutoresDAOImplTest {

    private static AutoresDAOImpl autoresDAO;

    @BeforeAll
    static void setUp() {
        autoresDAO = new AutoresDAOImpl();
    }

    @Test
    void testCreateAndFindAuthors() {
        // Crear nuevos autores
        Autores autor1 = new Autores();
        autor1.setNombre("Miguel de Cervantes");
        autor1.setNacionalidad("Española");

        Autores autor2 = new Autores();
        autor2.setNombre("Gabriel García Márquez");
        autor2.setNacionalidad("Colombiana");

        // Insertar en la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor1);
            session.save(autor2);
            transaction.commit();
        } catch (Exception e) {
            fail("Error al insertar autores: " + e.getMessage());
        }

        // Verificar si los autores se insertaron correctamente
        List<Autores> autoresEncontrados = autoresDAO.findByName("Miguel de Cervantes");
        assertNotNull(autoresEncontrados);
        assertFalse(autoresEncontrados.isEmpty(), "No se encontraron autores con el nombre 'Miguel de Cervantes'");
    }

    @Test
    void testUpdateAuthor() {
        // Crear un autor y guardarlo
        Autores autor = new Autores();
        autor.setNombre("Jorge Luis Borges");
        autor.setNacionalidad("Argentina");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
        }

        // Modificar el autor
        autor.setNacionalidad("Argentino");
        Autores autorActualizado = autoresDAO.update(autor);

        // Verificar si el autor fue actualizado correctamente
        assertNotNull(autorActualizado);
        assertEquals("Argentino", autorActualizado.getNacionalidad());
    }

    @Test
    void testDeleteAuthor() {
        // Crear un autor para eliminar
        Autores autor = new Autores();
        autor.setNombre("Mario Vargas Llosa");
        autor.setNacionalidad("Peruana");

        // Insertar el autor en la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
        }

        // Eliminar el autor por su ID
        boolean eliminado = autoresDAO.deleteById(autor.getId());

        // Verificar si el autor fue eliminado correctamente
        assertTrue(eliminado, "El autor no fue eliminado correctamente");
    }

    @Test
    void testFindByName() {
        // Crear un autor y guardarlo
        Autores autor = new Autores();
        autor.setNombre("Julio Cortázar");
        autor.setNacionalidad("Argentina");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
        }

        // Buscar autor por nombre
        List<Autores> autoresEncontrados = autoresDAO.findByName("Julio Cortázar");
        assertNotNull(autoresEncontrados);
        assertFalse(autoresEncontrados.isEmpty(), "No se encontraron autores con el nombre 'Julio Cortázar'");
    }

    @Test
    void testFindAll() {
        // Crear algunos autores y guardarlos
        Autores autor1 = new Autores();
        autor1.setNombre("Isaac Asimov");
        autor1.setNacionalidad("Rusa");

        Autores autor2 = new Autores();
        autor2.setNombre("Arthur C. Clarke");
        autor2.setNacionalidad("Británico");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor1);
            session.save(autor2);
            transaction.commit();
        }

        // Obtener todos los autores
        List<Autores> autores = autoresDAO.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() > 0, "No se encontraron autores en la base de datos");
    }
}
