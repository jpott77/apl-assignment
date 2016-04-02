package server;

import intermediate.itermediateCodeGeneration;

import java.net.*;
import java.util.LinkedList;
import java.io.*;

import code_generator.codeGeneration;

public class GreetingServer extends Thread
{	codeGeneration c = new codeGeneration();
itermediateCodeGeneration inter= new itermediateCodeGeneration() ;
ObjectOutputStream obj;
ObjectInputStream inobj;
OutputStream outserver;
InputStream inserver;
   private ServerSocket serverSocket;
   
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            inserver=server.getInputStream();
            System.out.println("f");
            outserver=server.getOutputStream();
            System.out.println("f");
            //DataInputStream in = new DataInputStream(inserver);
			inobj= new ObjectInputStream(inserver);
            System.out.println("f");
            //System.out.println(in.readUTF());
            obj= new ObjectOutputStream(outserver);
            //DataOutputStream out = new DataOutputStream(outserver);
            System.out.println("f");
            
            //obj.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
            /********added************/
            String node="";
            /*int rsize= inobj.readInt();
            obj.writeInt(rsize);
            int count=0;
            int count2=0;
            System.out.println("here");
            while(count<rsize)
            {
            	node=inobj.readUTF();
            	obj.writeUTF(c.bin(node));// convert to binary and sends to client
            	System.out.println("Count is: " + count + " size is: " + rsize);
            	
            }*/
            System.out.println("here");
            /***********************************inter*********************/
            System.out.println("here");
			//inobj.readU
			System.out.println("here");
			LinkedList<String> ToAscii=(LinkedList<String>) inobj.readObject();
			System.out.println(ToAscii.toString());
			obj.writeObject(inter.textToAscii(ToAscii));
			
			
			
			
			LinkedList<String> Tobin=(LinkedList<String>) inobj.readObject();
			System.out.println(Tobin.toString());
			obj.writeObject(c.bin(Tobin));
		
            
            
            
            
          /*  String test = in.readUTF();
            out.writeUTF(test);
            
           // String test1 = in.readUTF();
           // out.writeUTF(test1);
            
            String node2="";
            int rsize2= in.readInt();
            out.writeInt(rsize2);
            int count2=0;
            
            while(count2<rsize2)
            {
            	node2=in.readUTF();
            	out.writeUTF(inter.textToAscii(node2));// convert to binary and sends to client
            	
            	
            }
            /*String node2="";
            
            int count2=0;
            
            while(count2<rsize)
            {
            	node2=in.readUTF();
            	out.writeUTF(c.bin(node2));// convert to binary and sends to client
            	
            	
            }*/
            
            
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
         /******************intermediate code************************/
            
            
         
      
   }
   public static void main(String [] args)
   {
      int port = 4444;
      try
      {
         Thread t = new GreetingServer(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
	 
   }
}