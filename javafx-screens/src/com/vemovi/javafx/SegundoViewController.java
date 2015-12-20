package com.vemovi.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SegundoViewController extends ViewController implements Initializable {

	@FXML
	Label labelServer;

	@FXML
	Button buttonBack;
	
	@FXML
	Button buttonNext;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void viewDidAppear() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ": viewDidAppear");
	}

	@Override
	public void viewDidDisappear() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ": viewDidDisappear");
	}

	@Override
	public void viewWillAppear() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ": viewWillAppear");
	}

	@Override
	public void viewWillDisappear() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ": viewWillDisappear");
	}

	public void onNextViewController() {
		TerceroViewController viewController = (TerceroViewController) navigationController.initViewController("com/vemovi/javafx/TerceroViewController.fxml");
		navigationController.pushViewController(viewController);
	}

	public void onDismissViewController() {
		navigationController.dismissViewController();
	}

}
