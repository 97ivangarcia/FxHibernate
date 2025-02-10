module org.example.bibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    // Abre este paquete para JavaFX FXML
    opens org.example.javafxhibernate to javafx.fxml;

    // Permite que Hibernate acceda a las entidades
    opens org.example.entities to org.hibernate.orm.core;

    // Exporta el paquete javafxhibernate para que JavaFX pueda acceder a las clases
    exports org.example.javafxhibernate to javafx.graphics; // Permite acceso desde JavaFX
}
