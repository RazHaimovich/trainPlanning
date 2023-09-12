package finalTask.utils;

import finalTask.Storge;
import finalTask.entities.Driver;
import finalTask.entities.Employee;
import finalTask.entities.StationManager;
import finalTask.entities.TrainConductor;

public class StorgeUtils {

	public static void addEmployee(Storge storge, StationManager entity) {
		storge.getStationManagers().add(entity);
	}

	public static void addEmployee(Storge storge, Driver entity) {
		storge.getDrivers().add(entity);
	}

	public static void addEmployee(Storge storge, TrainConductor entity) {
		storge.getTrainConductor().add(entity);
	}

	public static void addEmployee(Storge storge, Employee entity) {
		if (entity instanceof StationManager) {
			addEmployee(storge, (StationManager) entity);
		} else if (entity instanceof Driver) {
			addEmployee(storge, (Driver) entity);
		} else if (entity instanceof TrainConductor) {
			addEmployee(storge, (TrainConductor) entity);
		}
	}
}
