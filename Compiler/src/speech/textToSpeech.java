package speech;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import javax.sound.sampled.AudioFileFormat.Type;


public class textToSpeech {

	public void say(String text, String voiceName,String title)
	{
		FreeTTS freetts;
        AudioPlayer audioPlayer = null;
        
        System.out.println();
        System.out.println("Using voice: " + voiceName);

        /* The VoiceManager manages all the voices for FreeTTS.
         */
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice helloVoice = voiceManager.getVoice(voiceName);

        if (helloVoice == null) {
            System.err.println(
                "Cannot find a voice named "
                + voiceName + ".  Please specify a different voice.");
            
        }

        /* Allocates the resources for the voice.
         */
        helloVoice.allocate();
        
		helloVoice.speak(text);
		/* Clean up and leave.
         */
        helloVoice.deallocate();
        
        saveAsAudioFile(text,voiceName,title);
        
        
	}
	
	public void saveAsAudioFile(String text, String voiceName,String title)
	{	FreeTTS freetts;
	    AudioPlayer audioPlayer = null;
	    
	    System.out.println();
	    System.out.println("Using voice: " + voiceName);
	
	    /* The VoiceManager manages all the voices for FreeTTS.
	     */
	    VoiceManager voiceManager = VoiceManager.getInstance();
	    Voice helloVoice = voiceManager.getVoice(voiceName);
	
	    if (helloVoice == null) {
	        System.err.println(
	            "Cannot find a voice named "
	            + voiceName + ".  Please specify a different voice.");
	        
	    }
	
	    /* Allocates the resources for the voice.
	     */
	    helloVoice.allocate();
	
	    /* Synthesize speech.
	     */
		//create a audioplayer to dump the output file
		audioPlayer = new SingleFileAudioPlayer("./Lyrics/"+title,Type.WAVE);
		//attach the audioplayer 
		helloVoice.setAudioPlayer(audioPlayer);
	    
		helloVoice.speak(text);
		/* Clean up and leave.
	     */
	    helloVoice.deallocate();
	    
	    //don't forget to close the audioplayer otherwise file will not be saved
	    audioPlayer.close();
	    
	}
}
