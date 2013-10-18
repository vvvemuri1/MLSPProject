import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class MidiToAsciiConverter 
{	
	public static void main(String []args) throws InvalidMidiDataException, IOException, MidiUnavailableException
	{
		ScoreListener listener = new ScoreListener("573");
		System.out.println("Total Duration: " + listener.getTotalDuration());
		System.out.println();
		
		ScoreListener listener2 = new ScoreListener("575");
		System.out.println("Total Duration: " + listener2.getTotalDuration());

	}
}