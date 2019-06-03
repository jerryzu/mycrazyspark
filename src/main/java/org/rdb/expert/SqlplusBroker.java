package org.rdb.expert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SqlplusBroker {
	private static String script_location = "";
	private static String file_extension = ".sql";
	private static ProcessBuilder processBuilder = null;

	public static void main(String[] args) {
		try {
			File Dir = new File("D:\\OM\\设计");
			File[] Files = Dir.listFiles(new FileFilter() {

				public boolean accept(File f) {
					if (f.getName().toLowerCase().endsWith(file_extension))
						return true;
					return false;
				}
			});
			for (int i = 0; i < Files.length; i++) {
				script_location = "@" + Files[i].getAbsolutePath(); 
				
				processBuilder = new ProcessBuilder("sqlplus",
						"UserName/Password@database_name", script_location);  
				 
				processBuilder.redirectErrorStream(true);
				Process process = processBuilder.start();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				String currentLine = null;
				while ((currentLine = in.readLine()) != null) {
					;
					System.out.println(" " + currentLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
