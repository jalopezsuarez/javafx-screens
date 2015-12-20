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

	private final float TransitionCrossDissolveDuration = 100.0f;
	private HashMap<String, ViewController> resourceControllers = new HashMap<>();

	public NavigationController() {
		super();
	}

	public ViewController initViewController(final String resource) {
		ViewController initViewController = null;
		try {
			if (!resourceControllers.containsKey(resource)) {
				FXMLLoader loaderViewController = new FXMLLoader(getClass().getClassLoader().getResource(resource));
				Parent parent;

				parent = (Parent) loaderViewController.load();
				parent.setId(resource);

				ViewController viewController = ((ViewController) loaderViewController.getController());
				viewController.setParentViewController(parent);
				viewController.setResourceController(resource);
				viewController.setNavigationController(this);

				resourceControllers.put(resource, viewController);
			}

			initViewController = resourceControllers.get(resource);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return initViewController;
	}

	public void pushViewController(final ViewController viewController) {
		try {

			// ----------------------------------------------------------
			Parent dismissParent = getChildren().size() > 0 ? (Parent) getChildren().get(getChildren().size() - 1) : null;
			final ViewController dismissViewController = getChildren().size() > 0 ? resourceControllers.get(dismissParent.getId()) : null;
			if (getChildren().size() > 0)
				dismissViewController.viewWillDisappear();
			// ----------------------------------------------------------
			viewController.viewWillAppear();
			// ----------------------------------------------------------

			final DoubleProperty opacity = opacityProperty();
			if (getChildren().size() > 0) {
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(TransitionCrossDissolveDuration), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent t) {
						// ----------------------------------------------------------
						getChildren().remove(viewController.getParentViewController());
						getChildren().add(viewController.getParentViewController());
						// ----------------------------------------------------------
						Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(TransitionCrossDissolveDuration), new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								// ----------------------------------------------------------
								if (getChildren().size() > 0)
									dismissViewController.viewDidDisappear();
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
				Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(TransitionCrossDissolveDuration), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void dismissViewController() {
		try {
			if (getChildren().size() > 1) {

				// ----------------------------------------------------------
				Parent dismissParent = (Parent) getChildren().get(getChildren().size() - 1);
				final ViewController dismissViewController = resourceControllers.get(dismissParent.getId());
				dismissViewController.viewWillDisappear();
				// ----------------------------------------------------------
				Parent parent = (Parent) getChildren().get(getChildren().size() - 2);
				final ViewController viewController = resourceControllers.get(parent.getId());
				viewController.viewWillAppear();
				// ----------------------------------------------------------

				final DoubleProperty opacity = opacityProperty();
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(TransitionCrossDissolveDuration), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent t) {
						// ----------------------------------------------------------
						getChildren().remove(getChildren().size() - 1);
						// ----------------------------------------------------------
						Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(TransitionCrossDissolveDuration), new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								// ----------------------------------------------------------
								dismissViewController.viewDidDisappear();
								viewController.viewDidAppear();
								// ----------------------------------------------------------
							}
						}, new KeyValue(opacity, 1.0)));
						fadeIn.play();
					}
				}, new KeyValue(opacity, 0.0)));
				fade.play();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
