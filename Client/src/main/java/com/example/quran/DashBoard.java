package com.example.quran;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.util.ArrayList;


public class DashBoard {
    static int x = 0;

    public static void show(Stage stage) {

        String[] surahNames = {
                "ٱلْفَاتِحَة", "ٱلْبَقَرَة", "آلِ عِمْرَان", "ٱلنِّسَاء", "ٱلْمَائِدَة", "ٱلْأَنْعَام", "ٱلْأَعْرَاف", "ٱلْأَنْفَال",
                "ٱلتَّوْبَة", "يُونُس", "هُود", "يُوسُف", "ٱلرَّعْد", "إِبْرَاهِيم", "ٱلْحِجْر", "ٱلنَّحْل",
                "ٱلْإِسْرَاء", "ٱلْكَهْف", "مَرْيَم", "طَه", "ٱلْأَنْبِيَاء", "ٱلْحَجّ", "ٱلْمُؤْمِنُون", "ٱلنُّور",
                "ٱلْفُرْقَان", "ٱلشُّعَرَاء", "ٱلنَّمْل", "ٱلْقَصَص", "ٱلْعَنْكَبُوت", "ٱلرُّوم", "لُقْمَان", "ٱلسَّجْدَة",
                "ٱلْأَحْزَاب", "سَبَإ", "فَاطِر", "يَس", "ٱلصَّافَّات", "ص", "ٱلزُّمَر", "غَافِر",
                "فُصِّلَت", "ٱلشُّورَى", "ٱلزُّخْرُف", "ٱلدُّخَان", "ٱلْجَاثِيَة", "ٱلْأَحْقَاف", "مُحَمَّد", "ٱلْفَتْح",
                "ٱلْحُجُرَات", "ق", "ٱلذَّارِيَات", "ٱلطُّور", "ٱلنَّجْم", "ٱلْقَمَر", "ٱلرَّحْمَن", "ٱلْوَاقِعَة",
                "ٱلْحَدِيد", "ٱلْمُجَادِلَة", "ٱلْحَشْر", "ٱلْمُمْتَحِنَة", "ٱلصَّف", "ٱلْجُمُعَة", "ٱلْمُنَافِقُون", "ٱلتَّغَابُن",
                "ٱلطَّلَاق", "ٱلْتَّحْرِيم", "ٱلْمَلِك", "ٱلْقَلَم", "ٱلْحَاقَّة", "ٱلْمَعَارِج", "نُوح", "ٱلْجِن",
                "ٱلْمُزَّمِّل", "ٱلْمَدَّثِّر", "ٱلْقِيَامَة", "ٱلْإِنسَان", "ٱلْمُرْسَلَات", "ٱلنَّبَأ", "ٱلنَّازِعَات", "عَبَس",
                "ٱلتَّكْوِير", "ٱلْإِنفِطَار", "ٱلْمُطَفِّفِينَ", "ٱلْإِنشِقَاق", "ٱلْبُرُوج", "ٱلطَّارِق", "ٱلْأَعْلَى", "ٱلْغَاشِيَة",
                "ٱلْفَجْر", "ٱلْبَلَد", "ٱلشَّمْس", "ٱلَّيْل", "ٱلضُّحَى", "ٱلشَّرْح", "ٱلتِّين", "ٱلْعَلَق",
                "ٱلْقَدْر", "ٱلْبَيِّنَة", "ٱلزَّلْزَلَة", "ٱلْعَادِيَات", "ٱلْقَارِعَة", "ٱلتَّكَاثُر", "ٱلْعَصْر", "ٱلْهُمَزَة",
                "ٱلْفِيل", "قُرَيْش", "ٱلْمَاعُون", "ٱلْكَوْثَر", "ٱلْكَافِرُون", "ٱلنَّصْر", "ٱلْمَسَد", "ٱلْإِخْلَاص",
                "ٱلْفَلَق", "ٱلنَّاس"
        };
        String[] surahNamesSearch = {
                "الفاتحة", "البقرة", "آل عمران", "النساء", "المائدة", "الأنعام", "الأعراف", "الأنفال",
                "التوبة", "يونس", "هود", "يوسف", "الرعد", "إبراهيم", "الحجر", "النحل",
                "الإسراء", "الكهف", "مريم", "طه", "الأنبياء", "الحج", "المؤمنون", "النور",
                "الفرقان", "الشعراء", "النمل", "القصص", "العنكبوت", "الروم", "لقمان", "السجدة",
                "الأحزاب", "سبإ", "فاطر", "يس", "الصافات", "ص", "الزمر", "غافر",
                "فصلت", "الشورى", "الزخرف", "الدخان", "الجاثية", "الأحقاف", "محمد", "الفتح",
                "الحجرات", "ق", "الذاريات", "الطور", "النجم", "القمر", "الرحمن", "الواقعة",
                "الحديد", "المجادلة", "الحشر", "الممتحنة", "الصف", "الجمعة", "المنافقون", "التغابن",
                "الطلاق", "التحريم", "الملك", "القلم", "الحاقة", "المعارج", "نوح", "الجن",
                "المزمل", "المدثر", "القيامة", "الإنسان", "المرسلات", "النبأ", "النازعات", "عبس",
                "التكوير", "الانفطار", "المطففين", "الانشقاق", "البروج", "الطارق", "الأعلى", "الغاشية",
                "الفجر", "البلد", "الشمس", "الليل", "الضحى", "الشرح", "التين", "العلق",
                "القدر", "البينة", "الزلزلة", "العاديات", "القارعة", "التكاثر", "العصر", "الهمزة",
                "الفيل", "قريش", "الماعون", "الكوثر", "الكافرون", "النصر", "المسد", "الإخلاص",
                "الفلق", "الناس"
        };

        String[] surahNamesEN = {
                "Al-Fatihah", "Al-Baqarah", "Al-Imran", "An-Nisa", "Al-Ma'idah", "Al-An'am", "Al-A'raf", "Al-Anfal",
                "At-Tawbah", "Yunus", "Hud", "Yusuf", "Ar-Ra'd", "Ibrahim", "Al-Hijr", "An-Nahl",
                "Al-Isra", "Al-Kahf", "Maryam", "Ta-Ha", "Al-Anbiya", "Al-Hajj", "Al-Mu'minun", "An-Nur",
                "Al-Furqan", "Ash-Shu'ara", "An-Naml", "Al-Qasas", "Al-Ankabut", "Ar-Rum", "Luqman", "As-Sajdah",
                "Al-Ahzab", "Saba", "Fatir", "Ya-Sin", "As-Saffat", "Sad", "Az-Zumar", "Ghafir",
                "Fussilat", "Ash-Shura", "Az-Zukhruf", "Ad-Dukhan", "Al-Jathiya", "Al-Ahqaf", "Muhammad", "Al-Fath",
                "Al-Hujurat", "Qaf", "Adh-Dhariyat", "At-Tur", "An-Najm", "Al-Qamar", "Ar-Rahman", "Al-Waqia",
                "Al-Hadid", "Al-Mujadila", "Al-Hashr", "Al-Mumtahina", "As-Saff", "Al-Jumu'a", "Al-Munafiqun", "At-Taghabun",
                "At-Talaq", "At-Tahrim", "Al-Mulk", "Al-Qalam", "Al-Haqqa", "Al-Ma'arij", "Nuh", "Al-Jinn",
                "Al-Muzzammil", "Al-Muddaththir", "Al-Qiyama", "Al-Insan", "Al-Mursalat", "An-Naba", "An-Nazi'at", "Abasa",
                "At-Takwir", "Al-Infitar", "Al-Mutaffifin", "Al-Inshiqaq", "Al-Buruj", "At-Tariq", "Al-A'la", "Al-Ghashiya",
                "Al-Fajr", "Al-Balad", "Ash-Shams", "Al-Lail", "Ad-Duha", "Ash-Sharh", "At-Tin", "Al-Alaq",
                "Al-Qadr", "Al-Bayyina", "Az-Zalzalah", "Al-Adiyat", "Al-Qari'a", "At-Takathur", "Al-Asr", "Al-Humazah",
                "Al-Fil", "Quraysh", "Al-Ma'un", "Al-Kawthar", "Al-Kafirun", "An-Nasr", "Al-Masad", "Al-Ikhlas",
                "Al-Falaq", "An-Nas"
        };



        Label head = new Label("Al Quran");
        HBox.setMargin(head, new Insets(0, 20, 0, 0));

        TextField searchField = new TextField();
        searchField.getStyleClass().add("custom-textfield");
        searchField.setPromptText("search by page number..");
        searchField.getStyleClass().add("searchField");


        Button btnSearch = new Button();
        Image imgSearch = new Image(DashBoard.class.getResourceAsStream("/img/searchIcon2.png"));
        ImageView imgSearchView = new ImageView(imgSearch);
        imgSearchView.setFitWidth(16);
        imgSearchView.setFitHeight(16);
        btnSearch.setGraphic(imgSearchView);
        btnSearch.getStyleClass().add("search-button");

        btnSearch.setOnAction(e -> {
            try {
                String s = searchField.getText();
                if (!s.matches("\\d+")) {
                    for (int i = 0; i < 114; i++) {
                        if (surahNamesSearch[i].equals(s)) {
                            ArrayList<Image> images = ServerClient.requestSurah(i + 1);
                            SurahViewer.show(stage, i + 1, images);
                            break;
                        }
                    }

                }
                int pageNumber = Integer.parseInt(searchField.getText());
                if (pageNumber <= 604 && pageNumber >=1 ) {
                    ArrayList<Image> images = ServerClient.requestPage(pageNumber);
                    SurahViewer.show(stage, pageNumber, images);
                }


            } catch (NumberFormatException ex) {
                System.out.println("Enter valid page number");
            }
        });
        Image imghead = new Image(DashBoard.class.getResourceAsStream("/img/head (2).png"));
        ImageView imgheadView = new ImageView(imghead);
        imgheadView.setFitWidth(380);
        imgheadView.setFitHeight(250);
        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.CENTER);

        VBox.setMargin(imageBox, new Insets(-20, -40, -50, -40));
        imageBox.getChildren().addAll(imgheadView);
        imageBox.getStyleClass().add("headImg");


        VBox heading = new VBox();
        heading.getStyleClass().add("heading");

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);

        header.getChildren().addAll(head, searchField, btnSearch);
        header.getStyleClass().add("header");

        heading.getChildren().addAll(header, imageBox);


        GridPane grid = new GridPane();
        grid.setVgap(3);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("grid");

        Image img = new Image(DashBoard.class.getResourceAsStream("/img/icon2.png"));


        for (int i = 0; i < 114; i++) {

            ImageView view = new ImageView(img);
            view.setFitWidth(40);
            view.setFitHeight(40);
            view.setPreserveRatio(true);

            Label imageLabel = new Label(String.valueOf(i + 1));
            imageLabel.getStyleClass().add("imageLabel");
            StackPane imageStack = new StackPane();
            imageStack.getChildren().addAll(view, imageLabel);
            StackPane.setAlignment(imageLabel, Pos.CENTER);

            Label label = new Label(surahNames[i]);
            Label labelE = new Label(surahNamesEN[i]);
            label.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            label.getStyleClass().add("labelAR");
            labelE.getStyleClass().add("labelE");


            HBox box = new HBox(10);
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(imageStack, labelE, label);
            box.getStyleClass().add("HBox");


            if (i == 0) {
                box.getStyleClass().add("first-cell");
            }

            final int surahIndex = i + 1;
            box.setOnMouseClicked((MouseEvent e) -> {
                ArrayList<Image> images = ServerClient.requestSurah(surahIndex);
                SurahViewer.show(stage, surahIndex, images);
            });


            grid.add(box, 0, i);
        }

        VBox ALLpage = new VBox();
        ALLpage.setAlignment(Pos.CENTER);
        ALLpage.getChildren().addAll(heading, grid);
        ALLpage.getStyleClass().add("ALLpage");

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(ALLpage);
        scroll.setFitToWidth(true);
        scroll.setPannable(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox root = new VBox(scroll, createBottomNav());
        Scene scene = new Scene(root, 400, 650);
        stage.setScene(scene);
        scene.getStylesheets().add(DashBoard.class.getResource("/CSS/dashboard.css").toExternalForm());
        stage.setTitle("Quran Surahs");
        stage.show();
    }

    private static HBox createBottomNav() {

        HBox bar = new HBox(40);
        bar.setAlignment(Pos.CENTER);

        bar.getChildren().addAll(
                navItem("/img/azkar.png", "الأذكار"),
                navItem("/img/prayer-rug.png", "الصلاة"),
                navItem("/img/home-button.png", "الرئيسية"),
                navItem("/img/calendar.png", "التقويم"),
                navItem("/img/application.png", "المزيد")
        );

        bar.getStyleClass().add("bottom-nav");
        return bar;
    }

    private static VBox navItem(String imgPath, String text) {

        ImageView icon;

        if (imgPath.equals("/img/home-button.png")) {
            icon = new ImageView(
                    new Image(DashBoard.class.getResourceAsStream(imgPath))
            );
            icon.setFitWidth(30);
            icon.setFitHeight(30);
        } else {
            icon = new ImageView(
                    new Image(DashBoard.class.getResourceAsStream(imgPath))
            );
            icon.setFitWidth(20);
            icon.setFitHeight(20);
        }
        icon.setPreserveRatio(true);


        Label label = new Label(text);

        VBox box = new VBox(icon, label);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("nav-item");

        return box;
    }
}
