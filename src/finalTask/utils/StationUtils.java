package finalTask.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;

import finalTask.entities.Station;
import finalTask.entities.StationAndTravelTime;
import finalTask.enums.Direction;

public class StationUtils {
	
	public static Vector<Station> getAccessibleStations(Station station) {
		HashSet<Station> visited = new HashSet<>();
		Vector<Station> stations = traverseStations(station, visited);
		stations.remove(station);
		
	    Collections.sort(stations, (a, b) -> a.getId() - b.getId());
		
		return stations;
	}

	private static Vector<Station> traverseStations(Station current, HashSet<Station> visited) {
		Vector<Station> stations = new Vector<>();

		if (current == null || visited.contains(current)) {
			return stations;
		}

		visited.add(current);

		StationAndTravelTime[] neighbors = { current.getNorthernStation(), current.getSouthernStation() };

		for (StationAndTravelTime neighbor : neighbors) {
			if (neighbor != null && !visited.contains(neighbor.getStation())) {
				stations.add(neighbor.getStation());
				stations.addAll(traverseStations(neighbor.getStation(), visited));
			}
		}

		return stations;
	}
	
	private static boolean isOnDirection(Station startStation, Station endStation, Direction direction) {
		StationAndTravelTime nextStationAndTime = getNextStation(startStation, direction);

		while (nextStationAndTime != null) {
			if (nextStationAndTime.getStation().equals(endStation)) {
				return true;
			}
			nextStationAndTime = getNextStation(nextStationAndTime.getStation(), direction);
		}

		return false;
	}
	
	public static Direction getDirection(Station startStation, Station endStation) {
		if (isOnDirection(startStation, endStation, Direction.North)) {
			return Direction.North;
		}
		if (isOnDirection(startStation, endStation, Direction.South)) {
			return Direction.South;
		}
		return null;
	}
	
	public static StationAndTravelTime getNextStation(Station currentStation, Direction direction) {
		return direction == Direction.North ? currentStation.getNorthernStation() : currentStation.getSouthernStation();
	}
}
