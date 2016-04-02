package intermediate;

import java.io.BufferedWriter;

import java.io.FileWriter;

import java.io.IOException;

import java.util.LinkedList;

public class itermediateCodeGeneration {
	
	public LinkedList<String> textToAscii(LinkedList<String> song) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter("./Lyrics/songlyricsascii.o"));
		LinkedList<String> sendback=new LinkedList<String>() ;
	
		for(String n:song){
			String strI = "";
			
			String asciiConcat = "";
			
		    char arr[]=n.toCharArray();
		
		    for(int i=0;i<arr.length;i++){
	
			    char A =arr[i]; 
			
			    int ascii = A; 
			
			    System.out.println("ASCII value of '"+arr[i]+"' is : " + ascii);
			
			    strI = Integer.toString(ascii);
			
			    asciiConcat = asciiConcat + " " +strI;
		    }
	    out.append(asciiConcat.trim());
	    sendback.add(asciiConcat);

        out.newLine();
        
        System.out.println("ASCII value of "+n +" is : " + asciiConcat.trim());
		}

	out.close();
	return sendback;
	}
	
}