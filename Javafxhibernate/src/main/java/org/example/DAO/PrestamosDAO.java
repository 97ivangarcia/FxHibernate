package org.example.DAO;

import org.example.entities.Prestamos;
import java.util.List;

public interface PrestamosDAO {

    /**
     * Registra un préstamo de un libro a un socio.
     * @param prestamo
     * @return préstamo registrado
     */
    Prestamos create(Prestamos prestamo);

    /**
     * Lista los libros que están actualmente prestados (es decir, que no han sido devueltos).
     * @return lista de libros prestados
     */
    List<Prestamos> findCurrentlyLoanedBooks();

    /**
     * Lista el historial de préstamos de un socio.
     * @param socioId
     * @return lista de préstamos de un socio
     */
    List<Prestamos> findLoanHistoryBySocio(Integer socioId);
}
