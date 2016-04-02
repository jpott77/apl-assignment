package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;


public class Splash 
{
	//Variables
	private ImageIcon load;
	private JLabel imageLabel;// loading image
	
	Splash()
	{
		this.splash();
	}
	
	
	 //Add splash Image
    public void  splash()
    {
    	//Image Components initialized
        load = new ImageIcon("Images/Splash.png");
        imageLabel = new JLabel();
        imageLabel.setIcon(load);
        
        //Add image to pop up Splash Window
    	JWindow window = new JWindow();
    	window.getContentPane().add(imageLabel);
    	window.setBounds(200, 200, 200, 200);
    	window.setLocationRelativeTo(null);
    	window.setVisible(true);
    	
    	try
    	{
    		Thread.sleep(7000);// Appears for 7 seconds before bringing up Log In Screen
    		
    	}
    	catch(InterruptedException e)
    	{
    		e.printStackTrace();
    	}
    	
    	//Disappears logo
    	window.setVisible(false);
    	window.dispose();
    	
    	new Start();// Show start screen
    }

}

