package interoperability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pages.fileManagement;

public class asciiToText {
	
	List<String> content = new ArrayList<String>();
	fileManagement f =new fileManagement();
	String text = "";
	String category = "";
	String line = "";
	
	int position = 0;
	
	public String processFile(String file)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    
			while ((line = br.readLine()) != null) {
		    	
				System.out.println(line);
		    	position ++;
		    	
		    	if(position == 1)
		    	{
		    		category = toText(line);
		    		category = category.toString();
		    		content.add(text);
		    		text = "";
		    	}else{
		    		content.add(toText(line));
		    		text = "";
		    	}
		    }
		    
		    System.out.println(content.toString());
		    
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
		
		String newFilePath = f.writeToFile(file, content, ":asciiToText.txt");
		
		content.clear();
		return newFilePath;
	}
	
	public String toText(String line)
	{
		System.out.println(line);
		List<String> items = Arrays.asList(line.trim().split(" "));
		
    	System.out.println(items.toString());
    	
    	for(String ascii:items)
    	{
    		int i = Integer.parseInt(ascii);
    		char t = (char)i;
    		text = text + t;
    	}
 
    	System.out.println(text);
    	
    	return text;
	}
}
