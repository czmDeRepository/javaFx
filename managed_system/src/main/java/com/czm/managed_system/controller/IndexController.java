package com.czm.managed_system.controller;

import com.czm.managed_system.ManagedSystemApplication;
import com.czm.managed_system.entity.Dormitory;
import com.czm.managed_system.service.DormitoryService;
import com.czm.managed_system.supper.TransformUtil;
import com.czm.managed_system.view.DormitoryView;
import com.czm.managed_system.view.StayView;
import com.czm.managed_system.view.AdministratorsView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

import javafx.util.Callback;

import javax.annotation.Resource;

import java.net.URL;
import java.util.List;

import java.util.ResourceBundle;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@FXMLController
public class IndexController implements Initializable {


    @Resource
    DormitoryService dormitoryService;
    /**
     * 宿舍类型
     */
    @FXML
    private ListView<String> listView;
    /**
     * 宿舍列表
     */
    @FXML
    private TableView<Dormitory> dormitory;

    @FXML
    private TableColumn name;

    @FXML
    private TableColumn stage;

    @FXML
    private TableColumn type;

    @FXML
    private TableColumn days;


    private Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ManagedSystemApplication.controllers.put(this.getClass().getSimpleName(),this);
        //初始化提示框
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(ManagedSystemApplication.getStage());

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                dormitoryList(TransformUtil.getTypeByString(newValue));
            }
        });
        listView.getSelectionModel().selectFirst();
        bindProperty();
        dormitoryList(0);
        /**
         * 宿舍列表双击事件
         */
        dormitory.setRowFactory(new Callback<TableView<Dormitory>, TableRow<Dormitory>>() {
            @Override
            public TableRow<Dormitory> call(TableView<Dormitory> param) {
                TableRow<Dormitory> row = new TableRow<Dormitory>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        if (event.getClickCount() == 2 && (! row.isEmpty()) )
                        {
                            System.out.println(row.getIndex());
                            Dormitory item = row.getItem();
                            System.out.println(item);
                            if (DormitoryController.initData(item)){
                                ((DormitoryController) ManagedSystemApplication.controllers.get(DormitoryController.class.getSimpleName())).init();
//                                System.out.println(DormitoryController.class.getSimpleName());
                            }
                            ManagedSystemApplication.showView(DormitoryView.class,Modality.APPLICATION_MODAL);
                        }
                    }
                });
                return row;
            }
        });
    }
    private void bindProperty(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        stage.setCellValueFactory(new PropertyValueFactory<>("stageString"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        days.setCellValueFactory(new PropertyValueFactory<>("startTimeString"));
//        tenant.setCellValueFactory(new PropertyValueFactory<>("tenant"));
    }

    private void dormitoryList(int type) {
        List<Dormitory> dormitoryList;
        if (type == 0) {
            dormitoryList = dormitoryService.findAll();
        } else {
            dormitoryList = dormitoryService.findByType(type);
        }
        //向表格中填充结果
        ObservableList<Dormitory> items = FXCollections.observableArrayList(dormitoryList);
        dormitory.setItems(items);
    }

    @FXML
    public void refresh(){
        dormitoryList(listView.getSelectionModel().getSelectedIndex());
    }

    /**
     * 显示添加居住页面
     */
    @FXML
    public void stayAction() {
        ManagedSystemApplication.showView(StayView.class,Modality.WINDOW_MODAL);
    }

    public void manage(){
        if (!ManagedSystemApplication.ROOT){
            alert.setHeaderText("您无此权限！");
            alert.showAndWait();
            return;
        }
        ManagedSystemApplication.showView(AdministratorsView.class,Modality.WINDOW_MODAL);
    }

    @FXML
    TextField manageName;
    @FXML
    ChoiceBox manageType;
    @FXML
    ChoiceBox manageStage;
    private Dormitory getmanageDoemitory() {
        Dormitory dormitory = new Dormitory();
        dormitory.setName(manageName.getText());
        dormitory.setStage(TransformUtil.stageInt(String.valueOf(manageStage.getValue())));
        dormitory.setType(Integer.parseInt(String.valueOf(manageType.getValue())));
        return dormitory;
    }

    @FXML
    public void add() {
        String name = manageName.getText();
        if (name.equals("")) {
            alert.setHeaderText("请先填宿舍名！");
        }else if (dormitoryService.findByName(name) != null){
            alert.setHeaderText("该宿舍已存在！");
            manageName.clear();
        }else {
            dormitoryService.insert(getmanageDoemitory());
            System.out.println(getmanageDoemitory());
            alert.setHeaderText("添加成功！");
            manageName.clear();
            refresh();
        }
        alert.showAndWait();
    }

    @FXML
    public void modify() {
        if (manageName.getText().equals("")) {
            alert.setHeaderText("请先填宿舍名！");
        }else if (dormitoryService.update(getmanageDoemitory())){
            alert.setHeaderText("修改成功！");
            manageName.clear();
            refresh();

        }else {
            alert.setHeaderText("该宿舍不存在!");
            manageName.clear();
        }
        alert.showAndWait();
    }

    @FXML
    public void delete(){
        String name = manageName.getText();
        if (name.equals("")) {
            alert.setHeaderText("请先填宿舍名！");
        }else if (dormitoryService.deleteByName(name)>0) {
            alert.setHeaderText("删除成功!");
            manageName.clear();
            refresh();
        }else {
            alert.setHeaderText("该宿舍不存在!");
            manageName.clear();
        }
        alert.showAndWait();
    }
}
