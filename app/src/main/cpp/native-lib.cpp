#include <jni.h>
#include <string>
#include "base/art_method.h"
#include <base.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_sjq_fix3_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_sjq_fix3_DexManager_replace(JNIEnv *env, jclass clazz, jobject wrongMehtod,
                                     jobject rightMethod) {

    //ArtMethod Android 系统源码中
    LOGE("执行开始");
    art::mirror::ArtMethod *wrong = (art::mirror::ArtMethod *) env->FromReflectedMethod(
            wrongMehtod);
    art::mirror::ArtMethod *right = (art::mirror::ArtMethod *) env->FromReflectedMethod(
            rightMethod);

    LOGE("执行ArtMethod结束");
    //method --->class    --->classLoader
    wrong->declaring_class_ = right->declaring_class_;
    wrong->dex_cache_resolved_methods_ = right->dex_cache_resolved_methods_;
    wrong->access_flags_ = right->access_flags_;
    wrong->dex_cache_resolved_types_ = right->dex_cache_resolved_types_;
    wrong->dex_code_item_offset_ = right->dex_code_item_offset_;
    //方法索引的替换
    wrong->method_index_ = right->method_index_;
    wrong->dex_method_index_ = right->dex_method_index_;
    LOGE("执行结束");



}extern "C"
JNIEXPORT void JNICALL
Java_com_sjq_fix3_DexManager_replaceTwo(JNIEnv *env, jclass clazz, jobject wrongMehtod,
                                        jobject rightMethod) {

    //ArtMethod Android 系统源码中
    LOGE("执行开始");
    art::mirror::ArtMethod *wrong = (art::mirror::ArtMethod *) env->FromReflectedMethod(
            wrongMehtod);
    art::mirror::ArtMethod *right = (art::mirror::ArtMethod *) env->FromReflectedMethod(
            rightMethod);

    LOGE("执行ArtMethod结束");
    //method --->class    --->classLoader
    wrong->declaring_class_ = right->declaring_class_;
    wrong->dex_cache_resolved_methods_ = right->dex_cache_resolved_methods_;
    wrong->access_flags_ = right->access_flags_;
    wrong->dex_cache_resolved_types_ = right->dex_cache_resolved_types_;
    wrong->dex_code_item_offset_ = right->dex_code_item_offset_;
    //方法索引的替换
    wrong->method_index_ = right->method_index_;
    wrong->dex_method_index_ = right->dex_method_index_;
    LOGE("执行结束");


}