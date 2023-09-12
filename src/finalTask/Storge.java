package finalTask;

import java.util.Vector;

import finalTask.entities.Driver;
import finalTask.entities.Route;
import finalTask.entities.Station;
import finalTask.entities.StationManager;
import finalTask.entities.Train;
import finalTask.entities.TrainConductor;

public class Storge {
	private Vector<Station> stations;
	private Vector<StationManager> stationManagers;
	private Vector<Driver> drivers;
	private Vector<TrainConductor> trainConductors;
	private Vector<Route> routes;
	private Vector<Train> trains;

	public Storge() {
		this.stations = new Vector<Station>();
		this.stationManagers = new Vector<StationManager>();
		this.drivers = new Vector<Driver>();
		this.trainConductors = new Vector<TrainConductor>();
		this.routes = new Vector<Route>();
		this.trains = new Vector<Train>();
	}

	public Vector<Station> getStations() {
		return this.stations;
	}

	public Vector<StationManager> getStationManagers() {
		return this.stationManagers;
	}

	public Vector<Driver> getDrivers() {
		return this.drivers;
	}

	public Vector<TrainConductor> getTrainConductor() {
		return this.trainConductors;
	}

	public Vector<Route> getRoutes() {
		return this.routes;
	}

	public Vector<Train> getTrains() {
		return this.trains;
	}
}
