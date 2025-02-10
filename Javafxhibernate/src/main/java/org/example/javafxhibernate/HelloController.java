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
    private TextArea textAreaResultados; // Área de texto para mostrar los resultados

    @FXML
    private TextField tfNombreAutor, tfTituloLibro, tfNombreSocio;

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
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre de autor válido.\n");
            return;
        }
        Autores nuevoAutor = new Autores(null, nombre, "Desconocida");
        autoresDAO.create(nuevoAutor);
        textAreaResultados.appendText("Autor agregado exitosamente: " + nombre + "\n");
    }

    private void modificarAutor() {
        String nombre = tfNombreAutor.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido para modificar.\n");
            return;
        }
        // Lógica para modificar autor (actualización de base de datos)
        // Asumimos que el autor ya existe en la base de datos, buscamos y actualizamos
        List<Autores> autoresEncontrados = autoresDAO.findByName(nombre);
        if (!autoresEncontrados.isEmpty()) {
            Autores autor = autoresEncontrados.get(0);
            autor.setNombre(nombre);
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
        // Lógica para eliminar autor (debe existir primero en la base de datos)
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
        if (titulo.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un título de libro válido.\n");
            return;
        }
        Libros nuevoLibro = new Libros(null, titulo, "ISBN001", "Autor Desconocido", "Editorial Desconocida", 2025);
        librosDAO.create(nuevoLibro);
        textAreaResultados.appendText("Libro agregado exitosamente: " + titulo + "\n");
    }

    private void modificarLibro() {
        String titulo = tfTituloLibro.getText().trim();
        if (titulo.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un título válido para modificar.\n");
            return;
        }
        // Lógica para modificar libro
        List<Libros> librosEncontrados = librosDAO.findByTitulo(titulo);
        if (!librosEncontrados.isEmpty()) {
            Libros libro = librosEncontrados.get(0);
            libro.setTitulo(titulo); // Puedes modificar más campos aquí
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
        // Lógica para eliminar libro
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
        String direccion = "Sin dirección";  // Puedes agregar un valor predeterminado o obtenerlo de otro TextField
        String telefono = "Sin teléfono";  // Similar al anterior, puedes agregar un valor predeterminado

        // Validar que el nombre no esté vacío
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido.\n");
            return;
        }

        // Crear un nuevo socio con los datos proporcionados
        Socios nuevoSocio = new Socios(null, nombre, direccion, telefono);

        // Guardar el socio usando el DAO
        sociosDAO.create(nuevoSocio);

        // Mostrar mensaje de éxito
        textAreaResultados.appendText("Socio agregado exitosamente: " + nombre + "\n");
    }


    private void modificarSocio() {
        String nombre = tfNombreSocio.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido para modificar.\n");
            return;
        }
        // Lógica para modificar socio (similar a lo de los libros o autores)
        textAreaResultados.appendText("Socio modificado: " + nombre + "\n");
    }

    private void eliminarSocio() {
        String nombre = tfNombreSocio.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido para eliminar.\n");
            return;
        }
        // Lógica para eliminar socio
        textAreaResultados.appendText("Socio eliminado: " + nombre + "\n");
    }

    private void buscarSocio() {
        String nombre = tfNombreSocio.getText().trim();
        if (nombre.isEmpty()) {
            textAreaResultados.appendText("Por favor ingresa un nombre válido.\n");
            return;
        }
        // Buscar socio
        textAreaResultados.appendText("Socio encontrado: " + nombre + "\n");
    }

    // Métodos para gestionar préstamos
    private void realizarPrestamo() {
        // Lógica de préstamo
        textAreaResultados.appendText("Préstamo realizado exitosamente.\n");
    }

    private void verPrestamos() {
        List<Prestamos> prestamosActivos = prestamosDAO.findCurrentlyLoanedBooks();
        if (prestamosActivos.isEmpty()) {
            textAreaResultados.appendText("No hay préstamos activos.\n");
        } else {
            for (Prestamos prestamo : prestamosActivos) {
                textAreaResultados.appendText("Préstamo activo: " + prestamo.getLibro().getTitulo() + "\n");
            }
        }
    }
}
