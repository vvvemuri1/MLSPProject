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
		    PrintWriter printWriter = new PrintWriter (OUTPUT_DIR_NAME + "/" + fileName
		    		+ ".txt");
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
		songToMidiMap.put("Aiken Drum", "707");
		songToMidiMap.put("The Beggar Man", "188");
		songToMidiMap.put("The Cruel Ship’s Carpenter", "219");
		songToMidiMap.put("The Devil and the Farmer’s Wife", "133");
		songToMidiMap.put("Edward", "47");
		songToMidiMap.put("Frere Jacques", "789");
		songToMidiMap.put("The Garden Gate", "791");
		songToMidiMap.put("Go Tell it on the Mountain", "296");
		songToMidiMap.put("Greensleves", "573");
		songToMidiMap.put("Hey Ho, The Morning Dew", "695");
		songToMidiMap.put("I’ll Tell You of a Fellow", "778");
		songToMidiMap.put("Jennie Jenkins", "609");
		songToMidiMap.put("John Barleycorn", "161");
		songToMidiMap.put("The Keys of Canterbury", "542");
		songToMidiMap.put("Lisbon (A)", "848");
		songToMidiMap.put("The Morning Break", "351");
		songToMidiMap.put("Nae Bonnie Laddie tae Tak’ Me Awa’", "42");
		songToMidiMap.put("The Outlandish Knight", "77");
		songToMidiMap.put("Paddy on the Railway", "659");
	}
}
