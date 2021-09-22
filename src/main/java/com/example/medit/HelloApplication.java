package com.example.medit;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {

    private static Connection conexao = null;

    public static Connection getConexao() {
        return conexao;
    }

    public static void setConexao(Connection conexao) {
        HelloApplication.conexao = conexao;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 716, 642);
        stage.setTitle("Med It!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void splash(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("splash.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 716, 642);
        stage.setTitle("Med It!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void resultSearch(String searchable) throws SQLException {

        ResultSet resultado = getConexao().createStatement().executeQuery("select dname from drug where dname like '%"+searchable+"%' order by dname;");

        while (resultado.next()){
            System.out.println(resultado.getString("dname"));
        }

    };

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        try {

            setConexao(DriverManager.getConnection("jdbc:mysql://localhost:3306/meditfarmacia","root",""));

        } catch (SQLException e) {

            System.out.println("Errinho:\n" +e);

        } finally {


            /*
            String searchable = "comprimido";

            ResultSet resultado = getConexao().createStatement().executeQuery("select dname from drug where dname like '%"+searchable+"%' order by dname;");

            while (resultado.next()){
                System.out.println(resultado.getString("dname"));
            }

            resultado = getConexao().createStatement().executeQuery("select * from users;");

            while(resultado.next()){
                System.out.println(resultado.getString("realname"));
            }

             */



        }


        launch();

    }
}