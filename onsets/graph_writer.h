#ifndef __GRAPH_WRITER__
#define __GRAPH_WRITER__

#include <string>
#include <vector>

#include "writer.h"

class GraphWriter : public Writer {
  public:
    GraphWriter(bool output);
    ~GraphWriter();

    virtual void Write(std::vector<std::string> files, std::vector<std::vector<int> > onsets);

  private:
    bool show_output;
};

#endif

