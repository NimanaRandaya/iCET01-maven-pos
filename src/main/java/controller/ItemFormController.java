package controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import dto.tm.itemTm;

import java.io.IOException;

public class ItemFormController {

    @FXML
    private BorderPane itemPane;

    @FXML
    private JFXTreeTableView<itemTm> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TreeTableColumn<?, ?> colItemCode;

    @FXML
    private TreeTableColumn<?, ?> colDescription;

    @FXML
    private TreeTableColumn<?, ?> colUnitPrice;

    @FXML
    private TreeTableColumn<?, ?> colQtyOnHand;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    public void initialize(){

    }
    @FXML
    void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage)itemPane.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveButtonOnAction(ActionEvent event) {

    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {

    }

}
