package org.example.DAO;

import org.example.Util.HibernateUtil;
import org.example.entities.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class
LibrosDAOImpl implements LibrosDAO {

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

    public List<Libros> findByAutor(String autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libros l WHERE l.autor LIKE :autor", Libros.class)
                    .setParameter("autor", "%" + autor + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Libros> findByTitulo(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libros l WHERE l.titulo LIKE :titulo", Libros.class)
                    .setParameter("titulo", "%" + nombre + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Libros> findByIsbn(String isbn) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libros l WHERE l.isbn = :isbn", Libros.class)
                    .setParameter("isbn", isbn)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public List<Libros> findAvailableBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libros l WHERE l.prestado = false", Libros.class).list();
        }
    }

    public Libros create(Libros libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return libro;
    }

    public Libros findById(int libroId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Libros.class, libroId);
        }
    }
}
