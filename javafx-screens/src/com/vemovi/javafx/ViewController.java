package com.vemovi.javafx;

import javafx.scene.Parent;

public abstract class ViewController {

	protected Parent parentViewController;
	protected String resourceController;
	protected NavigationController navigationController;

	public Parent getParentViewController() {
		return parentViewController;
	}

	public void setParentViewController(Parent parentViewController) {
		this.parentViewController = parentViewController;
	}

	public String getResourceController() {
		return resourceController;
	}

	public void setResourceController(String resourceController) {
		this.resourceController = resourceController;
	}

	public NavigationController getNavigationController() {
		return navigationController;
	}

	public void setNavigationController(NavigationController navigationController) {
		this.navigationController = navigationController;
	}

	public abstract void viewWillAppear();

	public abstract void viewWillDisappear();

	public abstract void viewDidAppear();

	public abstract void viewDidDisappear();

}
