package com.czm.managed_system.controller;

import com.czm.managed_system.ManagedSystemApplication;
import com.czm.managed_system.entity.Dormitory;
import com.czm.managed_system.entity.User;
import com.czm.managed_system.service.DormitoryService;
import com.czm.managed_system.service.UserService;
import com.czm.managed_system.supper.NumberTextField;
import com.czm.managed_system.supper.TransformUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@FXMLController
public class StayController implements Initializable {
    @FXML
    AnchorPane anchorPane;

    @FXML
    TextField stayName;
    @FXML
    TextField tenantName1;
    @FXML
    ChoiceBox tenantGender1;
    @FXML
    NumberTextField tenantAge1;
    @FXML
    NumberTextField tenantPhone1;
    @FXML
    TextField tenantName2;
    @FXML
    ChoiceBox tenantGender2;
    @FXML
    NumberTextField tenantAge2;
    @FXML
    NumberTextField tenantPhone2;
    @FXML
    TextField tenantName3;
    @FXML
    ChoiceBox tenantGender3;
    @FXML
    NumberTextField tenantAge3;
    @FXML
    NumberTextField tenantPhone3;
    @FXML
    TextField tenantName4;
    @FXML
    ChoiceBox tenantGender4;
    @FXML
    NumberTextField tenantAge4;
    @FXML
    NumberTextField tenantPhone4;

    private Alert alert;

    @Autowired
    DormitoryService dormitoryService;

    @Autowired
    UserService userService;
    @FXML
    public void tenantConfirm() {

        String name = stayName.getText();
        if (name.equals("")) {
            alert.setHeaderText("宿舍号必填");
        }else {
            Dormitory dormitory = dormitoryService.findByName(name);
            if (dormitory == null){
                alert.setHeaderText("不存在该宿舍");
            }else {
                List<User> list = initData();
                if (dormitory.getStage() == -1){
                    alert.setHeaderText("该宿舍当前不可租用！");
                }else if(dormitory.getStage() == 1) {
                    alert.setHeaderText("该宿舍已被租用！");
                }else if(dormitory.getType() < list.size() ){
                    alert.setHeaderText("超过该宿舍的最大人数限制！");
                }else {
                    dormitory.setStartTime(new Date());
                    dormitory.setStage(1);
                    dormitoryService.update(dormitory);
                    for (User u:list){
                        u.setDormitoryId(dormitory.getId());
                        u.setEnabled(1);
                        userService.addUser(u);
                    }
                    alert.setHeaderText("操作成功！");
                    alert.showAndWait();
                    exit();
                    ((IndexController) ManagedSystemApplication.controllers.get(IndexController.class.getSimpleName())).refresh();
                    return;
                }
            }

        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ManagedSystemApplication.controllers.put(this.getClass().getSimpleName(),this);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(ManagedSystemApplication.getStage());
        System.out.println(1231321);
    }

    private List<User> initData(){
        List<User> list = new ArrayList<>(4);
        String name1 = tenantName1.getText();
        String name2 = tenantName2.getText();
        String name3 = tenantName3.getText();
        String name4 = tenantName4.getText();
        if (!name1.equals("")) {
            list.add(initUser(tenantName1, tenantGender1,tenantAge1,tenantPhone1));
        }
        if (!name2.equals("")) {
            list.add(initUser(tenantName2,tenantGender2,tenantAge2,tenantPhone2));
        }
        if (!name3.equals("")) {
            list.add(initUser(tenantName3, tenantGender3, tenantAge3, tenantPhone3));
        }
        if (!name4.equals("")) {
            list.add(initUser(tenantName4, tenantGender4, tenantAge4, tenantPhone4));
        }
        return list;
    }

    private User initUser(TextField name, ChoiceBox gender, TextField age,TextField phone){
        User user = new User();
        user.setName(name.getText());
        user.setGender(TransformUtil.genderByString(String.valueOf(gender.getValue())));
        if (!age.getText().equals("")){
            user.setAge(Integer.parseInt(age.getText()));
        }
        user.setPhone(phone.getText());
        user.setRegisteredTime(new Date());
       return user;
    }

    @FXML
    public void exit() {
        ((Stage) anchorPane.getScene().getWindow()).close();
        clear();
    }

    /**
     * 清空数据
     */
    private void clear(){
        stayName.clear();
        tenantName1.clear();
        tenantAge1.clear();
        tenantPhone1.clear();
        tenantGender1.getSelectionModel().selectFirst();
        tenantName2.clear();
        tenantAge2.clear();
        tenantPhone2.clear();
        tenantGender2.getSelectionModel().selectFirst();
        tenantName3.clear();
        tenantAge3.clear();
        tenantPhone3.clear();
        tenantGender3.getSelectionModel().selectFirst();
        tenantName4.clear();
        tenantAge4.clear();
        tenantPhone4.clear();
        tenantGender4.getSelectionModel().selectFirst();
    }

}
