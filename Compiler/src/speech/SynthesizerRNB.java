package speech;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Patch;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;

public class SynthesizerRNB {
	
	static int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

	static int volume = 80; // between 0 et 127
	int duration = 200; // in milliseconds
	
	//Synthesizer synth = MidiSystem.getSynthesizer();
	static MidiChannel[] channels ;//= synth.getChannels();
	 public static Instrument seashore = null;
	
	
	// --------------------------------------
	// Play a few notes.
	// The two arguments to the noteOn() method are:
	// "MIDI note number" (pitch of the note),
	// and "velocity" (i.e., volume, or intensity).
	// Each of these arguments is between 0 and 127.

	public static void playLazySong(){

		int channel = 9; // 0 is a piano, 9 is percussion, other channels are for other instruments

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
			System.out.println(channels.toString());
			
			playChorus();
			
			channels[1].allNotesOff();
			Thread.sleep( 500 );

			synth.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void playChorus() throws InterruptedException{
		playNote("CM");
		playNote("GM");
		playNote("FM");
		
		Thread.sleep(3000);
		
		playNote("CM");
		playNote("GM");
		playNote("FM");
		
		Thread.sleep(3000);
		
		playNote("CM");
		playNote("GM");
		playNote("FM");
		
		Thread.sleep(3000);
		
		playNote("CM");
		playNote("E7");
		playNote("FM");
		
		Thread.sleep(3000);
	}
	
	public static void playVerse(){

		int channel = 10; // 0 is a piano, 9 is percussion, other channels are for other instruments

		int volume = 80; // between 0 et 127
		int duration = 200; // in milliseconds

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
			/*Soundbank bank = synth.getDefaultSoundbank();
			synth.loadAllInstruments(bank);
			Instrument instrs[] = synth.getAvailableInstruments();
		 
			for (int i=0; i < instrs.length; i++)
			{
			    System.out.println(instrs[i].getName());
			    if(instrs[i].getName().equals("Power"))
			    {
			    	seashore=instrs[i];
			    	System.out.println("Found");
			    	break;
			    }
			}*/
			
			
			channels[1].programChange(0,2);// electric grand
			channels[2].programChange(0,8);//celesta
			channels[3].programChange(0,4);//electric piano
			channels[4].programChange(0,5);//electric piano
			channels[5].programChange(1024,48);//orchestra
			channels[6].programChange(0,40);//violin
			channels[3].programChange(0,37);//brass
						
			playSongVerse();
			
			
			
			channels[4].allNotesOff();
			Thread.sleep( 100 );

			synth.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playSongVerse() throws InterruptedException{
		//channels[3].noteOn(0,volume);//brass
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("GM");
		
		Thread.sleep(1000);
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("GM");
		
		
		Thread.sleep(500);
		
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("GM");
		
		Thread.sleep(500);
		
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("GM");
		
		Thread.sleep(500);
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("GM");
		
		
		Thread.sleep(500);
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("GM");
		
		
		
		Thread.sleep(500);
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("GM");
		
		Thread.sleep(500);
		playNote("CM");
		playNote("Am");
		playNote("GM");
		playNote("FM");
		playNote("E7");
		
		
		Thread.sleep(500);
		
	}
	
	public static void playNote(String note) throws InterruptedException{
		if(note.equals("CM")){
			playCMajor();
		}else if(note.equals("C")){
			playC();
		}else if(note.equals("Am")){
			playAm();
		}else if(note.equals("FM")){
			playFMajor();
		}else if(note.equals("GM")){
			playGMajor();
		}else if(note.equals("E7")){
			playE7();
		}else if(note.equals("A")){
			playA();
		}else if(note.equals("B")){
			playB();
		}else if(note.equals("D")){
			playD();
		}else if(note.equals("E")){
			playE();
		}else if(note.equals("F")){
			playF();
		}else if(note.equals("G")){
			playG();
		}
		
	}
	
	public static void playE7() throws InterruptedException{
		// Play a E7 major chord.
		channels[1].noteOn( 64, volume ); // E
		channels[4].noteOn( 68, volume ); // G#
		channels[2].noteOn( 71, volume ); // B
		channels[6].noteOn( 74, volume ); // D
		
		Thread.sleep(100);
	}
	
	public static void playC() throws InterruptedException
	{
		// Play a C chord.
		channels[1].noteOn( 60, volume );//C
		Thread.sleep(500);
	}
	
	public static void playA() throws InterruptedException
	{
		// Play a C chord.
		channels[2].noteOn( 57, volume );//A
		Thread.sleep(500);
	}
	
	public static void playB() throws InterruptedException
	{
		// Play a B chord.
		channels[3].noteOn( 71, volume );//B
		Thread.sleep(100);
	}
	
	public static void playD() throws InterruptedException
	{
		// Play a D chord.
		channels[4].noteOn( 62, volume );//D
		Thread.sleep(100);
		channels[4].allNotesOff();
	}
	
	public static void playE() throws InterruptedException
	{
		// Play a C chord.
		channels[4].noteOn( 64, volume );//E
		Thread.sleep(100);
		channels[4].allNotesOff();
	}
	
	public static void playF() throws InterruptedException
	{
		// Play a C chord.
		channels[4].noteOn( 65, volume );//E
		Thread.sleep(100);
		channels[4].allNotesOff();
	}
	
	public static void playG() throws InterruptedException
	{
		// Play a C chord.
		channels[3].noteOn( 67, volume );//E
		Thread.sleep(500);
	}
	
	public static void playCMajor() throws InterruptedException
	{
		// Play a C major chord.
		channels[1].noteOn( 60, volume ); // C
		Thread.sleep(3500);
		channels[2].noteOn( 64, volume ); // E
		Thread.sleep(2000);
		channels[3].noteOn( 67, volume ); // G
		
		Thread.sleep(500);
	}

	public static void playAm() throws InterruptedException
	{
		//A Minor
		channels[2].noteOn( 57, volume );//A
		Thread.sleep(1500);
		channels[1].noteOn( 60, volume );//C
		Thread.sleep(1500);
		channels[3].noteOn( 64, volume );//E

		Thread.sleep(500);
	}
	
	public static void playGMajor() throws InterruptedException
	{
		//G Major Cord
		channels[1].noteOn( 67, volume );//G
		Thread.sleep(5000);
		channels[3].noteOn( 71, volume );//B
		Thread.sleep(100);
		channels[2].noteOn( 62, volume );//D
		
		Thread.sleep(500);
	}
	
	public static void playFMajor() throws InterruptedException
	{
		// Play a F major chord.
		channels[1].noteOn( 65, volume ); // F
		Thread.sleep(100);
		channels[4].noteOn( 69, volume ); // A
		Thread.sleep(1800);
		channels[2].noteOn( 72, volume ); // C 
		channels[4].allNotesOff();
		
		Thread.sleep(500);
	}
}
  