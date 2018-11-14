package calculator.model;

import java.util.List;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class Model 
{	

	
	public String calculate(String str) throws IllegalArgumentException
	{
		String outcome=str;
		
		//Setting precision of output string
		/*
		String temp = "Math.round(100000*(" + str;
		temp+="))/100000.0";
		str=null;
		*/
		String temp=str;
		
		
			JShell jshell = JShell.create();
			
			try(jshell)
			{
			List<SnippetEvent> events = jshell.eval(temp);
			
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
			
		
		
		return outcome;
	}
	

}