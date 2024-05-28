module com.example.ooad_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ooad_project to javafx.fxml;
    exports com.example.ooad_project;
}