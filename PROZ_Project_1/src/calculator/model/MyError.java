package calculator.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MyError 
{
	private Alert alert = new Alert(AlertType.NONE);
	
	public static void main(String[] args) 
	{
		//
	}

	public MyError()
	{
		alert.setTitle("Calculator Alert");
		alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
	}
	
	public void setup(String alertContent)
	{
		alert.setContentText(alertContent);
		alert.showAndWait();
	}
}
