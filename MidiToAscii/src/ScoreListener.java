import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import org.jfugue.ChannelPressure;
import org.jfugue.Controller;
import org.jfugue.Instrument;
import org.jfugue.KeySignature;
import org.jfugue.Layer;
import org.jfugue.Measure;
import org.jfugue.MidiParser;
import org.jfugue.Note;
import org.jfugue.ParserListener;
import org.jfugue.PitchBend;
import org.jfugue.PolyphonicPressure;
import org.jfugue.Tempo;
import org.jfugue.Time;
import org.jfugue.Voice;

public class ScoreListener implements ParserListener 
{
	private Iterator currNotes[];
	private Vector notes[];
	public Vector chords[];
	
	public int staves = 1;
	private int layer = 0;
	private long time = 0L;
	
	public String name;
	public int resolution;
	private int tempo;
	
	private long totalDuration = 0;
	
	public ScoreListener(String name)
	{
        this.name = name;
        parseMidi();
	}
	
	@Override
	public void channelPressureEvent(ChannelPressure arg0) 
	{
        System.out.println("pressure");		
	}

	@Override
	public void controllerEvent(Controller arg0) 
	{
        System.out.println("controller");
	}

	@Override
	public void instrumentEvent(Instrument arg0) 
	{
        System.out.println("instr");
	}

	@Override
	public void keySignatureEvent(KeySignature arg0) 
	{
        System.out.println("key");
	}

	@Override
	public void layerEvent(Layer arg0) 
	{
        System.out.println("layer");
	}

	@Override
	public void measureEvent(Measure arg0) 
	{
        System.out.println("measure");
	}

	@Override
	public void noteEvent(Note note) 
	{
		if (note.getDuration() != 0)
		{
	        System.out.println(Note.getStringForNote(note.getValue()) + " " 
	        		+ note.getDuration());
	        totalDuration += note.getDuration();
		}
	}

	@Override
	public void parallelNoteEvent(Note note) 
	{
        noteEvent(note);
        System.out.println("parallel");
	}

	@Override
	public void pitchBendEvent(PitchBend arg0) 
	{
        System.out.println("bend");
	}

	@Override
	public void polyphonicPressureEvent(PolyphonicPressure arg0) 
	{
        System.out.println("poly");
	}

	@Override
	public void sequentialNoteEvent(Note note) 
	{
        noteEvent(note);
        System.out.println("sequential");
	}

	@Override
	public void tempoEvent(Tempo arg0) 
	{
        tempo = arg0.getTempo();
	}

	@Override
	public void timeEvent(Time arg0) 
	{
		time = arg0.getTime();
	}

	@Override
	public void voiceEvent(Voice arg0) 
	{
        layer = arg0.getVoice();
	}
	
    public long getTotalDuration()
    {
    	return totalDuration;
    }
    
    private void parseMidi() 
    {
        try 
        {
            //Allow for alternate file extensions of midi files.
            File midiFile = new File("/Users/vvvemuri1/git/MidiToAscii/MidiToAscii/src/" + name + ".midi");
            File altFile = new File("/Users/vvvemuri1/git/MidiToAscii/MidiToAscii/src/" + name + ".mid");

            if (!midiFile.exists() && altFile.exists()) {
                midiFile = altFile;
            }

            //parse midi
            Sequence sequence = MidiSystem.getSequence(midiFile);
            resolution = sequence.getResolution();

            MidiParser parser = new MidiParser();
            parser.addParserListener(this);
            parser.parse(sequence);
        } 
        catch (Exception e) 
        {
            System.err.println("Parsing the score has failed. Error: ");
            e.printStackTrace();
        }
    }
}