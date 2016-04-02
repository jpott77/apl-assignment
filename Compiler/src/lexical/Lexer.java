package lexical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import pages.fileManagement;

public class Lexer {
	
	static ArrayList<Token> tokens= new ArrayList<Token>();
	static Boolean title = false;
	static int current = 1;
	
  public static enum TokenType {
    NUMBER("-?[0-9]+"),
    BINARYOP("[*|/|+|-]"), 
    SPACEBETWEENWORDS("\\ "),
    EXPRESSIONS("[!,-.?]"),
    WORD("[a-zA-Z]+"),
    TITLE("([\\\"])(?:(?=(\\\\?))\\2.)*?\\1"),
    CATEGORY(""),
    VERSE("(?i).*\\[verse\\].*"),
    CHORUS("(?i).*\\[chorus\\].*"),
    SENTENCE("");

    public final String pattern;

    private TokenType(String pattern) {
      this.pattern = pattern;
    }
  }
  
  public static String sentence = ("(.+)");
  public static String songTitle = "([\\\"])(?:(?=(\\\\?))\\2.)*?\\1";//checks for a song title
  private static String catGospel = "(?i)gospel";//searches for Gospel
  private static String catRnB = "(?i)rnb";//searches for RnB
  private static boolean catFound = false;//check if category was found
  private static boolean verseFound = false;//check if verse was found
  private static boolean chorusFound = false;//check if chorus was found
  public static String verse = ("(?i).*\\[verse\\].*");
  public static String chorus = ("(?i).*\\[chorus\\].*");
 
  public static class Token {
    public TokenType type;
    public String data;

    public Token(TokenType type, String data) {
      this.type = type;
      this.data = data;
    }

    @Override
    public String toString() {
      return String.format("(%s %s)", type.name(), data);
    }
  }

  public static void lex(String input) throws Exception {
    // The tokens to return

    // Lexer logic begins here
    StringBuffer tokenPatternsBuffer = new StringBuffer();
    for (TokenType tokenType : TokenType.values())
      tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
    Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));
    
    // Begin matching tokens
    Matcher matcher = tokenPatterns.matcher(input);
    while (matcher.find()) {
  
		if (matcher.group(TokenType.NUMBER.name()) != null) {
	        continue;
	      } else if (matcher.group(TokenType.BINARYOP.name()) != null) {
	        continue;
	      } else if(matcher.group(TokenType.EXPRESSIONS.name()) != null){
	    	  tokens.add(new Token(TokenType.EXPRESSIONS, matcher.group(TokenType.EXPRESSIONS.name())));
	          continue;
	      }else if(matcher.group(TokenType.WORD.name()) != null){
	  	  tokens.add(new Token(TokenType.WORD, matcher.group(TokenType.WORD.name())));
	        continue;
	      }else if(matcher.group(TokenType.SPACEBETWEENWORDS.name()) != null){
	      	  tokens.add(new Token(TokenType.SPACEBETWEENWORDS, matcher.group(TokenType.SPACEBETWEENWORDS.name())));
	          continue;
	      }
    }
    
    if(input.matches(sentence)){
  	  tokens.add(new Token(TokenType.SENTENCE, input.trim()));
    } 

  }
  
  public ArrayList<Token> dealFile(String file) throws Exception
  {
	  fileManagement f = new fileManagement();
	  ArrayList<Token> toBeWritten = new ArrayList<Token>();
	 
	  try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	
		    	checkForTitle(line);
		    	checkForCategory(line);
		    	checkForVerse(line);
		    	checkForChorus(line);
		    	
		    	if(title)
		    	{
		    		title = false;
		    	}else if(catFound){
		    		catFound = false;
		    	}else if(verseFound){
		    		verseFound = false;
		    	}else if(chorusFound){
		    		chorusFound = false;
		    	}else{
		    		lex(line);
		    	}
		    	toBeWritten.addAll(tokens);
		    	tokens.clear();
		    }
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  f.writeTokensToFile(file, toBeWritten, ":Lexical.txt");
	  return toBeWritten;
  }
  
  public static void checkForTitle(String input) throws Exception
  {
	  if(input.matches(songTitle)){
  			tokens.add(new Token(TokenType.TITLE, input));
  			title = true;
		   }
  		current++;
  	}
  
  public static void checkForVerse(String input) throws Exception
  {
	  if(input.matches(verse)){
  			tokens.add(new Token(TokenType.VERSE, input));
  			verseFound = true;
		   }
  	}
  
  public static void checkForChorus(String input) throws Exception
  {
	  if(input.matches(chorus)){
  			tokens.add(new Token(TokenType.CHORUS, input));
  			chorusFound = true;
		   }
  	}
  
  public void checkForCategory(String line) throws Exception
	{
		if(line.matches(catGospel))
		{
			tokens.add(new Token(TokenType.CATEGORY, line));
	    	catFound = true;
		}else if(line.matches(catRnB))
		{
			tokens.add(new Token(TokenType.CATEGORY, line));
	    	catFound = true;
		}
	}

}