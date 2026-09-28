module com.comp338.scheduler.comp338_coursescheduling {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.comp338.scheduler.comp338_coursescheduling to javafx.fxml;
    exports com.comp338.scheduler.comp338_coursescheduling;
}