module com.tablemasterordering.orderingtablemaster {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tablemasterordering.orderingtablemaster to javafx.fxml;
    exports com.tablemasterordering.orderingtablemaster;
    exports com.tablemasterordering.orderingtablemaster.models;
}