package org.example.DAO;

import org.example.entities.Prestamos;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamosDAOImpl implements PrestamosDAO {

    /**
     * Registra un préstamo de un libro a un socio.
     * @param prestamo
     * @return préstamo registrado
     */
    @Override
    public Prestamos create(Prestamos prestamo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
            return prestamo; // Préstamo registrado
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista los libros que están actualmente prestados (es decir, que no han sido devueltos).
     * @return lista de libros prestados
     */
    @Override
    public List<Prestamos> findCurrentlyLoanedBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamos p WHERE p.fechaDevolucion IS NULL", Prestamos.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // En caso de error, retorna una lista vacía
        }
    }

    /**
     * Lista el historial de préstamos de un socio.
     * @param socioId
     * @return lista de préstamos de un socio
     */
    @Override
    public List<Prestamos> findLoanHistoryBySocio(Integer socioId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamos p WHERE p.socio.id = :socioId", Prestamos.class)
                    .setParameter("socioId", socioId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // En caso de error, retorna una lista vacía
        }
    }
}
