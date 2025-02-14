package org.example.javafxhibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.DAO.*;
import org.example.entities.*;

import java.util.List;

public class HelloController {

    // Instancia de los DAOs para manejar la lógica de la base de datos
    private AutoresDAO autoresDAO = new AutoresDAOImpl();
    private LibrosDAO librosDAO = new LibrosDAOImpl();
    private SociosDAO sociosDAO = new SociosDAOImpl();
    private PrestamosDAO prestamosDAO = new PrestamosDAOImpl();

    @FXML
    private TextArea textAreaResultados;
    // Área de texto para mostrar los resultados

    @FXML
    private TextField tfNombreAutor, tfNacionalidadAutor, tfTituloLibro, tfISBNLibro, tfAutorLibro, tfEditorialLibro, tfAnioPublicacionLibro, tfNombreSocio, tfDireccionSocio, tfTelefonoSocio;

    @FXML
    private Button btnAgregarAutor, btnModificarAutor, btnEliminarAutor, btnBuscarAutor;
    @FXML
    private Button btnAgregarLibro, btnModificarLibro, btnEliminarLibro, btnBuscarLibro;
    @FXML
    private Button btnAgregarSocio, btnModificarSocio, btnEliminarSocio, btnBuscarSocio;
    @FXML
    private Button btnRealizarPrestamo, btnVerPrestamos;

    // Método de inicialización: agrega eventos a los botones
    @FXML
    public void initialize() {
        // Eventos de botones para gestión de autores
        btnAgregarAutor.setOnAction(event -> agregarAutor());
        btnModificarAutor.setOnAction(event -> modificarAutor());
        btnEliminarAutor.setOnAction(event -> eliminarAutor());
        btnBuscarAutor.setOnAction(event -> buscarAutor());

        // Eventos de botones para gestión de libros
        btnAgregarLibro.setOnAction(event -> agregarLibro());
        btnModificarLibro.setOnAction(event -> modificarLibro());
        btnEliminarLibro.setOnAction(event -> eliminarLibro());
        btnBuscarLibro.setOnAction(event -> buscarLibro());

        // Eventos de botones para gestión de socios
        btnAgregarSocio.setOnAction(event -> agregarSocio());
        btnModificarSocio.setOnAction(event -> modificarSocio());
        btnEliminarSocio.setOnAction(event -> eliminarSocio());
        btnBuscarSocio.setOnAction(event -> buscarSocio());

        // Eventos de botones para gestión de préstamos
        btnRealizarPrestamo.setOnAction(event -> realizarPrestamo());
        btnVerPrestamos.setOnAction(event -> verPrestamos());
    }

    // Métodos para la gestión de autores
    private void agregarAutor() {
        String nombre = tfNombreAutor.getText().trim();
        String nacionalidad = tfNacionalidadAutor.getText().trim();

        if (nombre.isEmpty() || nacionalidad.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre y nacionalidad del autor válidos.\n");
            return;
        }

        Autores nuevoAutor = new Autores(null, nombre, nacionalidad);
        autoresDAO.create(nuevoAutor);
        textAreaResultados.appendText("Autor agregado exitosamente: " + nombre + "\n");
    }

    private void modificarAutor() {
        String nombre = tfNombreAutor.getText().trim();
        String nacionalidad = tfNacionalidadAutor.getText().trim();

        if (nombre.isEmpty() || nacionalidad.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre y nacionalidad válidos para modificar.\n");
            return;
        }

        List<Autores> autoresEncontrados = autoresDAO.findByName(nombre);
        if (!autoresEncontrados.isEmpty()) {
            Autores autor = autoresEncontrados.get(0);
            autor.setNombre(nombre);
            autor.setNacionalidad(nacionalidad);
            autoresDAO.update(autor);
            textAreaResultados.appendText("Autor modificado exitosamente: " + nombre + "\n");
        } else {
            textAreaResultados.appendText("Autor no encontrado: " + nombre + "\n");
        }
    }

    private void eliminarAutor() {
        String nombre = tfNombreAutor.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido para eliminar.\n");
            return;
        }

        List<Autores> autoresEncontrados = autoresDAO.findByName(nombre);
        if (!autoresEncontrados.isEmpty()) {
            Autores autor = autoresEncontrados.get(0);
            autoresDAO.deleteById(autor.getId());
            textAreaResultados.appendText("Autor eliminado exitosamente: " + nombre + "\n");
        } else {
            textAreaResultados.appendText("Autor no encontrado: " + nombre + "\n");
        }
    }

    private void buscarAutor() {
        String nombre = tfNombreAutor.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido.\n");
            return;
        }

        List<Autores> autoresEncontrados = autoresDAO.findByName(nombre);
        if (!autoresEncontrados.isEmpty()) {
            for (Autores autor : autoresEncontrados) {
                textAreaResultados.appendText("Autor encontrado: " + autor.getNombre() + "\n");
            }
        } else {
            textAreaResultados.appendText("No se encontraron autores con ese nombre.\n");
        }
    }

    // Métodos para la gestión de libros
    private void agregarLibro() {
        String titulo = tfTituloLibro.getText().trim();
        String isbn = tfISBNLibro.getText().trim();
        String autor = tfAutorLibro.getText().trim();
        String editorial = tfEditorialLibro.getText().trim();
        String anio = tfAnioPublicacionLibro.getText().trim();

        if (titulo.isEmpty() || isbn.isEmpty() || autor.isEmpty() || editorial.isEmpty() || anio.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa todos los campos del libro.\n");
            return;
        }

        int anioPublicacion = Integer.parseInt(anio);
        Libros nuevoLibro = new Libros(null, titulo, isbn, autor, editorial, anioPublicacion);
        librosDAO.create(nuevoLibro);
        textAreaResultados.appendText("Libro agregado exitosamente: " + titulo + "\n");
    }

    private void modificarLibro() {
        String titulo = tfTituloLibro.getText().trim();
        if (titulo.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un título válido para modificar.\n");
            return;
        }

        List<Libros> librosEncontrados = librosDAO.findByTitulo(titulo);
        if (!librosEncontrados.isEmpty()) {
            Libros libro = librosEncontrados.get(0);
            libro.setTitulo(titulo);
            librosDAO.update(libro);
            textAreaResultados.appendText("Libro modificado exitosamente: " + titulo + "\n");
        } else {
            textAreaResultados.appendText("Libro no encontrado: " + titulo + "\n");
        }
    }

    private void eliminarLibro() {
        String titulo = tfTituloLibro.getText().trim();
        if (titulo.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un título válido para eliminar.\n");
            return;
        }

        List<Libros> librosEncontrados = librosDAO.findByTitulo(titulo);
        if (!librosEncontrados.isEmpty()) {
            Libros libro = librosEncontrados.get(0);
            librosDAO.deleteById(libro.getId());
            textAreaResultados.appendText("Libro eliminado exitosamente: " + titulo + "\n");
        } else {
            textAreaResultados.appendText("Libro no encontrado: " + titulo + "\n");
        }
    }

    private void buscarLibro() {
        String titulo = tfTituloLibro.getText().trim();
        if (titulo.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un título válido.\n");
            return;
        }

        List<Libros> librosEncontrados = librosDAO.findByTitulo(titulo);
        if (!librosEncontrados.isEmpty()) {
            for (Libros libro : librosEncontrados) {
                textAreaResultados.appendText("Libro encontrado: " + libro.getTitulo() + "\n");
            }
        } else {
            textAreaResultados.appendText("No se encontraron libros con ese título.\n");
        }
    }

    // Métodos para la gestión de socios
    private void agregarSocio() {
        String nombre = tfNombreSocio.getText();
        String direccion = tfDireccionSocio.getText();
        String telefono = tfTelefonoSocio.getText();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa todos los campos del socio.\n");
            return;
        }

        Socios nuevoSocio = new Socios(null, nombre, direccion, telefono);
        sociosDAO.create(nuevoSocio);
        textAreaResultados.appendText("Socio agregado exitosamente: " + nombre + "\n");
    }

    private void modificarSocio() {
        String nombre = tfNombreSocio.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido para modificar.\n");
            return;
        }

        List<Socios> sociosEncontrados = sociosDAO.findByName(nombre);
        if (!sociosEncontrados.isEmpty()) {
            Socios socio = sociosEncontrados.get(0);
            socio.setNombre(nombre);
            socio.setDireccion(tfDireccionSocio.getText());
            socio.setTelefono(tfTelefonoSocio.getText());
            sociosDAO.update(socio);
            textAreaResultados.appendText("Socio modificado exitosamente: " + nombre + "\n");
        } else {
            textAreaResultados.appendText("Socio no encontrado: " + nombre + "\n");
        }
    }

    private void eliminarSocio() {
        String nombre = tfNombreSocio.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido para eliminar.\n");
            return;
        }

        List<Socios> sociosEncontrados = sociosDAO.findByName(nombre);
        if (!sociosEncontrados.isEmpty()) {
            Socios socio = sociosEncontrados.get(0);
            sociosDAO.deleteById(socio.getId());
            textAreaResultados.appendText("Socio eliminado exitosamente: " + nombre + "\n");
        } else {
            textAreaResultados.appendText("Socio no encontrado: " + nombre + "\n");
        }
    }

    private void buscarSocio() {
        String nombre = tfNombreSocio.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido.\n");
            return;
        }

        List<Socios> sociosEncontrados = sociosDAO.findByName(nombre);
        if (!sociosEncontrados.isEmpty()) {
            for (Socios socio : sociosEncontrados) {
                textAreaResultados.appendText("Socio encontrado: " + socio.getNombre() + "\n");
            }
        } else {
            textAreaResultados.appendText("No se encontraron socios con ese nombre.\n");
        }
    }


    private void realizarPrestamo() {
        // Implementación del préstamo aquí
    }

    private void verPrestamos() {
        // Implementación de ver préstamos aquí
    }
}
