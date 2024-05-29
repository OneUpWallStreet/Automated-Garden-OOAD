module com.example.ooad_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.ooad_project to javafx.fxml;
    exports com.example.ooad_project;
}