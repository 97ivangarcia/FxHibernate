package Jdbc;

import DAO.*;
import Util.HibernateUtil;
import entities.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import org.hibernate.Transaction;

import java.util.List;
import java.util.Date;

public class Main extends Application {

    private AutoresDAO autoresDAO = new AutoresDAOImpl();
    private LibrosDAO librosDAO = new LibrosDAOImpl();
    private SociosDAO sociosDAO = new SociosDAOImpl();
    private PrestamosDAO prestamosDAO = new PrestamosDAOImpl();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Crear los controles de la interfaz gráfica
        primaryStage.setTitle("Biblioteca");

        // Panel principal
        VBox vbox = new VBox(10);
        vbox.setPadding(new javafx.geometry.Insets(20));

        // Botones
        Button btnAgregarAutor = new Button("Agregar Autor");
        Button btnModificarAutor = new Button("Modificar Autor");
        Button btnEliminarAutor = new Button("Eliminar Autor");
        Button btnBuscarAutor = new Button("Buscar Autor");
        Button btnAgregarLibro = new Button("Agregar Libro");
        Button btnModificarLibro = new Button("Modificar Libro");
        Button btnEliminarLibro = new Button("Eliminar Libro");
        Button btnBuscarLibro = new Button("Buscar Libro");
        Button btnAgregarSocio = new Button("Agregar Socio");
        Button btnModificarSocio = new Button("Modificar Socio");
        Button btnEliminarSocio = new Button("Eliminar Socio");
        Button btnBuscarSocio = new Button("Buscar Socio");
        Button btnRealizarPrestamo = new Button("Realizar Préstamo");
        Button btnVerPrestamos = new Button("Ver Préstamos Actuales");

        // Crear un campo de texto para la entrada de datos
        TextField tfEntrada = new TextField();
        tfEntrada.setPromptText("Ingrese los datos aquí");

        // Crear un área de texto para mostrar resultados
        TextArea textAreaResultados = new TextArea();
        textAreaResultados.setEditable(false);

        // Acciones para los botones
        btnAgregarAutor.setOnAction(event -> agregarAutor(tfEntrada, textAreaResultados));
        btnModificarAutor.setOnAction(event -> modificarAutor(tfEntrada, textAreaResultados));
        btnEliminarAutor.setOnAction(event -> eliminarAutor(tfEntrada, textAreaResultados));
        btnBuscarAutor.setOnAction(event -> buscarAutor(tfEntrada, textAreaResultados));
        btnAgregarLibro.setOnAction(event -> agregarLibro(tfEntrada, textAreaResultados));
        btnModificarLibro.setOnAction(event -> modificarLibro(tfEntrada, textAreaResultados));
        btnEliminarLibro.setOnAction(event -> eliminarLibro(tfEntrada, textAreaResultados));
        btnBuscarLibro.setOnAction(event -> buscarLibro(tfEntrada, textAreaResultados));
        btnAgregarSocio.setOnAction(event -> agregarSocio(tfEntrada, textAreaResultados));
        btnModificarSocio.setOnAction(event -> modificarSocio(tfEntrada, textAreaResultados));
        btnEliminarSocio.setOnAction(event -> eliminarSocio(tfEntrada, textAreaResultados));
        btnBuscarSocio.setOnAction(event -> buscarSocio(tfEntrada, textAreaResultados));
        btnRealizarPrestamo.setOnAction(event -> realizarPrestamo(tfEntrada, textAreaResultados));
        btnVerPrestamos.setOnAction(event -> verPrestamos(textAreaResultados));

        // Añadir botones y campos de texto al panel
        vbox.getChildren().addAll(
                btnAgregarAutor, btnModificarAutor, btnEliminarAutor, btnBuscarAutor,
                btnAgregarLibro, btnModificarLibro, btnEliminarLibro, btnBuscarLibro,
                btnAgregarSocio, btnModificarSocio, btnEliminarSocio, btnBuscarSocio,
                btnRealizarPrestamo, btnVerPrestamos,
                tfEntrada, textAreaResultados
        );

        // Crear la escena y mostrarla
        Scene scene = new Scene(vbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Métodos para las acciones (solo ejemplos)
    private void agregarAutor(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        // Aquí podrías pedir más datos en el campo de texto
        // Llama a los métodos del DAO y muestra el resultado
        Autores nuevoAutor = new Autores(null, nombre, "Nacionalidad");
        autoresDAO.create(nuevoAutor);
        textArea.appendText("Autor agregado exitosamente: " + nombre + "\n");
    }

    private void modificarAutor(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        // Modificar autor de acuerdo con los parámetros
        textArea.appendText("Autor modificado: " + nombre + "\n");
    }

    private void eliminarAutor(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        // Eliminar autor de acuerdo con el nombre
        textArea.appendText("Autor eliminado: " + nombre + "\n");
    }

    private void buscarAutor(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        // Buscar autor por nombre
        List<Autores> autoresEncontrados = autoresDAO.findByName(nombre);
        for (Autores autor : autoresEncontrados) {
            textArea.appendText("Autor encontrado: " + autor.getNombre() + "\n");
        }
    }

    private void agregarLibro(TextField tfEntrada, TextArea textArea) {
        String titulo = tfEntrada.getText();
        // Aquí puedes agregar los detalles del libro
        Libros nuevoLibro = new Libros(null, titulo, "ISBN", "Autor", "Editorial", 2025);
        librosDAO.update(nuevoLibro);
        textArea.appendText("Libro agregado exitosamente: " + titulo + "\n");
    }


    private void modificarLibro(TextField tfEntrada, TextArea textArea) {
        String titulo = tfEntrada.getText();
        // Modificar libro
        textArea.appendText("Libro modificado: " + titulo + "\n");
    }

    private void eliminarLibro(TextField tfEntrada, TextArea textArea) {
        String titulo = tfEntrada.getText();
        // Eliminar libro
        textArea.appendText("Libro eliminado: " + titulo + "\n");
    }

    private void buscarLibro(TextField tfEntrada, TextArea textArea) {
        String titulo = tfEntrada.getText();
        List<Libros> librosEncontrados = librosDAO.findByTitulo(titulo);
        for (Libros libro : librosEncontrados) {
            textArea.appendText("Libro encontrado: " + libro.getTitulo() + "\n");
        }
    }

    private void agregarSocio(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        // Agregar socio
        textArea.appendText("Socio agregado: " + nombre + "\n");
    }

    private void modificarSocio(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        // Modificar socio
        textArea.appendText("Socio modificado: " + nombre + "\n");
    }

    private void eliminarSocio(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        // Eliminar socio
        textArea.appendText("Socio eliminado: " + nombre + "\n");
    }

    private void buscarSocio(TextField tfEntrada, TextArea textArea) {
        String nombre = tfEntrada.getText();
        List<Socios> sociosEncontrados = sociosDAO.findByName(nombre);
        for (Socios socio : sociosEncontrados) {
            textArea.appendText("Socio encontrado: " + socio.getNombre() + "\n");
        }
    }

    private void realizarPrestamo(TextField tfEntrada, TextArea textArea) {
        String datos = tfEntrada.getText();
        // Realizar préstamo
        textArea.appendText("Préstamo realizado: " + datos + "\n");
    }

    private void verPrestamos(TextArea textArea) {
        // Ver préstamos actuales
        List<Prestamos> prestamosActivos = prestamosDAO.findCurrentlyLoanedBooks();
        for (Prestamos prestamo : prestamosActivos) {
            textArea.appendText("Préstamo: " + prestamo.getLibro().getTitulo() + "\n");
        }
    }
}
