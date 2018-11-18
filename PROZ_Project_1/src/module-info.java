/**
* Module-info
* @autor Krzysztof Kobyliñski
* @version 1.0
*/
module PROZ_Project_1 {
	requires transitive javafx.controls;
	requires jdk.jshell;
	requires javafx.fxml;
	requires javafx.graphics;
	exports calculator.controller to calculator.view;
	exports calculator.main;
	exports calculator.model to calculator.controller;
	opens calculator.controller to javafx.fxml;
	}