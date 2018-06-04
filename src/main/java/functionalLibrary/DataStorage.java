package functionalLibrary;

import java.util.HashMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Map;

public class DataStorage {
	private final static Map<String, String> map = new HashMap<String, String>();

	public static String get(String key) {
		return map.get(key);
	}

	public static void put(String key, String value) {
		map.put(key, value);
	}

	public static Properties loadProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/main/resources/selenium-framework.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;

	}
	
	public static String getReportConfigPath(){
		Properties prop = loadProperties();
		String reportConfigPath = prop.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}

}
