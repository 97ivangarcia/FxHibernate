package Jdbc;

import DAO.SociosDAOImpl;
import DAO.LibrosDAOImpl;
import DAO.PrestamosDAOImpl;
import DAO.AutoresDAOImpl;
import entities.Socios;
import entities.Libros;
import entities.Prestamos;
import entities.Autores;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private final SociosDAOImpl sociosDAO = new SociosDAOImpl();
    private final LibrosDAOImpl librosDAO = new LibrosDAOImpl();
    private final PrestamosDAOImpl prestamosDAO = new PrestamosDAOImpl();
    private final AutoresDAOImpl autoresDAO = new AutoresDAOImpl();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de Biblioteca");

        // Crear botones para cada acción
        Button btnSocios = new Button("Gestionar Socios");
        Button btnLibros = new Button("Gestionar Libros");
        Button btnPrestamos = new Button("Gestionar Préstamos");
        Button btnAutores = new Button("Gestionar Autores");

        // Crear contenedor y agregar los botones
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(btnSocios, btnLibros, btnPrestamos, btnAutores);

        // Configuración de la escena
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);

        // Acciones para cada botón
        btnSocios.setOnAction(event -> mostrarOpcionesSocios());
        btnLibros.setOnAction(event -> mostrarOpcionesLibros());
        btnPrestamos.setOnAction(event -> mostrarOpcionesPrestamos());
        btnAutores.setOnAction(event -> mostrarOpcionesAutores());

        primaryStage.show();
    }

    private void mostrarOpcionesSocios() {
        // Crear ventana secundaria para gestionar socios
        Stage sociosStage = new Stage();
        sociosStage.setTitle("Gestión de Socios");

        Button btnCrearSocio = new Button("Crear Socio");
        Button btnBuscarSocio = new Button("Buscar Socio");
        Button btnActualizarSocio = new Button("Actualizar Socio");
        Button btnEliminarSocio = new Button("Eliminar Socio");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(btnCrearSocio, btnBuscarSocio, btnActualizarSocio, btnEliminarSocio);

        btnCrearSocio.setOnAction(event -> {
            // Llamar a la acción de crear socio
            Socios socio = new Socios(); // Aquí puedes pedir datos al usuario y crear un nuevo socio
            sociosDAO.create(socio);
        });

        btnBuscarSocio.setOnAction(event -> {
            // Llamar a la acción de buscar socio
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Buscar Socio");
            dialog.setHeaderText("Introduce el nombre del socio");
            dialog.showAndWait().ifPresent(name -> {
                // Llamada al DAO para buscar por nombre
                sociosDAO.findByName(name).forEach(socio -> System.out.println(socio.getNombre()));
            });
        });

        btnActualizarSocio.setOnAction(event -> {
            // Llamar a la acción de actualizar socio
            // Este proceso debe permitir al usuario seleccionar un socio y actualizar sus datos
        });

        btnEliminarSocio.setOnAction(event -> {
            // Llamar a la acción de eliminar socio
            // Aquí puedes pedir al usuario un ID para eliminar un socio
        });

        Scene scene = new Scene(layout, 300, 250);
        sociosStage.setScene(scene);
        sociosStage.show();
    }

    private void mostrarOpcionesLibros() {
        // Crear ventana secundaria para gestionar libros
        Stage librosStage = new Stage();
        librosStage.setTitle("Gestión de Libros");

        Button btnBuscarLibro = new Button("Buscar Libro");
        Button btnActualizarLibro = new Button("Actualizar Libro");
        Button btnEliminarLibro = new Button("Eliminar Libro");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(btnBuscarLibro, btnActualizarLibro, btnEliminarLibro);

        btnBuscarLibro.setOnAction(event -> {
            // Llamar a la acción de buscar libro
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Buscar Libro");
            dialog.setHeaderText("Introduce el título del libro");
            dialog.showAndWait().ifPresent(title -> {
                librosDAO.findByTitulo(title).forEach(libro -> System.out.println(libro.getTitulo()));
            });
        });

        btnActualizarLibro.setOnAction(event -> {
            // Llamar a la acción de actualizar libro
            // Este proceso debe permitir al usuario seleccionar un libro y actualizar sus datos
        });

        btnEliminarLibro.setOnAction(event -> {
            // Llamar a la acción de eliminar libro
            // Aquí puedes pedir al usuario un ID para eliminar un libro
        });

        Scene scene = new Scene(layout, 300, 250);
        librosStage.setScene(scene);
        librosStage.show();
    }

    private void mostrarOpcionesPrestamos() {
        // Crear ventana secundaria para gestionar préstamos
        Stage prestamosStage = new Stage();
        prestamosStage.setTitle("Gestión de Préstamos");

        Button btnRegistrarPrestamo = new Button("Registrar Préstamo");
        Button btnVerPrestamos = new Button("Ver Préstamos Actuales");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(btnRegistrarPrestamo, btnVerPrestamos);

        btnRegistrarPrestamo.setOnAction(event -> {
            // Llamar a la acción de registrar préstamo
            Prestamos prestamo = new Prestamos(); // Aquí puedes pedir datos al usuario
            prestamosDAO.create(prestamo);
        });

        btnVerPrestamos.setOnAction(event -> {
            // Llamar a la acción de ver préstamos actuales
            prestamosDAO.findCurrentlyLoanedBooks().forEach(prestamo -> System.out.println(prestamo.getLibro().getTitulo()));
        });

        Scene scene = new Scene(layout, 300, 250);
        prestamosStage.setScene(scene);
        prestamosStage.show();
    }

    private void mostrarOpcionesAutores() {
        // Crear ventana secundaria para gestionar autores
        Stage autoresStage = new Stage();
        autoresStage.setTitle("Gestión de Autores");

        Button btnCrearAutor = new Button("Crear Autor");
        Button btnBuscarAutor = new Button("Buscar Autor");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(btnCrearAutor, btnBuscarAutor);

        btnCrearAutor.setOnAction(event -> {
            // Llamar a la acción de crear autor
            Autores autor = new Autores(); // Aquí puedes pedir datos al usuario
            autoresDAO.create(autor);
        });

        btnBuscarAutor.setOnAction(event -> {
            // Llamar a la acción de buscar autor
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Buscar Autor");
            dialog.setHeaderText("Introduce el nombre del autor");
            dialog.showAndWait().ifPresent(name -> {
                autoresDAO.findByName(name).forEach(autor -> System.out.println(autor.getNombre()));
            });
        });

        Scene scene = new Scene(layout, 300, 250);
        autoresStage.setScene(scene);
        autoresStage.show();
    }
}
