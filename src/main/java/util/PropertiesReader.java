package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties;

    public PropertiesReader() {
        properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Creds.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getUsername() {
        return properties.getProperty("username");

    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
