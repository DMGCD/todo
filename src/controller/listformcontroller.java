package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class listformcontroller {


    public AnchorPane root;

    public void btnaddlistOnaction(ActionEvent actionEvent) {


    }

    public void btnUpdateOnaction(ActionEvent actionEvent) {


    }

    public void btndeleteOnAction(ActionEvent actionEvent) {


    }

    public void btnAddnewTodoOnacction(ActionEvent actionEvent) {


    }

    public void btnLogoutOnaction(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/loginigform.fxml"));
        Scene scene = new Scene(parent);
        Stage stage =(Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("loging");
        stage.centerOnScreen();
    }
}
