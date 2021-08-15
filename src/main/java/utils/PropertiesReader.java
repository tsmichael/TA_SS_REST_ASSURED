package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private Properties props;

    public PropertiesReader(String fileName) {
        String filePath = getClass().getClassLoader().getResource(fileName).getPath();
        try (InputStream input = new FileInputStream(filePath)) {
            this.props = new Properties();
            this.props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String property) {
        return this.props.getProperty(property);
    }
}
