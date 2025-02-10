package org.example.javafxhibernate;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.DAO.*;
import org.example.entities.*;

public class HelloController {

    // Campos para los TextField de la interfaz
    @FXML private TextField autorNombreTextField;
    @FXML private TextField autorNacionalidadTextField;
    @FXML private TextField libroTituloTextField;
    @FXML private TextField libroIsbnTextField;
    @FXML private TextField libroAutorTextField;
    @FXML private TextField socioNombreTextField;
    @FXML private TextField socioDireccionTextField;
    @FXML private TextField socioTelefonoTextField;
    @FXML private TextField prestamoIsbnTextField;
    @FXML private TextField prestamoSocioIdTextField;
    @FXML private TextField autorIdTextField;  // Para actualizar y eliminar autor
    @FXML private TextField libroIdTextField;  // Para actualizar y eliminar libro
    @FXML private TextField socioIdTextField;  // Para eliminar socio

    // Instancias de los DAOs
    private AutoresDAOImpl autoresDAO = new AutoresDAOImpl();
    private LibrosDAOImpl librosDAO = new LibrosDAOImpl();
    private SociosDAOImpl sociosDAO = new SociosDAOImpl();
    private PrestamosDAOImpl prestamosDAO = new PrestamosDAOImpl();

    // Método para agregar autor
    @FXML
    private void onAddAuthorClicked() {
        String nombre = autorNombreTextField.getText();
        String nacionalidad = autorNacionalidadTextField.getText();
        if (nombre.isEmpty() || nacionalidad.isEmpty()) {
            System.out.println("Debe ingresar todos los campos.");
            return;
        }
        Autores autor = new Autores(null, nombre, nacionalidad);
        autoresDAO.create(autor);
        System.out.println("Autor agregado: " + autor.getNombre());
    }

    // Método para agregar libro
    @FXML
    private void onAddBookClicked() {
        String titulo = libroTituloTextField.getText();
        String isbn = libroIsbnTextField.getText();
        String autor = libroAutorTextField.getText();
        if (titulo.isEmpty() || isbn.isEmpty() || autor.isEmpty()) {
            System.out.println("Debe ingresar todos los campos.");
            return;
        }
        Libros libro = new Libros(null, titulo, isbn, autor, "", 0);
        librosDAO.create(libro);
        System.out.println("Libro agregado: " + libro.getTitulo());
    }

    // Método para agregar socio
    @FXML
    private void onAddSocioClicked() {
        String nombre = socioNombreTextField.getText();
        String direccion = socioDireccionTextField.getText();
        String telefono = socioTelefonoTextField.getText();
        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            System.out.println("Debe ingresar todos los campos.");
            return;
        }
        Socios socio = new Socios(null, nombre, direccion, telefono);
        sociosDAO.create(socio);
        System.out.println("Socio agregado: " + socio.getNombre());
    }

    // Método para registrar préstamo
    @FXML
    private void onRegisterLoanClicked() {
        String isbn = prestamoIsbnTextField.getText();
        Integer socioId = Integer.parseInt(prestamoSocioIdTextField.getText());

        Libros libro = librosDAO.findByIsbn(isbn).get(0);
        Socios socio = sociosDAO.findById(socioId);

        if (libro != null && socio != null) {
            Prestamos prestamo = new Prestamos();
            prestamo.setLibro(libro);
            prestamo.setSocio(socio);
            prestamosDAO.create(prestamo);
            System.out.println("Préstamo registrado.");
        } else {
            System.out.println("No se pudo registrar el préstamo.");
        }
    }

    // Método para actualizar autor
    @FXML
    private void onUpdateAuthorClicked() {
        try {
            int autorId = Integer.parseInt(autorIdTextField.getText());
            String nuevoNombre = autorNombreTextField.getText();
            String nuevaNacionalidad = autorNacionalidadTextField.getText();

            Autores autor = autoresDAO.findById(autorId);
            if (autor != null) {
                autor.setNombre(nuevoNombre);
                autor.setNacionalidad(nuevaNacionalidad);
                autoresDAO.update(autor);
                System.out.println("Autor actualizado: " + autor.getNombre());
            } else {
                System.out.println("Autor no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de autor inválido.");
        }
    }

    // Método para eliminar autor
    @FXML
    private void onDeleteAuthorClicked() {
        try {
            int autorId = Integer.parseInt(autorIdTextField.getText());
            boolean eliminado = autoresDAO.deleteById(autorId);
            if (eliminado) {
                System.out.println("Autor eliminado.");
            } else {
                System.out.println("No se encontró el autor.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de autor inválido.");
        }
    }

    // Método para actualizar libro
    @FXML
    private void onUpdateBookClicked() {
        try {
            int libroId = Integer.parseInt(libroIdTextField.getText());
            String nuevoTitulo = libroTituloTextField.getText();
            String nuevoIsbn = libroIsbnTextField.getText();
            String nuevoAutor = libroAutorTextField.getText();

            Libros libro = librosDAO.findById(libroId);
            if (libro != null) {
                libro.setTitulo(nuevoTitulo);
                libro.setIsbn(nuevoIsbn);
                libro.setAutor(nuevoAutor);
                librosDAO.update(libro);
                System.out.println("Libro actualizado: " + libro.getTitulo());
            } else {
                System.out.println("Libro no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de libro inválido.");
        }
    }

    // Método para eliminar libro
    @FXML
    private void onDeleteBookClicked() {
        try {
            int libroId = Integer.parseInt(libroIdTextField.getText());
            boolean eliminado = librosDAO.deleteById(libroId);
            if (eliminado) {
                System.out.println("Libro eliminado.");
            } else {
                System.out.println("No se encontró el libro.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de libro inválido.");
        }
    }

    // Método para eliminar socio
    @FXML
    private void onDeleteSocioClicked() {
        try {
            int socioId = Integer.parseInt(socioIdTextField.getText());
            boolean eliminado = sociosDAO.deleteById(socioId);
            if (eliminado) {
                System.out.println("Socio eliminado.");
            } else {
                System.out.println("No se encontró el socio.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de socio inválido.");
        }
    }
}
