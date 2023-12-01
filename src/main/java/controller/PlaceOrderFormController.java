package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {
    public AnchorPane placeOrderPane;
    public JFXComboBox cmbCustId;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtCustName;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtBuyingQty;

    private List<CustomerDto> customers;
    private List<ItemDto> items;

    private CustomerModel customerModel = new CustomerModelImpl();
    private ItemModel itemModel=new ItemModelImpl();
    public void initialize(){
        loadCustomerIds();
        loadItemCodes();

        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, id) -> {
            for (CustomerDto dto : customers) {
                if (dto.getId().equals(id)) {
                    txtCustName.setText(dto.getName());
                }
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, code) -> {
            for (ItemDto dto : items) {
                if (dto.getCode().equals(code)) {
                    txtDescription.setText(dto.getDescription());
                    txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
                }
            }
        });

    }

    private void loadItemCodes() {
        try {
            items =itemModel.allItems();
            ObservableList list= FXCollections.observableArrayList();
            for (ItemDto dto:items){
                list.add(dto.getCode());
            }
            cmbItemCode.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        try {
            customers = customerModel.allCustomer();
            ObservableList list= FXCollections.observableArrayList();
            for (CustomerDto dto:customers){
                list.add(dto.getId());
            }
            cmbCustId.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
