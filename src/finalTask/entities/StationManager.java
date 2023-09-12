package finalTask.entities;

public class StationManager extends Employee {

	private Station station;

	public StationManager(String id, String name) {
		super(id, name);
	}

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String toString() {
		return "Station Mananger: " + super.toString() + ", Station: " + (station != null ? station.getName() : " - ");
	}

}
