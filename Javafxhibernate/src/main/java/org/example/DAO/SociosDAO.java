package org.example.DAO;

import org.example.entities.Socios;

import java.util.List;

public interface SociosDAO {

    Socios create(Socios socios);

    /**
     * Modifica la información de un socio existente
     * @param socios
     * @return socio actualizado
     */
    Socios update(Socios socios);

    /**
     * Elimina un socio por su ID
     * @param id
     * @return true si fue eliminado, false en caso contrario
     */
    boolean deleteById(Integer id);

    /**
     * Busca socios por nombre
     * @param nombre
     * @return lista de socios que coinciden con el nombre
     */
    List<Socios> findByName(String nombre);

    /**
     * Busca socios por número de teléfono
     * @param telefono
     * @return lista de socios que coinciden con el número de teléfono
     */
    List<Socios> findByTelefono(String telefono);

    /**
     * Lista todos los socios
     * @return lista de socios
     */
    List<Socios> findAll();
}
