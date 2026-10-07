module org.example.module4_assignment2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.module4_assignment2 to javafx.fxml;
    exports org.example.module4_assignment2;
}