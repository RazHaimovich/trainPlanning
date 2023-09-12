package finalTask.entities;

import java.time.LocalTime;

import finalTask.utils.RouteUtils;
import finalTask.utils.StationUtils;

import finalTask.enums.Direction;

public class Route {
	private int id;
	private LocalTime departure;
	private Train train;
	private Driver driver;
	private TrainConductor trainConductor;
	private Station startStation;
	private Station endStation;
	private Direction direction;

	public Route(int id, LocalTime departure, Train train, Driver driver, TrainConductor trainConductor,
			Station startStation, Station endStation) {
		this.id = id;
		this.departure = departure;
		this.train = train;
		this.driver = driver;
		this.trainConductor = trainConductor;
		this.startStation = startStation;
		this.endStation = endStation;
		this.direction = StationUtils.getDirection(this.startStation, this.endStation);
		System.out.println(direction);
	}

	public int getId() {
		return this.id;
	}

	public LocalTime getDeparture() {
		return this.departure;
	}

	public Train getTrain() {
		return this.train;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public TrainConductor getTrainConductor() {
		return this.trainConductor;
	}

	public Station getStartStation() {
		return this.startStation;
	}

	public Station getEndStation() {
		return this.endStation;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public String toString() {
		return "Id: " + this.getId() + ", Departure Time: " + this.departure +", Finish Time: "+RouteUtils.getFinishTime(this)+ ", Driver:" + driver.getName() + ", Conductor:"
				+ this.trainConductor.getName() + ", Direction: " + this.direction + ", Start Station: "
				+ this.startStation.getName() + ", End Station: " + this.endStation.getName();
	}
}
