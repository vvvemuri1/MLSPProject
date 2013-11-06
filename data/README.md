Matlab Onsets Marker
================

Each song, for each style, currently has two files associated with it.  The wav file, and the wav.out file which contains the approximated onset locations (yeah, I know, bad name).  For example, the song 'Edward', has the files:

    baroque/Edward.wav
    baroque/Edward.wav.out
    folk/Edward.wav
    folk/Edward.wav.out

To run the Matlab script to mark the actual onsets, run the function markOnsets.  To mark the onsets of Edward in the folk style, run:

    markOnsets('folk', 'Edward');

This will create a plot with the wave form, and red lines representing the approximated onsets.  Simply click where the actual onsets are (the X-position is the only position tracked, the Y-position does not matter), hit 'z' on the keyboard to zoom in, 'x' to zoom out, and 'r' to undo.  When all onsets have been clicked, hit the 'q' key to complete.  This will create a new file which, for our example above, will be:

    folk/Edward.onsets

When marking the osnets, remember to mark the rests as well.

Generating Approximate Onsets
================

If there are missing approximate onset data, or if changes have been made to the c++ program to approximate the onsets, running the 'find_onsets.py' script will generate the .wav.out files for all training data.

TODO
================

Onsets are not marked after the users clicks.  They have to remember.  To do this, put the `ginput` call inside a for loop and pass in `1` as an argument (this will only wait for one input).  If the user left-clicks, mark the position and draw a line on the onset.  If the user right clicks, exit the loop.
(Done by Tina)

The figure does not close after the inputting is complete.  This is simply done by closing the figure after the `ginput` call(s) are completed.

The axis sometimes runs out when zooming in and zooming out the figure. Don't really know how to fix it though.
