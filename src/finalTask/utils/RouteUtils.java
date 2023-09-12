package finalTask.utils;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Vector;

import finalTask.entities.Employee;
import finalTask.entities.Route;
import finalTask.entities.Station;
import finalTask.entities.StationAndTravelTime;
import finalTask.entities.Train;

public class RouteUtils {

	public static LocalTime getFinishTime(Route route) {
		return getFinishTime(route, route.getEndStation());
	}

	public static LocalTime getFinishTime(Route route, Station endStation) {
		int totalMinutes = 0;
		StationAndTravelTime nextStation = StationUtils.getNextStation(route.getStartStation(), route.getDirection());
		while (nextStation != null) {
			totalMinutes += nextStation.getTravelTime();
			if (nextStation.getStation().getId() == endStation.getId()) {
				return route.getDeparture().plusMinutes(totalMinutes);
			}
			nextStation = StationUtils.getNextStation(nextStation.getStation(), route.getDirection());
		}
		return null;
	}

	public static <T> boolean hasCollision(Vector<Route> routes, T entity, LocalTime time) {
		if (entity instanceof Train)
			return hasCollision(routes, (Train) entity, time);
		if (entity instanceof Employee)
			return hasCollision(routes, (Employee) entity, time);
		return false;
	}

	public static boolean hasCollision(Vector<Route> routes, Train train, LocalTime time) {
		for (Route r : routes) {
			if (r.getTrain().getId() == train.getId()) {
				LocalTime departureTime = r.getDeparture();
				LocalTime finishTime = getFinishTime(r);

				if (departureTime.isBefore(finishTime)) {
					if (time.isAfter(departureTime) && time.isBefore(finishTime)) {
						return true;
					}
				} else { // Handle cases where finish time is on the next day (spanning midnight)
					if (time.isAfter(departureTime) || time.isBefore(finishTime)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean hasCollision(Vector<Route> routes, Employee employee, LocalTime time) {
		for (Route r : routes) {
			if (r.getDriver().getId() == employee.getId() || r.getTrainConductor().getId() == employee.getId()) {
				LocalTime departureTime = r.getDeparture();
				LocalTime finishTime = getFinishTime(r);

				if (departureTime.isBefore(finishTime)) {
					if (time.isAfter(departureTime) && time.isBefore(finishTime)) {
						return true;
					}
				} else { // Handle cases where finish time is on the next day (spanning midnight)
					if (time.isAfter(departureTime) || time.isBefore(finishTime)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static Vector<Route> findRoutesStoppingAtStationWithinInterval(Vector<Route> routes, Station station,
			LocalTime startTime, LocalTime endTime) {
		Vector<Route> filteredRoutes = new Vector<Route>();

		for (Route r : routes) {
			if (r.getStartStation().getId() == station.getId()) {
				filteredRoutes.add(r);
				continue;
			}
			LocalTime finishTime = getFinishTime(r, station);
			if (finishTime == null)
				continue;

			if (endTime.isBefore(startTime)) { // Time interval spans midnight
				if (finishTime.isAfter(startTime) || finishTime.isBefore(endTime)) {
					filteredRoutes.add(r);
				}
			} else if (finishTime.isAfter(startTime) && finishTime.isBefore(endTime)) {
				filteredRoutes.add(r);
			}
		}

		return filteredRoutes;
	}

	public static HashMap<Station, LocalTime> getPath(Route route) {
		return getPath(route, route.getEndStation());
	}
	
	public static HashMap<Station, LocalTime> getPath(Route route, Station endStation) {
		int totalTime = 0;
		HashMap<Station, LocalTime> path = new HashMap<Station, LocalTime>();
		path.put(route.getStartStation(), route.getDeparture());
		StationAndTravelTime nextStation = StationUtils.getNextStation(route.getStartStation(), route.getDirection());
		while (nextStation != null) {
			totalTime += nextStation.getTravelTime();
			LocalTime newTime = route.getDeparture().plusMinutes(totalTime);
			path.put(nextStation.getStation(), newTime);
			if (nextStation.getStation().getId() == endStation.getId()) {
				return path;
			}
			nextStation = StationUtils.getNextStation(nextStation.getStation(), route.getDirection());
		}
		return null;
	}

}
