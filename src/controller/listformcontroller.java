package controller;

import db.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class listformcontroller {


    public AnchorPane root;
    public Label lblhi;
    public Label lbluserid;
    public Pane subroot;
    public TextField txtdescription;
    public TextField txtdAndU;
    public Button btnupdate;
    public Button btndelete;


    public void initialize(){

        lblhi.setText("Hi "+logingformcontroller.usernamelbl+" Welcome To Todo List!");
        lbluserid.setText(logingformcontroller.userIdlbl);
        subroot.setVisible(false);
    }


    public void btnaddlistOnaction(ActionEvent actionEvent) {
     addtodolistNew();


    }

    public String todoidgenrator(){
        String toId =null;
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from todo order by id desc limit 1");
            boolean next = resultSet.next();
            if(next){
                String todoId = resultSet.getString(1);
                todoId = todoId.substring(1, todoId.length());
                int todo = Integer.parseInt(todoId);
                todo++;
                if(todo<10){

                    toId="T000"+todo;
                }
                else if(todo<100){
                    toId="T00"+todo;
                }
                else if(todo<1000){
                    toId="T0"+todo;
                }
                else{
                    toId ="T"+todo;
                }


            }
            else{
                toId ="T0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return toId;

    }



    public void btnUpdateOnaction(ActionEvent actionEvent) {


    }



    public void btndeleteOnAction(ActionEvent actionEvent) {
addtodolistNew();

    }

    public void addtodolistNew(){
        String todoId = todoidgenrator();
        String description = txtdescription.getText();
        String userid = lbluserid.getText();
        Connection connection = DBconnection.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("insert into todo values(?,?,?)");
            preparedStatement.setObject(1,todoId);
            preparedStatement.setObject(2,userid);
            preparedStatement.setObject(3,description);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        subroot.setVisible(false);
        txtdescription.clear();
    }




    public void btnAddnewTodoOnacction(ActionEvent actionEvent) {
        subroot.setVisible(true);
        txtdescription.requestFocus();


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

    public void txtdescriptionOnaction(ActionEvent actionEvent) {

    }
}
