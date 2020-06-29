
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    static Properties properties = new Properties();

    static{
        try {
            properties.load(new FileInputStream("authservice.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties(){
        return properties;
    }

}
