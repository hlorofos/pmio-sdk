package processmaker.pmio.api.initalizer;

import processmaker.pmio.ApiClient;
import processmaker.pmio.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiInitalizer {

    private static final String PROPERTIES_FILE = "src/test/resources/env.properties";

    public static void initApi() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(PROPERTIES_FILE));
            ApiClient apiClient = Configuration.getDefaultApiClient();
            apiClient.setBasePath("https://"+props.getProperty("host")+"/api/v1");
            apiClient.setAccessToken(props.getProperty("accesToken"));
            apiClient.setDebugging(true);
        } catch (IOException e) {
            throw new RuntimeException("Cant initalize api from properties", e);
        }
    }
}
