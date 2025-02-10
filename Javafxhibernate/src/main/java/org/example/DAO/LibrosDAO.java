package org.example.DAO;

import org.example.entities.Libros;
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
     * Busca libros por autor
     * @param autor
     * @return lista de libros que coinciden con el autor
     */
    List<Libros> findByAutor(String autor);

    /**
     * Busca libros por titulo
     * @param titulo
     * @return lista de libros que coinciden con el nombre
     */
    List<Libros> findByTitulo(String titulo);

    /**
     * Busca libros por ISBN
     * @param isbn
     * @return lista de libros que coinciden con el ISBN
     */
    List<Libros> findByIsbn(String isbn);

    /**
     * Lista todos los libros disponibles que no han sido prestados
     * @return lista de libros disponibles
     */
    List<Libros> findAvailableBooks();

    /**
     * Añade un nuevo libro
     * @param libros
     * @return libro agregado
     */
    Libros create(Libros libros);
}
