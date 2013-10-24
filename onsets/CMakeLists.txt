cmake_minimum_required(VERSION 2.6)
project(onsets)

set(onsets_VERSION_MAJOR 0)
set(onsets_VERSION_MINOR 1)

configure_file(
  "${PROJECT_SOURCE_DIR}/config.h.in"
  "${PROJECT_BINARY_DIR}/config.h"
)

include_directories("${PROJECT_BINARY_DIR}")

add_executable(onsets main.cpp aubio_detector.cpp)

target_link_libraries(onsets aubio)

