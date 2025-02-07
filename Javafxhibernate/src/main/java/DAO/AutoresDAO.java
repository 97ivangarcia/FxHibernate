package DAO;

import entities.Autores;

import java.util.List;

public interface AutoresDAO {

    /**
     * Añade un nuevo autor especificando nombre y nacionalidad
     * @param autores
     * @return autor agregado
     */
    Autores create(Autores autores);

    /**
     * Modifica la información de un autor existente
     * @param autor
     * @return autor actualizado
     */
    Autores update(Autores autor);

    /**
     * Elimina un autor por su ID
     * @param id
     * @return true si fue eliminado, false en caso contrario
     */
    boolean deleteById(Integer id);

    /**
     * Busca autores por nombre
     * @param nombre
     * @return lista de autores que coinciden con el nombre
     */
    List<Autores> findByName(String nombre);

    /**
     * Lista todos los autores
     * @return lista de autores
     */
    List<Autores> findAll();
}