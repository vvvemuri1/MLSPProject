import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class MidiToAsciiConverter 
{	
	private static final String OUTPUT_DIR_NAME = "Output";
	
	private static HashMap<String, String> songToMidiMap;
	
	public static void main(String []args) throws InvalidMidiDataException, 
		IOException, MidiUnavailableException
	{
		setupSongToMidiMap();
	
		Set<String> fileNames = songToMidiMap.keySet();
		for (String fileName : fileNames)
		{
		    PrintWriter printWriter = new PrintWriter (OUTPUT_DIR_NAME + "/" + 
		    		songToMidiMap.get(fileName));
		    printWriter.println("#");
			ScoreListener listener = new ScoreListener(songToMidiMap.get(fileName),
					printWriter);
		    printWriter.close();
		}
	}
	
	private static void setupSongToMidiMap()
	{
		songToMidiMap = new HashMap<String, String>();
		songToMidiMap.put("A Beggin' I Will Go", "126");
		songToMidiMap.put("Devil and the Farmer's Wife, The", "133");
		songToMidiMap.put("Beggar Man, The", "188");
		songToMidiMap.put("Cruel Ship's Carpenter, The", "219");
		songToMidiMap.put("Aiken Drum", "707");
	}
}