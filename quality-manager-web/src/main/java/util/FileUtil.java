package util;

import javax.servlet.http.Part;

public class FileUtil {

	public static String getFileName(Part filePart) {
		
		String fileName = null;
		
		String header = filePart.getHeader("content-disposition");
		
		for (String headerPart : header.split(";")) {
			if (headerPart.trim().startsWith("filename")) {
				fileName = headerPart.substring(headerPart.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		
		return fileName;
	}
}
