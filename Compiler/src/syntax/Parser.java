package syntax;

import java.util.LinkedList;
import java.util.List;

import lexical.Lexer.Token;

public class Parser {
	
	public static String checkStartUpperCase = "^[A-Z].*+";//checks that sentence start with a upper case
	public static String checkEndsFullStop = ".*[\\w]*+\\.$";//checks that sentence ends with a full stop
	public static String checkEndsComma = ".*[\\w]*+\\,$";//checks that sentence ends with a comma
	public static String checkEndsExclamation = ".*[\\w]*+\\!$";//checks that sentence ends with an exclamation mark
	public static String checkEndsQuestionMark = ".*[\\w]*+\\?$";//checks that sentence ends with a question mark
	public static String songTitle = "([\"])(?:(?=(\\\\?))\\2.)*?\\1";//checks for a song title that is in a double quote
	public static String songTitleSingle = "([\'])(?:(?=(\\\\?))\\2.)*?\\1";//checks for a song title that is in a single quote
	
	public static String checkBrackets1 = ".*\\(+.*";//checks for ( brackets
	public static String checkBrackets2 = ".*\\{+.*";//checks for { brackets
	public static String checkBrackets3 = ".*\\[+.*";//checks for [ brackets
	public static String checkBrackets4 = ".*\\)+.*";//checks for ) brackets
	public static String checkBrackets5 = ".*\\}+.*";//checks for } brackets
	public static String checkBrackets6 = ".*\\]+.*";//checks for ] brackets
	public static String verse = ("(?i).*\\{verse\\}.*");//checks for verse in {}
	public static String chorus = ("(?i).*\\{chorus\\}.*");//checks for chorus in {}
	public static String verse2 = ("(?i).*\\(verse\\).*");//checks for verse in ()
	public static String chorus2 = ("(?i).*\\(chorus\\).*");//checks for chorus in ()
	public static String chorusSentence = ("(?i)chorus");//checks for the word chorus in a sentence by itself
	public static String verseSentence = ("(?i)verse");//checks for the word verse in a sentence by itself
	
	public static Boolean titleFound = false;
	public static int lineTitleFound = 0;
	public static Boolean categoryFound = false;
	public static int lineCategoryFound = 0;
	public static String title = "";
	public static String category = "";
	public static Boolean bracketFound = false;
	public static Boolean wrongBracketVerseFound = false;
	public static Boolean wrongBracketChorusFound = false;
	
	LinkedList<String> ast = new LinkedList<String>();
	
	private int line = 0;
	private int shouldAdd = 0;
	
	
	
	public LinkedList<String> parse(List<Token> token) throws Exception
	{
		for(Token t: token)
		{
			if(t.type.toString().equals("TITLE"))
			{
				line++;
				
				if(t.data.toString().trim().matches(songTitle)){
					
					title = t.data.toString().trim();
					title = title.replaceAll("\"", "");
					System.out.println("Title "+title);
					if(title.matches(checkStartUpperCase))
					{
						//stores that title was found
						titleFound = true;
						ast.add(t.data.toString().trim());
					}
					else{
						throw new Exception("SYNTAX ERROR: line " + line + "\n The title of the song should be capitalised"+"\n"+t.data.toString().trim());
					}
				}
			}
			
			if(t.type.toString().equals("CATEGORY"))
			{
				line++;
				
					//stores that category was found 
					categoryFound = true;
					category = t.data.toString().trim();
					ast.add(category);
			}
			
			if(t.type.toString().equals("SENTENCE"))
			{
				line++;
				
				if(t.data.toString().matches(songTitleSingle)){	
					System.out.println("In single quote");
					throw new Exception("SYNTAX ERROR: line " + line + "\n The title of the song should be enclosed in double quotes."+"\n"+t.data.toString().trim());
				}
				
				//check that the chorus or verse is not enclosed in any bracket other than square brackets
				if(t.data.toString().matches(chorus)){
					wrongBracketChorusFound = true;
				}else if(t.data.toString().matches(verse)){
					wrongBracketVerseFound = true;
				}else if(t.data.toString().matches(chorus2)){
					wrongBracketChorusFound = true;
				}else if(t.data.toString().matches(verse2)){
					wrongBracketVerseFound = true;
				}
				
				if(wrongBracketVerseFound)
				{
					throw new Exception("SYNTAX ERROR: line " + line + "\n To specify a verse please enclose in [] "+"\n"+t.data.toString().trim());
				}
				
				if(wrongBracketChorusFound)
				{
					throw new Exception("SYNTAX ERROR: line " + line + "\n To specify a chorus please enclose in [] "+"\n"+t.data.toString().trim());
				}
				
				//checks for the word chorus is in a sentence by itself
				if(t.data.toString().matches(chorusSentence)){
					throw new Exception("SYNTAX ERROR: line " + line + "\n Keyword chorus should be in [] "+"\n"+t.data.toString().trim());
				}
				
				//checks for the word chorus is in a sentence by itself
				if(t.data.toString().matches(verseSentence)){
					throw new Exception("SYNTAX ERROR: line " + line + "\n Keyword verse should be in [] "+"\n"+t.data.toString().trim());
				}
				
				//check for brackets in song
				if(t.data.toString().matches(checkBrackets1)){
					bracketFound = true;
				}else if(t.data.toString().matches(checkBrackets2)){
					bracketFound = true;
				}else if(t.data.toString().matches(checkBrackets3)){
					bracketFound = true;
				}else if(t.data.toString().matches(checkBrackets4)){
					bracketFound = true;
				}else if(t.data.toString().matches(checkBrackets5)){
					bracketFound = true;
				}else if(t.data.toString().matches(checkBrackets6)){
					bracketFound = true;
				}
				
				if(bracketFound)
				{
					throw new Exception("SYNTAX ERROR: line " + line + "\n Brackets are not accepted "+"\n"+t.data.toString().trim());
				}
				
				//check that a sentence starts with an uppercase letter
				if(t.data.toString().matches(checkStartUpperCase))
				{
					//check that a sentence ends with one of the following .,?!
					if(t.data.toString().matches(checkEndsFullStop)){
						//add to ast
						shouldAdd++;
					}else if(t.data.toString().matches(checkEndsComma)){
						//add to ast
						shouldAdd++;
					}else if(t.data.toString().matches(checkEndsExclamation)){
						//add to ast
						shouldAdd++;
					}else if(t.data.toString().matches(checkEndsQuestionMark)){
						//add to ast
					}else{
						throw new Exception("SYNTAX ERROR: line " + line + "\n A sentence must end with one of the following:  .,?! "+"\n"+t.data.toString().trim());
					}
					
					if(shouldAdd>0){
						shouldAdd=0;
						//add to ast
						ast.add(t.data.toString().trim());
					}
				}
				else{
					throw new Exception("SYNTAX ERROR: line " + line + "\n The first word in a sentence should be capitalised"+"\n"+t.data.toString().trim());
				}
			}
			
			if(t.type.toString().equals("WORD"))
			{
				if(t.data.toString().toUpperCase().trim().equals("GOD")||t.data.toString().toUpperCase().trim().equals("JESUS")
						||t.data.toString().toUpperCase().trim().equals("SAVIOUR")||t.data.toString().toUpperCase().trim().equals("SAVIOR")
						||t.data.toString().toUpperCase().trim().equals("LORD")||t.data.toString().toUpperCase().trim().equals("CHRIST"))
				{
					if(t.data.toString().trim().matches(checkStartUpperCase))
					{
						//do nothing
					}
					else{
						throw new Exception("SYNTAX ERROR: line " + line + "\n The word "+t.data.toString().trim()+" must be capitalised"+"\n"+t.data.toString().trim());
					}
				}
			}
			
			if(t.type.toString().equals("CHORUS"))
			{
				line++;
				String keep = t.data.toString().trim().replace("[", "");
				keep = keep.trim().replace("]", "");
				
					if(!keep.trim().matches(checkStartUpperCase))
					{
						throw new Exception("SYNTAX ERROR: line " + line + "\n Keyword [Chorus] must be capitalised"+"\n"+t.data.toString().trim());
					}
					
					ast.add(t.data.toString().trim());
			}
			
			if(t.type.toString().equals("VERSE"))
			{
				line++;
				String keep = t.data.toString().trim().replace("[", "");
				
				keep = keep.trim().replace("]", "");
				
					if(!keep.trim().matches(checkStartUpperCase))
					{
						throw new Exception("SYNTAX ERROR: line " + line + "\n Keyword [Verse] must be capitalised"+"\n"+t.data.toString().trim());
					}
					
					ast.add(t.data.toString().trim());
			}
		
		}
		
		if(!titleFound){
			throw new Exception("SYNTAX ERROR: The song must contain a title. \n The title of the song must be in Double Quotes");
		}
		
		if(!categoryFound){
			throw new Exception("SYNTAX ERROR: The song must contain have a category. \n Accepted categories are RnB and Gospel");
		}
		
		return ast;
	}
	
}
