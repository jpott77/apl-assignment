package interoperability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pages.fileManagement;

public class getFromSymbol 
{
	private List<String> recreated = new ArrayList<String>();
	
	public void doFile(String file, int verses, LinkedList<String> VERSE, LinkedList<String> CHORUS)
	{
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		{
		    String line;
		    int count = 1;
		    int numVerse=verses;
		    
		    while ((line = br.readLine()) != null) 
		    {
		    	if(line.equalsIgnoreCase("[Chorus]"))
		    	{
		    		recreated.add("\n");
		    		recreated.add(CHORUS.toString());
		    		recreated.add("\n");
		    	}
		    	
		    	if(line.equalsIgnoreCase("Verse "+count))
		    	{
		    		// get Verse from Symbol Table
		    		recreated.add("\n");
		    		count++;
		    	}
		    	
		    }
		    
		   //// output to file
			   fileManagement fileWriter =new fileManagement();
			   String newfilePath=fileWriter.writeToFile(file,recreated, ":Recreated.txt");
			   recreated.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


