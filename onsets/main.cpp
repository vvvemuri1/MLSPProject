#include <iostream>
#include <tclap/CmdLine.h>
#include <string>
#include <vector>

#include "aubio_detector.h"
#include "config.h"
#include "detector.h"
#include "graph_writer.h"
#include "writer.h"

using std::cerr;
using std::endl;
using std::string;
using std::vector;

int main(int argc, char** argv) {
  try {
    TCLAP::CmdLine cmd("Onset dection program to help label audio files.", ' ', _ONSETS_VERSION);

    TCLAP::ValueArg<string> libraryArg("l", "library", "pick which onset library to use", false, "aubio", "current only accepts aubio");
    cmd.add(libraryArg);

    TCLAP::SwitchArg verboseArg("v", "verbose", "shows progress");
    cmd.add(verboseArg);

    TCLAP::UnlabeledMultiArg<string> filesArg("files", "a list of audio files to find onsets on", true, "file paths");
    cmd.add(filesArg);
    
    cmd.parse(argc, argv);

    string library = libraryArg.getValue();
    bool show_output = verboseArg.getValue();
    vector<string> files = filesArg.getValue();

    Detector* detector = NULL;
    
    if (library == "aubio") {
      detector = new AubioDetector(show_output);
    } else {
      throw "Library " + library + " is not supported.";
    }
    vector<vector<int> > onsets = detector->Process(files);

    Writer* writer = new GraphWriter(show_output);
    writer->Write(files, onsets);

    return 0;
  } catch (TCLAP::ArgException &e) {
    std::cerr << "error: " << e.error() << " for arg " << e.argId() << std::endl;
    return 1;
  } catch (string &e) {
    std::cerr << "error: " << e << std::endl;
    return 1;
  }
}

