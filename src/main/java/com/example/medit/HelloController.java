package com.example.medit;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private Label usernameLabel;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label searchResultLabel, drugTitle, drugPrice, drugUnits, drugPrescription, drugAbout, drugId;

    @FXML
    private Circle circleImage;

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

    public Circle getCircleImage() {
        return circleImage;
    }

    public void setCircleImage(Circle circleImage) {
        this.circleImage = circleImage;
    }

    @FXML
    public void signUpOnMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        MainScreen.changeScreen(MainScreen.getController(), MainScreen.getApplication(), "signup-form.fxml");

    }

    @FXML
    public void signInOnMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        MainScreen.changeScreen(MainScreen.getController(), MainScreen.getApplication(), "login-form.fxml");

    }

    @FXML
    public void logOutOnMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        MainScreen.logout(MainScreen.getApplication());

    }

    public Label getSearchResultLabel() {
        return searchResultLabel;
    }

    public void setSearchResultLabel(Label searchResultLabel) {
        this.searchResultLabel = searchResultLabel;
    }

    public Label getDrugId() {
        return drugId;
    }

    public void setDrugId(Label drugId) {
        this.drugId = drugId;
    }

    public Label getDrugTitle() {
        return drugTitle;
    }

    public void setDrugTitle(Label drugTitle) {
        this.drugTitle = drugTitle;
    }

    public Label getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(Label drugPrice) {
        this.drugPrice = drugPrice;
    }

    public Label getDrugUnits() {
        return drugUnits;
    }

    public void setDrugUnits(Label drugUnits) {
        this.drugUnits = drugUnits;
    }

    public Label getDrugPrescription() {
        return drugPrescription;
    }

    public void setDrugPrescription(Label drugPrescription) {
        this.drugPrescription = drugPrescription;
    }

    public Label getDrugAbout() {
        return drugAbout;
    }

    public void setDrugAbout(Label drugAbout) {
        this.drugAbout = drugAbout;
    }

    @FXML
    public void onSearchMouseClicked(MouseEvent mouseEvent) throws SQLException, IOException {

        String searchable = searchArea.getText();

        if(searchable.isBlank()){
            MainScreen.getApplication().startScreen(MainScreen.getApplication().getWindow(), "hello-view.fxml");

            MainScreen.getController().getSearchArea().setText(searchable);

            MainScreen.getController().getSearchArea().setFocusTraversable(false);

            return;
        }

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

        MainScreen.setSearchTerm(searchable);

        MainScreen.getApplication().startScreen(MainScreen.getApplication().getWindow(), "search-list.fxml");

        MainScreen.getController().getSearchArea().setText(searchable);

        MainScreen.getController().getSearchArea().setFocusTraversable(false);

        MainScreen.getController().getSearchResultLabel().setText("Resultados para '"+searchable+"'");

    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    @FXML
    public void initialize() throws SQLException {

        if(this.getListView() != null){

            String searchable = MainScreen.getSearchTerm();

            ObservableList<String> searchOp = FXCollections.observableArrayList(MainScreen.searchResults(this, MainScreen.getApplication(), searchable));

            getListView().setItems(searchOp);

        }

        if(this.getUsernameLabel() != null){
            this.getUsernameLabel().setText(MainScreen.getLoggedUser());
        }

        MainScreen.setController(this);


        if(getListView() != null){

            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if(getListView().getSelectionModel().getSelectedItem() == null){
                        return;
                    }

                    try {
                        MainScreen.setResults(MainScreen.getController(), MainScreen.getApplication(), getListView().getSelectionModel().getSelectedItem().toString());
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        }

    }

}