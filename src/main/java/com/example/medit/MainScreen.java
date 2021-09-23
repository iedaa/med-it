package com.example.medit;

import javafx.application.Application;
import javafx.scene.control.Alert;

import javax.mail.internet.AddressException;
import javax.xml.transform.Result;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.internet.InternetAddress;

public class MainScreen {

    private static HelloController controller;
    private static HelloApplication application;
    private static String loggedUser = null;

    public static HelloController getController() {
        return controller;
    }

    public static void setController(HelloController controller) {
        MainScreen.controller = controller;
    }
    
    public static HelloApplication getApplication() {
        return application;
    }

    public static void setApplication(HelloApplication application) {
        MainScreen.application = application;
    }


    //Esse método estático serve para alterar a tela atual para uma outra designada pela String filename.
    public static void changeScreen(HelloController controlador, HelloApplication application, String filename) throws IOException {

        application.startScreen(application.getWindow(),filename);

    };

    public static void registerAccount(HelloController controlador, HelloApplication application) throws SQLException, IOException {

        ResultSet resultado;

        //System.out.println(controlador.getSignUpUsernameField().getText()+" - "+controlador.getSignUpPassField().getText()+" - "+controlador.getSignUpEmailField().getText());

        ArrayList<String> warningList = new ArrayList<String>();

        resultado = application.getConexao().createStatement().executeQuery("select exists(select * from users where username = '"+controlador.getSignUpUsernameField().getText()+"');");

        if(resultado.next() && resultado.getInt(1) == 1){ //No caso, se existir e for igual a 1, já há alguém com esse username

            warningList.add("Nome de usuário já está sendo utilizado\n");

        }

        resultado = application.getConexao().createStatement().executeQuery("select exists(select * from users where email = '"+controlador.getSignUpEmailField().getText()+"');");

        if(resultado.next() && resultado.getInt(1) == 1){ //No caso, se existir e for igual a 1, já há alguém com esse email

            warningList.add("E-mail já está sendo utilizado\n");

        }


        try {
            InternetAddress email = new InternetAddress(MainScreen.getController().getSignUpEmailField().getText(), true);
            email.validate();
        } catch (AddressException e) {
            warningList.add("E-mail não é válido\n");
        }

        if(warningList.size()<=0){

            application.getConexao().createStatement().executeUpdate("insert into users values ('"+controlador.getSignUpUsernameField().getText()+"',default,'"+controlador.getSignUpPassField().getText()+"','"+controlador.getSignUpEmailField().getText()+"','user',default);");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Autenticada");
            alert.setHeaderText(null);
            alert.setContentText("Conta criada com sucesso!");

            alert.showAndWait();

            application.startScreen(application.getWindow(), "login-form.fxml");

        }else{

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText(warningList.toString().replace("[", "").replace("]", "").replace(", ", "")+"Tente novamente utilizando dados diferentes.");

            alert.show();

        }

    }

    public static boolean isAuthenticated(HelloController controlador, HelloApplication application) throws SQLException {

        String username = controlador.getUsernameField().getText();
        String password = controlador.getPassField().getText();

        ResultSet resultado = application.getConexao().createStatement().executeQuery("select username, userpw from users where username = '"+username+"' and userpw = '"+password+"'");

        if(resultado.next()){
            System.out.println("Autenticada!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Autenticada");
            alert.setHeaderText(null);
            alert.setContentText("Sua conta foi autenticada com sucesso!");

            alert.showAndWait();

            try {

                loggedUser = username;

                application.startScreen(application.getWindow(), "hello-view.fxml");

                changeScreen(controlador,application,"hello-view.fxml");

            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;

        }else{
            System.out.println("Conta não encontrada por algum motivo....");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Nome de usuário ou senha invalidos! Tente novamente.");

            alert.show();

            return false;

        }

    }

}
