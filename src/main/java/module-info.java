module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    requires java.mail;
    requires mysql.connector.j;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}