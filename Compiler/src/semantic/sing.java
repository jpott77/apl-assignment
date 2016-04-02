package semantic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import speech.SynthesizerTest1;
import speech.textToSpeech;

public class sing {
	
	//chorus and verse
		public static String chorusSentence = ("(?i).*\\[chorus\\].*");//checks for the word chorus in a sentence by itself
		public static String verseSentence = ("(?i).*\\[verse\\].*");//checks for the word verse in a sentence by itself
		
		private String category = "";
		
		public static String title = "";
		
		public static String songTitle = "([\\\"])(?:(?=(\\\\?))\\2.)*?\\1";//checks for a song title
		
		String text = "";
		
		private static String catGospel = "(?i)gospel";//searches for Gospel
		private static String catRnB = "(?i)rnb";//searches for RnB
		
		private static String catGospelSpanish = "(?i)evangelio";//searches for Gospel in Spanish
		private static String catRnBSpanish = "(?i)rnb";//searches for RnB in Spanish

	public void singingSong(String text,String cat)
	{
		Thread thread = new Thread() {
		    public void run() {
		    	textToSpeech t = new textToSpeech();
				t.say(text, "kevin16",title);
		    }
		};

		// Start the downloads.
		thread.start();

		// Wait for them both to finish
		while(thread.isAlive())
		{
			if(cat.matches(catGospel)){
				SynthesizerTest1.playVerse();
			}else if(cat.matches(catRnB)){
				SynthesizerTest1.playRNB();
			}
			
		}
	}
	
	
	public void singingSongSpanish(String text,String cat)
	{
		Thread thread = new Thread() {
		    public void run() {
		    	textToSpeech t = new textToSpeech();
				t.say(text, "kevin16",title);
		    }
		};

		// Start the downloads.
		thread.start();
		
		// Wait for them both to finish
		while(thread.isAlive())
		{
			if(cat.matches(catGospelSpanish)){
				SynthesizerTest1.playVerse();
			}else if(cat.matches(catRnBSpanish)){
				SynthesizerTest1.playRNB();
			}
			
		}
	}
}
