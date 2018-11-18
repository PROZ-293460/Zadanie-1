/**
* Controller CLass
* @autor Krzysztof Kobyliñski
* @version 1.0
*/

package calculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller 
{

    @FXML
    private Button btnDiv;

    @FXML
    private Button btn7;

    @FXML
    private Button btnPlus;

    @FXML
    private Button btnLn;

    @FXML
    private Button btnMod;

    @FXML
    private Button btn6;

    @FXML
    private Button btn5;

    @FXML
    private Button btn4;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btn9;

    @FXML
    private Button btn8;

    @FXML
    private Button btn0;

    @FXML
    private Button btnCL;

    @FXML
    private Button btnMult;

    @FXML
    private Button btn3;

    @FXML
    private Button btn2;

    @FXML
    private Button btn1;

    @FXML
    private Button btnSqrt;

    @FXML
    private Button btnEqua;

    @FXML
    private Button btnDot;

    @FXML
    private Button rightBracket;

    @FXML
    private Button leftBracket;
    
    @FXML
    private GridPane gridPane;
    
	@FXML
    private TextField txtField;
	
	//String which is provided to Model class after click equals button
    @FXML
	private String result="";
	
	//visible text on the screen
  	@FXML
  	private String txt="";
	
  	//Number of left brackets in the string
  	private int braNum=0;
  	
  	//Flag active during typing number with decimal fraction
  	private boolean dotFlag=false;
  	
  	//Flag activate after type first number
  	private boolean numberFlag=false;
  	
  	//Flag activate after presenting result on the screen
  	private boolean ansFlag=false;
  	
  	//Alert use for presenting alerts during errors
  	private calculator.model.MyError error1 = new calculator.model.MyError();
  	
	private calculator.model.Model model = new calculator.model.Model();
	
	/**
	* No-arguments Constructor
	* Setting private variables to default values
	*/
	public Controller()
	{
		result="";
		txt="";
		braNum=0;
		dotFlag=false;
		numberFlag=false;
		ansFlag=false;
	}
  	
	/**
	* addToString method
	* Method which interacts with most buttons and complete txt and result strings
	*/

	@FXML
	void addToString(ActionEvent event) 
	{	
		
		if(ansFlag)
		{
			txt="Ans";
			ansFlag=false;
		}
		
		//Completing result String
		//sqrt
		if(((Button)event.getSource()).getId().equals("btnSqrt"))
			{
			txt+="sqrt(";
			braNum++;
			result+=((Button)event.getSource()).getAccessibleText();
			dotFlag=false;
			numberFlag=false;
			}
		//mod
		else if(((Button)event.getSource()).getId().equals("btnMod"))
			{
			txt+=" mod ";
			result+=((Button)event.getSource()).getAccessibleText();
			dotFlag=false;
			numberFlag=false;
			}
		//ln
		else if(((Button)event.getSource()).getId().equals("btnLn"))
			{
			result+=((Button)event.getSource()).getAccessibleText();
			txt+="ln(";
			braNum++;
			dotFlag=false;
			numberFlag=false;
			}
		//LeftBracket
		else if(((Button)event.getSource()).getId().equals("leftBracket"))
		{
			braNum++;
			result+=((Button)event.getSource()).getAccessibleText();
			txt+=((Button)event.getSource()).getAccessibleText();
			dotFlag=false;
			numberFlag=false;
		}
		//RightBracket
		else if(((Button)event.getSource()).getId().equals("rightBracket"))
		{
			braNum--;
			result+=((Button)event.getSource()).getAccessibleText();
			txt+=((Button)event.getSource()).getAccessibleText();
			dotFlag=false;
			numberFlag=false;
		}
		//Dot
		else if(((Button)event.getSource()).getId().equals("btnDot"))
		{
			if(result.length()<=2 || !(result.substring(result.length()-2,result.length()).equals(".0")))
				{
				error1.setup("Incorrect location of the dot");
				clear();
				}
			else
			{
				String temp = result.substring(0,result.length()-1);
				result=new String();
				result+=temp;
				txt+=((Button)event.getSource()).getText();
				dotFlag=true;
				temp=null;
			}
		}
		else
		{
			dotFlag=false;
			numberFlag=false;
			txt+=((Button)event.getSource()).getText();
			result+=((Button)event.getSource()).getText();
		}
			
		//Completing text on the screen
		txtField.setText(txt);
		//System.out.println(result);
	}

	/**
	* addNumber method
	* Method which interacts with number buttons and complete txt and result strings
	*/
	@FXML
	//event on action when number
	void addNumber(ActionEvent event) 
	{
		if(ansFlag)
			clear();
		
		txt+=((Button)event.getSource()).getText();
		
		if(dotFlag)
		{
			result+=((Button)event.getSource()).getText();
		}
		else if(numberFlag)
		{
			String temp = result.substring(0,result.length()-2);
			result=new String();
			result+=temp;
			result+=((Button)event.getSource()).getAccessibleText();
			temp=null;
		}
		else
			result+=((Button)event.getSource()).getAccessibleText();
		
		numberFlag=true;
		
		//Setting text on the screen
		txtField.setText(txt);
		//System.out.println(result);
	}
	
	/**
	* clear method
	* Method which clears all strings, flags and set number of brackets to 0
	*/
	@FXML
	void clear()
	{
		txt="";
		result="";
		txtField.setText(txt);
		braNum=0;
		dotFlag=false;
		numberFlag=false;
		ansFlag=false;
	}
	
	/**
	* equals method
	* Method which interacts with equals button
	* Checks number of brackets and completes missing brackets or shows an error
	* Sends the string to model class and receives an result
	*/
	@FXML
	void equals(ActionEvent event) 
	{
		if(braNum>0)
			for(int i=0;i<braNum;i++)
			{
				result+=")";
			}
		else if(braNum<0)
		{
			error1.setup("Too much right brackets in the sentence");
			clear();
			return;
		}
		//Sending string to model
		try
		{
		txt=model.calculate(result);
		}
		catch(IllegalArgumentException e)
		{
			error1.setup("Incorrect input data");
			clear();
		}
		txtField.setText(txt);
		result=txt;
		braNum=0;
		dotFlag=true;
		numberFlag=true;
		ansFlag=true;
	}
}
