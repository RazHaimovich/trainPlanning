package finalTask.utils;

import java.util.Vector;

public class MenuUtils {

	public static <T> void printData(String title, Vector<T> data) {
		System.out.println("\n----------" + title + "----------");
		if (data.size() > 0) {
			for (T item : data) {
				System.out.println("\t" + item);
			}
		} else {
			System.out.println("\tThere are no " + title.toLowerCase());
		}
	}

}
