package controller;

import db.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
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
    public Label lbluserid;

    public void initialize(){
        disbtn(true);
        visilbl(false);
        lbluserid.setVisible(false);



    }

    public void btnRegisterOnaction(ActionEvent actionEvent) throws IOException {
        register();

    }
    public void txtconfirmpasswordOnaction(ActionEvent actionEvent) throws IOException {
        register();
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
        txtfname.requestFocus();
        useridGenarator();
        lbluserid.setVisible(true);

    }

    // disable txt field
    public void disbtn(Boolean x){

        btnregister.setDisable(x);
        txtfname.setDisable(x);
        txtlname.setDisable(x);
        txtemail.setDisable(x);
        txtidnumber.setDisable(x);
        txtusername.setDisable(x);
        txtpassword.setDisable(x);
        txtconfirmpassword.setDisable(x);





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

    // userid genrater code
    public void useridGenarator(){
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from user order by id desc limit 1");

            boolean haveUser = resultSet.next();
            if(haveUser){
                String useridS = resultSet.getString(1);
                String useridnumb = useridS.substring(1, useridS.length());
                int useridint = Integer.parseInt(useridnumb);
                useridint++;
                String iduser;
                if(useridint<10){
                    iduser ="U000"+useridint;
                }
                else if(useridint<100){
                    iduser ="U00"+useridint;
                }
                else if(useridint<1000){
                    iduser="U0"+useridint;
                }

                else{
                    iduser=iduser ="U"+useridint;
                }
                lbluserid.setText(iduser);


            }
            else{
                lbluserid.setText("U0001");



            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void register() throws IOException {
        if(txtfname.getText().isEmpty()){
            txtfname.requestFocus();
        }
        else if(txtlname.getText().isEmpty()){
            txtlname.requestFocus();
        }
        else if(txtidnumber.getText().isEmpty()){
            txtidnumber.requestFocus();
        }
        else if(txtemail.getText().isEmpty()){
            txtemail.requestFocus();
        }
        else if(txtusername.getText().isEmpty()){
            txtusername.requestFocus();
        }
        else if(txtpassword.getText().isEmpty()){
            txtpassword.requestFocus();
        }
        else if(txtconfirmpassword.getText().isEmpty()){
            txtconfirmpassword.requestFocus();
        }
        else{
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm All Are Correct !", ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.get().equals(ButtonType.YES)){

                    Connection connection = DBconnection.getInstance().getConnection();
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
                        preparedStatement.setObject(1,lbluserid.getText());
                        preparedStatement.setObject(2,firstname);
                        preparedStatement.setObject(3,lastname);
                        preparedStatement.setObject(4,email);
                        preparedStatement.setObject(5,idnumber);
                        preparedStatement.setObject(6,username);
                        preparedStatement.setObject(7,password);
                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    Parent parent =FXMLLoader.load(this.getClass().getResource("../view/loginigform.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Loging");
                    stage.centerOnScreen();
                }
                else{
                    txtusername.clear();
                    txtfname.clear();
                    txtlname.clear();
                    txtemail.clear();
                    txtidnumber.clear();
                    txtpassword.clear();
                    txtconfirmpassword.clear();
                    txtfname.requestFocus();


                }





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



    }
}
