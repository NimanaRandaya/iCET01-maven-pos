package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import db.DBConnection;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import dto.tm.ItemTm;
import java.io.IOException;
import java.sql.*;

public class ItemFormController {

    @FXML
    private BorderPane itemPane;

    @FXML
    private JFXTreeTableView<ItemTm> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TreeTableColumn colItemCode;

    @FXML
    private TreeTableColumn colDescription;

    @FXML
    private TreeTableColumn colUnitPrice;

    @FXML
    private TreeTableColumn colQtyOnHand;

    @FXML
    private TreeTableColumn colOption;

    public void initialize(){
        colItemCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadItemTable();

        tblItem.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->{
            setData(newValue);
        });
    }

    private void setData(TreeItem<ItemTm> newValue) {
        if (newValue!= null){
            txtItemCode.setEditable(false);
            txtItemCode.setText(newValue.getValue().getCode());
            txtDescription.setText(newValue.getValue().getDescription());
            txtUnitPrice.setText(String.valueOf(newValue.getValue().getUnitPrice()));
            txtQty.setText(String.valueOf(newValue.getValue().getQty()));
        }
    }



    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        String sql  = "SELECT * FROM item";
        try {
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            ResultSet result = stm.executeQuery(sql);

            while (result.next()){
                JFXButton btn = new JFXButton("Delete");
                ItemTm tm = new ItemTm(
                        result.getString(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        btn
                );
                btn.setOnAction(actionEvent ->{
                  deleteItem(tm.getCode());
                });
                tmList.add(tm);
            }
            RecursiveTreeItem <ItemTm> treeItem = new RecursiveTreeItem<ItemTm>(tmList, RecursiveTreeObject::getChildren);
            tblItem.setRoot(treeItem);
            tblItem.setShowRoot(false);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(String code) {
        String sql  = "DELETE from item WHERE code=?";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,code);
            int result = pstm.executeUpdate();
            if (result>0){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
                loadItemTable();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        tblItem.refresh();
        txtItemCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        txtItemCode.setEditable(true);
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
        ItemDto item = new ItemDto(txtItemCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        String sql  = "INSERT INTO item VALUES(?,?,?,?)";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, item.getCode());
            pstm.setString(2, item.getDescription());
            pstm.setDouble(3, item.getUnitPrice());
            pstm.setInt(4, item.getQty());
            int result = pstm.executeUpdate();
            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Item saved!").show();
                loadItemTable();
                clearFields();
            }
        }catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateButtonOnAction(ActionEvent event) {
       /* ItemDto item = new ItemDto(txtItemCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        String sql  = "UPDATE item SET description  = ?,unitPrice= ?,qtyOnHand =? WHERE code=?";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, item.getCode());
            pstm.setString(2, item.getDescription());
            pstm.setDouble(3, item.getUnitPrice());
            pstm.setInt(4, item.getQty());
            int result = pstm.executeUpdate();
            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Item"+item.getCode()+" Updated!").show();
                loadItemTable();
                clearFields();
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "Invalid numeric input!").show();

        }catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry!").show();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
