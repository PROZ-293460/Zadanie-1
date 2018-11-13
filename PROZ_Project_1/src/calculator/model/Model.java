package calculator.model;

import java.util.List;

import jdk.jshell.*;
import jdk.jshell.Snippet.Status;
import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;

public class Model 
{
	public String calculate(String str)
	{
		String outcome=str;
		
		
			JShell jshell = JShell.create();
			
			List<SnippetEvent> events = jshell.eval(str);
			
			for (SnippetEvent e : events) 
			{
				if (e.causeSnippet() == null) 
				{
					if (e.value() != null) 
					{
						outcome = e.value();
					}
					break;
				}

			}
		
		
		return outcome;
	}
	

}