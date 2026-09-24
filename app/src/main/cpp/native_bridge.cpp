#include <jni.h>
#include <string>
#include <sstream>
#include <iostream>
#include "engine/CuffEngine.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_cuffscript_runner_MainActivity_runCuff(JNIEnv *env, jobject, jstring jsource)
{
    const char *raw = env->GetStringUTFChars(jsource, nullptr);
    std::string source(raw);
    env->ReleaseStringUTFChars(jsource, raw);

    std::ostringstream captured;
    std::streambuf *old = std::cout.rdbuf(captured.rdbuf());
    cuff::CuffEngine::Result result = cuff::CuffEngine::execute(source, ".");
    std::cout.rdbuf(old);

    std::string text = captured.str();
    if (!result.success)
        text += "ERROR: " + result.error + "\n";

    return env->NewStringUTF(text.c_str());
}
