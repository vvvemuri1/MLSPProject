#include "aubio_detector.h"

#include <iostream>
#include <sndfile.hh>

using std::cout;
using std::endl;
using std::string;
using std::vector;

// TODO: consolidate constants.

AubioDetector::AubioDetector() {
  onset = new_aubio_onset((char_t*)"default", 512, 256, 44100);
}

AubioDetector::~AubioDetector() {
  del_aubio_onset(onset);
}

vector<vector<int> > AubioDetector::Process(vector<string> files) {
  vector<vector<int> > onsets = vector<vector<int> >(files.size());

  int overlap_size = 256;
  fvec_t* input = new_fvec(overlap_size);
  fvec_t* output = new_fvec(1);
  
  for (int i = 0; i < files.size(); i++) {
    aubio_source_t* file = new_aubio_source((char_t*)files[i].c_str(), 0, overlap_size);
    if (file == NULL) {
      throw "Could not find file '" + files[i];
    }

    uint_t frames = aubio_source_get_frames(file);
    cout << "Processing file '" << files[i] << "' with " << frames << " samples" << endl;

    uint_t read_counter = 0;
    int current_frame = 0;
    do {
      aubio_source_do(file, input, &read_counter);

      aubio_onset_do(onset, input, output);

      if (fvec_read_sample(output, 0)) {
        onsets[i].push_back(aubio_onset_get_last_s(onset));
      }

      current_frame++;
    } while (read_counter == overlap_size);

    del_aubio_source(file);

    cout << onsets[i].size() << " onsets found!" << endl;
  }

  del_fvec(input);
  del_fvec(output);

  return onsets;
}

