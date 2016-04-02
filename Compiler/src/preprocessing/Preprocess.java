package preprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pages.fileManagement;

public class Preprocess {

	//all are case insensitive
	private String patternRepeat = "(?i).*\\[repeat\\].*";//searches for [repeat]
	private String numberRepeat = "(?i).*\\[.*[0-9]x\\].*";//searches for [num]x
	private String numberRepeat2 = "(?i).*\\[.*x*[0-9]\\].*";//searches for x[num]
	private String catGospel = "(?i)gospel";//searches for Gospel
	private String catRnB = "(?i)rnb";//searches for RnB
	private Boolean categoryFound = false;
	private int position = 1;
	int toss = 0;
	List<String> content = new ArrayList<String>();
	
	public String processText(String file) throws Exception
	{
		fileManagement f =new fileManagement();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	
		    	if(categoryFound)
		    	{
		    		categoryFound = false;
		    	}else{
		    		//check if the category of the song is there
		    		file = this.checkForCategory(line,file);
		    	}
		    	
		    	if(!categoryFound)
		    	{
		    		line = line.replaceAll("-", "");
		    		content = this.checkRepetition(line);
		    	}
		    	
		    	//error handle 
		    	//does not accept if repeat and numx or xnum is not in bracket
		    	
		    }
		    
		    if(toss==0)
			{
		    	//if there is no category place it in an unknown
				String[] parts = file.split("/");
		    	String part1 = parts[0];
		    	String part2 = parts[1];
		    	String part3 = parts[2];
		    	
		    	file = part1+"/Unknown/"+part3;
		    	toss=0;
		    	categoryFound = true;
		    	System.out.println(line);
			}
		    
		    position = 1;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String newFilePath = f.writeToFile(file, content, ":Processed.txt");
		
		content.clear();
		return newFilePath;

	}
	
	public String checkForCategory(String line, String file) throws Exception
	{
		if(line.matches(catGospel))
		{
			String[] parts = file.split("/");
	    	String part1 = parts[0];
	    	String part2 = parts[1];
	    	String part3 = parts[2];
	    	
	    	file = part1+"/Gospel/"+part3;
	    	
	    	toss++;
	    	categoryFound = true;
	    	System.out.println(line);
	    	content.add(line);
		}else if(line.matches(catRnB))
		{
			String[] parts = file.split("/");
	    	String part1 = parts[0];
	    	String part2 = parts[1];
	    	String part3 = parts[2];
	    	
	    	file = part1+"/RnB/"+part3;
	    	toss++;
	    	categoryFound = true;
	    	System.out.println(line);
	    	content.add(line);
		}
		
		return file;
	}
	
	
	public List<String> checkRepetition(String line)
	{
		int count = 0;
		int num = 0;
		String str = "";
		
		if(line.contains("-"))
		{
			line = line.replaceAll("-", "");
		}
		
		if(line.matches(patternRepeat))
    	{
    		line = line.replace("[repeat]", "");
    		System.out.println(line);
    		content.add(line);
    		System.out.println(line);
    		content.add(line);
    	}
    	else if(line.matches(numberRepeat)){
    		
    		str = line.replaceAll("[^0-9]+", " ");
    		str = str.replaceAll("\\s+","");
    		num = Integer.parseInt(str);
    		
    		line = line.replace("["+num+"x]", "");
    		line = line.trim();
    		
    		while(count<num)
    		{
    			System.out.println(line);
    			content.add(line);
    			count++;
    		}
    		count = 0;
    	}
    	else if(line.matches(numberRepeat2)){
    		
    		str = line.replaceAll("[^0-9]+", " ");
    		str = str.replaceAll("\\s+","");
    		num = Integer.parseInt(str);
    		
    		line = line.replace("[x"+num+"]", "");
    		line = line.trim();
    		
    		while(count<num)
    		{
    			System.out.println(line);
    			content.add(line);
    			count++;
    		}
    		count = 0;
    	}
    	else{
    		line = line.trim();
	    	System.out.println(line);
	    	content.add(line);
    	}
		return content;
	}

}
