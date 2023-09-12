package finalTask.menuActions;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import finalTask.Storge;
import finalTask.entities.Station;
import finalTask.enums.CalculationType;
import finalTask.interfaces.MenuAction;
import finalTask.threads.FindPathThread;
import finalTask.threads.results.FindPathThreadResults;
import finalTask.utils.PathUtils;
import finalTask.utils.StationUtils;

public class FindPaths implements MenuAction {

	@Override
	public void action(Scanner scanner, Storge storge) throws Exception {
		if (storge.getRoutes().isEmpty())
			throw new Exception("There are no routes in the system!");
		CalculationType calculationType = getCalculationType(scanner);
		LocalTime time = getTime(scanner, calculationType);
		Station startStation = getStation(scanner, "Select starting station", storge.getStations());
		List<Station> accessibleStations = StationUtils.getAccessibleStations(startStation);
		Station endStation = getStation(scanner, "Select end station", accessibleStations);
		FindPathThreadResults results = new FindPathThreadResults();
		Thread thread = new Thread(
				new FindPathThread(storge.getRoutes(), startStation, endStation, time, calculationType, results));
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (results.getPaths().isEmpty())
			throw new Exception("No route matches your search criteria!");
		for (HashMap<Station, LocalTime> p : results.getPaths()) {
			PathUtils.printPath(p, startStation);
		}
	}

	private CalculationType getCalculationType(Scanner scanner) {
		CalculationType calculationType = null;

		while (calculationType == null) {
			System.out.println("Choose Your Search Type:");
			System.out.println("\t1. Based on departure time");
			System.out.println("\t2. Based on arrival time");

			try {
				int typeInt = scanner.nextInt();

				switch (typeInt) {
				case 1:
					calculationType = CalculationType.DEPARTURE_TIME_BASED;
					break;
				case 2:
					calculationType = CalculationType.ARRIVAL_TIME_BASED;
					break;
				default:
					System.out.println("Invalid choice. Please select either 1 or 2.");
					break;
				}
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid choice. Please select either 1 or 2.");
				scanner.nextLine(); // Consume the invalid input so it doesn't cause infinite loop
			}
		}
		return calculationType;
	}

	private LocalTime getTime(Scanner scanner, CalculationType type) {
		LocalTime time = null;
		while (time == null) {
			System.out.println("Enter " + (type == CalculationType.ARRIVAL_TIME_BASED ? "arrival" : "departure")
					+ " time (HH:mm format):");
			try {
				time = LocalTime.parse(scanner.nextLine());
			} catch (DateTimeParseException e) {
				System.out.println("Invalid time format. Try again.");
			}
		}
		return time;
	}

	private Station getStation(Scanner scanner, String message, List<Station> stations) {
		System.out.println(message + " (provide index):");
		for (int i = 0; i < stations.size(); i++) {
			System.out.println((i + 1) + ". " + stations.get(i).getName());
		}
		int stationIndex = scanner.nextInt() - 1;
		scanner.nextLine();
		return stations.get(stationIndex);
	}

}
