package com.vemovi.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class ApplicationDelegate extends Application {

	// Public Methods
	// ===============================================================

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Platform.setImplicitExit(false);

		NavigationController navigation = new NavigationController();
		PrimeroViewController viewController = (PrimeroViewController) navigation.initViewController("com/vemovi/javafx/PrimeroViewController.fxml");
		navigation.pushViewController(viewController);

		StackPane root = new StackPane();
		root.getChildren().add(navigation);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		primaryStage.setWidth(320);
		primaryStage.setHeight(568);
		primaryStage.setMinWidth(320);
		primaryStage.setMinHeight(568);

		primaryStage.show();
	}
}
