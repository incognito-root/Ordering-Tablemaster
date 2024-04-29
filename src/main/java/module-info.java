module com.tablemasterordering.orderingtablemaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens com.tablemasterordering.orderingtablemaster to javafx.fxml;
    exports com.tablemasterordering.orderingtablemaster;
    exports com.tablemasterordering.orderingtablemaster.models;
}