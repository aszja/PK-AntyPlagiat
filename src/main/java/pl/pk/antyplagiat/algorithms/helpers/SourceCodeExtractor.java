package algorithms.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

public class SourceCodeExtractor {

	public String deleteCommnents(String source)
	{
		while(source.contains("/*"))
		{
			try
			{
				int commentStart = source.indexOf("/*");
				int commentEnd = source.indexOf("*/", commentStart+1);
				source = source.replace(source.substring(commentStart, commentEnd+2), "");
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				System.out.println("Wyst¹pil problem z komentarzami - ktorys moglbyc niedomkniety!");
				break;
			}
		}
		
		while(source.contains("//"))
		{
			try
			{
				int commentStart = source.indexOf("//");
				int commentEnd = source.indexOf("\n", commentStart+1);
				source = source.replace(source.substring(commentStart, commentEnd+1), "");
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				System.out.println("Wyst¹pil problem z jednolinijkowymi komentarzami!");
				break;
			}
		}
		
		return source;
	}
	
	//get libraries
	public List<String> getListOfUsedLibraries(String source, String key)
	{
		List<String> libraries = new ArrayList<String>();
		int length = key.length();
		
		while(source.contains(key))
		{
			try
			{
				int start = source.indexOf(key);
				int end = source.indexOf(";", start+1);
				String sub = source.substring(start, end+1);
				source = source.substring(0, start)+ source.substring(end+1);
				sub = sub.replaceAll("\\s+", "");
				libraries.add(sub.substring(length, sub.length()-1));
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				System.out.println("Wyst¹pil problem podczas generowania listy bibliotek!");
				break;
			}
		}
		////debug
		//System.out.println(libraries);
		
		return libraries;
	}
	

	public Map<String, Integer> createDictioriesOfMethodsAndFields(String source)
	{
		
		Map<String, Integer> dict = new HashMap<String, Integer>();
		
		// delete quotes
		while(source.contains("\""))
		{
			try
			{
				int quoteStart = source.indexOf("\"");
				int quoteEnd = source.indexOf("\"", quoteStart+1);
				source = source.replace(source.substring(quoteStart, quoteEnd+1), "");
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				System.out.println("Wyst¹pil problem z cudzyslowami!");
				break;
			}
		}
		
		List<String> found = new ArrayList<String>();
		
		while(source.contains("("))
		{
			try
			{
				int end = source.indexOf("(");
				int start = end-1;
				while(!source.substring(start, start+1).equals(" ") && !source.substring(start, start+1).equals(".") && !source.substring(start, start+1).equals("\t") && !source.substring(start, start+1).equals("\n") && !source.substring(start, start+1).equals(">"))
				{
					start--;
				}
				found.add(source.substring(start+1, end).replaceAll("\\s+", ""));
				source = source.substring(0, start)+ source.substring(end+1);
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				System.out.println("Wyst¹pil problem podczas szukania metod!");
				break;
			}
		}
		
		while(source.contains("="))
		{
			try
			{
				int end = source.indexOf("=");
				int start = end-1;
				while(source.substring(start, start+1).equals(" ")||source.substring(start, start+1).equals("\t") || source.substring(start, start+1).equals("\n") || source.substring(start, start+1).equals("=") || source.substring(start, start+1).equals("!") || source.substring(start, start+1).equals("-") || source.substring(start, start+1).equals("+") || source.substring(start, start+1).equals("*") || source.substring(start, start+1).equals("/") )
				{
					start--;
				}
				while(!source.substring(start, start+1).equals(" ") && !source.substring(start, start+1).equals(".") && !source.substring(start, start+1).equals("\t") && !source.substring(start, start+1).equals("\n") && !source.substring(start, start+1).equals(">"))
				{
					start--;
				}
				
				found.add(source.substring(start+1, end).replaceAll("\\s+", ""));
				source = source.substring(0, start)+ source.substring(end+1);
			}
			catch(StringIndexOutOfBoundsException ex)
			{
				System.out.println("Wyst¹pil problem podczas szukania zmiennych!");
				break;
			}
		}
		
		DictionaryExtractor de = new DictionaryExtractor();
		dict = de.countWords(found);
		dict.remove("");
		
		return dict;
	}
	
	
	
}
