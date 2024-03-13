package com.example.sebuti14;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private static final int THUMBNAIL_SIZE = 150;

    private List<Image> images;
    private int currentIndex = 0;
    private Stage fullSizeImageStage;
    private ImageView fullSizeImageView;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f0f0;");

        images = loadImages();

        GridPane gridPane = createThumbnailGrid();
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Flower's Gallery Edition");
        primaryStage.show();
    }

    private GridPane createThumbnailGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        int col = 0;
        int row = 0;
        for (int i = 0; i < images.size(); i++) {
            Image image = images.get(i);
            ImageView thumbnail = createThumbnail(image);

            int finalI = i;
            thumbnail.setOnMouseClicked(event -> {
                currentIndex = finalI;
                showFullSizeImage(image);
            });

            gridPane.add(thumbnail, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        return gridPane;
    }

    private ImageView createThumbnail(Image image) {
        ImageView thumbnail = new ImageView(image);
        thumbnail.setFitWidth(THUMBNAIL_SIZE);
        thumbnail.setFitHeight(THUMBNAIL_SIZE);
        thumbnail.getStyleClass().add("thumbnail");
        return thumbnail;
    }

    private List<Image> loadImages() {
        // Load your images here
        List<Image> images = new ArrayList<>();
        images.add(new Image("flower.jpg"));
        images.add(new Image("flower1.jpg"));
        images.add(new Image("flower3.jpg"));
        images.add(new Image("flower2.jpg"));
        images.add(new Image("flower4.jpg"));
        images.add(new Image("flower5.jpg"));
        images.add(new Image("flower6.jpg"));
        images.add(new Image("flower7.jpg"));
        images.add(new Image("flower8.jpg"));
        images.add(new Image("flower9.jpg"));
        images.add(new Image("flower10.jpg"));
        images.add(new Image("flower11.jpg"));

        return images;
    }

    private void showFullSizeImage(Image image) {
        if (fullSizeImageStage != null) {
            fullSizeImageStage.close();
        }
        fullSizeImageStage = new Stage();
        fullSizeImageView = new ImageView(image);
        fullSizeImageView.setPreserveRatio(true);
        fullSizeImageView.setFitWidth(500);
        fullSizeImageView.setFitHeight(500);

        Button previousButton = new Button("Previous");
        previousButton.setOnAction(event -> showPreviousImage());

        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> showNextImage());

        Button allButton = new Button("Show All");
        allButton.setOnAction(event -> showAllImages());

        HBox buttonPane = new HBox(10);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(previousButton, nextButton, allButton);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(fullSizeImageView, buttonPane);
        vbox.setStyle("-fx-background-color: yellow;");

        Scene scene = new Scene(vbox);
        fullSizeImageStage.setScene(scene);
        fullSizeImageStage.setTitle("Full Size Image");
        fullSizeImageStage.show();
    }

    private void showNextImage() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
            showFullSizeImage(images.get(currentIndex));
        }
    }

    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            showFullSizeImage(images.get(currentIndex));
        }
    }

    private void showAllImages() {
        Stage allImagesStage = new Stage();
        VBox allImagesBox = new VBox(10);
        allImagesBox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(allImagesBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        for (Image image : images) {
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(500);
            imageView.setFitHeight(500);
            allImagesBox.getChildren().add(imageView);
        }

        Scene allImagesScene = new Scene(scrollPane);
        allImagesStage.setScene(allImagesScene);
        allImagesStage.setTitle("Gallery");
        allImagesStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
