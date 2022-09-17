cmake_minimum_required(VERSION 2.10)
project(winx)
include_directories("$ENV{JAVA_HOME}/include")
include_directories("$ENV{JAVA_HOME}/include/linux")
include_directories("$ENV{JAVA_HOME}/include/win32")

add_library("winx.rtl" SHARED "winx.cc")