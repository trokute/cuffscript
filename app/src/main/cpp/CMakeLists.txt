cmake_minimum_required(VERSION 3.22.1)
project(cuffbridge)

add_library(cuffbridge SHARED native_bridge.cpp)
target_include_directories(cuffbridge PRIVATE ${CMAKE_CURRENT_SOURCE_DIR})
target_compile_features(cuffbridge PRIVATE cxx_std_17)

find_library(log-lib log)
target_link_libraries(cuffbridge ${log-lib})
