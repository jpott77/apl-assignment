package interoperability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pages.fileManagement;

public class binaryToText {
	
	List<String> content = new ArrayList<String>();
	private int position = 0;
	
	String category = "";
	
	public String processBinary(String file) throws Exception
	{
		fileManagement f =new fileManagement();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	
		    	position ++;
		    	
		    	if(position == 1)
		    	{
		    		content = toText(line);
		    		category = content.toString();
		    		category = category.replaceAll("\\[", "");
		    		category = category.replaceAll("\\]", "");
		    	}else{
		    		content = toText(line);
		    	}
		    }
		    
		    if(category.equals(" "))
			{
		    	//if there is no category place it in an unknown
				String[] parts = file.split("/");
		    	String part1 = parts[0];
		    	String part2 = parts[1];
		    	String part3 = parts[2];
		    	
		    	file = part1+"/Unknown/"+part3;
		    	
		    	System.out.println(file);
			}else{
				//if there is no category place it in an unknown
				String[] parts = file.split("/");
		    	String part1 = parts[0];
		    	String part2 = parts[1];
		    	String part3 = parts[2];
		    	
		    	file = part1+"/"+category+"/"+part3;
		    	
		    	System.out.println(file);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String newFilePath = f.writeToFile(file, content, ":binaryToText.txt");
		
		content.clear();
		return newFilePath;

	}
	
	public List<String> toText(String binary)
	{
		
		String[] ss = binary.split( " " );
	    StringBuilder sb = new StringBuilder();
	    for ( int i = 0; i < ss.length; i++ ) { 
	        sb.append( (char)Integer.parseInt( ss[i], 2 ) );                                                                                                                                                        
	    }   
	    
	    content.add(sb.toString());  
	    System.out.println(sb.toString());
	
		return content;
	}
}
