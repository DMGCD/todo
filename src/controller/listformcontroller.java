package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class listformcontroller {


    public AnchorPane root;
    public Label lblhi;
    public Label lbluserid;
    public void initialize(){

        lblhi.setText("Hi "+logingformcontroller.usernamelbl+" Welcome To Todo List!");
        lbluserid.setText(logingformcontroller.usernamelbl);
    }

    public void btnaddlistOnaction(ActionEvent actionEvent) {


    }

    public void btnUpdateOnaction(ActionEvent actionEvent) {


    }

    public void btndeleteOnAction(ActionEvent actionEvent) {


    }

    public void btnAddnewTodoOnacction(ActionEvent actionEvent) {


    }

    public void btnLogoutOnaction(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To LogOut !", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)){
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/loginigform.fxml"));
            Scene scene = new Scene(parent);
            Stage stage =(Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("loging");
            stage.centerOnScreen();
        }


    }
}
