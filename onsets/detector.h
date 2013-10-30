#ifndef __DETECTOR__
#define __DETECTOR__

#include <string>
#include <vector>

class Detector {
  public:
    virtual std::vector<std::vector<int> > Process(std::vector<std::string> files) = 0;
};

#endif

