package finalTask.entities;

public class StationAndTravelTime {

	private Station station;
	private int travelTime;

	public StationAndTravelTime(Station station, int travelTime) {
		this.station = station;
		this.travelTime = travelTime;
	}

	public Station getStation() {
		return this.station;
	}

	public int getTravelTime() {
		return this.travelTime;
	}

	public String toString() {
		return "Station Name: " + this.getStation().getName() + ", travelTime: " + this.getTravelTime();
	}

}
