package com.czm.managed_system.controller;

import com.czm.managed_system.ManagedSystemApplication;
import com.czm.managed_system.entity.User;
import com.czm.managed_system.service.UserService;
import com.czm.managed_system.view.IndexView;
import de.felixroske.jfxsupport.FXMLController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@FXMLController
public class LoginController implements Initializable {


    @FXML
    private TextField loginName;

    @FXML
    private PasswordField loginPassword;

//    @FXML
//    private TextField registerName;
//
//    @FXML
//    private PasswordField registerPassword;



    @FXML
    private Button loginButton;
    @Autowired
    UserService userService;

    /**
     * 登陆
     */
    @FXML
    public void login() {
//        System.out.println(loginName.getText()+":"+loginPassword.getText());
        String name = loginName.getText();
        String password = loginPassword.getText();
        if (name.equals("") || password.equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.initOwner(ManagedSystemApplication.getStage());
            a.setHeaderText("用户名、密码必填！");
            a.showAndWait();
        }else if (userService.login(name,password)){
            ManagedSystemApplication.getStage().close();
            if (name.equals("root")) {
                ManagedSystemApplication.ROOT = true;
            }else {
                ManagedSystemApplication.ROOT = false;
            }
            ManagedSystemApplication.showView(IndexView.class);
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.initOwner(ManagedSystemApplication.getStage());
            a.setHeaderText("用户名或密码错误！");
            a.showAndWait();
            loginName.clear();
            loginPassword.clear();
        }
    }

//    /**
//     * 注册
//     */
//    @FXML
//    public void register(){
//        String name = registerName.getText();
//        String password = registerPassword.getText();
//        if (name.equals("") || password.equals("")){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.initOwner(ManagedSystemApplication.getStage());
//            a.setHeaderText("用户名、密码必填！");
//            a.showAndWait();
//        }else {
//            User user = new User();
//            user.setName(name);
//            user.setPassword(password);
//            Alert a = new Alert(Alert.AlertType.INFORMATION);
//            a.initOwner(ManagedSystemApplication.getStage());
//            if (userService.addAdmin(user)){
//                a.setHeaderText("注册成功！");
//                a.showAndWait();
//                ManagedSystemApplication.getStage().close();
//                ManagedSystemApplication.showView(IndexView.class);
//            }else {
//                a.setHeaderText("该用户名已存在！");
//                a.showAndWait();
//                registerName.clear();
//            }
//
//        }
//    }
//
//    /**
//     * 注销
//     */
//
//    public void cancel() {
//        String name = registerName.getText();
//        String password = registerPassword.getText();
//        if (name.equals("") || password.equals("")){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.initOwner(ManagedSystemApplication.getStage());
//            a.setHeaderText("用户名、密码必填！");
//            a.showAndWait();
//        }else {
//            Alert a = new Alert(Alert.AlertType.INFORMATION);
//            a.initOwner(ManagedSystemApplication.getStage());
//            if (userService.deleteAdmin(name, password) > 0){
//                a.setHeaderText("注销成功！");
//                a.showAndWait();
//            }else {
//                a.setHeaderText("密码或用户名错误！");
//                a.showAndWait();
//            }
//            registerName.clear();
//            registerPassword.clear();
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ManagedSystemApplication.controllers.put(this.getClass().getSimpleName(),this);

    }
}
