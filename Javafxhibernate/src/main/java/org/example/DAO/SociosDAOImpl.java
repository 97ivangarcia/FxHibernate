package org.example.DAO;

import org.example.entities.Socios;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SociosDAOImpl implements SociosDAO {

    /**
     * Añade un nuevo socio
     *
     * @param socios
     * @return socio agregado
     */
    @Override
    public Socios create(Socios socios) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socios);
            transaction.commit();
            return socios; // Socio agregado exitosamente
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si ocurre un error, retorna null
        }
    }

    /**
     * Modifica la información de un socio existente
     *
     * @param socios
     * @return socio actualizado
     */
    @Override
    public Socios update(Socios socios) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(socios);
            transaction.commit();
            return socios; // Socio actualizado exitosamente
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si ocurre un error, retorna null
        }
    }

    /**
     * Elimina un socio por su ID
     *
     * @param id
     * @return true si fue eliminado, false en caso contrario
     */
    @Override
    public boolean deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Socios socio = session.get(Socios.class, id);
            if (socio != null) {
                session.delete(socio);
                transaction.commit();
                return true; // Socio eliminado exitosamente
            }
            return false; // No se encontró el socio para eliminar
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Si ocurre un error, retorna false
        }
    }

    /**
     * Busca socios por nombre
     *
     * @param nombre
     * @return lista de socios que coinciden con el nombre
     */
    @Override
    public List<Socios> findByName(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios s WHERE s.nombre LIKE :nombre", Socios.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Busca socios por número de teléfono
     *
     * @param telefono
     * @return lista de socios que coinciden con el número de teléfono
     */
    @Override
    public List<Socios> findByTelefono(String telefono) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios s WHERE s.telefono LIKE :telefono", Socios.class)
                    .setParameter("telefono", "%" + telefono + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Lista todos los socios
     *
     * @return lista de socios
     */
    @Override
    public List<Socios> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios", Socios.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    public Socios findById(Integer socioId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Socios.class, socioId);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null en caso de error
        }
    }
}
