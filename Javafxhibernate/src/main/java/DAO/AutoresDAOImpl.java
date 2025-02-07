package DAO;

import entities.Autores;

import java.util.List;

public class AutoresDAOImpl implements AutoresDAO {
    /**
     * Añade un nuevo autor especificando nombre y nacionalidad
     *
     * @param autores
     * @return autor agregado
     */
    @Override
    public Autores create(Autores autores) {
        return null;
    }

    /**
     * Modifica la información de un autor existente
     *
     * @param autor
     * @return autor actualizado
     */
    @Override
    public Autores update(Autores autor) {
        return null;
    }

    /**
     * Elimina un autor por su ID
     *
     * @param id
     * @return true si fue eliminado, false en caso contrario
     */
    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    /**
     * Busca autores por nombre
     *
     * @param nombre
     * @return lista de autores que coinciden con el nombre
     */
    @Override
    public List<Autores> findByName(String nombre) {
        return List.of();
    }

    /**
     * Lista todos los autores
     *
     * @return lista de autores
     */
    @Override
    public List<Autores> findAll() {
        return List.of();
    }
}
