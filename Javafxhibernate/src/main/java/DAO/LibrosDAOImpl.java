package DAO;

import Util.HibernateUtil;
import entities.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LibrosDAOImpl implements LibrosDAO {

    @Override
    public Libros update(Libros libros) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(libros);
            transaction.commit();
            return libros;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Libros libros = session.get(Libros.class, id);
            if (libros != null) {
                session.delete(libros);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Libros> findByCriteria(String criterio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libros l WHERE l.titulo LIKE :criterio OR l.autor.nombre LIKE :criterio OR l.isbn LIKE :criterio", Libros.class)
                    .setParameter("criterio", "%" + criterio + "%")
                    .list();
        }
    }

    @Override
    public List<Libros> findAvailableBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libros l WHERE l.prestado = false", Libros.class).list();
        }
    }
}
