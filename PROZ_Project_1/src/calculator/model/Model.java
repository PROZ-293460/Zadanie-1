/**
* Model Class
* @autor Krzysztof Kobylinski
* @version 1.0
*/
package calculator.model;

import java.util.List;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class Model {
	
	/**
	* No-arguments Constructor
	*/
	public Model()
	{}
	
	/**
	* calculate method
	* Method make calculations using Jshell object
	* @param str String from Controller class which contains entered data 
	* @return String which is result of calculations and is shown on the screen later
	* @exception IllegalArgumentException Exception is thrown when incorrect input data
	* is entered to the calculator
	*/
	public String calculate(String str) throws IllegalArgumentException 
	{
		String outcome = str;

		JShell jshell = JShell.create();

		try (jshell) 
		{
			List<SnippetEvent> events = jshell.eval(str);

			for (SnippetEvent e : events) 
			{
				if (e.causeSnippet() == null) 
				{
					switch (e.status()) 
					{
					case VALID:
						if (e.value() != null) 
						{
							outcome = e.value();
						}
						break;

					default:
						throw new IllegalArgumentException();
					}
				}

			}

		}
		
		if(outcome.length()-outcome.indexOf('.')>8)
		{
			String temp = outcome.substring(0,outcome.indexOf('.')+8);
			Model model1 = new Model();
			temp=model1.calculate(temp);
			outcome=new String();
			outcome+=temp;
			temp=null;
		}
		
		return outcome;
	}

}