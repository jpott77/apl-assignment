package code_generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class codeGeneration {
	
	public LinkedList<String> bin(LinkedList<String> song) throws IOException
	{
		LinkedList<String> send = new LinkedList<String>();

		for(String node:song)
		{
		
			byte[] bytes = node.getBytes();
			StringBuilder binary = new StringBuilder();
				for (byte b : bytes)
				{
					int val = b;
						for (int i = 0; i < 8; i++)
						{
							binary.append((val & 128) == 0 ? 0 : 1);
							val <<= 1;
						}
					binary.append(' ');
				}
				
				send.add(binary.toString());
				System.out.println("'" + node + "' to binary: " + binary);
		}
				return send;
	}
}
