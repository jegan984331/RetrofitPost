//
// Created by jegan on 29-11-2021.
//

#include <jni.h>
#include <string>


extern "C" JNIEXPORT jstring  JNICALL
Java_com_example_retrofitpost_View_Fragement_CppFragment_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}