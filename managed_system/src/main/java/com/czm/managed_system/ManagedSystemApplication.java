package com.czm.managed_system;

import com.czm.managed_system.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@SpringBootApplication
public class ManagedSystemApplication extends AbstractJavaFxApplicationSupport {
    /**
     * 存放控制器
     */
    public static Map<String, Object> controllers = new HashMap<String, Object>();

    /**
     *  root用户标志
     */
    public static boolean ROOT = false;

    public static void main(String[] args){

//        launchApp(ManagedSystemApplication.class,IndexView.class,args);
        launch(ManagedSystemApplication.class,LoginView.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("退出确认框");
                //设置对话框的 icon 图标
                alert.initOwner(stage);
                alert.setHeaderText("您确定要现在退出程序？");
                Optional result = alert.showAndWait();
                if (result.get() == ButtonType.CANCEL) {
                    event.consume();
                }

            }
        });
        super.start(stage);
    }

}