package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class ExternalJSONRequestUtil {
	public static String getRequestBody(File file) {
		String requestBody = null;
		try {
			requestBody = IOUtils.toString(new FileInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requestBody;
	}
}
