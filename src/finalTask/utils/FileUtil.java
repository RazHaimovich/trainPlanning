package finalTask.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	public static void writeToFile(String fileName, String content) {
		File file = new File(fileName);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFromFile(String fileName) {
		File file = new File(fileName);

		if (!file.exists()) {
			return null;
		}

		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			boolean firstLine = true;
			while ((line = reader.readLine()) != null) {
				if (firstLine) {
					content.append(line);
					firstLine = false;
				} else {
					content.append('\n').append(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}
