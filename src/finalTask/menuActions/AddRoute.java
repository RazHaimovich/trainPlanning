package finalTask.menuActions;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import finalTask.Storge;
import finalTask.entities.Driver;
import finalTask.entities.Route;
import finalTask.entities.Station;
import finalTask.entities.Train;
import finalTask.entities.TrainConductor;
import finalTask.interfaces.MenuAction;
import finalTask.utils.RouteUtils;
import finalTask.utils.StationUtils;

public class AddRoute implements MenuAction {

	@Override
	public void action(Scanner scanner, Storge storge) throws Exception {
		Vector<Station> stations = storge.getStations();
		Vector<Driver> drivers = storge.getDrivers();
		Vector<TrainConductor> trainConductors = storge.getTrainConductor();
		Vector<Train> trains = storge.getTrains();
		Vector<Route> routes = storge.getRoutes();

		checkInitialConditions(stations, drivers, trainConductors, trains);

		int routeId = routes.size() + 1;
		LocalTime departure = getDepartureTime(scanner);

		Train train = getEntity(scanner, "Select train", trains, routes, departure);
		Driver driver = getEntity(scanner, "Select driver", drivers, routes, departure);
		TrainConductor trainConductor = getEntity(scanner, "Select train conductor", trainConductors, routes,
				departure);

		Station startStation = getStation(scanner, "Select starting station", stations);
		List<Station> accessibleStations = StationUtils.getAccessibleStations(startStation);
		Station endStation = getStation(scanner, "Select end station", accessibleStations);

		routes.add(new Route(routeId, departure, train, driver, trainConductor, startStation, endStation));
		System.out.println("Route added successfully!");
	}

	private void checkInitialConditions(Vector<Station> stations, Vector<Driver> drivers,
			Vector<TrainConductor> trainConductors, Vector<Train> trains) throws Exception {
		if (trains.isEmpty())
			exitWithMessage("There are no trains in the system!");
		if (drivers.isEmpty())
			exitWithMessage("There are no drivers in the system!");
		if (stations.size() < 2)
			exitWithMessage("You have fewer than 2 stations in the system!");
		if (trainConductors.isEmpty())
			exitWithMessage("There are no train conductors in the system!");
		for (Station s : stations)
			if (s.getNorthernStation() != null || s.getSouthernStation() != null)
				return;
		exitWithMessage("There are no stations with a connection between them");
	}

	private LocalTime getDepartureTime(Scanner scanner) {
		LocalTime departure = null;
		while (departure == null) {
			System.out.println("Enter departure time (HH:mm format):");
			try {
				departure = LocalTime.parse(scanner.nextLine());
			} catch (DateTimeParseException e) {
				System.out.println("Invalid time format. Try again.");
			}
		}
		return departure;
	}

	private <T> T getEntity(Scanner scanner, String message, Vector<T> entities, Vector<Route> routes,
			LocalTime departure) throws Exception {
		T entity = null;
		while (entity == null) {
			System.out.println(message + " (provide index):");
			Vector<Integer> allowedIndexes = new Vector<>();
			for (int i = 0; i < entities.size(); i++) {
				T currentItem = entities.get(i);
				if (!RouteUtils.hasCollision(routes, currentItem, departure)) {
					allowedIndexes.add(i);
					System.out.println((i + 1) + ". " + currentItem.toString());
				}
			}
			if (allowedIndexes.isEmpty())
				exitWithMessage("There are no available entities for: " + message);

			int entityIndex = scanner.nextInt() - 1;
			if (allowedIndexes.contains(entityIndex))
				entity = entities.get(entityIndex);
			scanner.nextLine(); // Clear newline
		}
		return entity;
	}

	private Station getStation(Scanner scanner, String message, List<Station> stations) {
		System.out.println(message + " (provide index):");
		for (int i = 0; i < stations.size(); i++) {
			if (stations.get(i).getNorthernStation() == null && stations.get(i).getSouthernStation() == null)
				continue;
			System.out.println((i + 1) + ". " + stations.get(i).getName());
		}
		int stationIndex = scanner.nextInt() - 1;
		scanner.nextLine(); // Clear newline
		return stations.get(stationIndex);
	}

	private void exitWithMessage(String message) throws Exception {
		throw new Exception(message);
	}
}
