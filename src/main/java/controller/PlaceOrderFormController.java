package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class PlaceOrderFormController {
    public AnchorPane placeOrderPane;

    public void addToCartButtonOnAction(MouseEvent mouseEvent) {
    }

    public void placeOrderButtonOnAction(MouseEvent mouseEvent) {
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
         
        Stage stage = (Stage)placeOrderPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
