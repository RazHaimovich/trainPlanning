package finalTask.menuActions;

import java.util.Scanner;
import java.util.Vector;
import finalTask.interfaces.MenuAction;

import finalTask.Storge;

public class PrintAllData implements MenuAction {

	@Override
	public void action(Scanner scanner, Storge storge) {
		printData("Stations", storge.getStations());
		printData("Station Managers", storge.getStationManagers());
		printData("Drivers", storge.getDrivers());
		printData("Train Conductors", storge.getTrainConductor());
		printData("Trains", storge.getTrains());
		printData("Routes", storge.getRoutes());
	}

	private <T> void printData(String title, Vector<T> data) {
		System.out.println("\n----------" + title + "----------");
		if (data.isEmpty()) {
			System.out.println("\tThere are no " + title.toLowerCase());
			return;
		}
		for (T item : data) {
			System.out.println("\t" + item);
		}
	}

}
