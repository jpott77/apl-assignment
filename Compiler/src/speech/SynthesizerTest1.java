package speech;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

public class SynthesizerTest1 {
	
	static int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

	static int volume = 127; // between 0 et 127
	int duration = 200; // in milliseconds
	
	//Synthesizer synth = MidiSystem.getSynthesizer();
	static MidiChannel[] channels ;//= synth.getChannels();
	
	
	// --------------------------------------
	// Play a few notes.
	// The two arguments to the noteOn() method are:
	// "MIDI note number" (pitch of the note),
	// and "velocity" (i.e., volume, or intensity).
	// Each of these arguments is between 0 and 127.

	public static void playLazySong(){

		int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
			
			playChorus();
			
			channels[channel].allNotesOff();
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

		int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

		int volume = 80; // between 0 et 127
		int duration = 200; // in milliseconds

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
						
			playSongVerse();
			
			channels[channel].allNotesOff();
			Thread.sleep( 500 );

			synth.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playRNB(){

		int channel = 12; // 0 is a piano, 9 is percussion, other channels are for other instruments

		int volume = 80; // between 0 et 127
		int duration = 200; // in milliseconds

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
						
			playRnB();
			
			channels[channel].allNotesOff();
			Thread.sleep( 500 );

			synth.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playSongVerse() throws InterruptedException{
		playNote("CM");
		Thread.sleep(800);
		playNote("FM");
		Thread.sleep(800);
		playNote("Am");
		Thread.sleep(800);
		playNote("GM");
		Thread.sleep(800);
		playNote("CM");
		
		Thread.sleep(1800);
		
		playNote("Am");
		Thread.sleep(800);
		playNote("GM");
		Thread.sleep(800);
		playNote("CM");
		Thread.sleep(800);
		playNote("FM");
		Thread.sleep(800);
		playNote("Am");
		Thread.sleep(800);
		playNote("FM");
		Thread.sleep(800);
		playNote("CM");
		
		Thread.sleep(1000);
	}
	
	public static void playRnB() throws InterruptedException{
		playNote("C");
		Thread.sleep(600);
		playNote("C");
		Thread.sleep(745);
		playNote("C");
		Thread.sleep(400);
		playNote("C");
		Thread.sleep(55);
		playNote("D");
		Thread.sleep(55);
		playNote("E");
		Thread.sleep(55);
		playNote("D");
		Thread.sleep(55);
		playNote("C");
		Thread.sleep(400);
		
		playNote("BL");
		Thread.sleep(750);
		playNote("BL");
		Thread.sleep(835);
		playNote("BL");
		Thread.sleep(450);
		playNote("BL");
		Thread.sleep(105);
		playNote("C");
		Thread.sleep(105);
		playNote("D");
		Thread.sleep(105);
		playNote("C");
		Thread.sleep(400);
		
		
		playNote("A");
		Thread.sleep(600);
		playNote("A");
		Thread.sleep(735);
		playNote("A");
		Thread.sleep(400);
		playNote("A");
		Thread.sleep(55);
		playNote("A");
		Thread.sleep(55);
		playNote("F");
		Thread.sleep(400);
		
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
		}else if(note.equals("CMH")){
			playCMajorHigh();
		}else if(note.equals("CH")){
			playCHigh();
		}else if(note.equals("AmH")){
			playAmHigh();
		}else if(note.equals("FMH")){
			playFMajorHigh();
		}else if(note.equals("GMH")){
			playGMajorHigh();
		}else if(note.equals("E7H")){
			playE7High();
		}else if(note.equals("AH")){
			playAHigh();
		}else if(note.equals("BH")){
			playBHigh();
		}else if(note.equals("DH")){
			playDHigh();
		}else if(note.equals("EH")){
			playEHigh();
		}else if(note.equals("FH")){
			playFHigh();
		}else if(note.equals("GH")){
			playGHigh();
		}else if(note.equals("BL")){
			playBLow();
		}
		
	}
	
	//low sound
	
	public static void playE7() throws InterruptedException{
		// Play a E7 major chord.
		channels[channel].noteOn( 64, volume ); // E
		channels[channel].noteOn( 68, volume ); // G#
		channels[channel].noteOn( 71, volume ); // B
		channels[channel].noteOn( 74, volume ); // D
		
		Thread.sleep(200);
	}
	
	public static void playC() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 60, volume );//C
		Thread.sleep(200);
	}
	
	public static void playA() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 57, volume );//A
		Thread.sleep(200);
	}
	
	public static void playB() throws InterruptedException
	{
		// Play a B chord.
		channels[channel].noteOn( 71, volume );//B
		Thread.sleep(200);
	}
	
	public static void playD() throws InterruptedException
	{
		// Play a D chord.
		channels[channel].noteOn( 62, volume );//D
		Thread.sleep(200);
	}
	
	public static void playE() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 64, volume );//E
		Thread.sleep(200);
	}
	
	public static void playF() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 65, volume );//E
		Thread.sleep(200);
	}
	
	public static void playG() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 67, volume );//E
		Thread.sleep(200);
	}
	
	public static void playCMajor() throws InterruptedException
	{
		// Play a C major chord.
		channels[channel].noteOn( 60, volume ); // C
		channels[channel].noteOn( 64, volume ); // E
		channels[channel].noteOn( 67, volume ); // G
		
		Thread.sleep(200);
	}

	public static void playAm() throws InterruptedException
	{
		//A Minor
		channels[channel].noteOn( 57, volume );//A
		channels[channel].noteOn( 60, volume );//C
		channels[channel].noteOn( 64, volume );//E

		Thread.sleep(200);
	}
	
	public static void playGMajor() throws InterruptedException
	{
		//G Major Cord
		channels[channel].noteOn( 67, volume );//G
		channels[channel].noteOn( 71, volume );//B
		channels[channel].noteOn( 62, volume );//D
		
		Thread.sleep(200);
	}
	
	public static void playFMajor() throws InterruptedException
	{
		// Play a F major chord.
		channels[channel].noteOn( 65, volume ); // F
		channels[channel].noteOn( 69, volume ); // A
		channels[channel].noteOn( 72, volume ); // C 
		
		Thread.sleep(200);
	}
	
	
	
	//high sound
	public static void playE7High() throws InterruptedException{
		// Play a E7 major chord.
		channels[channel].noteOn( 76, volume ); // E
		channels[channel].noteOn( 80, volume ); // G#
		channels[channel].noteOn( 83, volume ); // B
		channels[channel].noteOn( 86, volume ); // D
		
		Thread.sleep(200);
	}
	
	public static void playCHigh() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 72, volume );//C
		Thread.sleep(200);
	}
	
	public static void playAHigh() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 69, volume );//A
		Thread.sleep(200);
	}
	
	public static void playBHigh() throws InterruptedException
	{
		// Play a B chord.
		channels[channel].noteOn( 83, volume );//B
		Thread.sleep(200);
	}
	
	public static void playDHigh() throws InterruptedException
	{
		// Play a D chord.
		channels[channel].noteOn( 74, volume );//D
		Thread.sleep(200);
	}
	
	public static void playEHigh() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 76, volume );//E
		Thread.sleep(200);
	}
	
	public static void playFHigh() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 77, volume );//E
		Thread.sleep(200);
	}
	
	public static void playGHigh() throws InterruptedException
	{
		// Play a C chord.
		channels[channel].noteOn( 79, volume );//E
		Thread.sleep(200);
	}
	
	public static void playCMajorHigh() throws InterruptedException
	{
		// Play a C major chord.
		channels[channel].noteOn( 72, volume ); // C
		channels[channel].noteOn( 76, volume ); // E
		channels[channel].noteOn( 79, volume ); // G
		
		Thread.sleep(200);
	}

	public static void playAmHigh() throws InterruptedException
	{
		//A Minor
		channels[channel].noteOn( 69, volume );//A
		channels[channel].noteOn( 72, volume );//C
		channels[channel].noteOn( 76, volume );//E

		Thread.sleep(200);
	}
	
	public static void playGMajorHigh() throws InterruptedException
	{
		//G Major Cord
		channels[channel].noteOn( 79, volume );//G
		channels[channel].noteOn( 83, volume );//B
		channels[channel].noteOn( 74, volume );//D
		
		Thread.sleep(200);
	}
	
	public static void playFMajorHigh() throws InterruptedException
	{
		// Play a F major chord.
		channels[channel].noteOn( 77, volume ); // F
		channels[channel].noteOn( 81, volume ); // A
		channels[channel].noteOn( 84, volume ); // C 
		
		Thread.sleep(200);
	}
	
	public static void playBLow() throws InterruptedException
	{
		// Play a B chord.
		channels[channel].noteOn( 59, volume );//B
		Thread.sleep(200);
	}
}
  