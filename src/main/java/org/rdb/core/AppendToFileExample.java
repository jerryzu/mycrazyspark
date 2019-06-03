package org.rdb.core;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class AppendToFileExample {
	public static void main(String[] args) {
		try {
			String data = " This content will append to the end of the file";

			File file = new File("javaio-appendfile.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			FileWriter fileWritter = new FileWriter(file.getAbsoluteFile(), true);
			fileWritter.write(data);
			fileWritter.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}