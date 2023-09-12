package finalTask.entities;

public class Station {
	private int id;
	private String name;
	private StationManager stationManager;
	private StationAndTravelTime northernStation;
	private StationAndTravelTime southernStation;

	public Station(int id, String name, StationManager stationManager) {
		this.id = id;
		this.name = name;
		this.stationManager = stationManager;
		this.stationManager.setStation(this);
	}

	public void setNorthernStation(Station station, int travelTime) {
		this.northernStation = new StationAndTravelTime(station, travelTime);
		station.setSouthernStation(this, travelTime);
	}

	public void setSouthernStation(Station station, int travelTime) {
		this.southernStation = new StationAndTravelTime(station, travelTime);
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public StationAndTravelTime getNorthernStation() {
		return this.northernStation;
	}

	public StationAndTravelTime getSouthernStation() {
		return this.southernStation;
	}

	public String toString() {
		return "Id: " + this.getId() + " Name: " + this.getName() + ", Northern Station: | " + this.getNorthernStation()
				+ " |, Southern Station: | " + this.getSouthernStation() + " |";
	}

}
