package com.czm.managed_system.controller;

import com.czm.managed_system.ManagedSystemApplication;
import com.czm.managed_system.entity.User;
import com.czm.managed_system.service.UserService;
import com.czm.managed_system.view.IndexView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@FXMLController
public class AdministratorsController implements Initializable {
    @Autowired
    UserService userService;

    @FXML
    private TextField registerName;

    @FXML
    private PasswordField registerPassword;

    private Alert alert;
    /**
     * 注册
     */
    @FXML
    public void register(){
        String name = registerName.getText();
        String password = registerPassword.getText();
        if (name.equals("") || password.equals("")){
            alert.setHeaderText("用户名、密码必填！");
        }else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            if (userService.addAdmin(user)){
                alert.setHeaderText("注册成功！");
            }else {
                alert.setHeaderText("该用户名已存在！");
                registerName.clear();
            }
            registerName.clear();
            registerPassword.clear();
        }
        alert.showAndWait();
    }

    /**
     * 注销
     */

    public void cancel() {
        String name = registerName.getText();
        String password = registerPassword.getText();
        if (name.equals("") || password.equals("")){
            alert.setHeaderText("用户名、密码必填！");
        }else {
            if (userService.deleteAdmin(name, password) > 0){
                alert.setHeaderText("注销成功！");
            }else {
                alert.setHeaderText("密码或用户名错误！");
            }
            registerName.clear();
            registerPassword.clear();
        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ManagedSystemApplication.controllers.put(this.getClass().getSimpleName(),this);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(ManagedSystemApplication.getStage());

    }
}
