package com.czm.managed_system.controller;

import com.czm.managed_system.ManagedSystemApplication;
import com.czm.managed_system.entity.Dormitory;
import com.czm.managed_system.entity.User;
import com.czm.managed_system.service.DormitoryService;
import com.czm.managed_system.service.UserService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@FXMLController
public class DormitoryController implements Initializable {

    private static Dormitory dormitory;
    private static URL location = null;
    @FXML
    AnchorPane anchorPane;

    @FXML
    private TextField dormitoryName;

    @FXML
    private TextField time;

    @FXML
    private TableView<User> tenants;

    @FXML
    private TableColumn name;

    @FXML
    private TableColumn age;

    @FXML
    private TableColumn gender;

    @FXML
    private TableColumn phone;

    private Alert alert;
    @Autowired
    private UserService userService;

    @Autowired
    private DormitoryService dormitoryService;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ManagedSystemApplication.controllers.put(this.getClass().getSimpleName(),this);
        DormitoryController.location = location;
        bindProperty();
        init();
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(ManagedSystemApplication.getStage());
    }

    public void init() {
        dormitoryName.setText(DormitoryController.dormitory.getName());
        time.setText(DormitoryController.dormitory.getStartTimeString());
        //向表格中填充结果
        List<User> userList = userService.findByDormitoryId(DormitoryController.dormitory.getId());
        ObservableList<User> items = FXCollections.observableArrayList(userList);
        tenants.setItems(items);
    }

    private void bindProperty(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("genderString"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    @FXML
    public void exit() {
        Stage window = (Stage) anchorPane.getScene().getWindow();
        window.close();
    }

    @FXML
    public void checkOut() {
        if (DormitoryController.dormitory.getStage() ==1) {
            dormitoryService.reSet(DormitoryController.dormitory.getName(),0);
            userService.deleteUserByDormitoryId(DormitoryController.dormitory.getId());
            alert.setHeaderText("结账成功");
            ((IndexController) ManagedSystemApplication.controllers.get(IndexController.class.getSimpleName())).refresh();
        } else {
            alert.setHeaderText("该房间未租用！");
        }
        alert.showAndWait();
        exit();
    }

    public static boolean initData(Dormitory dormitory) {
        DormitoryController.dormitory = dormitory;
        if (DormitoryController.location != null){
            return true;
        }
        return false;
    }
}
