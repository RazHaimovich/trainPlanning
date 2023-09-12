package finalTask.threads;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Vector;

import finalTask.entities.Route;
import finalTask.entities.Station;
import finalTask.enums.CalculationType;
import finalTask.enums.Direction;
import finalTask.threads.results.FindPathThreadResults;
import finalTask.utils.RouteUtils;
import finalTask.utils.StationUtils;

public class FindPathThread implements Runnable {
	Vector<Route> allRoutes;
	private final Station startStation;
	private final Station endStation;
	private final LocalTime time;
	private final CalculationType type;
	private final FindPathThreadResults results;

	public FindPathThread(Vector<Route> allRoutes, Station startStation, Station endStation, LocalTime time,
			CalculationType type, FindPathThreadResults results) {
		this.allRoutes = allRoutes;
		this.startStation = startStation;
		this.endStation = endStation;
		this.time = time;
		this.type = type;
		this.results = results;
	}

	public void run() {
		Direction direction = StationUtils.getDirection(startStation, endStation);
		Vector<HashMap<Station, LocalTime>> paths = new Vector<HashMap<Station, LocalTime>>();
		Vector<Route> filtederRoutes = RouteUtils.findRoutesStoppingAtStationWithinInterval(this.allRoutes,
				getSearchStation(), getTimes()[0], getTimes()[1]);
		Station OppositeStation = getOppsiteStation();
		for(Route r: filtederRoutes) {
			if(r.getDirection() != direction) continue;
			HashMap<Station, LocalTime> path = RouteUtils.getPath(r, endStation);
			if(path == null || path.get(OppositeStation) == null) continue;
			paths.add(path);
		}
		results.setPaths(paths);
	}

	private LocalTime[] getTimes() {
		return new LocalTime[] { time.minusMinutes(15), time.plusMinutes(15) };
	}

	private Station getSearchStation() {
		if (this.type == CalculationType.ARRIVAL_TIME_BASED)
			return this.endStation;
		return this.startStation;
	}
	
	private Station getOppsiteStation() {
		if(getSearchStation().getId() == startStation.getId()) return endStation;
		return startStation;
	}
}
