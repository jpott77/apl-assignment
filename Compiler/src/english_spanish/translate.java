package english_spanish;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.fileManagement;
import semantic.sing;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class translate {
	
	public static String URL = "https://translate.google.com/#en/es/";
	public static String ENTER_ENGLISH = ".//*[@id='source']";
	public static String RESULT_BOX = ".//*[@id='result_box']";
	public static String TRANSLATE_BUTTON = ".//*[@id='gt-submit']";
	public String translatedText = "";
	
	List<String> content = new ArrayList<String>();
	
	public String text = "";
	public String category = "";
	//chorus and verse
	public static String chorusSentenceSpanish = ("(?i).*\\[coro\\].*");//checks for the word chorus in a sentence by itself in Spanish
	public static String verseSentenceSpanish = ("(?i).*\\[verso\\].*");//checks for the word verse in a sentence by itself in Spanish
	
	private static String catGospelSpanish = "(?i)evangelio";//searches for Gospel in Spanish
	private static String catRnBSpanish = "(?i)rnb";//searches for RnB in Spanish
	
	sing s = new sing();
	
	private boolean close;
	
	WebDriver driver = new FirefoxDriver();
	
	public String translateText(String file) throws InterruptedException, IOException{
		//navigate to URL
		driver.get(URL);
		Thread.sleep(1000);
		
		 fileManagement f =new fileManagement();
		    
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    
		    String line;
		    String lastLine = getLastLine(file);
		    
		    while ((line = br.readLine()) != null) {

		    	if(line.equalsIgnoreCase(lastLine))
		    	{
		    		close = true;
		    	}
		    	
		    	//Enter text that you want to be translated
				WebElement englishTextBox = driver.findElement(By.xpath(ENTER_ENGLISH));
				englishTextBox.clear();
				englishTextBox.sendKeys(line);
				
				//click translate
				WebElement translate = driver.findElement(By.xpath(TRANSLATE_BUTTON));
				translate.click();
				Thread.sleep(2000);
				
				//get translated text
				WebElement result = driver.findElement(By.xpath(RESULT_BOX));
				translatedText = result.getText();
				
				if(translatedText.matches(chorusSentenceSpanish)){
					text = text + "             ";	
				}else if(translatedText.matches(verseSentenceSpanish)){
					text = text + "  ";
				}else{
					text = text + translatedText + " ";
				}
		    	
		    	if(translatedText.matches(catGospelSpanish))
		    	{
		    		category = translatedText;
		    	}else if(translatedText.matches(catRnBSpanish))
		    	{
		    		category = translatedText;
		    	}
				
				content.add(translatedText);
		    	
		    	//checks if the browser should be closed
				if(close)
				{
					driver.close();
				}
					
		    }
		    
		    s.singingSongSpanish(text, category);
		    
		    String newFilePath = f.writeToFile(file, content, ":Translated.txt");
			
			content.clear();
			return newFilePath;
	}
	
	public String getLastLine(String file)
	{
		RandomAccessFile fileHandler = null;
	    try {
	        fileHandler = new RandomAccessFile( file, "r" );
	        long fileLength = fileHandler.length() - 1;
	        StringBuilder sb = new StringBuilder();

	        for(long filePointer = fileLength; filePointer != -1; filePointer--){
	            fileHandler.seek( filePointer );
	            int readByte = fileHandler.readByte();

	            if( readByte == 0xA ) {
	                if( filePointer == fileLength ) {
	                    continue;
	                }
	                break;

	            } else if( readByte == 0xD ) {
	                if( filePointer == fileLength - 1 ) {
	                    continue;
	                }
	                break;
	            }

	            sb.append( ( char ) readByte );
	        }

	        String lastLine = sb.reverse().toString();
	        return lastLine;
	    } catch( java.io.FileNotFoundException e ) {
	        e.printStackTrace();
	        return null;
	    } catch( java.io.IOException e ) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (fileHandler != null )
	            try {
	                fileHandler.close();
	            } catch (IOException e) {
	                /* ignore */
	            }
	    }
	}
}
