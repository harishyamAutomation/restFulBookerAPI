package helper;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BaseTestHelper {
	
	public static void createFolder(String path) {
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdir();
		}		
	}
	
	public static String currentDate() {
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy");
        String formattedDate = currentDate.format(formatter);
        System.out.println("Current Date: " + formattedDate);
        return formattedDate;
	}
	
	public static String Timestamp() {
//		Date now = new Date();
//		String timeStamp = now.toString().replace(":", "-");
//		return timeStamp;
		
		LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy HH:mm:ss");
        String formattedTimeStamp = currentDate.format(formatter).replace(':', '_').replace(' ', '_');
        return formattedTimeStamp;
	}
	
}
