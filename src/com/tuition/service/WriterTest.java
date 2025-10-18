package com.tuition.service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class WriterTest {
    public static void main(String[] args) throws Exception {    // 异常抛出，不处理
        File file= new File("D://test.txt") ;    // 声明File对象

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        OutputStreamWriter osr = new OutputStreamWriter(fileOutputStream);// 输出 往out里面准备写内容，内容在下面
        String str = "Hello World1!";
        osr.write(str);// 写
        // osr.flush();//如果用于网络传输，记得强制刷新缓冲区，否则输出的数据只停留在缓冲区中，而无法进行网络传输
        osr.close();// 关闭资源

        List<String> strList= Arrays.asList("hi", "you");
        System.out.print(strList);
    }
}
