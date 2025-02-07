package DAO;

import entities.Socios;

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
     * Busca socios por nombre o número de teléfono
     * @param criterio
     * @return lista de socios que coinciden con el criterio
     */
    List<Socios> findByCriteria(String criterio);

    /**
     * Lista todos los socios
     * @return lista de socios
     */
    List<Socios> findAll();
}
