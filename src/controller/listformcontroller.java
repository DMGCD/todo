package controller;

import TM.todoTM;
import db.DBconnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class listformcontroller {


    public AnchorPane root;
    public Label lblhi;
    public String selectId =null;
    public Label lbluserid;
    public Pane subroot;
    public TextField txtdescription;
    public TextField txtdAndU;
    public Button btnupdate;
    public Button btndelete;
    public ListView<todoTM>lsttodo;


    public void initialize(){

        lblhi.setText("Hi "+logingformcontroller.usernamelbl+" Welcome To Todo List!");
        lbluserid.setText(logingformcontroller.userIdlbl);
        subroot.setVisible(false);
         listload();
         dulDisable(true);
         lsttodo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<todoTM>() {
             @Override
             public void changed(ObservableValue<? extends todoTM> observable, todoTM oldValue, todoTM newValue) {
                 todoTM selectedItem = lsttodo.getSelectionModel().getSelectedItem();

                 selectId =selectedItem.getId();


                 dulDisable(false);
                 subroot.setVisible(false);
                 txtdAndU.requestFocus();

                 if(selectedItem == null){
                     return;
                 }
                 txtdAndU.setText(selectedItem.getDescription());
                 txtdAndU.requestFocus();


             }
         });

    }


    public void btnaddlistOnaction() {
     addtodolistNew();
     dulDisable(true);



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
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update todo set description=? where id =? ");
            preparedStatement.setObject(1,txtdAndU.getText());
            preparedStatement.setObject(2,selectId);
            preparedStatement.executeUpdate();
            listload();
            txtdAndU.clear();
            dulDisable(true);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    public void btndeleteOnAction(ActionEvent actionEvent) {




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
        listload();
    }




    public void btnAddnewTodoOnacction(ActionEvent actionEvent) {
        lsttodo.getSelectionModel().clearSelection();
        subroot.setVisible(true);
        txtdescription.requestFocus();
        dulDisable(true);
        txtdAndU.clear();




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
        addtodolistNew();
        listload();

    }




    public void listload() throws RuntimeException {

        ObservableList<todoTM> items = lsttodo.getItems();
        items.clear();
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from todo where userid=?");
            preparedStatement.setObject(1,lbluserid.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                String id = resultSet.getString(1);
                String userid =resultSet.getString(2);
                String description=resultSet.getString(3);
                todoTM TODOTmobj = new todoTM(id,description,userid);
                items.add(TODOTmobj);

            }
            lsttodo.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dulDisable(boolean x){

        btndelete.setDisable(x);
        btnupdate.setDisable(x);
        txtdAndU.setDisable(x);
    }
}
