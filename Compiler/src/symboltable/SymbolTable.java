package symboltable;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pages.fileManagement;
public class SymbolTable 
{
	private int position=1; // locates each line of file being read
	private int chorusline=0;
	private int verseline=0;
	private int versenum=0;
	private String category;// stores the category of the song
	private String title; // stores the title of the song
	private LinkedList<String> CHORUS= new LinkedList<String>();
	private LinkedList<String> VERSE= new LinkedList<String>();
	private List<String> structure= new ArrayList<String>();// stores the order of the parts of the song i.e verse,chorus,
	private int oderOfStructure=0;// counter used to track the order of song parts as they are being read from file
	private int numVerses=0;// stores the number of verses
	public int numChorusRepeat=0;// stores how many times the chorus repeat
	private int numWords=0; //stores the number of words in the sing
	private int numLines=0; //stores the number of lines in the sing
	private String order=null;///stores order of song
	private String line;// stores each line of text file as it is been read through buffer
	BufferedReader br;//buffer reader
	private List<String> table = new ArrayList<String>(); //stores table creation, and output to file
	
	public static String verse = ("(?i).*\\[verse\\].*");
	public static String chorus = ("(?i).*\\[chorus\\].*");
	
	public String createTable(String file)
	{
       try
		{
			br = new BufferedReader(new FileReader(file));
			numVerses=0;
			numWords=0;
			numLines=0;
			while ((line = br.readLine()) != null) 
			{
				// Finding the order of the song
				if(line.equalsIgnoreCase("[Verse]")==true || line.equalsIgnoreCase("[Chorus]")==true)
				{
					structure.add(line);
					
				}
				/* GET CHORUS */
				if(chorusline>0 && !line.equalsIgnoreCase("[Verse]")&& line !=null)
				{
					CHORUS.add(line);
				}
				if(line.equalsIgnoreCase("[Chorus]")==true)
				{
			
					chorusline++;
				}
				if(line.equalsIgnoreCase("[Verse]")== true)
				{
					chorusline=0;
				
				}
				/*************/
				
				
				/* GET VERSE*/
				
				if(line.equalsIgnoreCase("[Chorus]")== true)
				{
					verseline=0;
				}
				
				if(verseline>0 && !line.equalsIgnoreCase("[Chorus]")&& line !=null)
				{
					VERSE.add(line);
				}
				if(line.equalsIgnoreCase("[Verse]")== true)
				{
					verseline++;
					versenum++;
					VERSE.add("\nVerse " +Integer.toString(versenum)+"\n");
				}
				
				// Checks for category
				if(position==1)
				{
					String getcategory=line.toUpperCase();//get first line and change to uppercase
					if(line.isEmpty())// if first line is empty
					{
						category="NO TITLE";// No category entered
					}else if(getcategory.equals("GOSPEL")||getcategory.equals("RNB"))// if gospel or rnb
					{
					 category=getcategory;// category output
					}
					else// unknown category
					{
						category="UNKNOWN CATEGORY :"+line;
					}
				} 
				if(position==2)//check for title
				{
					title=line;
				}
			    // Check how many verses the song has
				countVerse(line);
				
				// Check how many times the chorus repeats
				countChorusRepeat(line);
				
				
				if(position>2)// if not the category or title
				{
					// Count words
					if(!line.isEmpty())
					 {
					 String[] words_arr = line.split(" ");
					 numWords += words_arr.length;
					 }
					
					//Count lines
					if(!line.isEmpty()&& line.equalsIgnoreCase("[Verse]")==false && line.equalsIgnoreCase("[Chorus]")==false)
					numLines++;
				}
				position++; 
			}
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       //Output table
       System.out.println("Symbol Table Generation \n");
       table.add("Symbol TableGeneration \n");
       
       System.out.format("%-20s %-20s \n", "STRUCTURES", "VALUES");
       table.add(String.format("%-20s %-20s \n", "STRUCTURES", "VALUES"));
       
       System.out.println("---------------------------------------");
       table.add("---------------------------------------");
       //category
       System.out.format("%-20s %-20s \n", "CATEGORY", category);
	   table.add(String.format("%-20s %-20s \n", "CATEGORY", category));
		
       //title
       System.out.format("%-20s %-20s \n", "TITLE", title);
       table.add(String.format("%-20s %-20s \n", "TITLE", title));
       
       //structure of song
        order = String.join(" - ", structure);
       System.out.format("%-20s %-20s \n", "ORDER OF SONG", order);
       table.add(String.format("%-20s %-20s \n", "ORDER OF SONG", order));
       
      
		
	   //verses
       System.out.format("%-20s %-20s \n", "NUMBER OF VERSES", numVerses);
	   table.add(String.format("%-20s %-20s \n", "NUMBER OF VERSES",Integer.toString(numVerses)));
	   
	   //Chorus Repeat
       System.out.format("%-20s %-20s \n", "CHORUS REPEAT", numChorusRepeat);
	   table.add(String.format("%-20s %-20s \n", "CHORUS REPEAT",Integer.toString(numChorusRepeat)));
	   
	 //chorus
	   System.out.format("%-20s %-20s \n", "CHORUS", CHORUS.toString());
	   table.add(String.format("%-20s %-20s \n", "CHORUS",CHORUS.toString()));
	   
	 //verse
	   System.out.format("%-20s %-20s \n", "VERSES", VERSE.toString());
	   table.add(String.format("%-20s %-20s \n", "VERSES",VERSE.toString()));
			   
	   //words
       System.out.format("%-20s %-20s \n", "WORDS", numWords);
	   table.add(String.format("%-20s %-20s \n", "WORDS",Integer.toString(numWords)));
	   
	   //lines
	   System.out.format("%-20s %-20s \n", "LINES", numLines);
	   table.add(String.format("%-20s %-20s \n", "LINES",Integer.toString(numLines)));
	   
	   // output to file
	   fileManagement fileWriter =new fileManagement();
	   String newfilePath=fileWriter.writeToFile(file,table, ":Symbol.txt");
	   table.clear();
	   
	   return newfilePath;
	}
	
	public void countVerse(String line)
	{
		if(line.matches(verse))
		{
			numVerses++;
		}
		
	}
	
	public void countChorusRepeat(String line)
	{
		if(line.matches(chorus))
		{
			numChorusRepeat++;
		}
		
	}
	
	public LinkedList<String> getChorus()
	{
		return this.CHORUS;
	}
	public LinkedList<String> getVerse()
	{
		return this.VERSE;
	}
	
	public int getNumVerse()
	{
		return this.numVerses;
	}
	
	public String getOrder()
	{
		return this.order;
	}
	
	
	

}