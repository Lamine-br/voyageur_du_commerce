module com.example.voyageur_commerce {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.voyageur_commerce to javafx.fxml;
    exports com.example.voyageur_commerce;
}