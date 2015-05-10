
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.*;
import javax.swing.*;

public class Music extends JFrame {
	
	public static Board brd;
	static Sequencer sequencer;
    public Music(Board b) {
    	brd = b;
    	setResizable(false);
        //initComponents();
    } 
    
    
   public Music() {
	   begin();
   }
      

   public static void begin() {
      try {
          
          Sequence sequence = MidiSystem.getSequence(new File("beethoven.mid"));
          
          sequencer = MidiSystem.getSequencer();
          sequencer.open();
          sequencer.setSequence(sequence);
     
          sequencer.start();
      } catch (MalformedURLException e) {
      } catch (IOException e) {
      } catch (MidiUnavailableException e) {
      } catch (InvalidMidiDataException e) { 
      }   
   }
   
   public void toggleMusic(){
	   if(sequencer.isRunning())
		   sequencer.stop();
	   else
		   sequencer.start();
   }
}

