package com.example.quran;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.quran.ServerClient.check;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        VBox mainBox = new VBox(15);
        mainBox.setId("mainBox");
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(100, 40, 40, 40));

        Label title = new Label("Login");
        title.setId("title");


        HBox usernameBox = new HBox(10);
        usernameBox.setAlignment(Pos.CENTER_LEFT);
        ImageView userIcon = new ImageView(new Image(getClass().getResourceAsStream("/img/user.png")));
        userIcon.setFitWidth(16);
        userIcon.setFitHeight(16);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameBox.setId("inputField");
        usernameBox.getChildren().addAll(userIcon, usernameField);


        HBox invalidAlert = new HBox();
        Label invalid = new Label("invalid password or wrong user name");
        invalid.getStyleClass().add("invalid");


        HBox passwordBox = new HBox(10);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        ImageView passIcon = new ImageView(new Image(getClass().getResourceAsStream("/img/lock.png")));
        passIcon.setFitWidth(16);
        passIcon.setFitHeight(16);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordBox.setId("inputField");
        passwordBox.getChildren().addAll(passIcon, passwordField);


        HBox optionsBox = new HBox(20);
        Label forgotLogin = new Label("Forgot login?");
        forgotLogin.setId("link");
        optionsBox.getChildren().addAll(forgotLogin);
        VBox.setMargin(optionsBox, new Insets(10, 0, 20, 0));

        Button loginBtn = new Button("Login");
        loginBtn.setId("loginButton");

        usernameField.setOnKeyReleased(e -> {

            invalidAlert.getChildren().remove(invalid);
        });

        loginBtn.setOnAction(e -> {

            if (check(usernameField.getText().concat(" " + passwordField.getText()))) {
                DashBoard.show(stage);
            } else {
                usernameField.setText("");
                passwordField.setText("");
                invalid.setText("invalid password or wrong user name");
                invalidAlert.getChildren().add(invalid);
            }
        });

        Button googleBtn = new Button("Login with Google");
        googleBtn.setId("googleButton");
        ImageView googleIcon = new ImageView(new Image(getClass().getResourceAsStream("/img/gg.jpeg")));
        googleIcon.setFitWidth(20);
        googleIcon.setFitHeight(20);
        googleBtn.setGraphic(googleIcon);
        googleBtn.setGraphicTextGap(10);
        VBox.setMargin(googleBtn, new Insets(10, 0, 0, 0));

        HBox signUpBox = new HBox(5);
        signUpBox.setAlignment(Pos.CENTER);
        Label dontHave = new Label("Don't have an account?");
        dontHave.setId("text");
        Label signUp = new Label("Sign Up");
        signUp.setId("link");
        signUpBox.getChildren().addAll(dontHave, signUp);
        VBox.setMargin(signUpBox, new Insets(20, 0, 0, 0));


        mainBox.getChildren().addAll(title, usernameBox, passwordBox, invalidAlert, optionsBox, loginBtn, googleBtn, signUpBox);

        Scene scene = new Scene(mainBox, 380, 600);
        stage.setScene(scene);
        scene.getStylesheets().add(HelloApplication.class.getResource("/CSS/style.css").toExternalForm());
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}