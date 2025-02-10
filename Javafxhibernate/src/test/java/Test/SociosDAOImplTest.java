package Test;

import org.example.DAO.SociosDAOImpl;
import org.example.entities.Socios;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class SociosDAOImplTest {
    private static SociosDAOImpl sociosDAO;

    @BeforeAll
    static void setUp() {
        sociosDAO = new SociosDAOImpl();
    }

    @Test
    void testCreateAndFindByName() {
        // Crear un socio
        Socios socio1 = new Socios();
        socio1.setNombre("Juan Pérez");
        socio1.setTelefono("123456789");
        socio1.setDireccion("Calle Ficticia 123");

        // Insertar en la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio1);
            transaction.commit();
        } catch (Exception e) {
            fail("Error al insertar socio: " + e.getMessage());
        }

        // Verificar si el socio se insertó correctamente
        List<Socios> sociosEncontrados = sociosDAO.findByName("Juan Pérez");
        assertNotNull(sociosEncontrados);
        assertFalse(sociosEncontrados.isEmpty(), "No se encontró el socio por nombre");
        assertEquals("Juan Pérez", sociosEncontrados.get(0).getNombre(), "El nombre del socio no coincide");
    }

    @Test
    void testCreateAndFindByTelefono() {
        // Crear otro socio
        Socios socio2 = new Socios();
        socio2.setNombre("Ana Gómez");
        socio2.setTelefono("987654321");
        socio2.setDireccion("Avenida Central 456");

        // Insertar en la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio2);
            transaction.commit();
        } catch (Exception e) {
            fail("Error al insertar socio: " + e.getMessage());
        }

        // Verificar si el socio se insertó correctamente
        List<Socios> sociosEncontrados = sociosDAO.findByTelefono("987654321");
        assertNotNull(sociosEncontrados);
        assertFalse(sociosEncontrados.isEmpty(), "No se encontró el socio por teléfono");
        assertEquals("987654321", sociosEncontrados.get(0).getTelefono(), "El teléfono del socio no coincide");
    }

    @Test
    void testUpdateSocio() {
        // Crear y guardar un socio
        Socios socio3 = new Socios();
        socio3.setNombre("Carlos Díaz");
        socio3.setTelefono("1122334455");
        socio3.setDireccion("Calle Real 789");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio3);
            transaction.commit();
        }

        // Modificar la información del socio
        socio3.setTelefono("9988776655");
        sociosDAO.update(socio3);

        // Verificar si el teléfono fue actualizado correctamente
        Socios socioActualizado = sociosDAO.findByTelefono("9988776655").get(0);
        assertNotNull(socioActualizado);
        assertEquals("9988776655", socioActualizado.getTelefono(), "El teléfono no se actualizó correctamente");
    }

    @Test
    void testDeleteSocio() {
        // Crear un socio para eliminar
        Socios socio4 = new Socios();
        socio4.setNombre("Laura Martínez");
        socio4.setTelefono("555444333");
        socio4.setDireccion("Plaza Mayor 101");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio4);
            transaction.commit();
        }

        // Eliminar el socio
        boolean eliminado = sociosDAO.deleteById(socio4.getId());
        assertTrue(eliminado, "El socio no fue eliminado correctamente");

        // Verificar que el socio ya no existe
        List<Socios> sociosEncontrados = sociosDAO.findByTelefono("555444333");
        assertTrue(sociosEncontrados.isEmpty(), "El socio no fue eliminado de la base de datos");
    }

    @Test
    void testFindAll() {
        // Crear varios socios
        Socios socio5 = new Socios();
        socio5.setNombre("Pedro López");
        socio5.setTelefono("333222111");
        socio5.setDireccion("Calle Sol 202");

        Socios socio6 = new Socios();
        socio6.setNombre("Marta Sánchez");
        socio6.setTelefono("666555444");
        socio6.setDireccion("Calle Luna 303");

        // Insertar en la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio5);
            session.save(socio6);
            transaction.commit();
        } catch (Exception e) {
            fail("Error al insertar socios: " + e.getMessage());
        }

        // Verificar si todos los socios fueron recuperados
        List<Socios> todosLosSocios = sociosDAO.findAll();
        assertNotNull(todosLosSocios);
        assertTrue(todosLosSocios.size() > 0, "No se recuperaron los socios de la base de datos");
    }
}
