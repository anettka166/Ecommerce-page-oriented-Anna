package com.academy.techcenture.ecommerce.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    //constant - final and static
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    //hiding the constructor so this class cannot be instantiated
    private ConfigReader(){}

    static {
        System.out.println("static block says hi!!!!!!!!!");
        try {
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e){

        }
    }

    public static String getProperty(String keyName){
        return properties.getProperty(keyName);
    }

    public static void setProperty(String key, String value) throws IOException {
        properties.store(new FileOutputStream(CONFIG_FILE_PATH), null);
        properties.setProperty(key, value);
    }

    public static void main(String[] args) {
        System.out.println(properties.getProperty("browser"));
        System.out.println(properties.getProperty("URL"));
        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));
        properties.setProperty("orderNumber","FKFL4543");
        System.out.println(properties.getProperty("orderNumber"));
    }



}
