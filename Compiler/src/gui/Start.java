package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;

import client.GreetingClient;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Start extends JFrame implements ActionListener
{
	private JLabel lblWelcome;
	File targetDestination= new File("./Lyrics/");
	private JLabel lblFile;
	private JFileChooser fileChooser;
	public String completeSongFile=null;
	public  File songFile = null;
	private JButton btnFile;
	private String[] choice={"--- Select One--","English to Spanish","English Only"} ;
	private JComboBox cmbChoice;
	private JLabel lblChoiceLanguage;// to choose from spanish-english or vice versa
	private JButton btnStart;
	private JPanel panWelcome, panFile, panLanguage, panButton;// panels
	private LinkedList<String> SONG= new LinkedList<String>();

	
	public Start()
	{
		super("Start Compiling");// Give name to log in window
        this.setLayout(new GridLayout(3,2));
        this.initializeComponents();
        this.addComponentsToPanels();
        this.addPanelstoWindow();
        this.setWindowProperties();
        this.registerListeners();
        
       
    
	}
	
	private void initializeComponents() 
    {
    	lblWelcome= new JLabel("Welcome to DPLMusicCompileMagic");
    	
    	lblFile= new JLabel("Choose song text file to compile");
    	btnFile=new JButton("Select a song file");
    	fileChooser= new JFileChooser();
    	fileChooser.setDialogTitle("Select file to continue");// set title
    	fileChooser.setAcceptAllFileFilterUsed(false);// disable "All file" option
    	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// allow user to select files only
    	fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("TEXT FILES","txt","text"));// set the extensions users can select from
    	
    	
    	
    	lblChoiceLanguage= new JLabel("Please select a conversion method");
    	btnStart= new JButton("Start Compiling");
    	btnStart.setPreferredSize(new Dimension(100,40));
    	btnStart.setEnabled(true);
    	
    	// Panels
        panWelcome = new JPanel(new GridLayout(1,1));
        panWelcome.setBorder(new EmptyBorder(10,10,10,10));
        
        panFile = new JPanel(new GridLayout(1,2));
        panFile.setBorder(new EmptyBorder(10,10,10,10));
        
        panLanguage = new JPanel(new GridLayout(1,2));
        panLanguage.setBorder(new EmptyBorder(10,10,10,10));
        
        panButton = new JPanel(new GridLayout(1,1));
        panButton.setBorder(new EmptyBorder(10,10,10,10));
    	
    }
	
	  private void addComponentsToPanels()
	    {
	        panWelcome.add(lblWelcome);
	        panFile.add(lblFile);panFile.add(btnFile);
	        //panLanguage.add(lblChoiceLanguage);panLanguage.add(cmbChoice);
	        panButton.add(btnStart);
	    }
	  
	  private void addPanelstoWindow() 
	    {
	        this.add(panWelcome);
	        this.add(panFile);
	        //this.add(panLanguage);
	        this.add(panButton);
	    }
	 
	    
	    private void setWindowProperties() 
	    {
	        this.setSize(500,200);
	        this.setVisible(true);
	        this.setLocationRelativeTo(null);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setResizable(false);
	    }
	    
	    public void registerListeners() 
	    {
	        btnStart.addActionListener(this);// add Listener to Start button
	        btnFile.addActionListener(this);// add Listener to File Chooser button
	    }
	    
	    
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		if(event.getSource().equals(btnStart))
        {
			if( songFile==null)
			{
				JOptionPane.showMessageDialog(null, "Please select a song file to start compilation", "Information Missing", JOptionPane.INFORMATION_MESSAGE);
			}else
			{
				
			 System.out.println("Start Compiling.....");

			 this.dispose();
			 //new GreetingClient(completeSongFile,cmbChoice.getSelectedIndex());
			 new Phases(this.readSong(completeSongFile),completeSongFile);// return song and song name
			}
        }
		
		if(event.getSource().equals(btnFile))
        {
			int rval=fileChooser.showOpenDialog(this);
			 songFile=fileChooser.getSelectedFile();
			 btnFile.setText(songFile.getName());
			 
			 try 
			 {
				FileUtils.copyFileToDirectory(songFile, targetDestination);
				completeSongFile="./Lyrics/"+songFile.getName();
				//System.out.println(completeSongFile);
				setFileName(completeSongFile);
			} catch (IOException e) 
			 {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	    
		
	   
	    
	}
	
	public LinkedList<String> readSong(String songfile)
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(songfile));
			String line;
			while ((line = br.readLine()) != null) 
			{
				SONG.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return SONG;
	}
	
	public void setFileName(String complete)
	{
		this.completeSongFile = complete;
	}

   public String getFileName()
   {
	   return this.completeSongFile;
   }
}
