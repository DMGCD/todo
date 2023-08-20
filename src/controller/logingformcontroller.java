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

public class logingformcontroller {

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

    public void btnlogingOnAction(ActionEvent actionEvent) throws IOException {
        // is the  check password and username are correct it canbee navigate  list form
        logintolist();



    }
    public void logintolist() throws IOException {

        Parent parent =FXMLLoader.load(this.getClass().getResource("../view/listform.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Todo List");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
