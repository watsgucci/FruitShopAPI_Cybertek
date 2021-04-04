package com.fruit_shop.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {


    private ConfigurationReader(){}


    private static Properties p1 = new Properties();


    static{

        try {
            FileInputStream f1 = new FileInputStream("configuration.properties");
            p1.load(f1);
            f1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static String getProperty(String keyName){
        return p1.getProperty(keyName);
    }





}
