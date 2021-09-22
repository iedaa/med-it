package com.example.medit;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private static ImageView searchButton;

    @FXML
    private TextField searchArea;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passField;

    @FXML
    public void authenticateClick(MouseEvent mouseEvent) throws SQLException {

        String username = usernameField.getText();
        String password = passField.getText();

        ResultSet resultado = HelloApplication.getConexao().createStatement().executeQuery("select username, userpw from users where username = '"+username+"' and userpw = '"+password+"'");

        if(resultado.next()){
            System.out.println(resultado.getString("username")+" e "+resultado.getString("userpw")+" são corretos!");
        }else{
            System.out.println("Conta não encontrada...");
        }

    }

    @FXML
    public void onSearchMouseClicked(MouseEvent mouseEvent) throws SQLException {

        String searchable = searchArea.getText();

        ResultSet resultado = HelloApplication.getConexao().createStatement().executeQuery("select count(drug_id) from drug where dname like '%"+searchable+"%';");


        if(resultado.next()){
            if(resultado.getInt(1) >= 15){
                System.out.println("Muitos resultados.");
                return;
            }
            if(resultado.getInt(1) <= 0){
                System.out.println("Nenhum resultado.");
                return;
            }
        }

        resultado = HelloApplication.getConexao().createStatement().executeQuery("select dname from drug where dname like '%"+searchable+"%' order by dname;");

        while (resultado.next()){
            System.out.println(resultado.getString("dname"));
        }

    }
}