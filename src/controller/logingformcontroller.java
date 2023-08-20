package controller;

import db.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logingformcontroller {
    public static String usernamelbl;
    public static String userIdlbl;

    public TextField txtusername;
    public PasswordField txtpassword;
    public AnchorPane root;

    public void lblCreateaccountOnmouseClick(MouseEvent mouseEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/createform.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Create Account ");
        stage.centerOnScreen();




    }

    public void btnlogingOnAction(ActionEvent actionEvent)  {
        login();



    }


    public void txtpasswordOnAction(ActionEvent actionEvent) {
        login();
    }

    public void  login(){

        String username = txtusername.getText();
        String password = txtpassword.getText();
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from user where username=? and password =?");
            preparedStatement.setObject(1,username);
            preparedStatement.setObject(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean here = resultSet.next();
            if(here){
               userIdlbl= resultSet.getString(1);
                usernamelbl=resultSet.getString(6);

                Parent parent=FXMLLoader.load(this.getClass().getResource("../view/listform.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("List Todo");
                stage.centerOnScreen();
            }
            else{

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "CHECK PASSWORD AND USER NAME ARE CORRECT!");
                alert.showAndWait();

                txtusername.clear();
                txtpassword.clear();
                txtusername.requestFocus();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }



    }

}
