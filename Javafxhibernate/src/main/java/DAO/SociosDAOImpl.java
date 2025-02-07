package DAO;

import entities.Socios;

import java.util.List;

public class SociosDAOImpl implements SociosDAO {
    @Override
    public Socios create(Socios socios) {
        return null;
    }

    /**
     * Modifica la información de un socio existente
     *
     * @param socios
     * @return socio actualizado
     */
    @Override
    public Socios update(Socios socios) {
        return null;
    }

    /**
     * Elimina un socio por su ID
     *
     * @param id
     * @return true si fue eliminado, false en caso contrario
     */
    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    /**
     * Busca socios por nombre o número de teléfono
     *
     * @param criterio
     * @return lista de socios que coinciden con el criterio
     */
    @Override
    public List<Socios> findByCriteria(String criterio) {
        return List.of();
    }

    /**
     * Lista todos los socios
     *
     * @return lista de socios
     */
    @Override
    public List<Socios> findAll() {
        return List.of();
    }
}
