package DAO;

import entities.Libros;
import java.util.List;

public interface LibrosDAO {

    /**
     * Modifica la información de un libro existente
     * @param libros
     * @return libro actualizado
     */
    Libros update(Libros libros);

    /**
     * Elimina un libro por su ID
     * @param id
     * @return true si fue eliminado, false en caso contrario
     */
    boolean deleteById(Integer id);

    /**
     * Busca libros por título, autor o ISBN
     * @param criterio
     * @return lista de libros que coinciden con el criterio
     */
    List<Libros> findByCriteria(String criterio);

    /**
     * Lista todos los libros disponibles que no han sido prestados
     * @return lista de libros disponibles
     */
    List<Libros> findAvailableBooks();
}
