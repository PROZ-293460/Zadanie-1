package calculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

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
	
	//text provided to jshell
    @FXML
	private String result="";
	
	//visible text on the screen
  	@FXML
  	private String txt="";
	
  	//Number of left brackets in the string
  	private int braNum=0;
  	
  	//Changing font flag
  	private boolean fontFlag=false;
  	
  	private boolean dotFlag=false;
  	
  	//Flag activated after first number typing
  	private boolean numberFlag=false;
  	
  	//rzucac wyjatki po samych nawiasow
	@FXML
	void addToString(ActionEvent event) 
	{
		//Checking font size
		if(fontFlag)
			txtField.setFont(Font.font(40.0));
		
		//Completing text on the screen
		
		
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
			//System.out.println(result.length());
			if(result.length()<=2 || !(result.substring(result.length()-2,result.length()).equals(".0")))
				{
				//Alert kropka w nieprawidlowym miejscu
				txt="Nieprawidlowe wprowadzenie danych";
				txtField.setFont(Font.font(20.0));
				txtField.setText(txt);
				fontFlag=true;
				result="";
				txt="";
				}
			else
			{
				//ustawienie kropki i flagi kropki
				String temp = result.substring(0,result.length()-1);
				//System.out.println(temp);
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
			
		
		txtField.setText(txt);
		System.out.println(result);
	}

	@FXML
	//event on action when number
	void addNumber(ActionEvent event) 
	{
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
		
		txtField.setText(txt);
		System.out.println(result);
	}
	
	
	@FXML
	void clear(ActionEvent event) 
	{
		txt="";
		result="";
		txtField.setText(txt);
		braNum=0;
		dotFlag=false;
		numberFlag=false;
	}

	private calculator.model.Model model = new calculator.model.Model();
	
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
			txt= "Too much right brackets in the sentence";
			txtField.setFont(Font.font(20.0));
			txtField.setText(txt);
			fontFlag=true;
			result="";
			txt="";
			return;
		}
		txt=model.calculate(result);
		txtField.setText(txt);
		result=txt;
		braNum=0;
		//Rozkiminc flage kropki
		//dotFlag=false;
	}
}
