package semantic;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;


public class Semantic implements SpellCheckListener 
{
	private LinkedList<String> semantic = new LinkedList<String>();
	private int line = 0;
	
	public static String checkNum = ".*\\d+.*";//checks for a number
	
	//title
	public static Boolean titleFound = false;
	public static int lineTitleFound = 0;
	public static String songTitle = "([\\\"])(?:(?=(\\\\?))\\2.)*?\\1";//checks for a song title
	
	//category
	public static Boolean catFound = false;
	public static int lineCatFound = 0;
	private static String catGospel = "(?i)gospel";//searches for Gospel
	private static String catRnB = "(?i)rnb";//searches for RnB
	public static String title = "";
	String text = "";
	
	//chorus and verse
	public static String chorusSentence = ("(?i).*\\[chorus\\].*");//checks for the word chorus in a sentence by itself
	public static String verseSentence = ("(?i).*\\[verse\\].*");//checks for the word verse in a sentence by itself
	
	//spellcheck
	private static String dictFile = "./Library/english.0";
	private static String phonetFile = "./Library/phonet.en";
	
	private String category = "";

	private SpellChecker spellCheck = null;
  
	boolean take = false;
	
	LinkedList<String> spellCheckError = null;

	public LinkedList<String> semanticAnalysis (LinkedList<String> ast) throws Exception
	{
		sing s = new sing();
		
		for(String node: ast)
		{
			line++;
			
			 spellChecks(node.toString().trim());
			 Thread.sleep(1000);
			 checkingError();
			
			//check that the category of the song is in line 1
			if(node.toString().trim().matches(catGospel)||node.toString().trim().matches(catRnB)){
				catFound = true;
				if(line!=1)
				{
					throw new Exception("SYMANTIC ERROR: line " + line + "The category of the song must be in line 1.\n Accepted Categories are Gospel and RnB."+"\n"+node.toString().trim());
				}
				semantic.add(node.toString().trim());
				category = node.toString().trim();
			}else if(node.toString().trim().matches(songTitle))
			{
				//checks that the title of the song is in line 2
				titleFound = true;
				if(line!=2){
					throw new Exception("SYMANTIC ERROR: line " + line + "The title of the song must be in line 2."+"\n"+node.toString().trim());
				}
				
				title = node.toString().trim();
				title = title.replaceAll("\"","");
				semantic.add(node.toString().trim());
			}else{
				//checks for numbers
				if(node.toString().trim().matches(checkNum)){
					throw new Exception("SYNTAX ERROR: line " + line + "\n Numbers are not accepted "+"\n"+node.toString().trim());
				}
				
				if(node.toString().trim().matches(chorusSentence)){
					text = text + "             ";
					semantic.add(node.toString().trim());
				}else if(node.toString().trim().matches(verseSentence)){
					text = text + "  ";
					semantic.add(node.toString().trim());
				}else{
					text = text + node.toString().trim() + " ";
					semantic.add(node.toString().trim());
				}
			}
			
		}
		
		if(!catFound)
		{
			throw new Exception("SYMANTIC ERROR: The song must have a category. \n Accepted Categories are Gospel and RnB.");
		}
		
		if(!titleFound)
		{
			throw new Exception("SYMANTIC ERROR: The song must contain a title. \n The title of the song must be in Double Quotes");
		}
		
		s.singingSong(text, category);
		
		return semantic;

	}
	
	public void spellChecks(String text) {
	    try {
	      SpellDictionary dictionary = new SpellDictionaryHashMap(new File(dictFile), new File(phonetFile));

	      spellCheck = new SpellChecker(dictionary);
	      spellCheck.addSpellCheckListener(this);
	      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	        if (text.length() >= 1){
	        	spellCheck.checkSpelling(new StringWordTokenizer(text));
	        }

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }


	  public void spellingError(SpellCheckEvent event){
	    List suggestions = event.getSuggestions();
	    
	    if (suggestions.size() > 0) {
	      System.out.println("MISSPELT WORD: " + event.getInvalidWord());
	      spellCheckError.add("MISSPELT WORD: " + event.getInvalidWord());
	      for (Iterator suggestedWord = suggestions.iterator(); suggestedWord.hasNext();) {
	        //System.out.println("\tSuggested Word: " + suggestedWord.next());
	        spellCheckError.add("Suggested Word: " + suggestedWord.next());
	      }
	    } else {
	      System.out.println("MISSPELT WORD: " + event.getInvalidWord());
	      spellCheckError.add("MISSPELT WORD: " + event.getInvalidWord());
	      spellCheckError.add("No suggestions");
	      //System.out.println("\tNo suggestions");
	    }
	    //Null actions
	    take = true;
	  }
	  
	  public void checkingError()throws Exception{
		  if(take){
			  throw new Exception("SYMANTIC ERROR: Please see errors and suggestions. \n"+spellCheckError.toString()); 
		  }
	  }
}
