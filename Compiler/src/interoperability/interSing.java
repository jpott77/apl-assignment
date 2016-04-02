package interoperability;

import java.io.BufferedReader;
import java.io.FileReader;

import pages.fileManagement;
import semantic.sing;

public class interSing {
	
	//chorus and verse
		public static String chorusSentence = ("(?i).*\\[chorus\\].*");//checks for the word chorus in a sentence by itself
		public static String verseSentence = ("(?i).*\\[verse\\].*");//checks for the word verse in a sentence by itself
	
	public void processText(String file) throws Exception
	{
		sing s = new sing();
		
		String text = "";
		String cat = "";
		
		fileManagement f =new fileManagement();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	
		    	if(line.toString().trim().matches(chorusSentence)){
					text = text + "             ";
				}else if(line.toString().trim().matches(verseSentence)){
					text = text + "  ";
				}else{
					text = text + line.toString().trim() + " ";
				}
		    }
		    cat = f.getCategory(file);
		    
		}
		s.singingSong(text, cat);
		
	}

}
