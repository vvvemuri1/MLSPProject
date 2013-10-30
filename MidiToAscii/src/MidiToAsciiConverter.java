import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class MidiToAsciiConverter 
{	
	private static HashMap<String, String> songToMidiMap;
	
	public static void main(String []args) throws InvalidMidiDataException, 
		IOException, MidiUnavailableException
	{
		setupSongToMidiMap();
	
		Set<String> fileNames = songToMidiMap.keySet();
		for (String fileName : fileNames)
		{
			ScoreListener listener = new ScoreListener("789");
			System.out.println("Total Duration: " + listener.getTotalDuration());
		}
	}
	
	private static void setupSongToMidiMap()
	{
		songToMidiMap = new HashMap<String, String>();
		songToMidiMap.put("A Beggin' I Will Go", "126.midi");
		songToMidiMap.put("Devil and the Farmer's Wife, The", "133.midi");
		songToMidiMap.put("Beggar Man, The", "188.midi");
		songToMidiMap.put("Cruel Ship's Carpenter, The", "219.midi");
		songToMidiMap.put("Aiken Drum", "707.midi");
	}
}