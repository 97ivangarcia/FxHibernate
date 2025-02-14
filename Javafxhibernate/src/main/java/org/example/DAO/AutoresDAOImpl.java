package org.example.DAO;

import org.example.entities.Autores;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoresDAOImpl implements AutoresDAO {

    /**
     * Añade un nuevo autor especificando nombre y nacionalidad
     *
     * @param autor
     * @return autor agregado
     */
    @Override
    public Autores create(Autores autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
            return autor; // Autor agregado exitosamente
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si ocurre un error, retorna null
        }
    }

    /**
     * Modifica la información de un autor existente
     *
     * @param autor
     * @return autor actualizado
     */
    @Override
    public Autores update(Autores autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(autor);
            transaction.commit();
            return autor; // Autor actualizado exitosamente
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si ocurre un error, retorna null
        }
    }

    /**
     * Elimina un autor por su ID
     *
     * @param id
     * @return true si fue eliminado, false en caso contrario
     */
    @Override
    public boolean deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Autores autor = session.get(Autores.class, id);
            if (autor != null) {
                session.delete(autor);
                transaction.commit();
                return true; // El autor fue eliminado exitosamente
            }
            return false; // No se encontró el autor para eliminar
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Si ocurre un error, retorna false
        }
    }

    /**
     * Busca autores por nombre
     *
     * @param nombre
     * @return lista de autores que coinciden con el nombre
     */
    @Override
    public List<Autores> findByName(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Autores a WHERE a.nombre LIKE :nombre", Autores.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Lista todos los autores
     *
     * @return lista de autores
     */
    @Override
    public List<Autores> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Autores", Autores.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }



    public Autores findById(int autorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Autores.class, autorId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
