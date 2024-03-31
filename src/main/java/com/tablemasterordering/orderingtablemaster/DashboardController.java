package com.tablemasterordering.orderingtablemaster;

import com.tablemasterordering.orderingtablemaster.models.ComponentDetailsModel;
import com.tablemasterordering.orderingtablemaster.models.DiscountModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashboardController {
    @FXML
    private VBox mostOrderedList;

    @FXML
    private VBox discountsList;

    public DashboardController() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> Platform.runLater(() -> {
            try {
                fillDiscountData();
                fillMostOrderedData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }), 3, TimeUnit.SECONDS);
    }

    public void fillMostOrderedData() throws IOException {
        ArrayList<ComponentDetailsModel> allDetails = new ArrayList<>();
        allDetails.add(new ComponentDetailsModel("img", "text", "text"));
        allDetails.add(new ComponentDetailsModel("img2", "text2", "text2"));
        allDetails.add(new ComponentDetailsModel("img3", "text3", "text3"));

        fillData(allDetails, mostOrderedList);
    }

    public void fillDiscountData() throws IOException {
        ArrayList<DiscountModel> allDetails = new ArrayList<>();
        allDetails.add(new DiscountModel("img0", "text0", "text0"));
        allDetails.add(new DiscountModel("img4", "text4", "text4"));
        allDetails.add(new DiscountModel("img5", "text5", "text5"));

        fillData(allDetails, discountsList);
    }

    public void fillData(ArrayList<? extends ComponentDetailsModel> dataList, VBox parentList) throws IOException {

        for (ComponentDetailsModel details : dataList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("details-pane.fxml"));
            AnchorPane anchorPane = loader.load();
            DetailsPaneController paneController = loader.getController();

            if (details instanceof DiscountModel) {
                System.out.println("here");
                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.schedule(() -> Platform.runLater(() -> paneController.getDetailsPaneArrow().setVisible(true)), 1, TimeUnit.SECONDS);
            }

            paneController.setData(details.getImage(), details.getTitleText(), details.getDescriptionText());
            parentList.getChildren().add(anchorPane);
        }
    }
}
