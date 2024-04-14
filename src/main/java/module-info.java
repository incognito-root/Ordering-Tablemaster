module com.tablemasterordering.orderingtablemaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.tablemasterordering.orderingtablemaster to javafx.fxml;
    exports com.tablemasterordering.orderingtablemaster;
    exports com.tablemasterordering.orderingtablemaster.models;
}