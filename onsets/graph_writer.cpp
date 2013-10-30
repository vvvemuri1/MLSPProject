#include "graph_writer.h"

#include <fstream>
#include <iostream>

using std::cout;
using std::endl;
using std::ofstream;
using std::string;
using std::vector;

GraphWriter::GraphWriter(bool output) {
  show_output = output;
}

GraphWriter::~GraphWriter() {
}

void GraphWriter::Write(vector<string> files, vector<vector<int> > onsets) {
  if (files.size() != onsets.size()) {
    throw "The number of files used to calculate onsets is different than the number of files passed in.";
  }

  if (show_output) {
    cout << "Starting the output process." << endl;
  }

  for (int i = 0; i < files.size(); i++) {
    string filename = files[i] + ".out";
    ofstream file(filename.c_str());

    if (show_output) {
      cout << "Writing out " << filename << endl;
    }

    vector<int> onset = onsets[i];

    int sample = 0;
    for (int j = 0; j < onset.size(); j++) {
      while (sample < onset[j]) {
        file << 0 << "\n";
        sample++;
      }
      file << 1 << "\n";
      sample++;
    }
  }
}

