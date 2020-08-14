# SpringBoot2.3.1.RELEASE与javaFx整合

### 加入maven依赖

```xml &lt;dependency&gt;
 <dependency>
     <groupId>de.roskenet</groupId>
     <artifactId>springboot-javafx-support</artifactId>
     <version>2.1.6</version>
</dependency>
```

### 启动类继承AbstractJavaFxApplicationSupport

![启动类](./images/1597375394146.png)

### 采用mvc模式

- 每个fxml页面都有对应的java视图类

![视图](./images/1597375588.png)

- 视图类需继承**AbstractFxmlView**且有**@FXMLView注解**（value值为对应的fxml文件路径）

![视图类](./images/1597375950(1).jpg)

- fxml文件中需指定对应controller类

![fxml文件](./images/1597376230(1).jpg)

- controller类需实现**Initializable**接口并加上**@FXMLController**注解(属性上有FXML注解的会根据属性名与fxml中的fx:id值对应的组件绑定，方便获取值等信息)

![](./images/1597376444(1).jpg)

![](./images/1597376926(1).jpg)

- fxml中的绑定事件名前面需加个#，即可绑定对应controller的同名方法

![](./images/1597377406(1).jpg)

![](./images/1597377507(1).jpg)

### 示例页面效果展示(只是简单项目，页面可能有点丑)

- 登陆页面

![登陆](./images/1597377745(1).jpg)

- 主页面

![主页](./images/1597377865(1).jpg)

- 点击住宿按钮弹出住宿页面进行信息登记

![](./images/1597377915(1).jpg)

- 双击对应表格中的宿舍即可查看详情

![](./images/1597378007(1).jpg)

- 宿舍管理页面

![](./images/1597378103(1).jpg)

### 获取项目

- [Git仓库地址](https://github.com/czmDeRepository/javaFx)

- **注：运行项目前先在数据库建表,可直接执行sql文件：management_system.sql**

### END