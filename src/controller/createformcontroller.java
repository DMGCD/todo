package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class createformcontroller {
    public TextField txtfname;
    public TextField txtlname;
    public TextField txtidnumber;
    public TextField txtemail;
    public PasswordField txtpassword;
    public PasswordField txtconfirmpassword;
    public AnchorPane root;
    public Button btncancel;
    public Button btnregister;
    public Label lblnotmaching1;
    public Label lblnotmaching2;

    public void initialize(){
        disbtn(true);



    }

    public void btnRegisterOnaction(ActionEvent actionEvent) throws IOException {
        String firstname = txtfname.getText();
        String lastname = txtlname.getText();
        String email = txtemail.getText();
        String idnumber = txtidnumber.getText();
        String password = txtpassword.getText();
        String confirmpassword = txtconfirmpassword.getText();
        if(password.equals(confirmpassword)){

            Parent parent =FXMLLoader.load(this.getClass().getResource("../view/loginigform.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Loging");
            stage.centerOnScreen();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Register Are Complited", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Not Matched Password!", ButtonType.OK);
            alert.showAndWait();



        }



    }

    public void btnCancelOnaction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " YOU ARE NOT REGISTER !", ButtonType.NO,ButtonType.YES);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)){

            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/loginigform.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("loging");
            stage.centerOnScreen();


        }

    }




    public void btnaddnewuser(ActionEvent actionEvent) {

        disbtn(false);
    }

    public void disbtn(Boolean x){

        btnregister.setDisable(x);
        txtfname.setDisable(x);
        txtlname.setDisable(x);
        txtemail.setDisable(x);
        txtidnumber.setDisable(x);
        txtpassword.setDisable(x);
        txtconfirmpassword.setDisable(x);




    }
}
