package english_spanish;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import pages.fileManagement;
import semantic.sing;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class windowsAzureTranslate {
	
	ArrayList<String> content = new ArrayList<String>();
	public String text = "";
	public String category = "";
	//chorus and verse
	public static String chorusSentenceSpanish = ("(?i).*\\[coro\\].*");//checks for the word chorus in a sentence by itself in Spanish
	public static String verseSentenceSpanish = ("(?i).*\\[verso\\].*");//checks for the word verse in a sentence by itself in Spanish
	
	private static String catGospelSpanish = "(?i)evangelio";//searches for Gospel in Spanish
	private static String catRnBSpanish = "(?i)rnb";//searches for RnB in Spanish
	
	sing s = new sing();
	
	public String translateEnglishToSpanish(String file) throws Exception
	{
		Translate.setClientId("carlington");
	    Translate.setClientSecret("Kw+WuS6ImRBGruqm+s9jsaC7v26ePErrWnGBbBVPchw=");
	    
	    fileManagement f =new fileManagement();
	    
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    
	    String line;
	    
	    while ((line = br.readLine()) != null) {
	    	String spanishTranslation = Translate.execute(line.toString().trim(), Language.SPANISH);
	    	System.out.println(spanishTranslation);
	    	
	    	if(spanishTranslation.matches(chorusSentenceSpanish)){
				text = text + "             ";	
			}else if(spanishTranslation.matches(verseSentenceSpanish)){
				text = text + "  ";
			}else{
				text = text + spanishTranslation + " ";
			}
	    	
	    	if(spanishTranslation.matches(catGospelSpanish))
	    	{
	    		category = spanishTranslation;
	    	}else if(spanishTranslation.matches(catRnBSpanish))
	    	{
	    		category = spanishTranslation;
	    	}
	    	
	    	content.add(spanishTranslation);
	    }

	    s.singingSongSpanish(text, category);
	    
	    String newFilePath = f.writeToFileSpanish(file, content, ":Translated.txt");
	    
	    content.clear();
		System.out.println(newFilePath);
		return newFilePath;
	}

}

