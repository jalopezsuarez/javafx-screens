package com.vemovi.javafx;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;

public class NavigationController extends AnchorPane {

	private HashMap<String, ViewController> resourceControllers = new HashMap<>();

	public NavigationController() {
		super();
	}

	public void pushViewController(final String resource) {

		try {

			if (!resourceControllers.containsKey(resource)) {
				FXMLLoader loaderViewController = new FXMLLoader(getClass().getClassLoader().getResource(resource));
				Parent parent = (Parent) loaderViewController.load();
				parent.setId(resource);

				ViewController viewController = ((ViewController) loaderViewController.getController());
				viewController.setParentViewController(parent);
				viewController.setResourceController(resource);
				viewController.setNavigationController(this);

				resourceControllers.put(resource, viewController);
			}

			// ----------------------------------------------------------
			final ViewController viewController = resourceControllers.get(resource);
			viewController.viewWillAppear();
			// ----------------------------------------------------------

			final DoubleProperty opacity = opacityProperty();
			if (getChildren().size() > 0) {
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(100), new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent t) {
								// ----------------------------------------------------------
								getChildren().remove(viewController.getParentViewController());
								getChildren().add(viewController.getParentViewController());
								// ----------------------------------------------------------
								Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
										new KeyFrame(new Duration(100), new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {
										// ----------------------------------------------------------
										viewController.viewDidAppear();
										// ----------------------------------------------------------
									}
								}, new KeyValue(opacity, 1.0)));
								fadeIn.play();
							}
						}, new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				setOpacity(0.0);
				// ----------------------------------------------------------
				getChildren().remove(viewController.getParentViewController());
				getChildren().add(viewController.getParentViewController());
				viewController.viewDidAppear();
				// ----------------------------------------------------------
				Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
						new KeyFrame(new Duration(100), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void dismissViewController() {

		try {

			if (getChildren().size() > 1) {

				// ----------------------------------------------------------
				Parent parent = (Parent) getChildren().get(getChildren().size() - 1);
				final ViewController viewController = resourceControllers.get(parent.getId());
				viewController.viewWillAppear();
				// ----------------------------------------------------------

				final DoubleProperty opacity = opacityProperty();
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(100), new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent t) {
								// ----------------------------------------------------------
								getChildren().remove(getChildren().size() - 1);
								// ----------------------------------------------------------
								Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
										new KeyFrame(new Duration(100), new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {
										// ----------------------------------------------------------
										viewController.viewDidAppear();
										// ----------------------------------------------------------
									}
								}, new KeyValue(opacity, 1.0)));
								fadeIn.play();
							}
						}, new KeyValue(opacity, 0.0)));
				fade.play();
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
