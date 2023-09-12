package finalTask.utils;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import finalTask.entities.Station;

public class PathUtils {

	public static void printPath(HashMap<Station, LocalTime> path, Station startStation) {
		Vector<Station> orderedStations = path.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey).collect(Collectors.toCollection(Vector::new));

		boolean visitStartStation = false;

		System.out
				.println(TerminalColors.CYAN + "--------------------Path----------------------" + TerminalColors.RESET);

		// Print table header with updated width
		System.out.printf(TerminalColors.YELLOW + "| %-30s | %-8s |\n", "Station Name", "Time");
		System.out.println("|--------------------------------|----------|" + TerminalColors.RESET);

		// Identify the last station that should be printed
		Station lastStation = orderedStations.get(orderedStations.size() - 1);

		for (Station s : orderedStations) {
			if (s.getId() == startStation.getId()) {
				visitStartStation = true;
			}

			if (visitStartStation) {
				// Check if it's the first or the last station to be printed
				if (s.getId() == startStation.getId() || s.getId() == lastStation.getId())
					System.out.print(TerminalColors.GREEN);
				System.out.printf("| %-30s | %-8s |\n", s.getName(), path.get(s));
				System.out.print(TerminalColors.RESET);
			}
		}
		System.out.println("--------------------------------------------");
	}

}
