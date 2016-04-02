package client;

import gui.DriverGUI;
import gui.Start;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import preprocessing.Preprocess;
import semantic.Semantic;
import symboltable.SymbolTable;
import lexical.Lexer;
import lexical.Lexer.Token;
import syntax.Parser;

public class GreetingClient

{

   public GreetingClient(String fileName, int choice)

   {

      String serverName = "localhost";

      int port = 4444;
      
      String file=fileName;
      System.out.println("The file is"+fileName);

       

      Preprocess p = new Preprocess();

    Lexer l = new Lexer();

    Parser pr = new Parser();

    SymbolTable table= new SymbolTable();


    LinkedList<String> ast = new LinkedList();

    LinkedList<String> result = new LinkedList();


    ArrayList<Token> t = new ArrayList<Token>();


    Semantic sem = new Semantic();



    String filePath;

    try {

    filePath = p.processText("./Lyrics/unbreak-my-heart.txt");

    t = l.dealFile(filePath);


    System.out.println("Huh" +t.toString()); //to be pass as lexical analysis (tokens)


    } catch (Exception e1) {

    // TODO Auto-generated catch block

    e1.printStackTrace();

    }


    //syntax

    try {
		ast = pr.parse(t);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

    System.out.println("Huh" +ast.toString()); 


    //semantic

    //result = sem.semanticAnalysis(ast); //change from comment

    //System.out.println(result.toString());
      try

      {

         System.out.println("Connecting to server " + serverName +" on port " + port);

         Socket client = new Socket(serverName, port);

         System.out.println("Just connected to "  + client.getRemoteSocketAddress());

         OutputStream outToServer = client.getOutputStream();

         DataOutputStream out = new DataOutputStream(outToServer);

         out.writeUTF("Hello from "+ client.getLocalSocketAddress());

         InputStream inFromServer = client.getInputStream();

         DataInputStream in = new DataInputStream(inFromServer);
         
         System.out.println("Server says " + in.readUTF());
      // passing to the server

         int a=ast.size();
         
         out.writeInt(a);//change to result
         System.out.println("Server says size " + in.readInt());
         
         

         client.close();

      }catch(IOException e)

      {

         e.printStackTrace();

      }

   }
   

}