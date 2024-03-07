package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesLoader {
    public static final Properties properties  = new Properties();
    public static void loaderProperties(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("application.properties"))){
            // get  url username and password
            properties.load(bufferedReader);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
