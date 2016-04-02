package gui;

import intermediate.itermediateCodeGeneration;
import interoperability.asciiToText;
import interoperability.getFromSymbol;
import interoperability.interSing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;

import compress.Compress;
import compress.Decompress;
import optimization.Optimize;
import english_spanish.translate;
import english_spanish.windowsAzureTranslate;
import fileTransfer.SimpleFileClient;
import fileTransfer.simpleFileServer;
import lexical.Lexer;
import lexical.Lexer.Token;
import pages.fileManagement;
import preprocessing.Preprocess;
import symboltable.SymbolTable;
import syntax.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import semantic.Semantic;



public class Phases extends JFrame implements ActionListener
{
	/******************* BACK END SERVER ****************************************************/
	Socket client;
	OutputStream outToServer;
	//DataOutputStream out;
	InputStream inFromServer;
	//DataInputStream in;
	ObjectOutputStream obj;
	ObjectInputStream inobj;
	
	String serverName = "localhost";
    int port = 4444;
	/*****************************************initialize*************************************/
	Preprocess p = new Preprocess();

    Lexer l = new Lexer();

    Parser pr = new Parser();

    SymbolTable table= new SymbolTable();
    
    LinkedList<String> optim1 = new LinkedList();

    LinkedList<String> ast = new LinkedList();

    LinkedList<String> result = new LinkedList();


    ArrayList<Token> t = new ArrayList<Token>();


    Semantic sem = new Semantic();
    
    itermediateCodeGeneration inter= new itermediateCodeGeneration();

    getFromSymbol gfs = new  getFromSymbol();
    
    asciiToText ascii_text =new asciiToText();
    interSing interSing =new interSing();

    Optimize op = new Optimize();
    Compress compress=new Compress();
    Decompress decompress=new Decompress();
    String filePath;
    int stop=0;
    int codestop=0;
    LinkedList<String> binvalues= new LinkedList<String>();
    LinkedList<String> Asciivalues= new LinkedList<String>();
    /*****************************************initialize end*************************************/
	private int pre=0,lexical=0,syntax=0,semantic=0,codegen=0,interimcode=0,optimization=0;
	
	private String songName=null;
	private String preprocessFile=null;
	private String lexicalFile=null;
	private String syntaxFile=null;
	private String semanticFile=null;
	private String machineFile=null;
	private String asciiFile=null;
	private String compressedFile=null;
	private String compressedFile2=null;
	 ArrayList<Token> tokens=null;
	 
	private fileManagement fileWriter=new fileManagement();;

	
	private static   JTextArea textArea;
	private static JButton btnpre, btnsymbol,btnlexical,btnsyntax,btnsemantic,btncodeGen, btninterimCode,btnoptimization,btnSpan, btnServer,btnClient;
	private JRadioButton rbt0,rbt1,rbt2;
	private ButtonGroup bg;
	private Container levelCon;
	public Phases(LinkedList<String> Song, String songName) 
	{

		
		System.out.println("Connecting to server " + serverName +

		" on port " + port);

		try {
			client = new Socket(serverName, port);

			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());

			outToServer = client.getOutputStream();
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			inFromServer = client.getInputStream();
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());

			// out = new DataOutputStream(outToServer);
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());

			obj = new ObjectOutputStream(outToServer);
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			//
			// System.out.println("Just connected to " +
			// client.getRemoteSocketAddress());
			inobj = new ObjectInputStream(inFromServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.songName=songName;
		
	
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame("DPLMusicCompilerMagic");
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  
	    textArea = new JTextArea(" ", 50, 50);
	    textArea.setPreferredSize(new Dimension(50, 50));
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setEditable(false);

	    // Display song lyrics
        for (String line : Song)
	    textArea.append(line + "\n");
        
        
	    JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	  
	    scrollPane.setPreferredSize(new Dimension(600,600));
	    
	    
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	   
	    
	    JPanel btnPan= new JPanel(new GridLayout(12,1));
	    JPanel rbtPan = new JPanel(new GridLayout(1,3));
	    //buttons
	    btnpre=new JButton("Preprocessor");
	    btnsymbol=new JButton("Symbol Table");
	    btnlexical=new JButton("Lexical Analysis");
	    btnsyntax=new JButton("Syntax Analysis");
	    btnsemantic=new JButton("Semantic Analysis");
	    btnSpan=new JButton("Convert to Spanish");
	    btnServer=new JButton("Interoperability Server");
	    btnClient=new JButton("Interoperability Client");
	    
	    /*radiobutton*/
	    rbt0 = new JRadioButton("0");
	    rbt1 = new JRadioButton("1");
	    rbt2 = new JRadioButton("2");
	    
	    /*button group*/
	    bg = new ButtonGroup();
	    
	    bg.add(rbt0);
	    bg.add(rbt1);
	    bg.add(rbt2);
	    
	    
	    btninterimCode=new JButton("Intermediate Code");
	    btnoptimization=new JButton("Optimization");
	    btncodeGen=new JButton("Code Generation");
	    
	    btnPan.add(btnpre);btnPan.add(btnsymbol);
	    btnPan.add(btnlexical); btnPan.add(btnsyntax);
	    btnPan.add(btnsemantic);btnPan.add(btnSpan);btnPan.add(btninterimCode);
	    btnPan.add(btncodeGen);btnPan.add(btnoptimization);
	    rbtPan.add(rbt0);rbtPan.add(rbt1);rbtPan.add(rbt2);
	    btnPan.add(rbtPan);
	    btnPan.add(btnServer);
	    btnPan.add(btnClient);
	    
	
	    
	    // register listeners to buttons
	    btnpre.addActionListener(this);
	    btnsymbol.addActionListener(this);
	    btnlexical.addActionListener(this);
	    btnsyntax.addActionListener(this);
	    btnsemantic.addActionListener(this);
	    btnSpan.addActionListener(this);
	    btncodeGen.addActionListener(this);
	    btninterimCode.addActionListener(this);
	    btnoptimization.addActionListener(this);
	    btnServer.addActionListener(this);
	    btnClient.addActionListener(this);
	    
	    frame.add(btnPan);
	    frame.add(scrollPane);
	    frame.pack();
	    frame.setVisible(true);
	  }

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// PREPROCESS BUTTON
		if(event.getSource().equals(btnpre))
        {
		    try {
				filePath = p.processText(songName);
				FileReader reader;
				try {
					reader=new FileReader(filePath);
					textArea.read(reader,filePath);
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pre++;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
		/*********************************************/
		// SYMBOL TABLE BUTTON
		if(event.getSource().equals(btnsymbol))
        {
			if(pre>0)
			{
				String symbolFile=table.createTable(filePath);
				
				FileReader reader;
				try {
					reader=new FileReader(symbolFile);
					textArea.setText(null);// clear screen
					textArea.read(reader,symbolFile);
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else
			{
				JOptionPane.showMessageDialog(null, "Preprocessing phase must be done before a symbol table can be generated", "Phase", JOptionPane.INFORMATION_MESSAGE);
			}
        }
		
		/******************************************************/
		// LEXICAL ANALYSIS BUTTON
		if(event.getSource().equals(btnlexical))
        {
			if(pre>0)// preprocessing was done
			{
				
				 try {
					 t=l.dealFile(filePath);
					lexicalFile=fileWriter.writeTokensToFile(filePath,t, ":Lexical.txt");
					textArea.setText("");// clear screen
					
					for(Token token:t)
					{
					textArea.append(token.toString());//(d.toString());
					textArea.append("\n");
					
					}
					lexical++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else
			{
				JOptionPane.showMessageDialog(null, "Preprocessing phase must be done before Lexical Analysis", "Phase", JOptionPane.INFORMATION_MESSAGE);
			}
			
        }
		
		/***************************************************/
		//SYNTAX ANALYSIS BUTTON
		if(event.getSource().equals(btnsyntax))
        {
			if(lexical>0)// lexical was done
			{
				FileReader reader;
				try {
					ast.clear();
					ast = pr.parse(t);//syntax
					syntaxFile=fileWriter.writeToTree(lexicalFile,ast, ":Syntax.txt");
					
					
						reader=new FileReader(syntaxFile);
						textArea.setText(null);// clear screen
						textArea.read(reader,syntaxFile);
						reader.close();
					//textArea.setText(" ");// clear screen
					//textArea.setText(ast.toString());
					syntax++;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					textArea.setText(e1.getMessage());
				}
			}else
			{
				JOptionPane.showMessageDialog(null, "Lexical Analysis phase must be done before Syntax Analysis", "Phase", JOptionPane.INFORMATION_MESSAGE);
			}
        }
		
		/******************************************************/
		//SEMANTIC ANALYSIS BUTTON
		if(event.getSource().equals(btnsemantic))
        {
			if(syntax>0)// syntax was done
			{
				textArea.setText(null);// clear screen
				textArea.append("Conducting Spellcheck....");// clear screen
				FileReader reader;
				try {
					
					System.out.println("SEMANTIC AST: "+ast);
					result.clear();
					result = sem.semanticAnalysis(ast);
					optim1=result;
					semanticFile=fileWriter.writeToFile(syntaxFile,result, ":Semantic.txt");
					   
					//textArea.setText(result.toString());
					textArea.setText(" ");
					reader=new FileReader(semanticFile);
					textArea.read(reader,semanticFile);
					reader.close();
					
					semantic++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					textArea.setText(e.getMessage());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Syntax Analysis phase must be done before Semantic Analysis", "Phase", JOptionPane.INFORMATION_MESSAGE);
			}
        }
	
		/************************************************/
		// SPANISH CONVERSION BUTTON
		if (event.getSource().equals(btnSpan))

		{
			if(semantic>0)// syntax was done
			{

				// do Spanish Conversion

				FileReader reader;

				try {

					String spanishTranslation = "";

					windowsAzureTranslate w = new windowsAzureTranslate();

					spanishTranslation = w.translateEnglishToSpanish(filePath);

					System.out.println(spanishTranslation.toString());

					reader = new FileReader(spanishTranslation);

					textArea.read(reader, spanishTranslation);

				} catch (Exception e)

				{

					translate z = new translate();

					String returned = "";

					try 
					{

						returned = z.translateText(filePath);

						System.out.println(returned.toString());

					} catch (Exception ex)

					{

						System.out
								.println("Please ensure that you have internet connection");
						
						textArea.setText("Please ensure that you have internet connection \n");

						textArea.append(ex.toString());

					}

				}// end catch
				

			}//end if
			else
			{
				JOptionPane.showMessageDialog(null, "Semantic Analysis phase must be done before song can be converted to Spanish", "Phase", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		/**********************************************/
		//CODE GENERATION BUTTON

		if(event.getSource().equals(btncodeGen))
		{
			if(interimcode>0)
			{
				if (codestop==0)
				{
					
						    	
						    	 try 
						    	 {
									obj.writeObject(optim1);
									System.out.println("got results");
									binvalues=(LinkedList<String>)inobj.readObject();
						         	System.out.println("got results from bin");
						         textArea.setText(binvalues.toString());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						    	 codestop++;
				}
				else{
					textArea.setText(binvalues.toString());
				}
			     
				machineFile=fileWriter.writeToFile(asciiFile, binvalues, ":Machine.txt");
			}else
			{
				JOptionPane.showMessageDialog(null, "Intermediate Code Generation phase must be done before Code Generation", "Phase", JOptionPane.INFORMATION_MESSAGE);
			}
		    	 
		    	 

		}
		
		/**************************************************/
		//INTERMEDIATE CODE GENERATION BUTTON
		if(event.getSource().equals(btninterimCode))
		{
			if(semantic>0)
			{
				if (stop==0) // Ensures process can not be repeated as it has been converted already
				 {
					 
							try {
								
						         	obj.writeObject(optim1);
						         	System.out.println("got results");
						         	
						         	Asciivalues=(LinkedList<String>)inobj.readObject();
						         	System.out.println("got results");
						         textArea.setText(Asciivalues.toString());
						         
						         asciiFile=fileWriter.writeToFile(semanticFile, Asciivalues, ":Ascii");
				
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								textArea.setText(e.toString());
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								textArea.setText(e.toString());
							}
							stop++;
							interimcode++;
				 }
				 else
				 {
					 textArea.setText(Asciivalues.toString()); 
					 
				 }
			}else
			{
				JOptionPane.showMessageDialog(null, "Semantic Analysis phase must be done before Intermediate Code Generation", "Phase", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}
	
		/*********************************************/
		//OPTIMIZATION BUTTONS
		
		
		
		// NO OPTIMIZATION
			if(event.getSource().equals(btnoptimization) && rbt0.isSelected())
			{
					System.out.print("Level 0");
					optim1=result;
					textArea.setText(optim1.toString());	
			}
			
		// SPEED OPTIMIZATION
			if(event.getSource().equals(btnoptimization) && rbt1.isSelected())
			{
				System.out.print("Level 1");
				optim1=op.Opt1(result,machineFile);
				textArea.setText(optim1.toString());
				System.out.println("works");
			}
			
			
		// SPACE OPTIMIZATION
			if(event.getSource().equals(btnoptimization) && rbt2.isSelected())
			{
				System.out.print("Level 2");
				textArea.setText("Level 2 \n");
				
				compressedFile=compress.gzipFile(asciiFile);
				textArea.append("Ascii File compressed and stored to "+compressedFile);
				
				optimization++;
				
				//compressedFile2=compress.gzipFile(machineFile);
				//textArea.append("Machine File compressed and stored to "+compressedFile2);
				
				
			}
	
			/*********************************************/
			// INTEROPERABILITY
			if(event.getSource().equals(btnServer))
			{
				if(optimization>0)
				{
					btnClient.setEnabled(false);
					try {
						simpleFileServer transferServer = new simpleFileServer();
						transferServer.SimpleFileServer(compressedFile);
						textArea.setText("Waiting for connection......");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						textArea.setText(e.getMessage().toString());
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Optimization Level 2 phase must be done before you can transfer to another computer", "Phase", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			if(event.getSource().equals(btnClient))
			{
				
					btnServer.setEnabled(false);
					FileReader reader;
					try {
						textArea.setText("Connecting to server......");
						SimpleFileClient transferClient = new SimpleFileClient();
						
						String received=transferClient.getFile();// get zip ascii file
						textArea.append("\nReceived zipped ASCII File from MAC Computer..." +received);
						
						textArea.append("\nDecompressing file...");
						String decompressFile=decompress.gUnzipFile(received);
						textArea.append("\nASCII FILE...\n");
							reader=new FileReader(decompressFile);
							
							textArea.read(reader,decompressFile);
							
							
						String songFile=ascii_text.processFile(decompressFile);
						textArea.append("\nCOMPILED SONG FILE...\n");
						reader=new FileReader(songFile);
						
						textArea.read(reader,songFile);
						
						textArea.append("\nSINGING FILE...\n");
						interSing.processText(songFile);
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						textArea.setText(e.getMessage().toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						textArea.setText(e.getMessage().toString());
					}
				
			}
		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	/***************************************************Client connection End***********************************************/	
	
	}