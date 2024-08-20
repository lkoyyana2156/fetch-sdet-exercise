package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestSettings {
    public String Browser;
    public String Url;
    private static TestSettings instance;

    private TestSettings(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/java/appsettings.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Browser = properties.getProperty("browser");
        Url = properties.getProperty("url");
    }

    public static TestSettings getInstance() {
        if(instance==null){
            instance = new TestSettings();
        }
        return instance;
    }
}
