package com.sjq.fix3;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

public class DexManager {
    private Context context;

    public DexManager(Context context) {
        this.context = context;
    }

    public void loadFile(File file) {
        //dex文件
        try {
            DexFile dexFile = DexFile.loadDex(file.getAbsolutePath(), new File(context.getCacheDir(), "opt").getAbsolutePath(), Context.MODE_PRIVATE);

            //当前dex下的class的类名结合
            Enumeration<String> entry = dexFile.entries();
            while (entry.hasMoreElements()) {
                String className = entry.nextElement();
                Class realClass = dexFile.loadClass(className, context.getClassLoader());
                if (null != realClass) {
                    fixClazz(realClass);
                }
            }
        } catch (IOException e) {

        }
    }

    private void fixClazz(Class realClass) {

        //andfix  class  -->bug class
        //method         -->bug method
        Method[] methods = realClass.getMethods();
        for (Method rightMethod : methods) {

            Replace replace = rightMethod.getAnnotation(Replace.class);
            if (null == replace) {
                continue;
            }
            //拿到了从网络上下载的rightMethod
            //本地的bug class 的method
            String clzzName = replace.clazz();
            String methodName = replace.method();
            try {
                Class wrongClazz = Class.forName(clzzName);
                Method wrongMehtod = wrongClazz.getDeclaredMethod(methodName, rightMethod.getParameterTypes());
                replaceTwo(wrongMehtod,rightMethod);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public native static void replace(Method wrongMehtod, Method rightMethod);
    public native static void replaceTwo(Method wrongMehtod, Method rightMethod);
}
