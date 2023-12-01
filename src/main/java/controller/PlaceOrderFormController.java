package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ItemModel;
import model.impl.CustomerModelImpl;
import model.impl.ItemModelImpl;
import dto.CustomerDto;
import dto.ItemDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {
    public AnchorPane placeOrderPane;
    public JFXComboBox cmbCustId;
    public JFXComboBox cmbItemCode;

    private List<CustomerDto> customers = new ArrayList<>();
    private List<ItemDto> items = new ArrayList<>();

    private CustomerModel customerModel = new CustomerModelImpl();
    private ItemModel itemModel=new ItemModelImpl();
    public void initialize(){
        loadCustomerIds();
        loadItemCodes();
    }

    private void loadItemCodes() {
    }

    private void loadCustomerIds() {
        
    }

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
