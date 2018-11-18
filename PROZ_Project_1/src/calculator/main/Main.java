/**
* Main Class
* @autor Krzysztof Kobylinski
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
			Parent root = FXMLLoader.load(getClass().getResource("/calculator/view/Main2.fxml"));		
			Scene scene = new Scene(root, 450, 503);
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

