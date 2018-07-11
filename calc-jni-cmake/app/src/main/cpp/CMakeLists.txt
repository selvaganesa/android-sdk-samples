cmake_minimum_required(VERSION 3.4.1)

add_library(calc-jni SHARED
            calc-jni.c )

# Include libraries needed for hello-jni lib
target_link_libraries(calc-jni
                      android
                      log)