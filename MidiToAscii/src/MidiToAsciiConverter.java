import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import org.jfugue.Note;
import org.jfugue.Pattern;
import org.jfugue.Player;

public class MidiToAsciiConverter 
{
	private static String MIDI_LOC = "/Users/vvvemuri1/Desktop/hw2Workspace/MidiToAscii/src/573.midi";
	
	public static void main(String []args) throws InvalidMidiDataException, IOException, MidiUnavailableException
	{
		File midiFile = new File(MIDI_LOC);
		Player player = new Player();
		Pattern pattern = (Pattern) player.loadMidi(midiFile);
		Sequence sequence = player.getSequence(pattern);
		Track[] tracks = sequence.getTracks();
		
		for (int i = 0; i < tracks.length; i++)
		{
			Track track = tracks[i];
			int size = track.size();
			for (int j = 0; j < size; j++)
			{
				MidiEvent event = track.get(j);
				MidiMessage message = event.getMessage();
				System.out.println("Track " + i + " Ticks = " + 
						event.getTick() + " ");
				int sizeBytes = message.getLength();
				for (int k = 0; k < sizeBytes; k++)
				{
					System.out.print((int)(message.getMessage()[k]));
				}
				
				System.out.println();
			}			
		}
	}
}