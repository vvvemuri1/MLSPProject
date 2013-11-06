#ifndef __WRITER__
#define __WRITER__

#include <string>
#include <vector>

class Writer {
  public:
    virtual void Write(std::vector<std::string> files, std::vector<std::vector<int> > onsets) = 0;
};

#endif

