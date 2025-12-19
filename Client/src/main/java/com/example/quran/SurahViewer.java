package com.example.quran;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;

public class SurahViewer {

    public static void show(Stage stage, int surahNumber, ArrayList<javafx.scene.image.Image> images) {

        VBox box = new VBox(5);
        box.getStyleClass().add("box");
        box.setAlignment(Pos.CENTER);

        Button back = new Button("go back");
        back.getStyleClass().add("back");

        back.setOnAction(e -> {
            DashBoard.show(stage);
        });

        Image imgback = new Image(DashBoard.class.getResourceAsStream("/img/back.png"));
        ImageView imgSearchView = new ImageView(imgback);
        imgSearchView.setFitWidth(30);
        imgSearchView.setFitHeight(25);

        HBox Hbox = new HBox();
        Hbox.setAlignment(Pos.BASELINE_LEFT);
        Hbox.getChildren().add(back);
        back.setGraphic(imgSearchView);

        box.getChildren().add(Hbox);

        for (Image img : images) {
            ImageView view = new ImageView(img);
            view.setFitWidth(350);
            view.setPreserveRatio(true);
            box.getChildren().add(view);
        }

        ScrollPane scroll = new ScrollPane(box);
        scroll.setFitToWidth(true);
        scroll.setPannable(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene scene = new Scene(scroll, 400, 650);
        scene.getStylesheets().add(SurahViewer.class.getResource("/CSS/view.css").toExternalForm());
        stage.setScene(scene);
    }
}

