package com.example.medit;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {

    private Stage window;

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    private static Connection conexao = null;

    public static Connection getConexao() {
        return conexao;
    }

    public static void setConexao(Connection conexao) {
        HelloApplication.conexao = conexao;
    }

    @Override
    public void start(Stage stage) throws IOException {

        window = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 716, 642);
        window.setTitle("Med It!");
        window.setScene(scene);
        window.show();
        window.setResizable(false);

        MainScreen.setApplication(this);

    }

    public void startScreen(Stage stage, String filename) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(filename));
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

    public Image createImage(String address) throws IOException{

        URL teste = getClass().getResource(address);

        BufferedImage image = ImageIO.read(teste);

        Image newImage = SwingFXUtils.toFXImage(image, null);

        return newImage;

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        try {

            setConexao(DriverManager.getConnection("jdbc:mysql://localhost:3306/meditfarmacia","root",""));

        } catch (SQLException e) {

            System.out.println("Errinho:\n" +e);

        } finally { }

        launch();

    }
}