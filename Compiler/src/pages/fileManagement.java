package pages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexical.Lexer.Token;

public class fileManagement {
	
	public String writeToFile(String file, List<String> content,String additional)
	{
		String newFileName = "";
		try {
			
			String[] parts = file.split(".txt");
			String part1 = parts[0];
			
			String name[] = part1.split(":");
			
			String nameFile = name[0];
			
			newFileName = nameFile+additional;
			System.out.println(newFileName);

			File newFile = new File(newFileName);

			if (newFile.createNewFile()){
		        System.out.println("File is created!");
		        
		        FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				for(String c:content)
				{
					bw.write(c);
					bw.write("\n");
				}
				
				bw.close();

				System.out.println("Done");
		      }else{
		        System.out.println("File already exists.");
		      }


		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFileName;
		
	}
	
	public String writeToTree(String file, List<String> content,String additional)
	{
		int line=0;
		String newFileName = "";
		try {
			
			String[] parts = file.split(".txt");
			String part1 = parts[0];
			
			String name[] = part1.split(":");
			
			String nameFile = name[0];
			
			newFileName = nameFile+additional;
			System.out.println(newFileName);

			File newFile = new File(newFileName);

			if (newFile.createNewFile()){
		        System.out.println("File is created!");
		        
		        FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				for(String c:content)
				{
					if(line<=1)
					{
						bw.write(c);
						bw.write("\n");
						line++;
					}else if(c.equalsIgnoreCase("[Verse]") || c.equalsIgnoreCase("[Chorus]"))
					{
						bw.write("____" +c);
						bw.write("\n");
					} else if(!c.equalsIgnoreCase("[Verse]") && ! c.equalsIgnoreCase("[Chorus]") && line>1)
					{
						bw.write("______" +c);
						bw.write("\n");
					}
				}
				
				bw.close();

				System.out.println("Done");
		      }else{
		        System.out.println("File already exists.");
		      }


		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFileName;
		
	}

	public String writeTokensToFile(String file, ArrayList<Token> content,String additional)
	{
		String newFileName = null;
		try {
			String[] parts = file.split(".txt");
			String part1 = parts[0];
			
			String name[] = part1.split(":");
			
			String nameFile = name[0];
			
			newFileName = nameFile+additional;
			System.out.println(newFileName);

			File newFile = new File(newFileName);

			if (newFile.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }

			FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(Token token:content)
			{
				//System.out.println(token);
				bw.write(token.toString());
				bw.write("\n");
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFileName;
	}

	public String writeToFileSpanish(String file, ArrayList<String> content,String additional) {
		String newFileName = "";
		try {

			String[] parts = file.split(".txt");
			String part1 = parts[0];
			
			String name[] = part1.split(":");
			
			String nameFile = name[0];
			
			newFileName = nameFile+additional;
			System.out.println(newFileName);

			File newFile = new File(newFileName);

			if (newFile.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }

			FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(String c:content)
			{
				bw.write(c);
				bw.write("\n");
			}
			
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFileName;
	}
	
	public String getCategory(String file)
	{
		String line = "";
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    
			line = br.readLine();
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return line;
	}
	
}
