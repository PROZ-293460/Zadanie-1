/**
* MyError CLass
* Class which is used to set and show errors during operation of the program
* @autor Krzysztof Kobylinski
* @version 1.0
*/
package calculator.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MyError 
{
	private Alert alert = new Alert(AlertType.NONE);

	/**
	* No-arguments Constructor
	*/
	public MyError()
	{
		alert.setTitle("Calculator Alert");
		alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
	}
	
	/**
	* setup method
	* Method which is used to set text of the error and show this on the screen
	* @param alertContent String which is shown on the Alert
	*/
	public void setup(String alertContent)
	{
		alert.setContentText(alertContent);
		alert.showAndWait();
	}
}
