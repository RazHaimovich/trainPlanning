package finalTask.menuActions;

import java.util.Scanner;
import java.util.Vector;

import finalTask.Storge;
import finalTask.entities.Station;
import finalTask.entities.StationManager;
import finalTask.interfaces.MenuAction;
import finalTask.utils.TerminalColors;

public class AddStation implements MenuAction {

	@Override
	public void action(Scanner scanner, Storge storge) throws Exception {
		Vector<Station> stations = storge.getStations();
		Vector<StationManager> stationManagers = storge.getStationManagers();

		Vector<StationManager> freeStationManagers = filterFreeStationManagers(stationManagers);

		if (freeStationManagers.isEmpty())
			throw new Exception("You must add a station manager before!");

		System.out.println(
				TerminalColors.PURPLE + "You need to add the station starting from the north and extending to the south"
						+ TerminalColors.RESET);
		System.out.println("Enter the name of the new station:");
		String stationName = scanner.nextLine();

		displayFreeStationManagers(freeStationManagers);

		int managerIndex = scanner.nextInt() - 1;
		scanner.nextLine();
		StationManager selectedManager = freeStationManagers.get(managerIndex);
		Station newStation = new Station(stations.size(), stationName, selectedManager);

		if (!stations.isEmpty()) {
			Station northernStation = chooseNorthernStation(scanner, stations);
			if (northernStation != null) {
				System.out.println("What the travel time between " + stationName + " and " + northernStation.getName()
						+ ": (in minutes)");
				int travelTime = scanner.nextInt();
				newStation.setNorthernStation(northernStation, travelTime);
			}
		}

		stations.add(newStation);
	}

	private Vector<StationManager> filterFreeStationManagers(Vector<StationManager> stationManagers) {
		Vector<StationManager> freeStationManagers = new Vector<>();
		for (StationManager manager : stationManagers) {
			if (manager.getStation() == null) {
				freeStationManagers.add(manager);
			}
		}
		return freeStationManagers;
	}

	private void displayFreeStationManagers(Vector<StationManager> freeStationManagers) {
		System.out.println("Choose a Station Manager:");
		for (int i = 0; i < freeStationManagers.size(); i++) {
			StationManager manager = freeStationManagers.get(i);
			System.out.println("\t" + (i + 1) + ") " + manager.getName());
		}
	}

	private Station chooseNorthernStation(Scanner scanner, Vector<Station> stations) {
		Station choosenStation = null;
		while (choosenStation == null) {
			System.out.println("Choose a northern station:");
			System.out.println("\t0) None");
			for (int i = 0; i < stations.size(); i++) {
				Station station = stations.get(i);
				if (station.getSouthernStation() == null) {
					System.out.println("\t" + (i + 1) + ") " + station.getName());
				}
			}
			try {
			int northernStationIndex = scanner.nextInt() - 1;
			scanner.nextLine();
			if (northernStationIndex == -1)
				return null;
			choosenStation = stations.get(northernStationIndex);
			}catch(Exception e) {}
		}
		return choosenStation;
	}

}
