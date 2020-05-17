package com.sjq.fix3.web;

import com.sjq.fix3.Replace;

public class Caculator {
    //修复的类
    @Replace(clazz = "com.sjq.fix3.Caculator",method = "caculator")
   public int caculator(){
        return 10;
    }
}
