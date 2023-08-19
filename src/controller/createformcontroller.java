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
    public TextField txtusername;

    public void initialize(){
        disbtn(true);
        visilbl(false);



    }

    public void btnRegisterOnaction(ActionEvent actionEvent) throws IOException {
        String firstname = txtfname.getText();
        String lastname = txtlname.getText();
        String email = txtemail.getText();
        String idnumber = txtidnumber.getText();
        String password = txtpassword.getText();
        String confirmpassword = txtconfirmpassword.getText();
        String username = txtusername.getText();
        // password are matching and data add the  database
        if(password.equals(confirmpassword)){


            setpasswordcolor("transparant");
            visilbl(false);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Register Are Complited", ButtonType.OK);
            alert.showAndWait();

            Parent parent =FXMLLoader.load(this.getClass().getResource("../view/loginigform.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Loging");
            stage.centerOnScreen();



        }
        else{
            setpasswordcolor("red");
            visilbl(true);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Not Matched Password!", ButtonType.OK);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.get().equals(ButtonType.OK)){
                visilbl(false);
                txtpassword.clear();
                txtconfirmpassword.clear();
                txtpassword.requestFocus();
            }






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

    // disable txt field
    public void disbtn(Boolean x){

        btnregister.setDisable(x);
        txtfname.setDisable(x);
        txtlname.setDisable(x);
        txtemail.setDisable(x);
        txtidnumber.setDisable(x);
        txtpassword.setDisable(x);
        txtconfirmpassword.setDisable(x);
        txtusername.setDisable(x);




    }
// visible not matchin labale of password
    public void visilbl(boolean x){

        lblnotmaching1.setVisible(x);
        lblnotmaching2.setVisible(x);
    }
    public void setpasswordcolor( String n){

        txtconfirmpassword.setStyle("-fx-border-color:"+n);
        txtpassword.setStyle("-fx-border-color:"+n);
    }
}
