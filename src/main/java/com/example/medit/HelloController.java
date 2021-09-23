package com.example.medit;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private ImageView searchButton;

    @FXML
    private TextField searchArea;

    public Label getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    public void setSearchButton(ImageView searchButton) {
        this.searchButton = searchButton;
    }

    public ImageView getSearchButton() {
        return searchButton;
    }

    public TextField getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(TextField searchArea) {
        this.searchArea = searchArea;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPassField() {
        return passField;
    }

    public void setPassField(PasswordField passField) {
        this.passField = passField;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passField;

    public TextField getSignUpUsernameField() {
        return signUpUsernameField;
    }

    public void setSignUpUsernameField(TextField signUpUsernameField) {
        this.signUpUsernameField = signUpUsernameField;
    }

    public PasswordField getSignUpPassField() {
        return signUpPassField;
    }

    public void setSignUpPassField(PasswordField signUpPassField) {
        this.signUpPassField = signUpPassField;
    }

    public TextField getSignUpEmailField() {
        return signUpEmailField;
    }

    public void setSignUpEmailField(TextField signUpEmailField) {
        this.signUpEmailField = signUpEmailField;
    }

    @FXML
    private TextField signUpUsernameField;

    @FXML
    private PasswordField signUpPassField;

    @FXML
    private TextField signUpEmailField;

    @FXML
    public void authenticateClick(MouseEvent mouseEvent) throws SQLException {

        //Solução errada mas funciona, dá uma olhada dps

        //aplicacao .isAuthenticated(  window aqui  );

        MainScreen.isAuthenticated(MainScreen.getController(), MainScreen.getApplication());

    }

    @FXML
    public void createAccountOnMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        MainScreen.registerAccount(MainScreen.getController(), MainScreen.getApplication());

    };

    @FXML
    public void signUpOnMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        MainScreen.changeScreen(MainScreen.getController(), MainScreen.getApplication(), "signup-form.fxml");

    }

    @FXML
    public void signInOnMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        MainScreen.changeScreen(MainScreen.getController(), MainScreen.getApplication(), "login-form.fxml");

    }

    @FXML
    public void onSearchMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        String searchable = searchArea.getText();

        if(searchable.isBlank()){ return; }

        ResultSet resultado = MainScreen.getApplication().getConexao().createStatement().executeQuery("select count(drug_id) from drug where dname like '%"+searchable+"%';");

        if(resultado.next()){
            if(resultado.getInt(1) >= 15){
                System.out.println("Muitos resultados.");
                return;
            }
            if(resultado.getInt(1) <= 0){
                System.out.println("Nenhum resultado.");

                MainScreen.getApplication().startScreen(MainScreen.getApplication().getWindow(), "error-404.fxml");

                MainScreen.getController().getSearchArea().setText(searchable);

                MainScreen.getController().getSearchArea().setFocusTraversable(false);

                return;
            }
        }

        resultado = HelloApplication.getConexao().createStatement().executeQuery("select dname from drug where dname like '%"+searchable+"%' order by dname;");

        while (resultado.next()){
            System.out.println(resultado.getString("dname"));
        }

        MainScreen.getApplication().startScreen(MainScreen.getApplication().getWindow(), "hello-view.fxml");

        MainScreen.getController().getSearchArea().setText(searchable);

        MainScreen.getController().getSearchArea().setFocusTraversable(false);

    }

    @FXML
    public void initialize(){

        System.out.println("test");

        MainScreen.setController(this);

    }

}