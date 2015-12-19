package com.vemovi.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimeroViewController extends ViewController implements Initializable {

	@FXML
	Label labelServer;

	@FXML
	Button buttonNext;

	@FXML
	Button buttonBack;

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
		this.navigationController.pushViewController("com/vemovi/javafx/SegundoViewController.fxml");
	}

	public void onDismissViewController() {
		this.navigationController.dismissViewController();
	}

}