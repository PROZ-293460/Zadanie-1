/**
* Main Class
* @autor Krzysztof Kobyliński
* @version 1.0
*/
package calculator.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/calculator/view/Main.fxml"));		
			Scene scene = new Scene(root, 430, 500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{ launch(args); }
}

