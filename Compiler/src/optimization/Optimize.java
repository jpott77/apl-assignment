package optimization;

import java.util.LinkedList;

import pages.fileManagement;

public class Optimize 
{
	private String newFilePath=null;
	
	public LinkedList<String> Opt1(LinkedList<String> sem,String file)
	{
		
		fileManagement f = new fileManagement();

			 LinkedList<String> sem2=new LinkedList<String>();
			 
		     int count=0;
				for(String node:sem){
					if(node.equalsIgnoreCase("[Verse]") ){
						count++;
				sem2.add("Verse"+count);
					
					
			      }
					else if(node.equalsIgnoreCase("[CHORUS]")
							|| node.equals("Title") || node.equals("Category") ){
						sem2.add(node);	
					      }
				}
				for(String n:sem2){
					System.out.println(n);
					
				}
				
				 newFilePath= f.writeToFile(file, sem2, ":Optimized.txt");
				return sem2;
      }
	
	
	public String getnewFileName()
	{
		return this.newFilePath;
	}
}


	

