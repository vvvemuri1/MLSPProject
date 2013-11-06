#ifndef __AUBIO_DETECTOR__
#define __AUBIO_DETECTOR__

#include <aubio/aubio.h>

#include "detector.h"

class AubioDetector : public Detector {
  public:
    AubioDetector(bool output);
    ~AubioDetector();
    virtual std::vector<std::vector<int> > Process(std::vector<std::string> files);

  private:
    aubio_onset_t* onset;
    bool show_output;
};

#endif

